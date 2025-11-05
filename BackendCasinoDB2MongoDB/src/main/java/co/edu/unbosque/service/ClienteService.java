package co.edu.unbosque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.documents.Cliente;
import co.edu.unbosque.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;
    
    public ArrayList<Cliente> findAll() {
        return (ArrayList<Cliente>) clienteRepository.findAll();
    }
    
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public Optional<Cliente> findById(String id) {
        return clienteRepository.findById(id);
    }
    
    public Cliente updateById(Cliente request, String id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setNombre(request.getNombre());
            cliente.setDocumento(request.getDocumento());
            cliente.setFechaRegistro(request.getFechaRegistro());
            cliente.setTelefono(request.getTelefono());
            cliente.setEmail(request.getEmail());
            return clienteRepository.save(cliente);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            clienteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}