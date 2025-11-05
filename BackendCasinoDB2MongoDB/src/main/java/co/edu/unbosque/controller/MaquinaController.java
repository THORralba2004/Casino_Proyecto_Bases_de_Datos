package co.edu.unbosque.controller;

import co.edu.unbosque.documents.Maquina;
import co.edu.unbosque.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/maquinas")
@CrossOrigin(origins = "*")
public class MaquinaController {
    
    @Autowired
    MaquinaService maquinaService;
    
    @GetMapping
    public ArrayList<Maquina> findAll() {
        return maquinaService.findAll();
    }
    
    @PostMapping
    public Maquina save(@RequestBody Maquina maquina) {
        return maquinaService.save(maquina);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Maquina> findById(@PathVariable String id) {
        Optional<Maquina> maquina = maquinaService.findById(id);
        return maquina.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Maquina> updateById(@RequestBody Maquina request, @PathVariable String id) {
        Maquina maquina = maquinaService.updateById(request, id);
        return maquina != null ? ResponseEntity.ok(maquina) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean deleted = maquinaService.deleteById(id);
        return deleted ? ResponseEntity.ok("Máquina eliminada") : ResponseEntity.notFound().build();
    }
}