package co.edu.unbosque.controller;

import co.edu.unbosque.documents.InscripcionTorneo;
import co.edu.unbosque.service.InscripcionTorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/inscripciones-torneo")
@CrossOrigin(origins = "*")
public class InscripcionTorneoController {
    
    @Autowired
    InscripcionTorneoService inscripcionTorneoService;
    
    @GetMapping
    public ArrayList<InscripcionTorneo> findAll() {
        return inscripcionTorneoService.findAll();
    }
    
    @PostMapping
    public InscripcionTorneo save(@RequestBody InscripcionTorneo inscripcionTorneo) {
        return inscripcionTorneoService.save(inscripcionTorneo);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InscripcionTorneo> findById(@PathVariable String id) {
        Optional<InscripcionTorneo> inscripcionTorneo = inscripcionTorneoService.findById(id);
        return inscripcionTorneo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InscripcionTorneo> updateById(@RequestBody InscripcionTorneo request, @PathVariable String id) {
        InscripcionTorneo inscripcionTorneo = inscripcionTorneoService.updateById(request, id);
        return inscripcionTorneo != null ? ResponseEntity.ok(inscripcionTorneo) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean deleted = inscripcionTorneoService.deleteById(id);
        return deleted ? ResponseEntity.ok("Inscripción a torneo eliminada") : ResponseEntity.notFound().build();
    }
}