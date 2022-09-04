package com.br.senac.planey.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.br.senac.planey.application.exception.BusinessException;
import com.br.senac.planey.domain.dto.UsuarioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRACAO = 600_000;
    public static final String SENHA = "83daaa1f-ae3d-4d17-9959-51c36041d573";

    private final AuthenticationManager authenticationManager;

    public JwtAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UsuarioDTO usuarioDTO = new ObjectMapper().readValue(request.getInputStream(), UsuarioDTO.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuarioDTO.getUsername(), usuarioDTO.getPassword(), new ArrayList<>()
            ));

        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Falha ao autenticar o usuário.");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UsuarioDTO usuarioDTO = (UsuarioDTO) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(usuarioDTO.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                .sign(Algorithm.HMAC512(SENHA));

        response.getWriter().write(token);
        response.getWriter().flush();

    }
}
