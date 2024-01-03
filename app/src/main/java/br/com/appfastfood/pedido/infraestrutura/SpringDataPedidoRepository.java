package br.com.appfastfood.pedido.infraestrutura;

import br.com.appfastfood.pedido.infraestrutura.entidades.PedidoEntidade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface SpringDataPedidoRepository extends MongoRepository<PedidoEntidade, Long> {
     PedidoEntidade findPedidoEntidadeById(Long id);

     @Query("{ 'status': { $nin: ['FINALIZADO'] } }")
     List<PedidoEntidade> findNotInFinalzado();
}
