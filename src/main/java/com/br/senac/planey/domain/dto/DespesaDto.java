package com.br.senac.planey.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DespesaDto {

    private Long id;
    private Double valor;
    private LocalDateTime data;
    private String tag;

}
