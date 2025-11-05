package co.edu.unbosque.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.documents.Empleado;

@Repository
public interface EmpleadoRepository extends MongoRepository<Empleado, String> {
}
