package br.com.appfastfood.configuracoes.client.carrinho;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "http://a63ca3d0b51b849349ddff3fbc1f438a-f36c87df02ec6970.elb.us-east-1.amazonaws.com/", name = "Carrinho")
public interface CarrinhoClient {

    @GetMapping("carrinho")
    List<Carrinho> getCarrinho();

    @DeleteMapping("carrinho/{id}")
    void deleteCarrinho(@RequestParam Long id);
}
