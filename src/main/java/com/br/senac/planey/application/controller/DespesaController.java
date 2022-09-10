package com.br.senac.planey.application.controller;

import com.br.senac.planey.domain.dto.DespesaDto;
import com.br.senac.planey.domain.response.ErrorResponse;
import com.br.senac.planey.domain.service.DespesaService;
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

@Tag(name = "Despesa", description = "Grupo de microsserviços para gerenciamento das despesas dos usuários")
@RestController
@RequestMapping("${host.v1}")
public class DespesaController {

    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @Operation(summary = "Retorna uma lista com todas despesas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de despesas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DespesaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de negócio",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping("/despesa")
    public List<DespesaDto> getDespesas(@RequestHeader("Authorization") String authorization, @RequestParam("user_id") long userid) {
        return despesaService.listarDespesas(userid);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Despesa localizada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DespesaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de negócio",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @Operation(summary = "Retorna uma despesa por ID")
    @GetMapping("/despesa/{id}")
    public DespesaDto getDespesaById(@Parameter(description = "ID de identificação da despesa.")
                                     @PathVariable Long id) {
        return despesaService.listarDespesaPorId(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Despesa criada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DespesaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de negócio",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @Operation(summary = "Cria uma nova despesa")
    @PostMapping("/despesa")
    public ResponseEntity<DespesaDto> criarDespesa(@RequestBody DespesaDto despesa) {
        return new ResponseEntity<>(despesaService.criarDespesa(despesa), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicação de despesa excluída com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DespesaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de negócio",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @Operation(summary = "Deleta uma despesa através do ID")
    @DeleteMapping("/despesa/{id}")
    public ResponseEntity<String> deleteDespesa(@Parameter(description = "ID de identificação da despesa.")
                                                @PathVariable Long id) {
        despesaService.deletarDespesa(id);
        return new ResponseEntity<>("Id " + id + " deletado com sucesso.", HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nova despesa atualizada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DespesaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de negócio",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @Operation(summary = "Atualiza uma despesa existente através do ID")
    @PutMapping("/despesa/{id}")
    public ResponseEntity<DespesaDto> atualizarDespesa(@RequestBody DespesaDto despesa,
                                                       @Parameter(description = "ID de identificação da despesa.")
                                                       @PathVariable Long id) {
        return new ResponseEntity<>(despesaService.atualizarDespesa(despesa, id), HttpStatus.OK);
    }

}
