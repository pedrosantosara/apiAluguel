package com.example.apiAluguel.controller;

import com.example.apiAluguel.carro.CarroRepository;
import com.example.apiAluguel.carro.Carro; 
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroRepository carroRepository;

    public CarroController(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @GetMapping
    public List<Carro> listarCarros() {
        return carroRepository.findAll();
    }

    @PostMapping
    public Carro adicionarCarro(@RequestBody Carro carro) {
        return carroRepository.save(carro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
        Optional<Carro> carroOptional = carroRepository.findById(id);

        if (carroOptional.isPresent()) {
            Carro carro = carroOptional.get();
            carro.setMarca(carroAtualizado.getMarca());
            carro.setModelo(carroAtualizado.getModelo());

            carroRepository.save(carro);
            return ResponseEntity.ok(carro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCarro(@PathVariable Long id) {
        Optional<Carro> carroOptional = carroRepository.findById(id);

        if (carroOptional.isPresent()) {
            Carro carro = carroOptional.get();
            carroRepository.delete(carro);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
