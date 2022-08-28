package com.br.senac.planey.application;

import com.br.senac.planey.domain.dto.DespesaDto;
import com.br.senac.planey.domain.entity.Despesa;
import com.br.senac.planey.infrastructure.repositories.DespesaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("${host.v1}")
public class DespesaController {

    private final DespesaRepository despesaRepository;

    public DespesaController(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    @GetMapping("/despesa")
    public List<DespesaDto> getDespesas() {
        return Collections.singletonList(DespesaDto.builder()
                .data(LocalDateTime.now())
                .tag("COMPRAS")
                .valor(150.90)
                .build());

    }

    @GetMapping("/despesa/{id}")
    public Despesa getDespesaById(@PathVariable Long id) {
        return despesaRepository.findById(id).get();
    }

    @PostMapping("/despesa")
    public DespesaDto criarDespesa(@RequestBody Despesa despesa) {
        Despesa despesaDto = despesaRepository.save(despesa);
        System.out.println("funcionou");
        return null;
    }

    @DeleteMapping("/despesa")
    public ResponseEntity<String> deleteDespesa() {
        return new ResponseEntity<>("Despesa deletada com sucesso.", HttpStatus.OK);
    }

}
