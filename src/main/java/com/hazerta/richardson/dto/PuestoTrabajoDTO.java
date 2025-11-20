package com.hazerta.richardson.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Normalized;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PuestoTrabajoDTO {

    private int id;
    @NotEmpty
    @Size(min = 3, max = 60)
    private String nombre;
    @NotNull
    private Integer nivel;
    private double sueldo;
}
