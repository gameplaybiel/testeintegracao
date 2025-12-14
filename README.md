# Teste de integração

O projeto de Cadastrar o Paciente consiste em fazer um teste de integração utilizando Cucumber para testar se vários componentes funcionam juntos corretamente.

## Funcionalidades:
- Cucumber
- H2 Database
- Spring Web
- Spring Data JPA
- Spring Boot DevTools

## Cenário de teste
O teste verifica se é possível cadastrar um paciente com sucesso:
1. Dados do paciente são informados via DataTable do Cucumber.
2. Requisição POST é enviada para `/pacientes`.
3. Verifica se o status da resposta está correto.
4. Verifica se o paciente foi salvo no banco de dados.

## Estrutura do projeto
- `src/main/java` - Código fonte
- `src/test/java` - Testes de integração com Cucumber
- `src/test/resources/feature` - Arquivos .feature do Cucumber

## Referências:
Você NÃO está TESTANDO direito (Cucumber com Spring Boot) https://www.youtube.com/watch?v=rzPcS3mrRzI&t=116s
