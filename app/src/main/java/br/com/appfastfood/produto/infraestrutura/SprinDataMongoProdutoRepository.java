package br.com.appfastfood.produto.infraestrutura;

import br.com.appfastfood.produto.infraestrutura.entidades.ProdutoEntidade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SprinDataMongoProdutoRepository extends MongoRepository<ProdutoEntidade, Long> {

}
