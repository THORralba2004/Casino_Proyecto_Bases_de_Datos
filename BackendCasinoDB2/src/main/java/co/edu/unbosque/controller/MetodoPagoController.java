package co.edu.unbosque.controller;

import co.edu.unbosque.entity.MetodoPago;
import co.edu.unbosque.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/metodos-pago")
@CrossOrigin(origins = "*")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public List<MetodoPago> findAll() {
        return metodoPagoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> findById(@PathVariable Integer id) {
        Optional<MetodoPago> metodoPago = metodoPagoService.findById(id);
        return metodoPago.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MetodoPago create(@RequestBody MetodoPago metodoPago) {
        return metodoPagoService.save(metodoPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> update(@PathVariable Integer id, @RequestBody MetodoPago metodoPagoDetails) {
        MetodoPago updatedMetodoPago = metodoPagoService.updateById(id, metodoPagoDetails);
        return updatedMetodoPago != null ? ResponseEntity.ok(updatedMetodoPago) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (metodoPagoService.existsById(id)) {
            metodoPagoService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}