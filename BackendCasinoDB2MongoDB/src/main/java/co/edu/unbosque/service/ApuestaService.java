package co.edu.unbosque.service;

import co.edu.unbosque.documents.Apuesta;
import co.edu.unbosque.repository.ApuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ApuestaService {
    
    @Autowired
    ApuestaRepository apuestaRepository;
    
    public ArrayList<Apuesta> findAll() {
        return (ArrayList<Apuesta>) apuestaRepository.findAll();
    }
    
    public Apuesta save(Apuesta apuesta) {
        return apuestaRepository.save(apuesta);
    }
    
    public Optional<Apuesta> findById(String id) {
        return apuestaRepository.findById(id);
    }
    
    public Apuesta updateById(Apuesta request, String id) {
        Optional<Apuesta> apuestaOpt = apuestaRepository.findById(id);
        if (apuestaOpt.isPresent()) {
            Apuesta apuesta = apuestaOpt.get();
            apuesta.setIdCliente(request.getIdCliente());
            apuesta.setIdEvento(request.getIdEvento());
            apuesta.setIdEmpleado(request.getIdEmpleado());
            apuesta.setFechaHora(request.getFechaHora());
            apuesta.setMonto(request.getMonto());
            apuesta.setResultado(request.getResultado());
            apuesta.setGananciaPerdida(request.getGananciaPerdida());
            apuesta.setEstado(request.getEstado());
            return apuestaRepository.save(apuesta);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            apuestaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}