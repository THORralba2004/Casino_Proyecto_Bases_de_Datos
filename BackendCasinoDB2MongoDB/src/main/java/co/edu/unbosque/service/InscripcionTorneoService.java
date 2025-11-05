package co.edu.unbosque.service;

import co.edu.unbosque.documents.InscripcionTorneo;
import co.edu.unbosque.repository.InscripcionTorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class InscripcionTorneoService {
    
    @Autowired
    InscripcionTorneoRepository inscripcionTorneoRepository;
    
    public ArrayList<InscripcionTorneo> findAll() {
        return (ArrayList<InscripcionTorneo>) inscripcionTorneoRepository.findAll();
    }
    
    public InscripcionTorneo save(InscripcionTorneo inscripcionTorneo) {
        return inscripcionTorneoRepository.save(inscripcionTorneo);
    }
    
    public Optional<InscripcionTorneo> findById(String id) {
        return inscripcionTorneoRepository.findById(id);
    }
    
    public InscripcionTorneo updateById(InscripcionTorneo request, String id) {
        Optional<InscripcionTorneo> inscripcionTorneoOpt = inscripcionTorneoRepository.findById(id);
        if (inscripcionTorneoOpt.isPresent()) {
            InscripcionTorneo inscripcionTorneo = inscripcionTorneoOpt.get();
            inscripcionTorneo.setIdEvento(request.getIdEvento());
            inscripcionTorneo.setIdCliente(request.getIdCliente());
            inscripcionTorneo.setFechaInscripcion(request.getFechaInscripcion());
            inscripcionTorneo.setFeePagado(request.getFeePagado());
            return inscripcionTorneoRepository.save(inscripcionTorneo);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            inscripcionTorneoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}