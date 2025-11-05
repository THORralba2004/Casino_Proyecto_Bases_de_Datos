package co.edu.unbosque.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.documents.TipoJuego;

public interface TipoJuegoRepository extends MongoRepository<TipoJuego, String>{

}
