package com.br.senac.planey.application.controller;

import com.br.senac.planey.domain.dto.ReceitaDto;
import com.br.senac.planey.domain.dto.UsuarioDTO;
import com.br.senac.planey.domain.response.ErrorResponse;
import com.br.senac.planey.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuario", description = "Grupo de microsserviços para gerenciamento e login dos usuários")
@RestController
@RequestMapping("${host.v1}")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Retorna uma lista com todos usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista dos usuários do sistema",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReceitaDto.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping("/usuario")
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @Operation(summary = "Realiza o cadastro de um novo usuário na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReceitaDto.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping("/usuario")
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(usuarioService.salvar(usuario));
    }


}
