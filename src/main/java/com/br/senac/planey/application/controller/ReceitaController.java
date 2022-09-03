package com.br.senac.planey.application.controller;

import com.br.senac.planey.domain.dto.ReceitaDto;
import com.br.senac.planey.domain.service.ReceitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${host.v1}")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping("/receita")
    public List<ReceitaDto> getDespesas() {
        return receitaService.listarReceitas();
    }

    @GetMapping("/receita/{id}")
    public ReceitaDto getDespesaById(@PathVariable Long id) {
        return receitaService.listarReceitaPorId(id);
    }

    @PostMapping("/receita")
    public ResponseEntity<ReceitaDto> criarReceita(@RequestBody ReceitaDto receitaDto) {
        return new ResponseEntity<>(receitaService.criarReceita(receitaDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/receita/{id}")
    public ResponseEntity<String> deleteReceita(@PathVariable Long id) {
        receitaService.deletarReceita(id);
        return new ResponseEntity<>("Id " + id + " deletado com sucesso.", HttpStatus.OK);
    }

    @PutMapping("/receita/{id}")
    public ResponseEntity<ReceitaDto> atualizarReceita(@RequestBody ReceitaDto receitaDto, @PathVariable Long id) {
        return new ResponseEntity<>(receitaService.atualizarReceita(receitaDto, id), HttpStatus.OK);
    }
}
