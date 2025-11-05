package co.edu.unbosque.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.documents.InscripcionTorneo;

@Repository
public interface InscripcionTorneoRepository extends MongoRepository<InscripcionTorneo, String> {
}
