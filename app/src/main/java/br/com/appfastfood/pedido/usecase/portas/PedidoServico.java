package br.com.appfastfood.pedido.usecase.portas;
import br.com.appfastfood.pedido.dominio.modelos.Pedido;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import jakarta.transaction.Transactional;

import java.util.List;


public interface PedidoServico {

    @Transactional
    void criar(String carrinho);

    @Transactional
    void atualizar(String message, boolean isCancelarPedido);
    Pedido buscarPedidoPorId(Long id);
    List<Pedido> listarTodosPedidos();
    StatusPagamentoEnum atualizarPagamento(Long id);
}
