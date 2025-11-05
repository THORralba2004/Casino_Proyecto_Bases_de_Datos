package co.edu.unbosque.controller;

import co.edu.unbosque.entity.TipoJuego;
import co.edu.unbosque.service.TipoJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipos-juego")
@CrossOrigin(origins = "*")
public class TipoJuegoController {

    @Autowired
    private TipoJuegoService tipoJuegoService;

    @GetMapping
    public List<TipoJuego> findAll() {
        return tipoJuegoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoJuego> findById(@PathVariable Integer id) {
        Optional<TipoJuego> tipoJuego = tipoJuegoService.findById(id);
        return tipoJuego.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoJuego create(@RequestBody TipoJuego tipoJuego) {
        return tipoJuegoService.save(tipoJuego);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoJuego> update(@PathVariable Integer id, @RequestBody TipoJuego tipoJuegoDetails) {
        TipoJuego updatedTipoJuego = tipoJuegoService.updateById(id, tipoJuegoDetails);
        return updatedTipoJuego != null ? ResponseEntity.ok(updatedTipoJuego) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (tipoJuegoService.existsById(id)) {
            tipoJuegoService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}