package co.edu.unbosque.service;

import co.edu.unbosque.documents.Maquina;
import co.edu.unbosque.repository.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MaquinaService {
    
    @Autowired
    MaquinaRepository maquinaRepository;
    
    public ArrayList<Maquina> findAll() {
        return (ArrayList<Maquina>) maquinaRepository.findAll();
    }
    
    public Maquina save(Maquina maquina) {
        return maquinaRepository.save(maquina);
    }
    
    public Optional<Maquina> findById(String id) {
        return maquinaRepository.findById(id);
    }
    
    public Maquina updateById(Maquina request, String id) {
        Optional<Maquina> maquinaOpt = maquinaRepository.findById(id);
        if (maquinaOpt.isPresent()) {
            Maquina maquina = maquinaOpt.get();
            maquina.setCodigoSerie(request.getCodigoSerie());
            maquina.setDenominacion(request.getDenominacion());
            maquina.setIdEvento(request.getIdEvento());
            return maquinaRepository.save(maquina);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            maquinaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}