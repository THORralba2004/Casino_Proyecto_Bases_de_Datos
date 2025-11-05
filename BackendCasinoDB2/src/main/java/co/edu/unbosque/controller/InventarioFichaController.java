package co.edu.unbosque.controller;

import co.edu.unbosque.entity.InventarioFicha;
import co.edu.unbosque.service.InventarioFichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventario-fichas")
@CrossOrigin(origins = "*")
public class InventarioFichaController {

    @Autowired
    private InventarioFichaService inventarioFichaService;

    @GetMapping
    public List<InventarioFicha> findAll() {
        return inventarioFichaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioFicha> findById(@PathVariable Integer id) {
        Optional<InventarioFicha> inventarioFicha = inventarioFichaService.findById(id);
        return inventarioFicha.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public InventarioFicha create(@RequestBody InventarioFicha inventarioFicha) {
        return inventarioFichaService.save(inventarioFicha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioFicha> update(@PathVariable Integer id, @RequestBody InventarioFicha inventarioDetails) {
        InventarioFicha updatedInventario = inventarioFichaService.updateById(id, inventarioDetails);
        return updatedInventario != null ? ResponseEntity.ok(updatedInventario) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (inventarioFichaService.existsById(id)) {
            inventarioFichaService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}