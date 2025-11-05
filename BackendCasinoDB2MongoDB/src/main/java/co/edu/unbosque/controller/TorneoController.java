package co.edu.unbosque.controller;

import co.edu.unbosque.documents.Torneo;
import co.edu.unbosque.service.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/torneos")
@CrossOrigin(origins = "*")
public class TorneoController {
    
    @Autowired
    TorneoService torneoService;
    
    @GetMapping
    public ArrayList<Torneo> findAll() {
        return torneoService.findAll();
    }
    
    @PostMapping
    public Torneo save(@RequestBody Torneo torneo) {
        return torneoService.save(torneo);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Torneo> findById(@PathVariable String id) {
        Optional<Torneo> torneo = torneoService.findById(id);
        return torneo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Torneo> updateById(@RequestBody Torneo request, @PathVariable String id) {
        Torneo torneo = torneoService.updateById(request, id);
        return torneo != null ? ResponseEntity.ok(torneo) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean deleted = torneoService.deleteById(id);
        return deleted ? ResponseEntity.ok("Torneo eliminado") : ResponseEntity.notFound().build();
    }
}