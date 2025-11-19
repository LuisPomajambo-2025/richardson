package com.hazerta.richardson.controller;

import com.hazerta.richardson.dto.PuestoTrabajoDTO;
import com.hazerta.richardson.entity.PuestosTrabajo;
import com.hazerta.richardson.service.IPuestoTrabajoService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/puestos_trabajo")
public class PuestoTrabajoController {
    @Autowired
    private IPuestoTrabajoService puestoTrabajoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PuestoTrabajoDTO>> obtenerTodosLosPuestosTrabajo() {
        List<PuestoTrabajoDTO> puestosTrabajoDTO = puestoTrabajoService.obtenerTodosLosPuestosTrabajo()
                .stream()
                .map(puesto -> modelMapper.map(puesto, PuestoTrabajoDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(puestosTrabajoDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuestoTrabajoDTO> obtenerUnpuestoTrabajo(@PathVariable int id){
        PuestosTrabajo puestoTrabajo = puestoTrabajoService.obtenerPuestoTrabajoPorId(id);
        return new ResponseEntity(modelMapper.map(puestoTrabajo, PuestoTrabajoDTO.class), HttpStatus.OK);
    }
}
