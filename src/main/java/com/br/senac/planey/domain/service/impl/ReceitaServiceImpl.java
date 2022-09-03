package com.br.senac.planey.domain.service.impl;

import com.br.senac.planey.application.exception.BusinessException;
import com.br.senac.planey.application.exception.RegistroNaoEncontradoException;
import com.br.senac.planey.domain.dto.ReceitaDto;
import com.br.senac.planey.domain.entity.Receita;
import com.br.senac.planey.domain.service.ReceitaService;
import com.br.senac.planey.infrastructure.repositories.ReceitaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceitaServiceImpl implements ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final ModelMapper modelMapper;

    public ReceitaServiceImpl(ReceitaRepository despesaRepository, ModelMapper modelMapper) {
        this.receitaRepository = despesaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ReceitaDto> listarReceitas() {
        List<Receita> listaDeReceitas = receitaRepository.findAll();

        if (listaDeReceitas.isEmpty())
            throw new RegistroNaoEncontradoException("Nenhuma receita encontrada.");

        return listaDeReceitas.stream().map(receita -> modelMapper.map(receita, ReceitaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReceitaDto listarReceitaPorId(long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Id " + id + " não encontrado."));
        return modelMapper.map(receita, ReceitaDto.class);
    }

    @Override
    public ReceitaDto criarReceita(ReceitaDto receitaDto) {
        if (receitaDto != null) {
            Receita receitaMap = modelMapper.map(receitaDto, Receita.class);
            return modelMapper.map(receitaRepository.save(receitaMap), ReceitaDto.class);
        }
        throw new BusinessException("Requisição inválida para criação de despesa.");
    }

    @Override
    public void deletarReceita(long id) {
        Receita despesa = receitaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Id " + id + " não encontrado."));
        receitaRepository.delete(despesa);
    }

    @Override
    public ReceitaDto atualizarReceita(ReceitaDto receitaDto, long id) {
        Receita receita = null;

        if (receitaDto != null) {
            receita = receitaRepository.findById(id)
                    .map(receitaDb -> {
                        receitaDb.setData(receitaDto.getData());
                        receitaDb.setTag(receitaDto.getTag());
                        receitaDb.setValor(receitaDto.getValor());
                        return receitaDb;
                    })
                    .orElseThrow(() -> new BusinessException("Id " + id + " não encontrado."));


            return modelMapper.map(receitaRepository.save(receita), ReceitaDto.class);
        }

        throw new BusinessException("Erro ao atualizar a receita.");
    }
}
