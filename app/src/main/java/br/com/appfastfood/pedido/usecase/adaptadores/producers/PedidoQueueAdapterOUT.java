package br.com.appfastfood.pedido.usecase.adaptadores.producers;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.pedido.dominio.modelos.vo.ProdutoVO;
import br.com.appfastfood.pedido.usecase.adaptadores.producers.out.PedidoRequisicaoOut;
import br.com.appfastfood.pedido.usecase.adaptadores.producers.out.ProdutosRequisicaoOut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.usecase.portas.TopicHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoQueueAdapterOUT {
    private TopicHandler snsTopic;
    public PedidoQueueAdapterOUT(TopicHandler snstopicHandler) {
        this.snsTopic = snstopicHandler;
    }
   
    public void criar(Pedido pedido) {


        List<ProdutosRequisicaoOut> produtosReqOut = new ArrayList<ProdutosRequisicaoOut>();
        for(ProdutoVO prodVo : pedido.getProdutos()){
            produtosReqOut.add(new ProdutosRequisicaoOut(prodVo.getIdProduto(), prodVo.getQuantidadeProduto(), null, null, null, null));
        }
        PedidoRequisicaoOut pedidoRequisicaoOut = new PedidoRequisicaoOut(
                produtosReqOut,
                pedido.getCliente(),
                pedido.getValorTotal(),
                pedido.getStatus().name(),
                pedido.getTempoEspera(),
                pedido.getId().toString(),
                pedido.getStatusPagamento().name());
        try {

            snsTopic.publish(new ObjectMapper().writeValueAsString(pedidoRequisicaoOut), "arn:aws:sns:us-east-1:101478099523:pedido-criado");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void pedidoPreparado(Pedido pedido) {

        List<ProdutosRequisicaoOut> produtosReqOut = new ArrayList<ProdutosRequisicaoOut>();
        for(ProdutoVO prodVo : pedido.getProdutos()){
            produtosReqOut.add(new ProdutosRequisicaoOut(prodVo.getIdProduto(), prodVo.getQuantidadeProduto(), null, null, null, null));
        }
        PedidoRequisicaoOut pedidoRequisicaoOut = new PedidoRequisicaoOut(
                produtosReqOut,
                pedido.getCliente(),
                pedido.getValorTotal(),
                pedido.getStatus().name(),
                pedido.getTempoEspera(),
                pedido.getId().toString(),
                pedido.getStatusPagamento().name());
        try {
            snsTopic.publish(new ObjectMapper().writeValueAsString(pedidoRequisicaoOut), "arn:aws:sns:us-east-1:101478099523:pedido-preparado");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelaPedido(Pedido pedido){
        List<ProdutosRequisicaoOut> produtosReqOut = new ArrayList<ProdutosRequisicaoOut>();
        for(ProdutoVO prodVo : pedido.getProdutos()){
            produtosReqOut.add(new ProdutosRequisicaoOut(prodVo.getIdProduto(), prodVo.getQuantidadeProduto(), null, null, null, null));
        }
        PedidoRequisicaoOut pedidoRequisicaoOut = new PedidoRequisicaoOut(
                produtosReqOut,
                pedido.getCliente(),
                pedido.getValorTotal(),
                pedido.getStatus().name(),
                pedido.getTempoEspera(),
                pedido.getId().toString(),
                pedido.getStatusPagamento().name());
        try {
            snsTopic.publish(new ObjectMapper().writeValueAsString(pedidoRequisicaoOut), "arn:aws:sns:us-east-1:101478099523:pedido-finalizado");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void pedidoFinalizado(Pedido pedido){
        List<ProdutosRequisicaoOut> produtosReqOut = new ArrayList<ProdutosRequisicaoOut>();
        for(ProdutoVO prodVo : pedido.getProdutos()){
            produtosReqOut.add(new ProdutosRequisicaoOut(prodVo.getIdProduto(), prodVo.getQuantidadeProduto(), null, null, null, null));
        }
        PedidoRequisicaoOut pedidoRequisicaoOut = new PedidoRequisicaoOut(
                produtosReqOut,
                pedido.getCliente(),
                pedido.getValorTotal(),
                pedido.getStatus().name(),
                pedido.getTempoEspera(),
                pedido.getId().toString(),
                pedido.getStatusPagamento().name());
        try {
            snsTopic.publish(new ObjectMapper().writeValueAsString(pedidoRequisicaoOut), "arn:aws:sns:us-east-1:101478099523:pedido-finalizado");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        
    }
}