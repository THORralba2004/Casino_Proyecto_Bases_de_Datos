package co.edu.unbosque.service;

import co.edu.unbosque.entity.MovimientoFicha;
import co.edu.unbosque.repository.MovimientoFichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoFichaService {

    @Autowired
    private MovimientoFichaRepository movimientoFichaRepository;

    public List<MovimientoFicha> findAll() {
        return movimientoFichaRepository.findAll();
    }

    public Optional<MovimientoFicha> findById(Integer id) {
        return movimientoFichaRepository.findById(id);
    }

    public MovimientoFicha save(MovimientoFicha movimientoFicha) {
        return movimientoFichaRepository.save(movimientoFicha);
    }

    public MovimientoFicha updateById(Integer id, MovimientoFicha movimientoDetails) {
        Optional<MovimientoFicha> optionalMovimiento = movimientoFichaRepository.findById(id);
        if (optionalMovimiento.isPresent()) {
            MovimientoFicha movimiento = optionalMovimiento.get();
            movimiento.setIdInventario(movimientoDetails.getIdInventario());
            movimiento.setIdEmpleado(movimientoDetails.getIdEmpleado());
            movimiento.setFechaHora(movimientoDetails.getFechaHora());
            movimiento.setCantidad(movimientoDetails.getCantidad());
            movimiento.setTipoMov(movimientoDetails.getTipoMov());
            movimiento.setObservacion(movimientoDetails.getObservacion());
            return movimientoFichaRepository.save(movimiento);
        }
        return null;
    }

    public void deleteById(Integer id) {
        movimientoFichaRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return movimientoFichaRepository.existsById(id);
    }
}