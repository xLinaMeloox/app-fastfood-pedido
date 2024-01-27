package br.com.appfastfood.configuracoes;

import br.com.appfastfood.AppFastfoodApplication;
import br.com.appfastfood.configuracoes.client.carrinho.CarrinhoClient;
import br.com.appfastfood.configuracoes.logs.Log;
import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
import br.com.appfastfood.pedido.usecase.portas.PedidoServico;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class BeanConfigurationTest {

    @Mock
    private PedidoRepositorio pedidoRepositorio;

    @Mock
    private CarrinhoClient carrinhoClient;

    @Test
    void pedidoServico_BeanDeveSerCriadoCorretamente() {
        // Arrange
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanConfiguration.class);
        context.refresh();

        // Act
        PedidoServico pedidoServico = context.getBean("pedidoServicoImpl", PedidoServico.class);

        // Assert
        assertNotNull(pedidoServico);
    }

    @Test
    void log_BeanDeveSerCriadoCorretamente() {
        // Arrange
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanConfiguration.class);
        context.refresh();

        // Act
        Log log = context.getBean(Log.class);

        // Assert
        assertNotNull(log);
    }

}
