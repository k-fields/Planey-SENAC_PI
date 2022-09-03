package com.br.senac.planey.domain.service;

import com.br.senac.planey.domain.dto.DespesaDto;

import java.util.List;

public interface DespesaService {

    List<DespesaDto> listarDespesas();

    DespesaDto listarDespesaPorId(long id);

    DespesaDto criarDespesa(DespesaDto despesaDto);

    void deletarDespesa(long id);

    DespesaDto atualizarDespesa(DespesaDto despesaDto, long id);

}
