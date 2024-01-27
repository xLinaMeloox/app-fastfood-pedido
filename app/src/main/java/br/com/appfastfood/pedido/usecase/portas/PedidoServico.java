package br.com.appfastfood.pedido.usecase.portas;

import br.com.appfastfood.configuracoes.client.carrinho.Carrinho;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;

import java.util.List;


public interface PedidoServico {

    String criar();
    Pedido atualizar(Long id);
    Pedido buscarPedidoPorId(Long id);
    List<Pedido> listarTodosPedidos();
    StatusPagamentoEnum atualizarPagamento(Long id);
}
