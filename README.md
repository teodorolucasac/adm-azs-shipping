# adm-azs-shipping
API REST - CRUD of shipping information

# DESAFIO BACKEND AZSHIP.
Trata-se de uma API REST para gerenciar informações de frete.

### Desafio:
1 - API REST com CRUD dinâmico desenvolvido em Java/Spring.

2 - Utilização de arquitetura hexagonal (Port and Adapter).

3 - Método de busca dinâmico utilizando um únco parâmetro para buscando todas as propriedades, sendo a busca paginada.

4 - Armazenamento em Banco de Dados (MySQL). 

5 - Solução entregada em ambiente Docker, com seus arquivos de configuração.

6 - Tratamento de erros completo e personalizado.

7 - Testes de api e integração.

8 - Criação e gerenciamento das tabelas do Banco de Dados por Flyway.

9 - Separação do DB de Desenvolvimento com DB de Testes, ambos com inserção de massa de dados.

### Utilizando o Sistema:
Faça o build do Dockerfile do diretório raiz da aplicação com o comando:
"docker build -t teodorolucasac/api-test-azs:v1 ."

Inicie o container docker com o seguinte comando:
"docker-compose up -d"
Ou utilize o comando a baixo caso precise atualiza uma alteração:
"docker-compose up -d --build"

(Lembre-se de que pode ser preciso esperar a instância do banco de dados executar por alguns segundos para ser inicializado a instância da aplicação).

Para acessar ao MySQL use:

hostname: localhost

port: 3307

pas: 1234567

Para requisições do Sistema API-FRETE-AZS use:

hostname: localhost

port: 8080

### ARQUIVO DAS REQUISIÇÕES HTTP:
O arquivo da collection das requisições está disponibilizado no diretório raiz da aplicação no arquivo:
"AZShip-API-FRETE.postman_collection.json"

Use para importar requisições prontas no Postman ou no seu programa favorito.
