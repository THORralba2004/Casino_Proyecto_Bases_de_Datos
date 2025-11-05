package co.edu.unbosque.controller;

import co.edu.unbosque.entity.Apuesta;
import co.edu.unbosque.service.ApuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apuestas")
@CrossOrigin(origins = "*")
public class ApuestaController {

    @Autowired
    private ApuestaService apuestaService;

    @GetMapping
    public List<Apuesta> findAll() {
        return apuestaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apuesta> findById(@PathVariable Integer id) {
        Optional<Apuesta> apuesta = apuestaService.findById(id);
        return apuesta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Apuesta create(@RequestBody Apuesta apuesta) {
        return apuestaService.save(apuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apuesta> update(@PathVariable Integer id, @RequestBody Apuesta apuestaDetails) {
        Apuesta updatedApuesta = apuestaService.updateById(id, apuestaDetails);
        return updatedApuesta != null ? ResponseEntity.ok(updatedApuesta) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (apuestaService.existsById(id)) {
            apuestaService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}