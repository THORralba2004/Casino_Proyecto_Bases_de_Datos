package co.edu.unbosque.controller;

import co.edu.unbosque.documents.Empleado;
import co.edu.unbosque.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {
    
    @Autowired
    EmpleadoService empleadoService;
    
    @GetMapping
    public ArrayList<Empleado> findAll() {
        return empleadoService.findAll();
    }
    
    @PostMapping
    public Empleado save(@RequestBody Empleado empleado) {
        return empleadoService.save(empleado);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> findById(@PathVariable String id) {
        Optional<Empleado> empleado = empleadoService.findById(id);
        return empleado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateById(@RequestBody Empleado request, @PathVariable String id) {
        Empleado empleado = empleadoService.updateById(request, id);
        return empleado != null ? ResponseEntity.ok(empleado) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean deleted = empleadoService.deleteById(id);
        return deleted ? ResponseEntity.ok("Empleado eliminado") : ResponseEntity.notFound().build();
    }
}