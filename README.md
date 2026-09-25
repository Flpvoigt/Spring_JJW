
# API de Ordens de Serviço

Projeto desenvolvido em parceria por [Flpvoigt](https://github.com/Flpvoigt) e
[GustavoLoes](https://github.com/GustavoLoes).

## Requisitos

- Java 25
- Maven Wrapper incluído no projeto

## Como executar

No Windows, execute `iniciar.cmd`. O script utiliza o JDK 25 armazenado em
`.tools` somente durante a execução deste projeto.

## Endpoints

| Método | Rota | Descrição |
| --- | --- | --- |
| `POST` | `/ordens` | Cria uma ordem de serviço |
| `GET` | `/ordens` | Lista as ordens cadastradas |
| `GET` | `/ordens/{id}` | Consulta uma ordem pelo ID |
| `PUT` | `/ordens/{id}` | Atualiza uma ordem existente |
| `DELETE` | `/ordens/{id}` | Exclui uma ordem existente |

## Testes

Com o Java 25 configurado, execute:

```powershell
.\mvnw.cmd test
```
