package br.com.appfastfood.configuracoes;

import br.com.appfastfood.AppFastfoodApplication;
import br.com.appfastfood.configuracoes.client.carrinho.CarrinhoClient;
import br.com.appfastfood.configuracoes.client.pagamento.PagamentoClient;
import br.com.appfastfood.configuracoes.logs.Log;
import br.com.appfastfood.configuracoes.logs.Log4jLog;
import br.com.appfastfood.pedido.dominio.repositorios.PedidoRepositorio;
import br.com.appfastfood.pedido.usecase.adaptadores.PedidoServicoImpl;
import br.com.appfastfood.pedido.usecase.portas.PedidoServico;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = AppFastfoodApplication.class)
@Qualifier("pedidoServicoImpl")
public class BeanConfiguration {

    @Bean
    PedidoServico pedidoServico(PedidoRepositorio pedidoRepositorio, CarrinhoClient carrinhoClient){
        return new PedidoServicoImpl(pedidoRepositorio, carrinhoClient);
    }
    @Bean
    Log log(){
        return new Log4jLog(BeanConfiguration.class);
    }
}
