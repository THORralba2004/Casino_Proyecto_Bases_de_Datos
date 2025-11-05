package co.edu.unbosque.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.documents.InventarioFicha;

@Repository
public interface InventarioFichaRepository extends MongoRepository<InventarioFicha, String> {
}
