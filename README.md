# Workfree API

Este README descreve como executar o projeto `workfree-api`, tanto localmente com Maven quanto usando Docker Compose.

Workfree API é uma API para cadastro de Prestadores de serviços que atuam como freelacer.

> Observação: instruções em Windows (cmd.exe). Ajuste para bash se estiver usando macOS/Linux.


 <img width="1281" height="251" alt="Workfree-API drawio" src="https://github.com/user-attachments/assets/b0747966-dc17-443f-8634-21afa0441efd" />


## Pré-requisitos

- Java 17 (JDK) instalado localmente se for usar Maven localmente.
  - Verifique com: `java -version`
- Spring Boot, WebClient, Spring Data JPA , PostgreSQL / H2, Flyway, WireMock, JUnit5/Mockito, springdoc-openapi,Lombok.
- Maven (opcional se utilizar o wrapper `mvnw.cmd`).
- Docker & Docker Compose (para executar a stack via containers).

## Conteúdo principal do repositório

- `src/main/java` – código-fonte da aplicação (controllers, serviços, domínio, repositórios).
- `src/test/java` – testes unitários e de integração (inclui teste de integração com WireMock embutido).
- `Dockerfile` – imagem multi-stage para construir e rodar a aplicação.
- `docker-compose.yml` – orquestra: Postgres, WireMock e a aplicação.
- `docker/wiremock/mappings/cep-01001000.json` – mapping de exemplo do WireMock (CEP 01001000).

## Executando com Docker Compose (recomendado para ambiente local/escritório)

Isso sobe três serviços:
- `db` (Postgres)
- `wiremock` (mock da API externa de CEP)
- `app` (a aplicação Spring Boot)

No cmd (na raiz do projeto):

```cmd
cd C:\Users\Matheus\Documents\Workspace\workfree-api\workfree-api
docker compose up --build -d
```

- Builda a imagem da aplicação (usa Maven dentro do builder stage) e sobe os containers.
- A aplicação ficará disponível em `http://localhost:8080`.
- WireMock ficará exposto em `http://localhost:8089` (mapeado para o container WireMock).

Ver logs do app:

```cmd
docker compose logs -f app
```

Parar e remover containers:

```cmd
docker compose down
```

## Executando localmente com Maven (sem Docker)

Se preferir executar localmente:

1. Garanta que `JAVA_HOME` aponte para JDK 17.
2. Compile e rode:

```cmd
cd C:\Users\Matheus\Documents\Workspace\workfree-api\workfree-api
.\mvnw.cmd -DskipTests package
java -jar target\workfree-api-0.0.1-SNAPSHOT.jar
```

ou rode direto com o plugin Spring Boot:

```cmd
.\mvnw.cmd spring-boot:run
```

Se você quiser testar contra um WireMock standalone, rode o WireMock na porta 8089 e certifique-se de ajustar `external.cep.base-url` em `src/main/resources/application.yaml` ou usar variável de ambiente.

## Variáveis de ambiente / Configurações

As principais propriedades podem ser sobrescritas por env vars:

- `SPRING_DATASOURCE_URL` (ex.: `jdbc:postgresql://localhost:5432/workfree`)
- `SPRING_DATASOURCE_USERNAME` (ex.: `workfree`)
- `SPRING_DATASOURCE_PASSWORD` (ex.: `password`)
- `external.cep.base-url` — URL base para chamada de CEP externa (por exemplo `http://wiremock:8080/ws` ou `http://localhost:8089/ws`).

No `docker-compose.yml` a propriedade `external.cep.base-url` é passada via `SPRING_APPLICATION_JSON` apontando para o container `wiremock`.

## Executando os testes

Testes unitários (muitos deles usam Mockito):

```cmd
.\mvnw.cmd test
```

Testes de integração (nomeados `*IntegrationTest.java`) são executados pelo Failsafe durante o `verify`:

```cmd
.\mvnw.cmd verify
```

Se quiser rodar apenas o teste de integração `CepWireMockIntegrationTest`:

```cmd
.\mvnw.cmd -Dtest=CepWireMockIntegrationTest test
```

Ou via Docker (sem JDK local):

```cmd
docker run --rm -v "%cd%":/work -w /work maven:3.8.8-jdk-17 mvn verify
```

## Endpoints principais

- `GET /cep/{cep}` — consulta externa de CEP (a controller usa `CepService` e grava log em `cep_log`).
  - Exemplo:

```cmd
curl http://localhost:8080/cep/01001000
```

- `GET /cep/logs` — lista logs das consultas de CEP (tabela `cep_log`).

- `POST /v1/prestadores` — cria um prestador; antes de persistir, o serviço enriquece os campos de endereço (`rua`, `bairro`, `cidade`, `uf`) chamando o serviço de CEP.

Exemplo mínimo de payload para `POST /v1/prestadores` (salve `payload.json`):

```json
{
  "nomeCompleto": "Fulano de Tal",
  "cpf": "12345678909",
  "funcao": "Pintor",
  "valorDiaria": 100.0,
  "telefone": "(11)99999-0000",
  "cep": "01001000",
  "rua": null,
  "numero": "123",
  "bairro": null,
  "cidade": null,
  "uf": null,
  "complemento": ""
}
```

Enviar via curl:

```cmd
curl -v -H "Content-Type: application/json" -X POST http://localhost:8080/v1/prestadores --data-binary "@payload.json"
```

Após a criação, verifique `GET /cep/logs` para confirmar que a chamada ao provedor de CEP foi registrada.

## Onde está o mapeamento do WireMock (mock do CEP)

- `docker/wiremock/mappings/cep-01001000.json`
  - Contém um mapeamento que responde à requisição `GET /ws/01001000/json` com um JSON de exemplo.

Se você não usar Docker Compose, pode rodar o WireMock standalone com a mesma pasta `docker/wiremock/mappings` montada.

## Troubleshooting rápido

- Erro: `The JAVA_HOME environment variable is not defined correctly`
  - Configure `JAVA_HOME` para o diretório do JDK 17 e abra novo terminal.

- Erro: `docker: command not found` ou `Cannot connect to the Docker daemon`
  - Instale e inicie o Docker Desktop (Windows) e certifique-se que o daemon está rodando.

- Porta 8080/8089 em uso
  - Pare o serviço que ocupa a porta ou ajuste o `ports` no `docker-compose.yml`.

- Testes falhando por falha de conectividade com o CEP externo
  - Verifique se WireMock está rodando (no Compose está em `http://localhost:8089` ou `http://wiremock:8080` quando usado em compose) e se o mapping do CEP existe.

- Erro de compilação relacionado à inferência de tipos (lambda/method reference)
  - Execute `mvn -U clean package` para limpar e forçar atualização; verifique se todos os arquivos fonte foram atualizados no seu workspace/IDE.

