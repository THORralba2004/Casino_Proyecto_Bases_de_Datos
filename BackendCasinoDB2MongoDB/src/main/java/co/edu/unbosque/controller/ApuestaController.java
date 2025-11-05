package co.edu.unbosque.controller;

import co.edu.unbosque.documents.Apuesta;
import co.edu.unbosque.service.ApuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/apuestas")
@CrossOrigin(origins = "*")
public class ApuestaController {
    
    @Autowired
    ApuestaService apuestaService;
    
    @GetMapping
    public ArrayList<Apuesta> findAll() {
        return apuestaService.findAll();
    }
    
    @PostMapping
    public Apuesta save(@RequestBody Apuesta apuesta) {
        return apuestaService.save(apuesta);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Apuesta> findById(@PathVariable String id) {
        Optional<Apuesta> apuesta = apuestaService.findById(id);
        return apuesta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Apuesta> updateById(@RequestBody Apuesta request, @PathVariable String id) {
        Apuesta apuesta = apuestaService.updateById(request, id);
        return apuesta != null ? ResponseEntity.ok(apuesta) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean deleted = apuestaService.deleteById(id);
        return deleted ? ResponseEntity.ok("Apuesta eliminada") : ResponseEntity.notFound().build();
    }
}