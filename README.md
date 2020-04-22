# Transações Api


### Objetivo
- Aplicação de transações, possuí apis de cadastros de contas e de operações de entrada e saída financeira.

### Tecnologias
- Java
- Maven
- Spring boot web
- Spring data jpa
- JUnit
- Hamcrest
- Swagger

### Execução
#### Execução via IDE
Executar classe br.com.lojavirtual.LojaVirtualApplication.java.

#### Execução via JAR
Após executar o comando mvn clean install na raíz do projeto para gerar o arquivo to tipo jar. Acessar a pasta target, e executar: 
java -jar .\transacoes-api-0.0.1-SNAPSHOT.jar br.com.transacoesapi.TransacoesApiApplication

#### Execução via Docker
<p>Após executar o comando mvn clean install na raíz do projeto para gerar o arquivo to tipo jar. Acessar a pasta docker, e executar abaixo para gerar a imagem:<p/> 
<p>docker build -t {NOME DA IMAGEM}:{VERSÃO} .</p>
<p>Com a imagem já gerada, executar o comando para rodar a imagem: docker run -p 8080:8080 {NOME DA IMAGEM}:{VERSÃO}</p>

### Links
<p>A Aplicação fica disponível em: http://localhost:8080/transacoes-api</p>
<p>Teste e documentação da api está disponível em: http://localhost:8080/transacoes-api/swagger-ui.html</p>
