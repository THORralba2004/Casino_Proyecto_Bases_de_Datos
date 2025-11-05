package co.edu.unbosque.controller;

import co.edu.unbosque.entity.Maquina;
import co.edu.unbosque.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/maquinas")
@CrossOrigin(origins = "*")
public class MaquinaController {

    @Autowired
    private MaquinaService maquinaService;

    @GetMapping
    public List<Maquina> findAll() {
        return maquinaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maquina> findById(@PathVariable Integer id) {
        Optional<Maquina> maquina = maquinaService.findById(id);
        return maquina.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Maquina create(@RequestBody Maquina maquina) {
        return maquinaService.save(maquina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maquina> update(@PathVariable Integer id, @RequestBody Maquina maquinaDetails) {
        Maquina updatedMaquina = maquinaService.updateById(id, maquinaDetails);
        return updatedMaquina != null ? ResponseEntity.ok(updatedMaquina) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (maquinaService.existsById(id)) {
            maquinaService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}