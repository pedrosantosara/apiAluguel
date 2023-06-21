package com.example.apiAluguel.controller;

import com.example.apiAluguel.locacao.LocacaoRepository;
import com.example.apiAluguel.carro.Carro;
import com.example.apiAluguel.carro.CarroRepository;
import com.example.apiAluguel.cliente.Cliente;
import com.example.apiAluguel.cliente.ClienteRepository;
import com.example.apiAluguel.locacao.Locacao; 


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;



@RestController
@RequestMapping("/locacoes")
public class LocacaoController {

    private final LocacaoRepository locacaoRepository;
    private final CarroRepository carroRepository;
    private final ClienteRepository clienteRepository;

    public LocacaoController(LocacaoRepository locacaoRepository, CarroRepository carroRepository, ClienteRepository clienteRepository) {
        this.locacaoRepository = locacaoRepository;
        this.carroRepository = carroRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Locacao> listarLocacoes() {
        return locacaoRepository.findAll();
    }

    @PostMapping("/locacoes/alugar")
    public ResponseEntity<Locacao> alugarCarro(@RequestBody Locacao locacao) {
    Carro carro = carroRepository.findByModelo(locacao.getCarro().getModelo())
            .orElseThrow(() -> new IllegalArgumentException("Carro não encontrado"));

    Cliente cliente = clienteRepository.findByEmail(locacao.getCliente().getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

    locacao.setCarro(carro);
    locacao.setCliente(cliente);
    locacao.setDataInicio(LocalDateTime.now());

    Locacao novaLocacao = locacaoRepository.save(locacao);

    return ResponseEntity.ok(novaLocacao);
    }

}
