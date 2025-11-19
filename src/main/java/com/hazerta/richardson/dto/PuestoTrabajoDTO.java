package com.hazerta.richardson.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PuestoTrabajoDTO {
    private int id;
    private String nombre;
    private int nivel;
    private double sueldo;
}
