package com.br.senac.planey.infrastructure.repositories;

import com.br.senac.planey.domain.dto.DespesaDto;
import com.br.senac.planey.domain.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {


}
