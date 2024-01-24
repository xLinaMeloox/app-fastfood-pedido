package br.com.appfastfood.pedido.usecase.adaptadores;

import br.com.appfastfood.configuracoes.client.carrinho.Carrinho;
import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.vo.ProdutoVO;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
import br.com.appfastfood.pedido.exceptions.ExceptionsMessages;
import br.com.appfastfood.pedido.usecase.portas.PedidoServico;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoServicoImpl implements PedidoServico {

    private final PedidoRepositorio pedidoRepositorio;
    private RestTemplate restTemplate;

    public PedidoServicoImpl(PedidoRepositorio pedidoRepositorio) {

        this.pedidoRepositorio = pedidoRepositorio;
    }
    public PedidoServicoImpl(PedidoRepositorio pedidoRepositorio, RestTemplate restTemplate) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.restTemplate = restTemplate;
    }

    @Override
    public String criar(PedidoRequisicao pedidoReq, String status, String tempo) {
        return null;
    }
    public String criar(List<Carrinho> carrinho) {
        String idsCriados = "";

        Pedido pedido = new Pedido(null,null,null,null,null,null,null);
        for (Carrinho listaCarrinho : carrinho) {
        List<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();
            ProdutoVO produtoVO = new ProdutoVO(listaCarrinho.getProdutos().get(0).idProduto().toString(), listaCarrinho.getProdutos().get(0).quantidadeProduto().toString());
            produtosVO.add(produtoVO);
            pedido = new Pedido(produtosVO, carrinho.get(0).getIdCliente(), listaCarrinho.getValorTotal(),
                    StatusPedidoEnum.buscaEnumPorStatusString("RECEBIDO"), "1", StatusPagamentoEnum.PENDENTE);

            idsCriados += this.pedidoRepositorio.criar(pedido) + ",";
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
        return null;
    }

    @Override
    public List<Pedido> listarTodosPedidos() {

        List<Pedido> listaTotal = this.pedidoRepositorio.listarTodosOsPedidos();
        List<Pedido> listaEmPreparacao = listaTotal.stream()
                .filter(ped -> ped.getStatus() == StatusPedidoEnum.EM_PREPARACAO).collect(Collectors.toList());
        List<Pedido> listaPronto = listaTotal.stream().filter(ped -> ped.getStatus() == StatusPedidoEnum.PRONTO)
                .collect(Collectors.toList());
        List<Pedido> listaEmRecebibo = listaTotal.stream().filter(ped -> ped.getStatus() == StatusPedidoEnum.RECEBIDO)
                .collect(Collectors.toList());

        listaTotal = new ArrayList();
        listaTotal.addAll(listaPronto);
        listaTotal.addAll(listaEmPreparacao);
        listaTotal.addAll(listaEmRecebibo);
        return listaTotal;
    }

    public StatusPagamentoEnum atualizarPagamento(Long id) {
        return StatusPagamentoEnum.APROVADO;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
