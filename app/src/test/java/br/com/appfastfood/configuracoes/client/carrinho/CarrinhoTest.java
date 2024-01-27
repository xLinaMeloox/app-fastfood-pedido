package br.com.appfastfood.configuracoes.client.carrinho;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarrinhoTest {

    @Test
    void getters_CorretamenteImplementados() {
        // Arrange
        Long id = 1L;
        String status = "Em andamento";
        List<Carrinho.Produto> produtos = new ArrayList<>();
        produtos.add(new Carrinho.Produto("1", 2));
        String idCliente = "cliente123";
        Double valorTotal = 100.00;

        // Act
        Carrinho carrinho = new Carrinho(id, status, produtos, idCliente, valorTotal);

        // Assert
        assertEquals(id, carrinho.id());
        assertEquals(status, carrinho.status());
        assertEquals(produtos, carrinho.produtos());
        assertEquals(idCliente, carrinho.idCliente());
        assertEquals(valorTotal, carrinho.valorTotal());
    }

    @Test
    void innerRecordProduto_DeveSerCriadoCorretamente() {
        // Arrange
        String idProduto = "1";
        Integer quantidadeProduto = 2;

        // Act
        Carrinho.Produto produto = new Carrinho.Produto(idProduto, quantidadeProduto);

        // Assert
        assertNotNull(produto);
        assertEquals(idProduto, produto.idProduto());
        assertEquals(quantidadeProduto, produto.quantidadeProduto());
    }


//    @Test
//    public void testGetProdutos() {
//        // Cria uma instância da sua classe
//        SuaClasse suaClasse = new SuaClasse();
//
//        // Adicione alguns produtos à lista de produtos
//        // (Você precisa adaptar isso de acordo com a implementação real da sua classe)
//        Produto produto1 = new Produto("Produto1", 10.0);
//        Produto produto2 = new Produto("Produto2", 20.0);
//        suaClasse.getProdutos().add(produto1);
//        suaClasse.getProdutos().add(produto2);
//
//        // Testa se a lista de produtos retornada contém os produtos adicionados
//        assertEquals(2, suaClasse.getProdutos().size());
//        assertTrue(suaClasse.getProdutos().contains(produto1));
//        assertTrue(suaClasse.getProdutos().contains(produto2));
//    }
//
//    @Test
//    public void testGetIdCliente() {
//        // Cria uma instância da sua classe
//        SuaClasse suaClasse = new SuaClasse();
//
//        // Define um ID de cliente
//        String idCliente = "123456";
//        suaClasse.setIdCliente(idCliente);
//
//        // Testa se o ID do cliente retornado é o mesmo que foi definido
//        assertEquals(idCliente, suaClasse.getIdCliente());
//    }
//
//    @Test
//    public void testGetValorTotal() {
//        // Cria uma instância da sua classe
//        SuaClasse suaClasse = new SuaClasse();
//
//        // Adiciona alguns produtos à lista de produtos
//        Produto produto1 = new Produto("Produto1", 10.0);
//        Produto produto2 = new Produto("Produto2", 20.0);
//        suaClasse.getProdutos().add(produto1);
//        suaClasse.getProdutos().add(produto2);
//
//        // Testa se o valor total retornado é a soma dos preços dos produtos
//        assertEquals(30.0, suaClasse.getValorTotal(), 0.001); // Usando delta para lidar com possíveis erros de precisão de ponto flutuante
//    }
}
