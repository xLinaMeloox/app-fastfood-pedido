import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "listar-carrinho", url = "localhost:8080/carrinho")
public interface PedidoFeignClient {

    @PostMapping("pedidos")
    ResponseEntity<String> criarPedido(@RequestBody PedidoRequisicao pedidoRequisicao);
}
