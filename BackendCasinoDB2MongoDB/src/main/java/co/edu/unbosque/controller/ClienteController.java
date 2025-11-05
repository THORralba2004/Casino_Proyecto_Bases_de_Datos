package co.edu.unbosque.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.documents.Cliente;
import co.edu.unbosque.service.ClienteService;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    
    @Autowired
    ClienteService clienteService;
    
    @GetMapping
    public ArrayList<Cliente> findAll() {
        return clienteService.findAll();
    }
    
    @PostMapping
    public Cliente save(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable String id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateById(@RequestBody Cliente request, @PathVariable String id) {
        Cliente cliente = clienteService.updateById(request, id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean deleted = clienteService.deleteById(id);
        return deleted ? ResponseEntity.ok("Cliente eliminado") : ResponseEntity.notFound().build();
    }
}