package co.edu.unbosque.controller;

import co.edu.unbosque.entity.MovimientoFicha;
import co.edu.unbosque.service.MovimientoFichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos-ficha")
@CrossOrigin(origins = "*")
public class MovimientoFichaController {

    @Autowired
    private MovimientoFichaService movimientoFichaService;

    @GetMapping
    public List<MovimientoFicha> findAll() {
        return movimientoFichaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoFicha> findById(@PathVariable Integer id) {
        Optional<MovimientoFicha> movimientoFicha = movimientoFichaService.findById(id);
        return movimientoFicha.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MovimientoFicha create(@RequestBody MovimientoFicha movimientoFicha) {
        return movimientoFichaService.save(movimientoFicha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoFicha> update(@PathVariable Integer id, @RequestBody MovimientoFicha movimientoDetails) {
        MovimientoFicha updatedMovimiento = movimientoFichaService.updateById(id, movimientoDetails);
        return updatedMovimiento != null ? ResponseEntity.ok(updatedMovimiento) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (movimientoFichaService.existsById(id)) {
            movimientoFichaService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}