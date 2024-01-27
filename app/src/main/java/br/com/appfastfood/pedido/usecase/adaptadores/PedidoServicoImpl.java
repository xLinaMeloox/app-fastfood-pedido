package br.com.appfastfood.pedido.usecase.adaptadores;

import br.com.appfastfood.configuracoes.client.carrinho.Carrinho;
import br.com.appfastfood.configuracoes.client.carrinho.CarrinhoClient;
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
import br.com.appfastfood.pedido.usecase.portas.PedidoServico;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PedidoServicoImpl implements PedidoServico {

    private final PedidoRepositorio pedidoRepositorio;
    private final CarrinhoClient carrinhoClient;

    public PedidoServicoImpl(PedidoRepositorio pedidoRepositorio, CarrinhoClient carrinhoClient) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.carrinhoClient = carrinhoClient;
    }

    @Override
    public String criar() {
        List<Carrinho> carrinho = this.carrinhoClient.getCarrinho();
        String idsCriados = "";
        Pedido pedido = new Pedido(null,null,null,null,null,null,null);
        for (Carrinho listaCarrinho : carrinho) {
            if(listaCarrinho.status().toUpperCase().equals("FECHADO")){
                List<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();
                ProdutoVO produtoVO = new ProdutoVO(listaCarrinho.getProdutos().get(0).idProduto().toString(), listaCarrinho.getProdutos().get(0).quantidadeProduto().toString());
                produtosVO.add(produtoVO);
                pedido = new Pedido(produtosVO, carrinho.get(0).getIdCliente(), listaCarrinho.getValorTotal(),
                    StatusPedidoEnum.buscaEnumPorStatusString("RECEBIDO"), "35:00",StatusPagamentoEnum.PENDENTE);
                this.carrinhoClient.deleteCarrinho(listaCarrinho.id());
                idsCriados += this.pedidoRepositorio.criar(pedido) + ",";
            }
        }
        return idsCriados;
    }

    @Override
    public Pedido atualizar(Long id) {
        Pedido pedidoBusca = this.pedidoRepositorio.buscarPedidoPorId(id);
        Pedido pedido = null;

        if (pedidoBusca == null) {
            throw new BadRequestException(ExceptionsMessages.PEDIDO_NAO_ENCONTRADO.getValue());
        }
        if (pedidoBusca.getStatusPagamento() == StatusPagamentoEnum.RECUSADO){
            throw new BadRequestException(ExceptionsMessages.PAGAMENTO_NAO_FOI_APROVADO.getValue());
        }
        if (pedidoBusca.getStatusPagamento() == StatusPagamentoEnum.PENDENTE){
            throw new BadRequestException(ExceptionsMessages.PAGAMENTO_PENDENTE.getValue());
        }

        if (pedidoBusca.getStatus() == StatusPedidoEnum.RECEBIDO) {
            pedido = new Pedido(
                    pedidoBusca.getId(),
                    pedidoBusca.getProdutos(),
                    pedidoBusca.getCliente(),
                    pedidoBusca.getValorTotal(),
                    pedidoBusca.getStatus(),
                    pedidoBusca.getTempoEspera(),
                    atualizarPagamento(pedidoBusca.getId()));
        } else {
            pedido = pedidoBusca;
        }

        return this.pedidoRepositorio.atualizar(pedido.atualizaStatus());
    }

    @Override
    public Pedido buscarPedidoPorId(Long id) {
        Pedido pedido = this.pedidoRepositorio.buscarPedidoPorId(id);
        return pedido;
    }

    @Override
    public List<Pedido> listarTodosPedidos() {

        List<Pedido> listaTotal = this.pedidoRepositorio.listarTodosOsPedidos();
        List<Pedido> listaEmPreparacao = listaTotal.stream()
                .filter(ped -> ped.getStatus() == StatusPedidoEnum.EM_PREPARACAO).toList();
        List<Pedido> listaPronto = listaTotal.stream().filter(ped -> ped.getStatus() == StatusPedidoEnum.PRONTO)
                .toList();
        List<Pedido> listaEmRecebibo = listaTotal.stream().filter(ped -> ped.getStatus() == StatusPedidoEnum.RECEBIDO)
                .toList();

        listaTotal = new ArrayList<>();
        listaTotal.addAll(listaPronto);
        listaTotal.addAll(listaEmPreparacao);
        listaTotal.addAll(listaEmRecebibo);
        return listaTotal;
    }

    @Override
    public StatusPagamentoEnum atualizarPagamento(Long id) {
        return this.pedidoRepositorio.realizarPagamento(id);
    }

}
