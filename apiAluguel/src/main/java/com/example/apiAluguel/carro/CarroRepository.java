package com.example.apiAluguel.carro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface CarroRepository extends JpaRepository<Carro, Long> {
    Optional<Carro> findByModelo(String modelo);
}