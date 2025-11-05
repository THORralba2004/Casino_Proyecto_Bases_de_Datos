package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
