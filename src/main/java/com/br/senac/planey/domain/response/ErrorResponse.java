package com.br.senac.planey.domain.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private String mensagem;
    private LocalDateTime dtHrOcorrencia;
}
