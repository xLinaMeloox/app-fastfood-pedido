import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequisicaoExcecaoTest {

    @Test
    public void testGetMensagem() {
        // Arrange
        String mensagem = "Erro na requisição";
        int codigo = 404;
        RequisicaoExcecao requisicaoExcecao = new RequisicaoExcecao(mensagem, codigo);

        // Act
        String resultado = requisicaoExcecao.getMensagem();

        // Assert
        assertEquals(mensagem, resultado);
    }

    @Test
    public void testGetCodigo() {
        // Arrange
        String mensagem = "Erro na requisição";
        int codigo = 404;
        RequisicaoExcecao requisicaoExcecao = new RequisicaoExcecao(mensagem, codigo);

        // Act
        int resultado = requisicaoExcecao.getCodigo();

        // Assert
        assertEquals(codigo, resultado);
    }

    @Test
    public void testConstrutor() {
        // Arrange
        String mensagem = "Erro na requisição";
        int codigo = 404;

        // Act
        RequisicaoExcecao requisicaoExcecao = new RequisicaoExcecao(mensagem, codigo);

        // Assert
        assertEquals(mensagem, requisicaoExcecao.getMensagem());
        assertEquals(codigo, requisicaoExcecao.getCodigo());
    }
}
