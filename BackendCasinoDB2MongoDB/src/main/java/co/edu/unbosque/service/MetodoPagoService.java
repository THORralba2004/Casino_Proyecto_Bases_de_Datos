package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.documents.*;
import co.edu.unbosque.repository.*;

@Service
public class MetodoPagoService {
    
    @Autowired
    MetodoPagoRepository metodoPagoRepository;
    
    public ArrayList<MetodoPago> findAll() {
        return (ArrayList<MetodoPago>) metodoPagoRepository.findAll();
    }
    
    public MetodoPago save(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }
    
    public Optional<MetodoPago> findById(String id) {
        return metodoPagoRepository.findById(id);
    }
    
    public MetodoPago updateById(MetodoPago request, String id) {
        Optional<MetodoPago> metodoPagoOpt = metodoPagoRepository.findById(id);
        if (metodoPagoOpt.isPresent()) {
            MetodoPago metodoPago = metodoPagoOpt.get();
            metodoPago.setNombre(request.getNombre());
            return metodoPagoRepository.save(metodoPago);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            metodoPagoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
