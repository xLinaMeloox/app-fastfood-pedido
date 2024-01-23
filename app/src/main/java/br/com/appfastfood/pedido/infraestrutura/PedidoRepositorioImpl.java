package br.com.appfastfood.pedido.infraestrutura;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.vo.ProdutoVO;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
import br.com.appfastfood.pedido.exceptions.ExceptionsMessages;
import br.com.appfastfood.pedido.infraestrutura.entidades.PedidoEntidade;
import br.com.appfastfood.pedido.infraestrutura.entidades.ProdEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PedidoRepositorioImpl implements PedidoRepositorio {
    @Autowired
    private MongoTemplate mongoTemplate;
    private final SpringDataPedidoRepository springDataPedidoRepository;

    public PedidoRepositorioImpl(SpringDataPedidoRepository springDataPedidoRepository) {
        this.springDataPedidoRepository = springDataPedidoRepository;
    }

    @Override
    public String criar(Pedido pedido) {
        List<ProdEnt> produtosEntidade = new ArrayList<>();
        pedido.getProdutos().forEach(produto -> {
            produtosEntidade.add(new ProdEnt(produto.getIdProduto(), produto.getQuantidadeProduto()));
        });
        PedidoEntidade pedidoDb = new PedidoEntidade(1L, produtosEntidade, pedido.getCliente(), pedido.getValorTotal(),
                StatusPedidoEnum.retornaNomeEnum(pedido.getStatus()), pedido.getTempoEspera(),
                StatusPagamentoEnum.retornaNomeStatusPagamentoEnum(pedido.getStatusPagamento()));

        if (realizarPagamento()) {
            springDataPedidoRepository.save(pedidoDb);
        } else {
            throw new BadRequestException(ExceptionsMessages.PAGAMENTO_NAO_FOI_APROVADO.getValue());
        }
        return pedidoDb.getId().toString();
    }

    @Override
    public Pedido atualizar(Pedido pedido) {
        List<ProdEnt> produtosEntidade = new ArrayList<>();
        pedido.getProdutos().forEach(produto -> {
            produtosEntidade.add(new ProdEnt(produto.getIdProduto(), produto.getQuantidadeProduto()));
        });
        PedidoEntidade pedidoDb = new PedidoEntidade(pedido.getId(), produtosEntidade, pedido.getCliente(),
                pedido.getValorTotal(), StatusPedidoEnum.retornaNomeEnum(pedido.getStatus()), pedido.getTempoEspera(),
                StatusPagamentoEnum.retornaNomeStatusPagamentoEnum(pedido.getStatusPagamento()));
        this.springDataPedidoRepository.save(pedidoDb);
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
    public Boolean realizarPagamento() {
        return true;
    }

    @Override
    public Pedido atualizaPagamento(Pedido pedido) {
        List<ProdEnt> produtosEntidade = new ArrayList<>();
        pedido.getProdutos().forEach(produto -> {
            produtosEntidade.add(new ProdEnt(produto.getIdProduto(), produto.getQuantidadeProduto()));
        });
        PedidoEntidade pedidoDb = new PedidoEntidade(pedido.getId(), produtosEntidade, pedido.getCliente(),
                pedido.getValorTotal(), StatusPedidoEnum.retornaNomeEnum(pedido.getStatus()), pedido.getTempoEspera(),
                StatusPagamentoEnum.retornaNomeStatusPagamentoEnum(pedido.getStatusPagamento()));
        this.springDataPedidoRepository.save(pedidoDb);
        return pedido;
    }
}
