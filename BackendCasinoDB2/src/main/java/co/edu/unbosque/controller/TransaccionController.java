package co.edu.unbosque.controller;

import co.edu.unbosque.entity.Transaccion;
import co.edu.unbosque.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacciones")
@CrossOrigin(origins = "*")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    public List<Transaccion> findAll() {
        return transaccionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> findById(@PathVariable Integer id) {
        Optional<Transaccion> transaccion = transaccionService.findById(id);
        return transaccion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transaccion create(@RequestBody Transaccion transaccion) {
        return transaccionService.save(transaccion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaccion> update(@PathVariable Integer id, @RequestBody Transaccion transaccionDetails) {
        Transaccion updatedTransaccion = transaccionService.updateById(id, transaccionDetails);
        return updatedTransaccion != null ? ResponseEntity.ok(updatedTransaccion) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (transaccionService.existsById(id)) {
            transaccionService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}