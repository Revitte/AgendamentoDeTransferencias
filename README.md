# Documentação do Projeto: Agendamento de Transferências Financeiras

## Visão Geral
O projeto **Agendamento de Transferências Financeiras** é um sistema que permite aos usuários agendarem transferências bancárias para datas futuras, aplicando regras de taxa conforme a data de transferência. A aplicação é desenvolvida utilizando **Java 11, Spring Boot e Vue.js**, com uma arquitetura baseada em **API REST** para comunicação entre o backend e o frontend.

## Objetivos
- Criar um sistema que permita o agendamento de transferências financeiras.
- Aplicar regras de taxa conforme a data da transferência.
- Garantir uma interface simples e intuitiva para o usuário.
- Implementar uma API REST para facilitar integrações futuras.

---

# Arquitetura do Projeto

## Backend
- **Linguagem**: Java 17
- **Framework**: Spring Boot
- **Banco de Dados**: H2 (para persistência em memória durante o desenvolvimento)
- **Estrutura de Pacotes**:
  - **controllers**: Contém os controladores responsáveis por expor as APIs REST.
  - **entity**: Contém as classes que representam as entidades do sistema.
  - **repository**: Interfaces que interagem com o banco de dados.
  - **services**: Contém a lógica de negócio do sistema.

### Endpoints REST

#### **TransferenciaController**
- `POST /transferencias` - Agendar uma nova transferência.
- `GET /transferencias` - Buscar todas as transferências agendadas.

#### **ContaBancariaController**
- `POST /contas` - Criar uma nova conta bancária.
- `GET /contas/{id}` - Buscar detalhes de uma conta bancária.

---

## Frontend
- **Linguagem**: JavaScript
- **Framework**: Vue.js
- **UI Framework**: TailwindCSS
- **Estrutura de Diretórios**:
  - `views/` - Contém as páginas da aplicação.
  - `services/` - Contém a camada de serviços para chamadas à API.
  - `router/` - Define as rotas da aplicação.

### Funcionalidades
- Formulário para agendar transferências bancárias.
- Validação de entrada do usuário (como limites de conta e valores numéricos).
- Exibição de transferências agendadas com informações detalhadas.

---

# Decisões Arquiteturais

## 1. Uso do Spring Boot no Backend
A escolha do Spring Boot foi motivada pela sua facilidade na criação de APIs REST robustas, além da integração rápida com o banco de dados e suporte para testes automatizados.

## 2. Uso do Vue.js no Frontend
O Vue.js foi escolhido devido à sua curva de aprendizado amigável, sua flexibilidade na criação de componentes reutilizáveis e pela boa performance em aplicações SPA (Single Page Applications).

## 3. Persistência em Banco de Dados H2
Para a fase de desenvolvimento, optamos por utilizar o **H2 Database**, um banco de dados em memória que facilita os testes e reduz complexidade na configuração inicial.

## 4. Estilização com TailwindCSS
O TailwindCSS foi escolhido para estilização da interface devido à sua simplicidade e flexibilidade na criação de layouts responsivos e modernos.

## 5. Estrutura de Diretórios Separada
Mantivemos uma estrutura modularizada para garantir escalabilidade e facilitar a manutenção do sistema.

---

# Futuras Melhorias
- Implementar autenticação de usuários (JWT).
- Adicionar um banco de dados relacional definitivo (PostgreSQL ou MySQL).
- Criar testes automatizados para garantir a estabilidade do sistema.
- Melhorar a gestão de erros e validação de dados no backend e frontend.


