package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.documents.*;
import co.edu.unbosque.repository.*;

@Service
public class InventarioFichaService {
    
    @Autowired
    InventarioFichaRepository inventarioFichaRepository;
    
    public ArrayList<InventarioFicha> findAll() {
        return (ArrayList<InventarioFicha>) inventarioFichaRepository.findAll();
    }
    
    public InventarioFicha save(InventarioFicha inventarioFicha) {
        return inventarioFichaRepository.save(inventarioFicha);
    }
    
    public Optional<InventarioFicha> findById(String id) {
        return inventarioFichaRepository.findById(id);
    }
    
    public InventarioFicha updateById(InventarioFicha request, String id) {
        Optional<InventarioFicha> inventarioFichaOpt = inventarioFichaRepository.findById(id);
        if (inventarioFichaOpt.isPresent()) {
            InventarioFicha inventarioFicha = inventarioFichaOpt.get();
            inventarioFicha.setNombreBoveda(request.getNombreBoveda());
            inventarioFicha.setSaldo(request.getSaldo());
            return inventarioFichaRepository.save(inventarioFicha);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            inventarioFichaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
