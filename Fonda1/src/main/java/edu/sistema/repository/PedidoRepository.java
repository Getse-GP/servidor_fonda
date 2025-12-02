package edu.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sistema.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
