package co.edu.unbosque.service;

import co.edu.unbosque.documents.EventoJuego;
import co.edu.unbosque.repository.EventoJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class EventoJuegoService {
    
    @Autowired
    EventoJuegoRepository eventoJuegoRepository;
    
    public ArrayList<EventoJuego> findAll() {
        return (ArrayList<EventoJuego>) eventoJuegoRepository.findAll();
    }
    
    public EventoJuego save(EventoJuego eventoJuego) {
        return eventoJuegoRepository.save(eventoJuego);
    }
    
    public Optional<EventoJuego> findById(String id) {
        return eventoJuegoRepository.findById(id);
    }
    
    public EventoJuego updateById(EventoJuego request, String id) {
        Optional<EventoJuego> eventoJuegoOpt = eventoJuegoRepository.findById(id);
        if (eventoJuegoOpt.isPresent()) {
            EventoJuego eventoJuego = eventoJuegoOpt.get();
            eventoJuego.setTipoJuegoId(request.getTipoJuegoId());
            eventoJuego.setFechaAlta(request.getFechaAlta());
            eventoJuego.setEstado(request.getEstado());
            return eventoJuegoRepository.save(eventoJuego);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            eventoJuegoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}