package co.edu.unbosque.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.documents.TipoJuego;
import co.edu.unbosque.service.TipoJuegoService;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/tipos-juego")
@CrossOrigin(origins = "*")
public class TipoJuegoController {

    @Autowired
    TipoJuegoService tipoJuegoService;

    @GetMapping
    public ArrayList<TipoJuego> getAll() {
        return tipoJuegoService.findAll();
    }

    @PostMapping
    public ResponseEntity<TipoJuego> save(@RequestBody TipoJuego tipoJuego) {
        TipoJuego savedTipoJuego = tipoJuegoService.save(tipoJuego);
        return new ResponseEntity<>(savedTipoJuego, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoJuego> getById(@PathVariable String id) {
        Optional<TipoJuego> tipoJuego = tipoJuegoService.findById(id);
        return tipoJuego.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoJuego> update(@RequestBody TipoJuego tipoJuego, @PathVariable String id) {
        try {
            TipoJuego updatedTipoJuego = tipoJuegoService.updateById(tipoJuego, id);
            return new ResponseEntity<>(updatedTipoJuego, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        boolean deleted = tipoJuegoService.deleteById(id);
        if (deleted) {
            return new ResponseEntity<>("Tipo de juego eliminado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al eliminar tipo de juego", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}