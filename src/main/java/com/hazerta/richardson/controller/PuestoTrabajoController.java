package com.hazerta.richardson.controller;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.hazerta.richardson.dto.PuestoTrabajoDTO;
import com.hazerta.richardson.entity.PuestosTrabajo;
import com.hazerta.richardson.exception.NoEncontradoException;
import com.hazerta.richardson.service.IPuestoTrabajoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    @PostMapping
    public ResponseEntity<PuestoTrabajoDTO> crearUnPuestoTrabajo(@Valid @RequestBody PuestoTrabajoDTO puestoTrabajoDTO){
        PuestosTrabajo puestosTrabajo = modelMapper.map(puestoTrabajoDTO, PuestosTrabajo.class);
        PuestosTrabajo puestosTrabajoBBDD = puestoTrabajoService.crearPuestoTrabajo(puestosTrabajo);
        return new ResponseEntity<>(modelMapper.map(puestosTrabajoBBDD, PuestoTrabajoDTO.class), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<PuestoTrabajoDTO> modificarPuestoTrabajo(@Valid @RequestBody PuestoTrabajoDTO puestoTrabajoDTO){
        PuestosTrabajo puestosTrabajoBBDD = puestoTrabajoService.obtenerPuestoTrabajoPorId(puestoTrabajoDTO.getId());
        if(puestosTrabajoBBDD == null){
            throw new NoEncontradoException();
        }
        puestosTrabajoBBDD = puestoTrabajoService.actualizarPuestoTrabajo(modelMapper.map(puestoTrabajoDTO, PuestosTrabajo.class));
        return new ResponseEntity<>(modelMapper.map(puestosTrabajoBBDD, PuestoTrabajoDTO.class),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPuestoTrabajo(@PathVariable int id){
        PuestosTrabajo puestosTrabajoBBDD = puestoTrabajoService.obtenerPuestoTrabajoPorId(id);
        if(puestosTrabajoBBDD == null){
            throw new NoEncontradoException();
        }
        puestoTrabajoService.eliminarPuestoTrabajo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/hateos/{id}")
    public EntityModel<PuestoTrabajoDTO> consultarUnoH(@PathVariable("id") int id) {
        PuestosTrabajo puestoTrabajo = puestoTrabajoService.obtenerPuestoTrabajoPorId(id);
        if(puestoTrabajo==null){
            throw new NoEncontradoException();
        }
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).obtenerUnpuestoTrabajo(id));
        return EntityModel.of(modelMapper.map(puestoTrabajo,PuestoTrabajoDTO.class)).add(link1.withRel("puestos_trabajo-link"));
    }
    @GetMapping("/hateos")
    public CollectionModel<EntityModel<PuestoTrabajoDTO>> listarTodosH() {

        List<EntityModel<PuestoTrabajoDTO>> puestosHateo = puestoTrabajoService.obtenerTodosLosPuestosTrabajo()
                .stream()
                .map(p -> EntityModel.of(
                        modelMapper.map(p, PuestoTrabajoDTO.class),
                        linkTo(methodOn(this.getClass()).consultarUnoH(p.getId())).withSelfRel()
                ))
                .toList();

        if (puestosHateo.isEmpty()) {
            throw new NoEncontradoException();
        }

        return CollectionModel.of(puestosHateo, linkTo(methodOn(this.getClass()).listarTodosH()).withSelfRel());
        }
    }

