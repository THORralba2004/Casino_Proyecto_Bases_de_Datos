package co.edu.unbosque.controller;

import co.edu.unbosque.entity.Torneo;
import co.edu.unbosque.service.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/torneos")
@CrossOrigin(origins = "*")
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    @GetMapping
    public List<Torneo> findAll() {
        return torneoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Torneo> findById(@PathVariable Integer id) {
        Optional<Torneo> torneo = torneoService.findById(id);
        return torneo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Torneo create(@RequestBody Torneo torneo) {
        return torneoService.save(torneo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Torneo> update(@PathVariable Integer id, @RequestBody Torneo torneoDetails) {
        Torneo updatedTorneo = torneoService.updateById(id, torneoDetails);
        return updatedTorneo != null ? ResponseEntity.ok(updatedTorneo) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (torneoService.existsById(id)) {
            torneoService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}