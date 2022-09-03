package com.br.senac.planey.domain.service;

import com.br.senac.planey.domain.dto.ReceitaDto;

import java.util.List;

public interface ReceitaService {

    List<ReceitaDto> listarReceitas();

    ReceitaDto listarReceitaPorId(long id);

    ReceitaDto criarReceita(ReceitaDto despesaDto);

    void deletarReceita(long id);

    ReceitaDto atualizarReceita(ReceitaDto despesaDto, long id);

}
