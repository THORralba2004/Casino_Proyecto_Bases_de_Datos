package co.edu.unbosque.service;

import co.edu.unbosque.entity.MetodoPago;
import co.edu.unbosque.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPago> findAll() {
        return metodoPagoRepository.findAll();
    }

    public Optional<MetodoPago> findById(Integer id) {
        return metodoPagoRepository.findById(id);
    }

    public MetodoPago save(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    public MetodoPago updateById(Integer id, MetodoPago metodoPagoDetails) {
        Optional<MetodoPago> optionalMetodoPago = metodoPagoRepository.findById(id);
        if (optionalMetodoPago.isPresent()) {
            MetodoPago metodoPago = optionalMetodoPago.get();
            metodoPago.setNombre(metodoPagoDetails.getNombre());
            return metodoPagoRepository.save(metodoPago);
        }
        return null;
    }

    public void deleteById(Integer id) {
        metodoPagoRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return metodoPagoRepository.existsById(id);
    }
}