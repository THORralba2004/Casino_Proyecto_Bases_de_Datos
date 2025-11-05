package co.edu.unbosque.service;

import co.edu.unbosque.entity.EventoJuego;
import co.edu.unbosque.repository.EventoJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventoJuegoService {

    @Autowired
    private EventoJuegoRepository eventoJuegoRepository;

    public List<EventoJuego> findAll() {
        return eventoJuegoRepository.findAll();
    }

    public Optional<EventoJuego> findById(Integer id) {
        return eventoJuegoRepository.findById(id);
    }

    public EventoJuego save(EventoJuego eventoJuego) {
        return eventoJuegoRepository.save(eventoJuego);
    }

    public EventoJuego updateById(Integer id, EventoJuego eventoJuegoDetails) {
        Optional<EventoJuego> optionalEventoJuego = eventoJuegoRepository.findById(id);
        if (optionalEventoJuego.isPresent()) {
            EventoJuego eventoJuego = optionalEventoJuego.get();
            eventoJuego.setIdTipoJuego(eventoJuegoDetails.getIdTipoJuego());
            eventoJuego.setFechaAlta(eventoJuegoDetails.getFechaAlta());
            eventoJuego.setEstado(eventoJuegoDetails.getEstado());
            return eventoJuegoRepository.save(eventoJuego);
        }
        return null;
    }

    public void deleteById(Integer id) {
        eventoJuegoRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return eventoJuegoRepository.existsById(id);
    }
}