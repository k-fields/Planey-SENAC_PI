package com.br.senac.planey.infrastructure.repositories;

import com.br.senac.planey.domain.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
