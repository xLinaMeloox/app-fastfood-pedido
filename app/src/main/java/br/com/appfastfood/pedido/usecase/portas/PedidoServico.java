package br.com.appfastfood.pedido.usecase.portas;

import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.PedidoRequisicao;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;

import java.util.List;


public interface PedidoServico {

    String criar(PedidoRequisicao pedido, String status, String tempo);
    Pedido atualizar(Long id);
    Pedido buscarPedidoPorId(Long id);
    List<Pedido> listarTodosPedidos();
}
