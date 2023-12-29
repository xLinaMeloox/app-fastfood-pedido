package br.com.appfastfood.pedido.dominio.repositorios;

import br.com.appfastfood.pedido.dominio.modelos.Pedido;

import java.util.List;

public interface PedidoRepositorio {
    String criar(Pedido pedido);
    Pedido atualizar(Pedido pedido) ;
    List<Pedido> listarTodosOsPedidos();
    Pedido buscarPedidoPorId(Long id);
    Boolean realizarPagamento();
    Pedido atualizarPagamento(Pedido pedido);
} 
