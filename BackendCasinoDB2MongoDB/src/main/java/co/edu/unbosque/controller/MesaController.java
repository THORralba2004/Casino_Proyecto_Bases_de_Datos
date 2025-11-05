package co.edu.unbosque.controller;

import co.edu.unbosque.documents.Mesa;
import co.edu.unbosque.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/mesas")
@CrossOrigin(origins = "*")
public class MesaController {
    
    @Autowired
    MesaService mesaService;
    
    @GetMapping
    public ArrayList<Mesa> findAll() {
        return mesaService.findAll();
    }
    
    @PostMapping
    public Mesa save(@RequestBody Mesa mesa) {
        return mesaService.save(mesa);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Mesa> findById(@PathVariable String id) {
        Optional<Mesa> mesa = mesaService.findById(id);
        return mesa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Mesa> updateById(@RequestBody Mesa request, @PathVariable String id) {
        Mesa mesa = mesaService.updateById(request, id);
        return mesa != null ? ResponseEntity.ok(mesa) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean deleted = mesaService.deleteById(id);
        return deleted ? ResponseEntity.ok("Mesa eliminada") : ResponseEntity.notFound().build();
    }
}