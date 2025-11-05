package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer>{

}
