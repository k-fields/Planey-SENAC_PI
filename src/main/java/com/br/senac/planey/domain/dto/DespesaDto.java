package com.br.senac.planey.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DespesaDto {

    private String id;
    private Double valor;
    private LocalDateTime data;
    private String tag;

}
