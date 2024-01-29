package br.com.appfastfood.configuracoes.client.carrinho;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "appfastfood-producao")
public interface CarrinhoClient {

    @GetMapping("/carrinho")
    List<Carrinho> getCarrinho();

    @DeleteMapping("/carrinho/{id}")
    void deleteCarrinho(@RequestParam Long id);
}
