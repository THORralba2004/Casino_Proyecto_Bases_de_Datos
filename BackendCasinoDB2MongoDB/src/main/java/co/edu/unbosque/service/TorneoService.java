package co.edu.unbosque.service;

import co.edu.unbosque.documents.Torneo;
import co.edu.unbosque.repository.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TorneoService {
    
    @Autowired
    TorneoRepository torneoRepository;
    
    public ArrayList<Torneo> findAll() {
        return (ArrayList<Torneo>) torneoRepository.findAll();
    }
    
    public Torneo save(Torneo torneo) {
        return torneoRepository.save(torneo);
    }
    
    public Optional<Torneo> findById(String id) {
        return torneoRepository.findById(id);
    }
    
    public Torneo updateById(Torneo request, String id) {
        Optional<Torneo> torneoOpt = torneoRepository.findById(id);
        if (torneoOpt.isPresent()) {
            Torneo torneo = torneoOpt.get();
            torneo.setNombre(request.getNombre());
            torneo.setFechaInicio(request.getFechaInicio());
            torneo.setFechaFin(request.getFechaFin());
            torneo.setBuyIn(request.getBuyIn());
            torneo.setPremio(request.getPremio());
            torneo.setIdEvento(request.getIdEvento());
            return torneoRepository.save(torneo);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            torneoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}