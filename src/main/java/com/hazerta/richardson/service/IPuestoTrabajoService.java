package com.hazerta.richardson.service;

import com.hazerta.richardson.entity.PuestosTrabajo;
import org.springframework.beans.factory.ListableBeanFactory;

import java.util.List;

public interface IPuestoTrabajoService {
    List<PuestosTrabajo> obtenerTodosLosPuestosTrabajo();
    PuestosTrabajo obtenerPuestoTrabajoPorId(int id);
    PuestosTrabajo crearPuestoTrabajo(PuestosTrabajo puestoTrabajo);
    PuestosTrabajo actualizarPuestoTrabajo(PuestosTrabajo puestoTrabajo);
    void eliminarPuestoTrabajo(int id);
}
