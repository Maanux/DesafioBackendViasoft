# Desafio Back-End Viasoft

## 📌 Descrição do Projeto

Aplicação REST desenvolvida em **Spring Boot**, com um endpoint para receber e processar o envio de e-mails.
A aplicação adapta o objeto recebido para diferentes formatos (AWS ou OCI), conforme a configuração definida no arquivo `application.properties`.

O envio **não é realizado de fato**, apenas **simulado**, serializando o objeto em JSON e exibindo-o no console.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3.5.6**
* **Spring Web**
* **Spring Validation**
* **Jackson (para serialização JSON)**
* **Springdoc OpenAPI (Swagger UI)**
* **JUnit 5 (para testes unitários)**

---

## 📦 Estrutura do Projeto

```
src/
 ├─ main/
 │   ├─ java/DesafioBackEndViasoft/demo/
 │   │   ├─ controller/        → Controlador REST (EmailController)
 │   │   ├─ domain/            → Mensagem de erros (ValidationException)
 │   │   ├─ dto/               → Objetos de transferência (DTOs)
 │   │   │    ├─ mail/EmailDTO.java
 │   │   │    ├─ awsMail/EmailAwsDTO.java
 │   │   │    └─ ociMail/EmailOciDTO.java
 │   │   ├─ infra/exception/   → Tratamento global de exceções
 │   │   └─ service/           → Regras de negócio (EmailService)
 │   └─ resources/
 │       └─ application.properties
 └─ test/
     └─ java/... → Testes unitários (EmailServiceTest)
```

---

## ⚙️ Configuração

No arquivo `application.properties`, defina qual integração será usada:

```properties
mail.integracao=AWS
# ou
mail.integracao=OCI
```

---

## 📬 Endpoint REST

**POST** `/emails`

### Exemplo de Requisição

```json
{
  "emailDoDestinatario": "aluno@teste.com",
  "nomeDestinatario": "João da Silva",
  "emailDoRemetente": "professor@teste.com",
  "assunto": "Bem-vindo ao curso!",
  "conteudo": "Olá João, parabéns por se inscrever!"
}
```

### Respostas possíveis

| Status | Descrição                                           |
| ------ | --------------------------------------------------- |
| 204    | E-mail processado e impresso no console com sucesso |
| 400    | Dados inválidos ou configuração incorreta           |
| 500    | Erro interno do servidor                            |

---

## 🧠 Exemplo de Saída no Console

```bash
Enviando via AWS...
===== EMAIL SERIALIZADO =====
{
  "recipient": "aluno@teste.com",
  "recipientName": "João da Silva",
  "sender": "professor@teste.com",
  "subject": "Bem-vindo ao curso!",
  "content": "Olá João, parabéns por se inscrever!"
}
==============================
```

---

## 🧪 Testes Unitários

Os testes validam:

* A adaptação correta para AWS e OCI
* Validação dos campos obrigatórios
* Tratamento de exceções (400 e 500)

Para executar:

```bash
mvn test
```

Exemplo de teste (`EmailServiceTest.java`):

```java
@Test
void deveLancarErroQuandoRemetenteForNulo() {
    ReflectionTestUtils.setField(service, "integracao", "AWS");

    var email = new EmailDTO("dest@teste.com", "Destinatário", null, "Assunto", "Conteúdo");

    var ex = assertThrows(IllegalArgumentException.class, () -> service.processarEmail(email));
    assertEquals("O e-mail do remetente é obrigatório", ex.getMessage());
}
```

---

## 🧾 Documentação da API (Swagger)

Após rodar o projeto, acesse:
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Lá você poderá:

* Ver os endpoints disponíveis
* Testar as requisições diretamente pelo navegador

---

## 🧰 Tratamento de Erros

O tratamento centralizado é feito pela classe `ErrorHandler`, que intercepta exceções comuns e retorna os status adequados (400, 404, 500).

---

## 👨‍💻 Autor

**Emanuel dos Santos Kanetchny**
Desenvolvedor Back-End Java
[LinkedIn]([https://www.linkedin.com/in/emanuel-dos-santos-kanetchny/])
 

---

## 🏁 Resumo

| Requisito                                     | Implementado |
| --------------------------------------------- | ------------ |
| Endpoint REST para envio de e-mail            | ✅            |
| DTOs para AWS e OCI                           | ✅            |
| Seleção dinâmica via `application.properties` | ✅            |
| Serialização JSON e impressão no console      | ✅            |
| Tratamento de erros (400, 500, 204)           | ✅            |
| Documentação Swagger                          | ✅            |
| Testes unitários                              | ✅            |
