package co.edu.unbosque.controller;

import co.edu.unbosque.documents.EventoJuego;
import co.edu.unbosque.service.EventoJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/eventos-juego")
@CrossOrigin(origins = "*")
public class EventoJuegoController {
    
    @Autowired
    EventoJuegoService eventoJuegoService;
    
    @GetMapping
    public ArrayList<EventoJuego> findAll() {
        return eventoJuegoService.findAll();
    }
    
    @PostMapping
    public EventoJuego save(@RequestBody EventoJuego eventoJuego) {
        return eventoJuegoService.save(eventoJuego);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EventoJuego> findById(@PathVariable String id) {
        Optional<EventoJuego> eventoJuego = eventoJuegoService.findById(id);
        return eventoJuego.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EventoJuego> updateById(@RequestBody EventoJuego request, @PathVariable String id) {
        EventoJuego eventoJuego = eventoJuegoService.updateById(request, id);
        return eventoJuego != null ? ResponseEntity.ok(eventoJuego) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean deleted = eventoJuegoService.deleteById(id);
        return deleted ? ResponseEntity.ok("Evento de juego eliminado") : ResponseEntity.notFound().build();
    }
}