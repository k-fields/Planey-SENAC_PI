package com.br.senac.planey.application.controller;

import com.br.senac.planey.domain.dto.DespesaDto;
import com.br.senac.planey.domain.service.DespesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${host.v1}")
public class DespesaController {

    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @GetMapping("/despesa")
    public List<DespesaDto> getDespesas() {
        return despesaService.listarDespesas();
    }

    @GetMapping("/despesa/{id}")
    public DespesaDto getDespesaById(@PathVariable Long id) {
        return despesaService.listarDespesaPorId(id);
    }

    @PostMapping("/despesa")
    public ResponseEntity<DespesaDto> criarDespesa(@RequestBody DespesaDto despesa) {
        return new ResponseEntity<>(despesaService.criarDespesa(despesa), HttpStatus.CREATED);
    }

    @DeleteMapping("/despesa/{id}")
    public ResponseEntity<String> deleteDespesa(@PathVariable Long id) {
        despesaService.deletarDespesa(id);
        return new ResponseEntity<>("Id " + id + " deletado com sucesso.", HttpStatus.OK);
    }

    @PutMapping("/despesa/{id}")
    public ResponseEntity<DespesaDto> atualizarDespesa(@RequestBody DespesaDto despesa, @PathVariable Long id) {
        return new ResponseEntity<>(despesaService.atualizarDespesa(despesa, id), HttpStatus.OK);
    }

}
