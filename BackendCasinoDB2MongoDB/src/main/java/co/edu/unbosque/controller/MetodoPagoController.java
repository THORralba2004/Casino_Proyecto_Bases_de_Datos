package co.edu.unbosque.controller;

import co.edu.unbosque.documents.MetodoPago;
import co.edu.unbosque.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/metodos-pago")
@CrossOrigin(origins = "*")
public class MetodoPagoController {
    
    @Autowired
    MetodoPagoService metodoPagoService;
    
    @GetMapping
    public ArrayList<MetodoPago> findAll() {
        return metodoPagoService.findAll();
    }
    
    @PostMapping
    public MetodoPago save(@RequestBody MetodoPago metodoPago) {
        return metodoPagoService.save(metodoPago);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> findById(@PathVariable String id) {
        Optional<MetodoPago> metodoPago = metodoPagoService.findById(id);
        return metodoPago.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> updateById(@RequestBody MetodoPago request, @PathVariable String id) {
        MetodoPago metodoPago = metodoPagoService.updateById(request, id);
        return metodoPago != null ? ResponseEntity.ok(metodoPago) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean deleted = metodoPagoService.deleteById(id);
        return deleted ? ResponseEntity.ok("Método de pago eliminado") : ResponseEntity.notFound().build();
    }
}