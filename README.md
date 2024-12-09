<h1 align="center">
  Teste Digio
</h1>

<p align="center">
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Desafio&color=8257E5&labelColor=000000" alt="Desafio Digio" />
</p>

Lista de Produtos: [link](https://rgr3viiqdl8sikgv.public.blob.vercelstorage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json)

Lista de Clientes e Compras: [link](https://rgr3viiqdl8sikgv.public.blob.vercelstorage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json)

## Crie um microserviço com os seguintes endpoints após consumir os dados dos mocks acima e retorne o que está sendo solicitado:
1. GET: /compras - Retornar uma lista das compras ordenadas de forma
crescente por valor, deve conter o nome dos clientes, cpf dos clientes,
dado dos produtos, quantidade das compras e valores totais de cada
compra.
2. GET: /maior-compra/ano - (Exemplo: /maior_compra/2016) - Retornar a
maior compra do ano informando os dados da compra disponibilizados,
deve ter o nome do cliente, cpf do cliente, dado do produto, quantidade
da compra e seu valor total.
3. GET: /clientes-fieis - Retornar o Top 3 clientes mais fieis, clientes que
possuem mais compras recorrentes com maiores valores.
4. GET: /recomendacao/cliente/tipo - Retornar uma recomendação de vinho
baseado nos tipos de vinho que o cliente mais compra.

Entrega da prova: enviar o link do repositório no GitHub.
Stack de tecnologias: a prova deve ser feita em Spring Boot com versão Java >=
11

## Tecnologias
 
- [Spring Boot 3.4.0](https://spring.io/projects/spring-boot)
- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven 4.0.0](https://maven.apache.org/download.cgi)

## Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/winstore-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizado navegador padrão:

- GET: /compras - Retornar uma lista das compras ordenadas de forma
crescente por valor, deve conter o nome dos clientes, cpf dos clientes,
dado dos produtos, quantidade das compras e valores totais de cada
compra.
```
http://localhost:8080/busca/compras
```

- GET: /maior-compra/ano - (Exemplo: /maior_compra/2016) - Retornar a
maior compra do ano informando os dados da compra disponibilizados,
deve ter o nome do cliente, cpf do cliente, dado do produto, quantidade
da compra e seu valor total.
```
http://localhost:8080/busca/maior-compra/2018
```

- GET: /clientes-fieis - Retornar o Top 3 clientes mais fieis, clientes que
possuem mais compras recorrentes com maiores valores.
```
http://localhost:8080/busca/clientes-fieis
```

- GET: /recomendacao/cliente/tipo - Retornar uma recomendação de vinho
baseado nos tipos de vinho que o cliente mais compra.
```
http://localhost:8080/busca/recomendacao/96718391344/Espumante
```
