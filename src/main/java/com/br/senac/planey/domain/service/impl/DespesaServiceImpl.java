package com.br.senac.planey.domain.service.impl;

import com.br.senac.planey.application.exception.BusinessException;
import com.br.senac.planey.domain.dto.DespesaDto;
import com.br.senac.planey.domain.entity.Despesa;
import com.br.senac.planey.domain.service.DespesaService;
import com.br.senac.planey.infrastructure.repositories.DespesaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespesaServiceImpl implements DespesaService {

    private final DespesaRepository despesaRepository;
    private final ModelMapper modelMapper;

    public DespesaServiceImpl(DespesaRepository despesaRepository, ModelMapper modelMapper) {
        this.despesaRepository = despesaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DespesaDto> listarDespesas() {
        List<Despesa> listaDeDespesas = despesaRepository.findAll();

        if (listaDeDespesas.isEmpty())
            throw new BusinessException("Nenhuma despesa encontrada.");

        return listaDeDespesas.stream().map(despesa -> modelMapper.map(despesa, DespesaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DespesaDto listarDespesaPorId(long id) {
        Despesa despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Id " + id + " não encontrado."));
        return modelMapper.map(despesa, DespesaDto.class);
    }

    @Override
    public DespesaDto criarDespesa(DespesaDto despesa) {

        if (despesa != null) {
            Despesa despesaMap = modelMapper.map(despesa, Despesa.class);
            return modelMapper.map(despesaRepository.save(despesaMap), DespesaDto.class);
        }
        throw new BusinessException("Requisição inválida para criação de despesa.");
    }

    @Override
    public void deletarDespesa(long id) {
        Despesa despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Id " + id + " não encontrado."));
        despesaRepository.delete(despesa);
    }

    @Override
    public DespesaDto atualizarDespesa(DespesaDto despesaDto, long id) {
        Despesa despesa = null;

        if (despesaDto != null) {
            despesa = despesaRepository.findById(id)
                    .map(despesaDb -> {
                        despesaDb.setData(despesaDto.getData());
                        despesaDb.setTag(despesaDto.getTag());
                        despesaDb.setValor(despesaDto.getValor());
                        return despesaDb;
                    })
                    .orElseThrow(() -> new BusinessException("Id " + id + " não encontrado."));


            return modelMapper.map(despesaRepository.save(despesa), DespesaDto.class);
        }

        throw new BusinessException("Erro ao atualizar a despesa.");
    }
}
