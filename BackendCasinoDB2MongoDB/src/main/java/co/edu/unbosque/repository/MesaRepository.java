package co.edu.unbosque.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.documents.Mesa;

@Repository
public interface MesaRepository extends MongoRepository<Mesa, String> {
}