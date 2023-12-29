package br.com.appfastfood.produto.infraestrutura;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.produto.dominio.modelos.Produto;
import br.com.appfastfood.produto.dominio.repositorios.ProdutoRepositorio;
import br.com.appfastfood.produto.dominio.vo.*;
import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
import br.com.appfastfood.produto.exceptions.ExceptionsMessages;
import br.com.appfastfood.produto.infraestrutura.entidades.CustomSequence;
import br.com.appfastfood.produto.infraestrutura.entidades.ProdutoEntidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProdutoRepositorioImpl implements ProdutoRepositorio {
    @Autowired
    private MongoTemplate mongoTemplate;
    private final SpringDataProdutoRepository springDataProdutoRepository;
    public ProdutoRepositorioImpl(SpringDataProdutoRepository springDataProdutoRepository) {
        this.springDataProdutoRepository = springDataProdutoRepository;
    }

    @Override
    public void cadastrar(Produto produto) {
        ProdutoEntidade produtoSalvo = new ProdutoEntidade(
                generateNextId("product"),
                produto.getNome().getNome(),
                produto.getPreco().getPreco(),
                produto.getUriImagem().getUriImagem(),
                produto.getCategoria().name(),
                produto.getDescricao().getDescricao()
        );

        this.springDataProdutoRepository.save(produtoSalvo);
    }

    @Override
    public void remover(Long id) {
        this.springDataProdutoRepository.deleteById(id); 
    }

    @Override
    public Produto atualizar(Long id, Produto produto) {
        ProdutoEntidade produtoSalvo = new ProdutoEntidade(
                id,
                produto.getNome().getNome(),
                produto.getPreco().getPreco(),
                produto.getUriImagem().getUriImagem(),
                produto.getCategoria().name(),
                produto.getDescricao().getDescricao()
        );

        if (this.springDataProdutoRepository.existsById(id)) {
            this.springDataProdutoRepository.save(produtoSalvo);
            return produto;
        }

        throw new BadRequestException(ExceptionsMessages.ID_NAO_ENCONTRADO.getValue());

    }

    @Override
    public Optional<List<Produto>> buscarPorCategoria(String categoria) {
        List<Produto> produtos = new ArrayList<>();
        Optional<List<ProdutoEntidade>> produtoEntidadeCategoria;

        if(categoria.toUpperCase().equals(CategoriaEnum.todos.toString().toUpperCase())){
            produtoEntidadeCategoria = Optional.of(this.springDataProdutoRepository.findAll());
        }else{
            produtoEntidadeCategoria = this.springDataProdutoRepository.findProdutoEntidadeByCategoria(categoria);
        }
        

            if(produtoEntidadeCategoria.isPresent() && !produtoEntidadeCategoria.get().isEmpty()) {
                produtoEntidadeCategoria.get().forEach(produtoEntidade -> {
                    Produto produto = new Produto(
                            Long.valueOf(0),
                            new Nome(produtoEntidade.getNome()),
                            new Preco(produtoEntidade.getPreco()),
                            new UriImagem(produtoEntidade.getUriImagem()),
                            new Categoria(produtoEntidade.getCategoria()).getCategoria(),
                            new Descricao(produtoEntidade.getDescricao())
                    );
                    produtos.add(produto);
                });
                return Optional.of(produtos);
        }

        throw new BadRequestException(ExceptionsMessages.CATEGORIA_NAO_ENCONTRADA.getValue());
    }

    @Override
    public Produto buscarProdutoPorId(Long id){
        ProdutoEntidade produtoBusca = this.springDataProdutoRepository.findProdutoById(id);
        
        if (produtoBusca == null){
            throw new BadRequestException(ExceptionsMessages.ID_NAO_ENCONTRADO.getValue());
        }

        Produto produtoRetorno = new Produto(Long.valueOf(0),
                                    new Nome(produtoBusca.getNome()), 
                                    new Preco(produtoBusca.getPreco()), 
                                    new UriImagem(produtoBusca.getUriImagem()), 
                                    new Categoria(produtoBusca.getCategoria()).getCategoria(), 
                                    new Descricao(produtoBusca.getDescricao()));
        return produtoRetorno;
    }

    public Long generateNextId(String collectionName) {
        Query query = new Query(Criteria.where("_id").is(collectionName));
        Update update = new Update().inc("sequence", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);

        CustomSequence sequence = mongoTemplate.findAndModify(query, update, options, CustomSequence.class);

        return sequence.getSequence();
    }
}
