package br.com.appfastfood.configuracoes.client.pagamento;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://localhost:8080/", name = "pagamentos")
public interface PagamentoClient {
    @PostMapping("pagamentos")
    Pagamentos fazerPagamento(@RequestBody PagamentoRequisicao pagamentoRequisicao);
}
