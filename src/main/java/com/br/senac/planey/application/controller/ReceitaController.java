package com.br.senac.planey.application.controller;

import com.br.senac.planey.domain.dto.ReceitaDto;
import com.br.senac.planey.domain.response.ErrorResponse;
import com.br.senac.planey.domain.service.ReceitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Receita", description = "Grupo de microsserviços para gerenciamento das receitas dos usuários")
@RestController
@RequestMapping("${host.v1}")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @Operation(summary = "Retorna uma lista com todas receitas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de receitas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReceitaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de negócio",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping("/receita")
    public List<ReceitaDto> getReceitas() {
        return receitaService.listarReceitas();
    }

    @Operation(summary = "Retorna uma receita por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receita por ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReceitaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de negócio",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping("/receita/{id}")
    public ReceitaDto getReceitaById(@Parameter(description = "ID de identificação da receita.")
                                     @PathVariable Long id) {
        return receitaService.listarReceitaPorId(id);
    }

    @Operation(summary = "Cria uma nova receita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Receita criada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReceitaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de negócio",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping("/receita")
    public ResponseEntity<ReceitaDto> criarReceita(@RequestBody ReceitaDto receitaDto) {
        return new ResponseEntity<>(receitaService.criarReceita(receitaDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Deleta uma receita através do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicação de receita excluída com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReceitaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de negócio",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @DeleteMapping("/receita/{id}")
    public ResponseEntity<String> deleteReceita(@Parameter(description = "ID de identificação da receita.")
                                                @PathVariable Long id) {
        receitaService.deletarReceita(id);
        return new ResponseEntity<>("Id " + id + " deletado com sucesso.", HttpStatus.OK);
    }

    @Operation(summary = "Atualiza uma receita existente através do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receita atualizada com successo",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReceitaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de negócio",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @PutMapping("/receita/{id}")
    public ResponseEntity<ReceitaDto> atualizarReceita(@RequestBody ReceitaDto receitaDto,
                                                       @Parameter(description = "ID de identificação da receita.")
                                                       @PathVariable Long id) {
        return new ResponseEntity<>(receitaService.atualizarReceita(receitaDto, id), HttpStatus.OK);
    }
}
