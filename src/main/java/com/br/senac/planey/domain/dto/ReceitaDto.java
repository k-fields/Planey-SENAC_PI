package com.br.senac.planey.domain.dto;

import com.br.senac.planey.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaDto {

    private Long id;
    private Double valor;
    private LocalDateTime data;
    private String tag;
    private Usuario usuario;

}
