package co.edu.unbosque.service;

import co.edu.unbosque.entity.InventarioFicha;
import co.edu.unbosque.repository.InventarioFichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioFichaService {

    @Autowired
    private InventarioFichaRepository inventarioFichaRepository;

    public List<InventarioFicha> findAll() {
        return inventarioFichaRepository.findAll();
    }

    public Optional<InventarioFicha> findById(Integer id) {
        return inventarioFichaRepository.findById(id);
    }

    public InventarioFicha save(InventarioFicha inventarioFicha) {
        return inventarioFichaRepository.save(inventarioFicha);
    }

    public InventarioFicha updateById(Integer id, InventarioFicha inventarioDetails) {
        Optional<InventarioFicha> optionalInventario = inventarioFichaRepository.findById(id);
        if (optionalInventario.isPresent()) {
            InventarioFicha inventario = optionalInventario.get();
            inventario.setNombreBoveda(inventarioDetails.getNombreBoveda());
            inventario.setSaldo(inventarioDetails.getSaldo());
            return inventarioFichaRepository.save(inventario);
        }
        return null;
    }

    public void deleteById(Integer id) {
        inventarioFichaRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return inventarioFichaRepository.existsById(id);
    }
}