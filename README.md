
# API de Ordens de Serviço

Projeto desenvolvido em parceria por [Flpvoigt](https://github.com/Flpvoigt) e
[GustavoLoes](https://github.com/GustavoLoes).

## Requisitos

- Java 21 ou superior
- Maven Wrapper incluído no projeto

## Como executar

No Windows, confirme que o Java está disponível no `PATH` e execute
`iniciar.cmd`. Também é possível iniciar a aplicação diretamente:

```powershell
.\mvnw.cmd spring-boot:run
```

## Endpoints

| Método | Rota | Descrição |
| --- | --- | --- |
| `POST` | `/ordens` | Cria uma ordem de serviço |
| `GET` | `/ordens` | Lista as ordens cadastradas |
| `GET` | `/ordens?cliente=nome` | Filtra ordens pelo nome do cliente, ignorando maiúsculas e minúsculas |
| `GET` | `/ordens/{id}` | Consulta uma ordem pelo ID |
| `PUT` | `/ordens/{id}` | Atualiza uma ordem existente |
| `DELETE` | `/ordens/{id}` | Exclui uma ordem existente |

## Testes

Com o Java 21 ou superior configurado, execute:

```powershell
.\mvnw.cmd test
```
