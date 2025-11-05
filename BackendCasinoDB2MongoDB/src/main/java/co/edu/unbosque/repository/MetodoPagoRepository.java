package co.edu.unbosque.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.documents.MetodoPago;

@Repository
public interface MetodoPagoRepository extends MongoRepository<MetodoPago, String> {
}