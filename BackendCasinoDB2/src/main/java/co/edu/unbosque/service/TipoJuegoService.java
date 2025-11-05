package co.edu.unbosque.service;

import co.edu.unbosque.entity.TipoJuego;
import co.edu.unbosque.repository.TipoJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TipoJuegoService {

    @Autowired
    private TipoJuegoRepository tipoJuegoRepository;

    public List<TipoJuego> findAll() {
        return tipoJuegoRepository.findAll();
    }

    public Optional<TipoJuego> findById(Integer id) {
        return tipoJuegoRepository.findById(id);
    }

    public TipoJuego save(TipoJuego tipoJuego) {
        return tipoJuegoRepository.save(tipoJuego);
    }

    public TipoJuego updateById(Integer id, TipoJuego tipoJuegoDetails) {
        Optional<TipoJuego> optionalTipoJuego = tipoJuegoRepository.findById(id);
        if (optionalTipoJuego.isPresent()) {
            TipoJuego tipoJuego = optionalTipoJuego.get();
            tipoJuego.setNombre(tipoJuegoDetails.getNombre());
            return tipoJuegoRepository.save(tipoJuego);
        }
        return null;
    }

    public void deleteById(Integer id) {
        tipoJuegoRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return tipoJuegoRepository.existsById(id);
    }
}