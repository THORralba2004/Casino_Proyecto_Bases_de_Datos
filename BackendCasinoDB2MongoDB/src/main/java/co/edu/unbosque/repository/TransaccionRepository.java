package co.edu.unbosque.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.documents.Transaccion;

@Repository
public interface TransaccionRepository extends MongoRepository<Transaccion, String> {
}