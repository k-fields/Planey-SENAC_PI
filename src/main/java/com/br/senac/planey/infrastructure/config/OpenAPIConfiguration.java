package com.br.senac.planey.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Documentacao tecnica do projeto Planey",
                description = "" +
                        "O Planey visa o controle/planejamento financeiro de contas de maneira simples e direta na palma de suas mãos. Para todos que almejam uma saúde financeira mantendo a autonomia e centralizando as metas individuais de cada usuário, otimizando as despesas, gastos e lucros com apenas um clique.",
                contact = @Contact(
                        name = "Grupo 4 - SENAC",
                        url = "https://github.com/k-fields/Planey-SENAC_PI",
                        email = "teste@teste.com"
                )),
        servers = @Server(url = "http://localhost:8080", description = "Servidor para execução local")
)
class OpenAPIConfiguration {
}
