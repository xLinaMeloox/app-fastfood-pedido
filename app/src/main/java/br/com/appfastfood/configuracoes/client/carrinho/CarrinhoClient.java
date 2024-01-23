package br.com.appfastfood.configuracoes.client.carrinho;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8080/", name = "Carrinho")
public interface CarrinhoClient {

    @GetMapping("carrinho")
    List<Carrinho> getCarrinho();
}
