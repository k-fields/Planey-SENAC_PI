package com.br.senac.planey.domain.service.impl;

import com.br.senac.planey.application.exception.RegistroNaoEncontradoException;
import com.br.senac.planey.domain.dto.UsuarioDTO;
import com.br.senac.planey.domain.entity.Usuario;
import com.br.senac.planey.domain.service.UsuarioService;
import com.br.senac.planey.infrastructure.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario byUsername = usuarioRepository.findByUsername(username);

        if (byUsername == null)
            throw new RegistroNaoEncontradoException("Usuário não encontrado.");

        return UsuarioDTO.builder()
                .id(byUsername.getId())
                .username(byUsername.getUsername())
                .password(byUsername.getPassword())
                .build();
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        usuarioDTO.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        return modelMapper.map(usuarioRepository.save(usuario), UsuarioDTO.class);
    }
}
