package com.br.senac.planey.domain.service;

import com.br.senac.planey.domain.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO salvar(UsuarioDTO usuario);
}
