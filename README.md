
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
| `GET` | `/ordens?page=0&size=20&sort=cliente,asc` | Lista as ordens com paginação e ordenação |
| `GET` | `/ordens?cliente=nome&page=0&size=20` | Filtra ordens pelo cliente e mantém a paginação |
| `GET` | `/ordens/{id}` | Consulta uma ordem pelo ID |
| `PUT` | `/ordens/{id}` | Atualiza uma ordem existente |
| `PATCH` | `/ordens/{id}/status` | Atualiza o status da ordem |
| `DELETE` | `/ordens/{id}` | Exclui uma ordem existente |

Os status disponíveis são `ABERTA`, `EM_ANDAMENTO`, `CONCLUIDA` e
`CANCELADA`. Para atualizar, envie por exemplo:

```json
{
  "status": "CONCLUIDA"
}
```

Cada ordem também informa `criadaEm` e `atualizadaEm` em UTC. Esses campos
são preenchidos automaticamente e não precisam ser enviados nas requisições.

## Respostas de erro

Erros de validação retornam `400 Bad Request` no formato Problem Details,
incluindo uma propriedade `campos` com as mensagens de cada campo inválido.
Consultas, atualizações ou exclusões de IDs inexistentes retornam
`404 Not Found` no mesmo formato padronizado.

## Testes

Com o Java 21 ou superior configurado, execute:

```powershell
.\mvnw.cmd test
```
