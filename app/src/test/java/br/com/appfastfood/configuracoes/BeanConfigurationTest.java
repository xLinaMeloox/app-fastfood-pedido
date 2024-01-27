package br.com.appfastfood.configuracoes;

import br.com.appfastfood.configuracoes.logs.Log;
import br.com.appfastfood.pedido.usecase.portas.PedidoServico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeanConfigurationTest {

    @Autowired
    private PedidoServico pedidoServico;

    @Autowired
    private Log log;

    @Test
    void testPedidoServicoBean() {
        assertThat(pedidoServico).isNotNull();
    }

    @Test
    void testLogBean() {
        assertThat(log).isNotNull();
    }
}
