package br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoRequisicaoTest {

    @Test
    public void testPedidoRequisicaoSerialization() throws Exception {
        // Criar uma instância de PedidoRequisicao para teste
        List<ProdutosReq> produtos = new ArrayList<>();
        produtos.add(new ProdutosReq("Produto1", 10.0));
        produtos.add(new ProdutosReq("Produto2", 20.0));

        String idCliente = "123";
        Double valorTotal = 30.0;
        String status = "Aguardando Pagamento";
        String tempoEspera = "30 minutos";
        String idPedido = "456";
        String statusPagamento = "Pendente";

        PedidoRequisicao pedidoRequisicao = new PedidoRequisicao(
                produtos, idCliente, valorTotal, status, tempoEspera, idPedido, statusPagamento);

        // Serializar o objeto para JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(pedidoRequisicao);

        // Desserializar o JSON de volta para um objeto PedidoRequisicao
        PedidoRequisicao deserializedPedidoRequisicao = objectMapper.readValue(json, PedidoRequisicao.class);

        // Asserts para verificar se os valores foram preservados durante a serialização/desserialização
        assertEquals(idCliente, deserializedPedidoRequisicao.getIdCliente());
        assertEquals(valorTotal, deserializedPedidoRequisicao.getValorTotal());
        assertEquals(produtos.size(), deserializedPedidoRequisicao.getProdutos().size());
        assertEquals(status, deserializedPedidoRequisicao.getStatus());
        assertEquals(tempoEspera, deserializedPedidoRequisicao.getTempoEspera());
        assertEquals(idPedido, deserializedPedidoRequisicao.getIdPedido());
        assertEquals(statusPagamento, deserializedPedidoRequisicao.getStatusPagamento());
    }
}
