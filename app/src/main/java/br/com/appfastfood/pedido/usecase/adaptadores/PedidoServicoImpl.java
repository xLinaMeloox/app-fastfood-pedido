package br.com.appfastfood.pedido.usecase.adaptadores;

import br.com.appfastfood.configuracoes.client.carrinho.Carrinho;
import br.com.appfastfood.configuracoes.client.carrinho.CarrinhoClient;
import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.vo.ProdutoVO;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;
import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
import br.com.appfastfood.pedido.exceptions.ExceptionsMessages;
import br.com.appfastfood.pedido.usecase.portas.PedidoServico;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServicoImpl implements PedidoServico {

    private final PedidoRepositorio pedidoRepositorio;
    private final CarrinhoClient carrinhoClient;

    public PedidoServicoImpl(PedidoRepositorio pedidoRepositorio, CarrinhoClient carrinhoClient) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.carrinhoClient = carrinhoClient;
    }

    @Override
    @Transactional
    public void criar(String carrinho) {
         Pedido pedido;
         try {
            PedidoRequisicao pedidoReq = new ObjectMapper().readValue(carrinho, PedidoRequisicao.class);
                List<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();
                for(ProdutosReq prodVo : pedidoReq.getProdutos()){
                    produtosVO.add(new ProdutoVO(prodVo.getIdProduto(), prodVo.getQuantidadeProduto()));
                }
                
                pedido = new Pedido(Long.valueOf(pedidoReq.getIdPedido()), produtosVO, pedidoReq.getIdCliente(), pedidoReq.getValorTotal(),
                    StatusPedidoEnum.buscaEnumPorStatusString("RECEBIDO"), "35:00",StatusPagamentoEnum.PENDENTE);
                this.pedidoRepositorio.criar(pedido);
         } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
         }
    }

    @Override
    @Transactional
    public void atualizar(String message, boolean isCancelarPedido) {
        try{
            PedidoRequisicao pedidoReq = new ObjectMapper().readValue(message, PedidoRequisicao.class);
            Pedido pedidoBusca = this.pedidoRepositorio.buscarPedidoPorId(Long.valueOf(pedidoReq.getIdPedido()));
            Pedido pedido = null;
    
            if (pedidoBusca == null) {
                throw new BadRequestException(ExceptionsMessages.PEDIDO_NAO_ENCONTRADO.getValue());
            }
    
            pedido = pedidoBusca;
    
            if (isCancelarPedido) {
                pedido.setStatus(StatusPedidoEnum.CANCELADO);
            }
    
            this.pedidoRepositorio.atualizar(isCancelarPedido == true ? pedido : pedido.atualizaStatus());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
