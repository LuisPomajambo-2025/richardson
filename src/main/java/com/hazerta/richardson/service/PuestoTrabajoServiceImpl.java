package com.hazerta.richardson.service;

import com.hazerta.richardson.entity.PuestosTrabajo;
import com.hazerta.richardson.repository.IPuestosTrabajoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PuestoTrabajoServiceImpl implements IPuestoTrabajoService{
    @Autowired
    private IPuestosTrabajoRepo puestosTrabajoRepo;

    @Override
    public List<PuestosTrabajo> obtenerTodosLosPuestosTrabajo() {
        return puestosTrabajoRepo.findAll();
    }

    @Override
    public PuestosTrabajo obtenerPuestoTrabajoPorId(int id) {
        return puestosTrabajoRepo.findById(id).orElse(null);
    }

    @Override
    public PuestosTrabajo crearPuestoTrabajo(PuestosTrabajo puestoTrabajo) {
        return puestosTrabajoRepo.save(puestoTrabajo);
    }

    @Override
    public PuestosTrabajo actualizarPuestoTrabajo(PuestosTrabajo puestoTrabajo) {
        return puestosTrabajoRepo.save(puestoTrabajo);
    }

    @Override
    public void eliminarPuestoTrabajo(int id) {
        puestosTrabajoRepo.deleteById(id);
    }
}
