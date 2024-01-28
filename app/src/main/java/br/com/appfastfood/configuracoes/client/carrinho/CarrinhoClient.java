package br.com.appfastfood.configuracoes.client.carrinho;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "https://a4c676d7482b8444089c07fcdcf0f598-957dcc16b9d9f355.elb.us-east-1.amazonaws.com:8080/", name = "Carrinho")
public interface CarrinhoClient {

    @GetMapping("carrinho")
    List<Carrinho> getCarrinho();

    @DeleteMapping("carrinho/{id}")
    void deleteCarrinho(@RequestParam Long id);
}
