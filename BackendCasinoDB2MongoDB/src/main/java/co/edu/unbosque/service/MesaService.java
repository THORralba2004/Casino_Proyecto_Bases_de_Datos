package co.edu.unbosque.service;

import co.edu.unbosque.documents.Mesa;
import co.edu.unbosque.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MesaService {
    
    @Autowired
    MesaRepository mesaRepository;
    
    public ArrayList<Mesa> findAll() {
        return (ArrayList<Mesa>) mesaRepository.findAll();
    }
    
    public Mesa save(Mesa mesa) {
        return mesaRepository.save(mesa);
    }
    
    public Optional<Mesa> findById(String id) {
        return mesaRepository.findById(id);
    }
    
    public Mesa updateById(Mesa request, String id) {
        Optional<Mesa> mesaOpt = mesaRepository.findById(id);
        if (mesaOpt.isPresent()) {
            Mesa mesa = mesaOpt.get();
            mesa.setNroMesa(request.getNroMesa());
            mesa.setLimiteJugadores(request.getLimiteJugadores());
            mesa.setIdEvento(request.getIdEvento());
            return mesaRepository.save(mesa);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            mesaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}