package br.com.appfastfood.configuracoes.client.pagamento;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://a63ca3d0b51b849349ddff3fbc1f438a-f36c87df02ec6970.elb.us-east-1.amazonaws.com:8080/", name = "pagamentos")
public interface PagamentoClient {
    @PostMapping("pagamentos")
    Pagamentos fazerPagamento(@RequestBody PagamentoRequisicao pagamentoRequisicao);
}
