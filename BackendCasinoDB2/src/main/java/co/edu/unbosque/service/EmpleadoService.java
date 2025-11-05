package co.edu.unbosque.service;

import co.edu.unbosque.entity.Empleado;
import co.edu.unbosque.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> findById(Integer id) {
        return empleadoRepository.findById(id);
    }

    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado updateById(Integer id, Empleado empleadoDetails) {
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);
        if (optionalEmpleado.isPresent()) {
            Empleado empleado = optionalEmpleado.get();
            empleado.setNombre(empleadoDetails.getNombre());
            empleado.setCargo(empleadoDetails.getCargo());
            empleado.setActivo(empleadoDetails.getActivo());
            return empleadoRepository.save(empleado);
        }
        return null;
    }

    public void deleteById(Integer id) {
        empleadoRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return empleadoRepository.existsById(id);
    }
}