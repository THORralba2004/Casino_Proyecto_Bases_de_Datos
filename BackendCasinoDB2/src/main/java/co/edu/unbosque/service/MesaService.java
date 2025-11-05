package co.edu.unbosque.service;

import co.edu.unbosque.entity.Mesa;
import co.edu.unbosque.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> findById(Integer id) {
        return mesaRepository.findById(id);
    }

    public Mesa save(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Mesa updateById(Integer id, Mesa mesaDetails) {
        Optional<Mesa> optionalMesa = mesaRepository.findById(id);
        if (optionalMesa.isPresent()) {
            Mesa mesa = optionalMesa.get();
            mesa.setNroMesa(mesaDetails.getNroMesa());
            mesa.setLimiteJugadores(mesaDetails.getLimiteJugadores());
            return mesaRepository.save(mesa);
        }
        return null;
    }

    public void deleteById(Integer id) {
        mesaRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return mesaRepository.existsById(id);
    }
}