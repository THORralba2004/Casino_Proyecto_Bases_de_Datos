package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.documents.*;
import co.edu.unbosque.repository.*;

@Service
public class EmpleadoService {
    
    @Autowired
    EmpleadoRepository empleadoRepository;
    
    public ArrayList<Empleado> findAll() {
        return (ArrayList<Empleado>) empleadoRepository.findAll();
    }
    
    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }
    
    public Optional<Empleado> findById(String id) {
        return empleadoRepository.findById(id);
    }
    
    public Empleado updateById(Empleado request, String id) {
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(id);
        if (empleadoOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            empleado.setNombre(request.getNombre());
            empleado.setCargo(request.getCargo());
            empleado.setSalario(request.getSalario());
            empleado.setActivo(request.getActivo());
            return empleadoRepository.save(empleado);
        }
        return null;
    }
    
    public boolean deleteById(String id) {
        try {
            empleadoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}