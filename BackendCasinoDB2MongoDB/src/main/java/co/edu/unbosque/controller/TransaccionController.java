package co.edu.unbosque.controller;

import co.edu.unbosque.documents.Transaccion;
import co.edu.unbosque.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/transacciones")
@CrossOrigin(origins = "*")
public class TransaccionController {
    
    @Autowired
    TransaccionService transaccionService;
    
    @GetMapping
    public ArrayList<Transaccion> findAll() {
        return transaccionService.findAll();
    }
    
    @PostMapping
    public Transaccion save(@RequestBody Transaccion transaccion) {
        return transaccionService.save(transaccion);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> findById(@PathVariable String id) {
        Optional<Transaccion> transaccion = transaccionService.findById(id);
        return transaccion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Transaccion> updateById(@RequestBody Transaccion request, @PathVariable String id) {
        Transaccion transaccion = transaccionService.updateById(request, id);
        return transaccion != null ? ResponseEntity.ok(transaccion) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean deleted = transaccionService.deleteById(id);
        return deleted ? ResponseEntity.ok("Transacción eliminada") : ResponseEntity.notFound().build();
    }
}