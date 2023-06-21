package com.example.apiAluguel.locacao;

import java.time.LocalDateTime;

import com.example.apiAluguel.carro.Carro;
import com.example.apiAluguel.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Carro carro;

    @ManyToOne
    private Cliente cliente;

    private LocalDateTime dataInicio;
    @JsonIgnore
    private LocalDateTime dataFim;

   
}