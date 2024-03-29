package br.com.appfastfood.configuracoes.client.carrinho;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "172.20.136.67:8080/", name = "Carrinho")
public interface CarrinhoClient {

    @GetMapping("carrinho")
    List<Carrinho> getCarrinho();

    @GetMapping("carrinho/{id}")
    Carrinho getCarrinhoPorId(@RequestParam Long id);

    @DeleteMapping("carrinho/{id}")
    void deleteCarrinho(@RequestParam Long id);
}
