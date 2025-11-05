package co.edu.unbosque.service;

import co.edu.unbosque.documents.Transaccion;
import co.edu.unbosque.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TransaccionService {
    
    @Autowired
    TransaccionRepository transaccionRepository;
    
    public ArrayList<Transaccion> findAll() {
        return (ArrayList<Transaccion>) transaccionRepository.findAll();
    }
    
    public Transaccion save(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }
    
    public Optional<Transaccion> findById(String id) {
        return transaccionRepository.findById(id);
    }
    
    public Transaccion updateById(Transaccion request, String id) {
        Optional<Transaccion> transaccionOpt = transaccionRepository.findById(id);
        if (transaccionOpt.isPresent()) {
            Transaccion transaccion = transaccionOpt.get();
            transaccion.setIdEmpleado(request.getIdEmpleado());
            transaccion.setIdMetodo(request.getIdMetodo());
            transaccion.setIdCliente(request.getIdCliente());
            transaccion.setFechaHora(request.getFechaHora());
            transaccion.setMonto(request.getMonto());
            transaccion.setTipo(request.getTipo());
            transaccion.setObservacion(request.getObservacion());
            return transaccionRepository.save(transaccion);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            transaccionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}