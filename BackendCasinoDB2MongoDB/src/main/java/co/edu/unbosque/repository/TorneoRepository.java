package co.edu.unbosque.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.documents.Torneo;

@Repository
public interface TorneoRepository extends MongoRepository<Torneo, String> {
}
