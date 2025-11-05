package co.edu.unbosque.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.documents.TipoJuego;
import co.edu.unbosque.repository.TipoJuegoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoJuegoService {
    
    @Autowired
    TipoJuegoRepository tipoJuegoRepository;
    
    public ArrayList<TipoJuego> findAll() {
        return (ArrayList<TipoJuego>) tipoJuegoRepository.findAll();
    }
    
    public TipoJuego save(TipoJuego tipoJuego) {
        return tipoJuegoRepository.save(tipoJuego);
    }
    
    public Optional<TipoJuego> findById(String id) {
        return tipoJuegoRepository.findById(id);
    }
    
    public TipoJuego updateById(TipoJuego request, String id) {
        Optional<TipoJuego> tipoJuegoOpt = tipoJuegoRepository.findById(id);
        if (tipoJuegoOpt.isPresent()) {
            TipoJuego tipoJuego = tipoJuegoOpt.get();
            tipoJuego.setNombre(request.getNombre());
            return tipoJuegoRepository.save(tipoJuego);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            tipoJuegoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}