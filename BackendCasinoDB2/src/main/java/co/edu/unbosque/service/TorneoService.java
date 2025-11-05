package co.edu.unbosque.service;

import co.edu.unbosque.entity.Torneo;
import co.edu.unbosque.repository.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;

    public List<Torneo> findAll() {
        return torneoRepository.findAll();
    }

    public Optional<Torneo> findById(Integer id) {
        return torneoRepository.findById(id);
    }

    public Torneo save(Torneo torneo) {
        return torneoRepository.save(torneo);
    }

    public Torneo updateById(Integer id, Torneo torneoDetails) {
        Optional<Torneo> optionalTorneo = torneoRepository.findById(id);
        if (optionalTorneo.isPresent()) {
            Torneo torneo = optionalTorneo.get();
            torneo.setNombre(torneoDetails.getNombre());
            torneo.setFechaInicio(torneoDetails.getFechaInicio());
            torneo.setFechaFin(torneoDetails.getFechaFin());
            torneo.setBuyIn(torneoDetails.getBuyIn());
            torneo.setPremio(torneoDetails.getPremio());
            return torneoRepository.save(torneo);
        }
        return null;
    }

    public void deleteById(Integer id) {
        torneoRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return torneoRepository.existsById(id);
    }
}