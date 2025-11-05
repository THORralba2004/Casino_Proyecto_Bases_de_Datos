package co.edu.unbosque.controller;

import co.edu.unbosque.entity.EventoJuego;
import co.edu.unbosque.service.EventoJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/eventos-juego")
@CrossOrigin(origins = "*")
public class EventoJuegoController {

    @Autowired
    private EventoJuegoService eventoJuegoService;

    // ✅ GET: Devolver todos los eventos
    @GetMapping
    public List<EventoJuego> findAll() {
        return eventoJuegoService.findAll();
    }

    // ✅ GET individual
    @GetMapping("/{id}")
    public ResponseEntity<EventoJuego> findById(@PathVariable Integer id) {
        Optional<EventoJuego> eventoOpt = eventoJuegoService.findById(id);
        return eventoOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // ✅ POST: Crear nuevo evento
    @PostMapping
    public EventoJuego create(@RequestBody EventoJuego eventoJuego) {
        return eventoJuegoService.save(eventoJuego);
    }

    // ✅ PUT: Actualizar evento existente
    @PutMapping("/{id}")
    public ResponseEntity<EventoJuego> update(@PathVariable Integer id, @RequestBody EventoJuego eventoJuegoDetails) {
        EventoJuego updatedEventoJuego = eventoJuegoService.updateById(id, eventoJuegoDetails);
        return updatedEventoJuego != null ? ResponseEntity.ok(updatedEventoJuego) : ResponseEntity.notFound().build();
    }

    // ✅ DELETE: Eliminar evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (eventoJuegoService.existsById(id)) {
            eventoJuegoService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}