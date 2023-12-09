
# SA03 - ShoesHappy

Trabalho dedicado a matéria de Desenvolvimento de Sistemas do curso Técnico de Desenvolvimento de Sistemas do Senai/SC

## Script Para Criação do Banco

Script da criação do banco e da tabela clientes

```bash
CREATE DATABASE happyshoes;

USE happyshoes;

CREATE TABLE cliente (
    matricula INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    endereco VARCHAR(200) NOT NULL,
    modalidade VARCHAR(200) NOT NULL
)

SELECT * FROM cliente
```
    
## Como se conectar ao banco

Para se conectar ao banco da aplicação é necessario utilizar as informações abaixo.

`User: root`

`senha: sua_senha_do_banco`

Lembre-se de alterá-las no arquivo ClienteDao.Java 

```bash
// Nome de usuário 'root' para acesso ao banco de dados do SGBD MySQL
private String jdbcNomeUsuario = "root";

// Senha do usuário 'root' para acesso ao banco de dados do SGBD MySQL
private String jdbcSenha = "sua_senha_do_banco";
```




## Rodando localmente

Inicie o servidor WildFly em seu computador e no browser de sua preferência acesse:

```bash
  http://localhost:8080/ShoesHappyApp/
```

Caso não localize utilize a url abaixo:

```bash
  http://localhost:8080/ShoesHappyApp/main
```


