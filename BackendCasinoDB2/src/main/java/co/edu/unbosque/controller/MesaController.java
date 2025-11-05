package co.edu.unbosque.controller;

import co.edu.unbosque.entity.Mesa;
import co.edu.unbosque.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mesas")
@CrossOrigin(origins = "*")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public List<Mesa> findAll() {
        return mesaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> findById(@PathVariable Integer id) {
        Optional<Mesa> mesa = mesaService.findById(id);
        return mesa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mesa create(@RequestBody Mesa mesa) {
        return mesaService.save(mesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> update(@PathVariable Integer id, @RequestBody Mesa mesaDetails) {
        Mesa updatedMesa = mesaService.updateById(id, mesaDetails);
        return updatedMesa != null ? ResponseEntity.ok(updatedMesa) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (mesaService.existsById(id)) {
            mesaService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}