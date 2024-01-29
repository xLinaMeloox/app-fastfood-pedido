package br.com.appfastfood.configuracoes.client.pagamento;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://a4c676d7482b8444089c07fcdcf0f598-957dcc16b9d9f355.elb.us-east-1.amazonaws.com/", name = "pagamentos")
public interface PagamentoClient {
    @PostMapping("pagamentos")
    Pagamentos fazerPagamento(@RequestBody PagamentoRequisicao pagamentoRequisicao);
}
