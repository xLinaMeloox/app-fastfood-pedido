package br.com.appfastfood.produto.aplicacao.adaptadores;

import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import br.com.appfastfood.configuracoes.logs.Log;
import br.com.appfastfood.produto.aplicacao.adaptadores.requisicao.ProdutoRequisicao;
import br.com.appfastfood.produto.aplicacao.adaptadores.resposta.ProdutoResposta;
import br.com.appfastfood.produto.dominio.modelos.Produto;
import br.com.appfastfood.produto.dominio.vo.*;
import br.com.appfastfood.produto.usecase.portas.ProdutoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Tudo sobre produtos")
public class ProdutoController {

    private ProdutoServico produtoServico;
    private Log logger;

    public ProdutoController(ProdutoServico produtoServico, Log logger) {
        this.produtoServico = produtoServico;
        this.logger = logger;
    }

    @PostMapping
    @Operation(summary = "Cadastrar Produto", description = "Funcionalidade de criar um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProdutoResposta.class)) }),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RequisicaoExcecao.class)))})

    public ResponseEntity cadastrar(@RequestBody ProdutoRequisicao produtoRequisicao){

        Produto produto = new Produto(
                    produtoRequisicao.getId(),
                    new Nome(produtoRequisicao.getNome()),
                    new Preco(produtoRequisicao.getPreco()),
                    new UriImagem(produtoRequisicao.getUriImagem()),
                    new Categoria(produtoRequisicao.getCategoria()).getCategoria(),
                    new Descricao(produtoRequisicao.getDescricao())
            );
        this.produtoServico.cadastrar(produto);

        ProdutoResposta produtoResposta = ProdutoResposta
                .builder()
                .nome(produto.getNome().getNome())
                .preco(produto.getPreco().getPreco())
                .descricao(produto.getDescricao().getDescricao())
                .categoria(produto.getCategoria().name())
                .uriImagem(produto.getUriImagem().getUriImagem())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResposta);

    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover Produto", description = "Funcionalidade de remover um produto passando o parametro 'id' do produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto removido com suceso",
                    content = { @Content() }),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RequisicaoExcecao.class)))})
    public ResponseEntity remover(@PathVariable("id") Long id){
        this.produtoServico.remover(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Produto", description = "Funcionalidade de atualização de um produto passando o parametro 'id' e o corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProdutoResposta.class))}),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RequisicaoExcecao.class)))})
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ProdutoRequisicao produtoRequisicao){

            Produto produto = new Produto(
                    produtoRequisicao.getId(),
                    new Nome(produtoRequisicao.getNome()),
                    new Preco(produtoRequisicao.getPreco()),
                    new UriImagem(produtoRequisicao.getUriImagem()),
                    new Categoria(produtoRequisicao.getCategoria()).getCategoria(),
                    new Descricao(produtoRequisicao.getDescricao())
            );
        Produto produtoResultado = this.produtoServico.atualizar(id, produto);

        ProdutoResposta produtoResposta = ProdutoResposta
                .builder()
                .nome(produtoResultado.getNome().getNome())
                .preco(produtoResultado.getPreco().getPreco())
                .descricao(produtoResultado.getDescricao().getDescricao())
                .categoria(produtoResultado.getCategoria().name())
                .uriImagem(produtoResultado.getUriImagem().getUriImagem())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(produtoResposta);
    }

    @GetMapping()
    @Operation(summary = "Buscar Produtos por Categoria", description = "Funcionalidade que retorna uma lista de produtos por um filtro de Categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos filtrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class, subTypes = { ProdutoResposta.class }))}),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RequisicaoExcecao.class)))})
    public ResponseEntity buscarPorCategoria(@Parameter(description = "Deve ser buscado por: lanche, bebida, sobremesa ou todos")@RequestParam(value = "categoria") String categoria){

            List<Produto> produtos = this.produtoServico.buscarPorCategoria(categoria);

            List<ProdutoResposta> produtosResposta =  produtos.stream().map(produto -> ProdutoResposta
                    .builder()
                    .id(produto.getId().toString())
                    .nome(produto.getNome().getNome())
                    .preco(produto.getPreco().getPreco())
                    .descricao(produto.getDescricao().getDescricao())
                    .categoria(produto.getCategoria().name())
                    .uriImagem(produto.getUriImagem().getUriImagem())
                    .build()).toList();

            return ResponseEntity.status(HttpStatus.OK).body(produtosResposta);
    }

}
