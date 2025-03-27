package com.alura.mudi.repository;

import com.alura.mudi.enuns.StatusPedido;
import com.alura.mudi.model.Pedido;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Cacheable("pedidos")//não precisa sempre ir ao banco buscar informação somente havendo alteração
    List<Pedido> findByStatus(StatusPedido status, Pageable pageable);

    @Query(value = "select p from Pedido p join p.user u where u.username= :username")
    List<Pedido> findByUser(@Param("username") String username, Pageable pageable);

    @Query(value = "select p from Pedido p join p.user u where u.username= :username and p.status = :status")
    List<Pedido> findByStatusUser(@Param("status") StatusPedido status, @Param("username") String username, Pageable pageable);

    /*
    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.ofertas WHERE p.id = :id")
    Optional<Pedido> buscarPedidoComOfertas(@Param("id") Long id);
*/
}
