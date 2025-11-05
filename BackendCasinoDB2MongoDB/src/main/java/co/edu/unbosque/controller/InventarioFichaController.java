package co.edu.unbosque.controller;

import co.edu.unbosque.documents.InventarioFicha;
import co.edu.unbosque.service.InventarioFichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/inventarios-ficha")
@CrossOrigin(origins = "*")
public class InventarioFichaController {
    
    @Autowired
    InventarioFichaService inventarioFichaService;
    
    @GetMapping
    public ArrayList<InventarioFicha> findAll() {
        return inventarioFichaService.findAll();
    }
    
    @PostMapping
    public InventarioFicha save(@RequestBody InventarioFicha inventarioFicha) {
        return inventarioFichaService.save(inventarioFicha);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InventarioFicha> findById(@PathVariable String id) {
        Optional<InventarioFicha> inventarioFicha = inventarioFichaService.findById(id);
        return inventarioFicha.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InventarioFicha> updateById(@RequestBody InventarioFicha request, @PathVariable String id) {
        InventarioFicha inventarioFicha = inventarioFichaService.updateById(request, id);
        return inventarioFicha != null ? ResponseEntity.ok(inventarioFicha) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean deleted = inventarioFichaService.deleteById(id);
        return deleted ? ResponseEntity.ok("Inventario de ficha eliminado") : ResponseEntity.notFound().build();
    }
}