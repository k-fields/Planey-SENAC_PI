# Planey-SENAC_PI
Projeto Integrador - Aplicação Web de Controle Financeiro Pessoal

# Executando o projeto Planey

### Conectando no banco
Instalar o MySQL na porta 3306, e executar o seguinte comando quando se conectar ao localhost:
> create database planeyDB

As entidades utilizadas serão criadas automaticamente pelo hibernate.

### Documentação openapi do projeto
Ao iniciar o projeto em sua máquina, é possível visualizar todas operações e testar os endpoints do projeto, acessando o seguinte endereço:
> http://localhost:8080/swagger-ui/index.html#/

### Primeiro uso do sistema
Ao subir localmente o projeto, executar a request de cadastrar usuário "POST cadastrar usuario".
Uma vez feita essa consulta, executar o serviço de login:
> http://localhost:8080/login
No retorno do /login será devolvido o token que deverá ser utilizado em todas as próximas consultas.

