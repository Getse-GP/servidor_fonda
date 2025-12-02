package edu.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sistema.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
