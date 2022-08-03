
# Movie Classificator Service

Um microsserviço que classifica e permite a consulta dos produtores que tem maior e menor intervalo
de prêmios conscutivos da categoria Pior Filme do Golden Raspberry Awards.


#### OBS: O arquivo para leitura deve estar na pasta csv localizada na raiz do projeto e ter obrigatoriamente o nome movielist.csv 
    


## Stack utilizada

**Back-end:** Java, Spring Boot


## Build

Considerando que você já tem o maven instalado em seu computador, basta executar na raiz do projeto os seguinte comando


```bash
  mvn install
```


## Rodando os testes

Para rodar os testes, rode o seguinte comando

```bash
  ./mvnw test
```


## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/johnattamedeiros/movie-classificator-service.git
```

Entre no diretório do projeto

```bash
  cd movie-classificator-service
```

Instale as dependências

```bash
  mvn install
```

Inicie o servidor

```bash
  mvn spring-boot:run
```


## Documentação da API

#### Retornará os produtores com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido

#### O projeto está configurado para porta 8080

```http
  GET /api/producer/producers/winner-successive-summary
```

```http
  curl -v http://localhost:8080/api/producer/producers/winner-successive-summary
```