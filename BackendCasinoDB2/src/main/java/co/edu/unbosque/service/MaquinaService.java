package co.edu.unbosque.service;

import co.edu.unbosque.entity.Maquina;
import co.edu.unbosque.repository.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaquinaService {

    @Autowired
    private MaquinaRepository maquinaRepository;

    public List<Maquina> findAll() {
        return maquinaRepository.findAll();
    }

    public Optional<Maquina> findById(Integer id) {
        return maquinaRepository.findById(id);
    }

    public Maquina save(Maquina maquina) {
        return maquinaRepository.save(maquina);
    }

    public Maquina updateById(Integer id, Maquina maquinaDetails) {
        Optional<Maquina> optionalMaquina = maquinaRepository.findById(id);
        if (optionalMaquina.isPresent()) {
            Maquina maquina = optionalMaquina.get();
            maquina.setCodigoSerie(maquinaDetails.getCodigoSerie());
            maquina.setDenominacion(maquinaDetails.getDenominacion());
            return maquinaRepository.save(maquina);
        }
        return null;
    }

    public void deleteById(Integer id) {
        maquinaRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return maquinaRepository.existsById(id);
    }
}