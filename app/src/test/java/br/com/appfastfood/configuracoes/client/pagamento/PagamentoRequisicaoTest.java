package br.com.appfastfood.configuracoes.client.pagamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PagamentoRequisicaoTest {

    @Test
    void gettersAndSetters_CorretamenteImplementados() {
        // Arrange
        String meioPagamento = "Cartao";
        String idMeioPagamento = "1234567891011121";
        Double valor = 100.00;

        // Act
        PagamentoRequisicao requisicao = new PagamentoRequisicao(meioPagamento, idMeioPagamento, valor);
        requisicao.setMeioPagamento(meioPagamento);
        requisicao.setIdMeioPagamento(idMeioPagamento);
        requisicao.setValor(valor);

        // Assert
        assertEquals(meioPagamento, requisicao.getMeioPagamento());
        assertEquals(idMeioPagamento, requisicao.getIdMeioPagamento());
        assertEquals(valor, requisicao.getValor());
    }
}
