package com.hazerta.richardson.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "puestos_trabajo")
public class PuestosTrabajo {
    @Id()
    private int id;
    @Column(length = 60, nullable = false)
    private String nombre;
    private int nivel;
    private double sueldo;
}