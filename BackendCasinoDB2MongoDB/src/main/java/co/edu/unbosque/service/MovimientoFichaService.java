package co.edu.unbosque.service;

import co.edu.unbosque.documents.MovimientoFicha;
import co.edu.unbosque.repository.MovimientoFichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MovimientoFichaService {
    
    @Autowired
    MovimientoFichaRepository movimientoFichaRepository;
    
    public ArrayList<MovimientoFicha> findAll() {
        return (ArrayList<MovimientoFicha>) movimientoFichaRepository.findAll();
    }
    
    public MovimientoFicha save(MovimientoFicha movimientoFicha) {
        return movimientoFichaRepository.save(movimientoFicha);
    }
    
    public Optional<MovimientoFicha> findById(String id) {
        return movimientoFichaRepository.findById(id);
    }
    
    public MovimientoFicha updateById(MovimientoFicha request, String id) {
        Optional<MovimientoFicha> movimientoFichaOpt = movimientoFichaRepository.findById(id);
        if (movimientoFichaOpt.isPresent()) {
            MovimientoFicha movimientoFicha = movimientoFichaOpt.get();
            movimientoFicha.setIdInventario(request.getIdInventario());
            movimientoFicha.setIdEmpleado(request.getIdEmpleado());
            movimientoFicha.setFechaHora(request.getFechaHora());
            movimientoFicha.setCantidad(request.getCantidad());
            movimientoFicha.setTipoMov(request.getTipoMov());
            movimientoFicha.setObservacion(request.getObservacion());
            return movimientoFichaRepository.save(movimientoFicha);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            movimientoFichaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}