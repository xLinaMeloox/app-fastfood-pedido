import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutosReqTest {

    @Test
    public void testConstrutorEGetters() {
        // Dados de exemplo
        String idProduto = "123";
        String quantidadeProduto = "2";
        String nome = "Hamburguer";
        Double preco = 10.0;
        String categoria = "Fast Food";
        String uriImagem = "http://example.com/image.jpg";

        // Construir objeto ProdutosReq
        ProdutosReq produtosReq = ProdutosReq.builder()
                .idProduto(idProduto)
                .quantidadeProduto(quantidadeProduto)
                .nome(nome)
                .preco(preco)
                .categoria(categoria)
                .uriImagem(uriImagem)
                .build();

        // Verificar se os valores foram atribuídos corretamente
        assertEquals(idProduto, produtosReq.getIdProduto());
        assertEquals(quantidadeProduto, produtosReq.getQuantidadeProduto());
        assertEquals(nome, produtosReq.getNome());
        assertEquals(preco, produtosReq.getPreco());
        assertEquals(categoria, produtosReq.getCategoria());
        assertEquals(uriImagem, produtosReq.getUriImagem());
    }

    // Adicione mais testes conforme necessário
}
