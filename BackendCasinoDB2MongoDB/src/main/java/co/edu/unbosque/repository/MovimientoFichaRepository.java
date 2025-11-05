package co.edu.unbosque.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.documents.MovimientoFicha;

@Repository
public interface MovimientoFichaRepository extends MongoRepository<MovimientoFicha, String> {
}