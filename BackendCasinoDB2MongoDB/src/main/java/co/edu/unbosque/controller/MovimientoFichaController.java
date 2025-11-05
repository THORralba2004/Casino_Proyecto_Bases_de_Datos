package co.edu.unbosque.controller;

import co.edu.unbosque.documents.MovimientoFicha;
import co.edu.unbosque.service.MovimientoFichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos-ficha")
@CrossOrigin(origins = "*")
public class MovimientoFichaController {
    
    @Autowired
    MovimientoFichaService movimientoFichaService;
    
    @GetMapping
    public ArrayList<MovimientoFicha> findAll() {
        return movimientoFichaService.findAll();
    }
    
    @PostMapping
    public MovimientoFicha save(@RequestBody MovimientoFicha movimientoFicha) {
        return movimientoFichaService.save(movimientoFicha);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoFicha> findById(@PathVariable String id) {
        Optional<MovimientoFicha> movimientoFicha = movimientoFichaService.findById(id);
        return movimientoFicha.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MovimientoFicha> updateById(@RequestBody MovimientoFicha request, @PathVariable String id) {
        MovimientoFicha movimientoFicha = movimientoFichaService.updateById(request, id);
        return movimientoFicha != null ? ResponseEntity.ok(movimientoFicha) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean deleted = movimientoFichaService.deleteById(id);
        return deleted ? ResponseEntity.ok("Movimiento de ficha eliminado") : ResponseEntity.notFound().build();
    }
}