package br.com.appfastfood.pedido.infraestrutura;

import br.com.appfastfood.configuracoes.client.pagamento.PagamentoClient;
import br.com.appfastfood.configuracoes.client.pagamento.PagamentoRequisicao;
import br.com.appfastfood.configuracoes.client.pagamento.Pagamentos;
import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.vo.ProdutoVO;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
import br.com.appfastfood.pedido.exceptions.ExceptionsMessages;
import br.com.appfastfood.pedido.infraestrutura.entidades.PedidoEntidade;
import br.com.appfastfood.pedido.infraestrutura.entidades.ProdEnt;
import br.com.appfastfood.pedido.usecase.adaptadores.producers.PedidoQueueAdapterOUT;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PedidoRepositorioImpl implements PedidoRepositorio {

    private final MongoTemplate mongoTemplate;
    private final PagamentoClient pagamentoClient;
    private final SpringDataPedidoRepository springDataPedidoRepository;
    private final PedidoQueueAdapterOUT pedidoQueueAdapterOUT;   

    public PedidoRepositorioImpl(SpringDataPedidoRepository springDataPedidoRepository, PagamentoClient pagamentoClient, MongoTemplate mongoTemplate ,PedidoQueueAdapterOUT pedidoQueueAdapterOUT ) {
        this.springDataPedidoRepository = springDataPedidoRepository;
        this.pagamentoClient = pagamentoClient;
        this.mongoTemplate = mongoTemplate;
        this.pedidoQueueAdapterOUT = pedidoQueueAdapterOUT;
    }

    @Override
    public String criar(Pedido pedido) {
        List<ProdEnt> produtosEntidade = new ArrayList<>();
        pedido.getProdutos().forEach(produto -> {
            produtosEntidade.add(new ProdEnt(produto.getIdProduto(), produto.getQuantidadeProduto()));
        });
        PedidoEntidade pedidoDb = new PedidoEntidade(pedido.getId(), produtosEntidade, pedido.getCliente(), pedido.getValorTotal(),
                StatusPedidoEnum.retornaNomeEnum(pedido.getStatus()), pedido.getTempoEspera(),
                StatusPagamentoEnum.retornaNomeStatusPagamentoEnum(pedido.getStatusPagamento()));
           springDataPedidoRepository.save(pedidoDb);
           pedidoQueueAdapterOUT.criar(pedido);

        return pedidoDb.getId().toString();
    }

    @Override
    public Pedido atualizar(Pedido pedido){
        List<ProdEnt> produtosEntidade = new ArrayList<>();
        pedido.getProdutos().forEach(produto -> {
            produtosEntidade.add(new ProdEnt(produto.getIdProduto(), produto.getQuantidadeProduto()));
        });
        PedidoEntidade pedidoDb = new PedidoEntidade(pedido.getId(), produtosEntidade, pedido.getCliente(),
                pedido.getValorTotal(), StatusPedidoEnum.retornaNomeEnum(pedido.getStatus()), pedido.getTempoEspera(),
                StatusPagamentoEnum.retornaNomeStatusPagamentoEnum(pedido.getStatusPagamento()));
        this.springDataPedidoRepository.save(pedidoDb);
        
        if(pedido.getStatus().getNome().equals(StatusPedidoEnum.PRONTO.getNome())){
            pedidoQueueAdapterOUT.pedidoPreparado(pedido);
        }else if(pedido.getStatus().getNome().equals(StatusPedidoEnum.FINALIZADO.getNome())){
            pedidoQueueAdapterOUT.pedidoFinalizado(pedido);
        }else if(pedido.getStatus().getNome().equals(StatusPedidoEnum.CANCELADO.getNome())){
            pedidoQueueAdapterOUT.cancelaPedido(pedido);
        }
        return pedido;
    }

    @Override
    public List<Pedido> listarTodosOsPedidos() {

        List<PedidoEntidade> pedido = this.springDataPedidoRepository.findNotInFinalzado();

        if (pedido.isEmpty()) {
            throw new BadRequestException(ExceptionsMessages.PEDIDO_NAO_ENCONTRADO.getValue());
        }
        List<Pedido> pedidosRetorno = new ArrayList<>();
        pedido.forEach(pedidoEntidade -> {
            List<ProdutoVO> produtosVO = pedidoEntidade.getProdutos().stream()
                    .map(prodEnt -> new ProdutoVO(prodEnt.getIdProduto(), prodEnt.getQuantidadeProduto()))
                    .collect(Collectors.toList());

            Pedido pedidoRetorno = new Pedido(pedidoEntidade.getId(), produtosVO, pedidoEntidade.getClienteId(),
                    pedidoEntidade.getValorTotal(),
                    StatusPedidoEnum.buscaEnumPorStatusString(pedidoEntidade.getStatus()),
                    pedidoEntidade.getTempoEspera(),
                    StatusPagamentoEnum.buscaEnumPorStatusString(pedidoEntidade.getStatusPagamento()));
            pedidosRetorno.add(pedidoRetorno);
        });
        return pedidosRetorno;
    }

    @Override
    public Pedido buscarPedidoPorId(Long id) {

        Optional<PedidoEntidade> pedidoEntidadeBusca = this.springDataPedidoRepository.findById(id);
        if (!pedidoEntidadeBusca.isPresent()) {
           throw new BadRequestException(ExceptionsMessages.PEDIDO_NAO_ENCONTRADO.getValue());
        }
        List<ProdutoVO> produtosVO = pedidoEntidadeBusca.get().getProdutos().stream()
                .map(prodEnt -> new ProdutoVO(prodEnt.getIdProduto(), prodEnt.getQuantidadeProduto()))
                .collect(Collectors.toList());

        Pedido pedidoRetorno = new Pedido(pedidoEntidadeBusca.get().getId(), produtosVO,
                pedidoEntidadeBusca.get().getClienteId(),
                pedidoEntidadeBusca.get().getValorTotal(),
                StatusPedidoEnum.buscaEnumPorStatusString(pedidoEntidadeBusca.get().getStatus()),
                pedidoEntidadeBusca.get().getTempoEspera(),
                StatusPagamentoEnum.buscaEnumPorStatusString(pedidoEntidadeBusca.get().getStatusPagamento()));
        return pedidoRetorno;
    }

    @Override
    public StatusPagamentoEnum realizarPagamento(Long id){
        Optional<PedidoEntidade> pedidoEntidadeBusca = this.springDataPedidoRepository.findById(id);
        if (!pedidoEntidadeBusca.isPresent()) {
            throw new BadRequestException(ExceptionsMessages.PEDIDO_NAO_ENCONTRADO.getValue());
        }
        List<ProdutoVO> produtosVO = pedidoEntidadeBusca.get().getProdutos().stream()
                .map(prodEnt -> new ProdutoVO(prodEnt.getIdProduto(), prodEnt.getQuantidadeProduto()))
                .collect(Collectors.toList());

        PagamentoRequisicao requisicao = PagamentoRequisicao.builder()
                .meioPagamento("Cartao")
                .idMeioPagamento("1234567891011121")
                .valor(pedidoEntidadeBusca.get().getValorTotal())
                .build();

        Pagamentos pagamentoRetorno = this.pagamentoClient.fazerPagamento(requisicao);
        StatusPagamentoEnum statusPgto = null;

        if(pagamentoRetorno.status().toUpperCase().equals("APROVADO")){
            statusPgto = StatusPagamentoEnum.APROVADO;
        }else{
            statusPgto = StatusPagamentoEnum.RECUSADO;
        }

        Pedido pedidoRetorno = new Pedido(pedidoEntidadeBusca.get().getId(), produtosVO,
                pedidoEntidadeBusca.get().getClienteId(),
                pedidoEntidadeBusca.get().getValorTotal(),
                StatusPedidoEnum.buscaEnumPorStatusString(pedidoEntidadeBusca.get().getStatus()),
                pedidoEntidadeBusca.get().getTempoEspera(),
                statusPgto);
        this.atualizar(pedidoRetorno);

        return statusPgto;
    }


}
