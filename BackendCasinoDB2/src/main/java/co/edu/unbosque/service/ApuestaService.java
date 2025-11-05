package co.edu.unbosque.service;

import co.edu.unbosque.entity.Apuesta;
import co.edu.unbosque.repository.ApuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ApuestaService {

    @Autowired
    private ApuestaRepository apuestaRepository;

    public List<Apuesta> findAll() {
        return apuestaRepository.findAll();
    }

    public Optional<Apuesta> findById(Integer id) {
        return apuestaRepository.findById(id);
    }

    public Apuesta save(Apuesta apuesta) {
        return apuestaRepository.save(apuesta);
    }

    public Apuesta updateById(Integer id, Apuesta apuestaDetails) {
        Optional<Apuesta> optionalApuesta = apuestaRepository.findById(id);
        if (optionalApuesta.isPresent()) {
            Apuesta apuesta = optionalApuesta.get();
            apuesta.setIdCliente(apuestaDetails.getIdCliente());
            apuesta.setIdEvento(apuestaDetails.getIdEvento());
            apuesta.setIdEmpleado(apuestaDetails.getIdEmpleado());
            apuesta.setFechaHora(apuestaDetails.getFechaHora());
            apuesta.setMonto(apuestaDetails.getMonto());
            apuesta.setResultado(apuestaDetails.getResultado());
            apuesta.setGananciaPerdida(apuestaDetails.getGananciaPerdida());
            apuesta.setEstado(apuestaDetails.getEstado());
            return apuestaRepository.save(apuesta);
        }
        return null;
    }

    public void deleteById(Integer id) {
        apuestaRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return apuestaRepository.existsById(id);
    }
}