package co.edu.unbosque.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.documents.Apuesta;

@Repository
public interface ApuestaRepository extends MongoRepository<Apuesta, String> {
}
