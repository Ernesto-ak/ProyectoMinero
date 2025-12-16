package com.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persona_juridica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaJuridica {

    @Id
    @GeneratedValue
    private Integer id;

    private String ruc;
    private String rso;

    @OneToOne
    @JoinColumn(name = "id")
    private Usuario usuario;
}

