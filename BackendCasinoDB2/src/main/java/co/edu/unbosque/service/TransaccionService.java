package co.edu.unbosque.service;

import co.edu.unbosque.entity.Transaccion;
import co.edu.unbosque.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    public List<Transaccion> findAll() {
        return transaccionRepository.findAll();
    }

    public Optional<Transaccion> findById(Integer id) {
        return transaccionRepository.findById(id);
    }

    public Transaccion save(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public Transaccion updateById(Integer id, Transaccion transaccionDetails) {
        Optional<Transaccion> optionalTransaccion = transaccionRepository.findById(id);
        if (optionalTransaccion.isPresent()) {
            Transaccion transaccion = optionalTransaccion.get();
            transaccion.setIdEmpleado(transaccionDetails.getIdEmpleado());
            transaccion.setIdMetodo(transaccionDetails.getIdMetodo());
            transaccion.setIdCliente(transaccionDetails.getIdCliente());
            transaccion.setFechaHora(transaccionDetails.getFechaHora());
            transaccion.setMonto(transaccionDetails.getMonto());
            transaccion.setTipo(transaccionDetails.getTipo());
            transaccion.setObservacion(transaccionDetails.getObservacion());
            return transaccionRepository.save(transaccion);
        }
        return null;
    }

    public void deleteById(Integer id) {
        transaccionRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return transaccionRepository.existsById(id);
    }
}