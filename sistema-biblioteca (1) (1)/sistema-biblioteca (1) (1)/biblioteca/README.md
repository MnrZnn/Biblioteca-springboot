# 📚 Sistema de Controle de Biblioteca

Sistema web para gerenciamento de biblioteca desenvolvido com **Spring Boot**, **MongoDB** e **Thymeleaf**, seguindo o padrão **MVC** com camada de serviços.

---

## 🏗️ Arquitetura (MVC Evoluído)

```
src/main/java/com/biblioteca/
├── model/          → Entidades de domínio (Model)
│   ├── Usuario.java
│   ├── Livro.java
│   ├── Emprestimo.java
│   ├── Reserva.java
│   └── Multa.java
├── repository/     → Camada de acesso a dados (DAO / Spring Data MongoDB)
│   ├── UsuarioRepository.java
│   ├── LivroRepository.java
│   ├── EmprestimoRepository.java
│   ├── ReservaRepository.java
│   └── MultaRepository.java
├── service/        → Camada de regras de negócio (Service Layer)
│   ├── UsuarioService.java
│   ├── LivroService.java
│   ├── EmprestimoService.java
│   └── ReservaService.java
├── controller/     → Camada de controle HTTP (Controller)
│   ├── HomeController.java
│   ├── LivroController.java
│   ├── UsuarioController.java
│   ├── EmprestimoController.java
│   └── ReservaController.java
└── config/
    └── DataInitializer.java  → Dados de exemplo na inicialização

src/main/resources/
├── templates/      → Views Thymeleaf (View)
│   ├── index.html
│   ├── livros/
│   ├── usuarios/
│   ├── emprestimos/
│   └── reservas/
├── static/css/
│   └── style.css
└── application.properties
```

---

## ✅ Funcionalidades

| Módulo       | Operações disponíveis                          |
|--------------|------------------------------------------------|
| Livros       | Listar, buscar, adicionar, editar, remover     |
| Usuários     | Listar, adicionar (Aluno/Professor/Funcionário), editar, remover |
| Empréstimos  | Registrar empréstimo, devolver livro, listagem |
| Reservas     | Criar reserva, cancelar reserva, listagem      |
| Multas       | Geradas automaticamente ao devolver com atraso (R$ 2,50/dia) |

### Regras de negócio
- Empréstimo só é permitido se o livro tiver quantidade > 0
- Prazo padrão de empréstimo: **14 dias**
- Reserva válida por **7 dias** (status: ATIVA → CANCELADA)
- Ao devolver com atraso, gera `Multa` automaticamente
- Quantidade do livro é decrementada no empréstimo e incrementada na devolução

---

## 🛠️ Pré-requisitos

- **Java 17+**
- **Maven 3.8+**
- **MongoDB 6+** (local ou nuvem)

---

## 🚀 Como rodar localmente

### 1. Clonar o repositório
```bash
git clone https://github.com/SEU_USUARIO/sistema-biblioteca.git
cd sistema-biblioteca
```

### 2. Configurar o MongoDB

**Opção A — MongoDB local:**
```bash
# Instalar e iniciar MongoDB
sudo systemctl start mongod
# ou no macOS:
brew services start mongodb-community
```

**Opção B — MongoDB Atlas (nuvem gratuita):**
1. Criar conta em https://www.mongodb.com/atlas
2. Criar cluster gratuito (M0)
3. Obter a connection string e editar `application.properties`:
```properties
spring.data.mongodb.uri=mongodb+srv://usuario:senha@cluster0.xxxxx.mongodb.net/biblioteca
```

### 3. Executar a aplicação
```bash
mvn spring-boot:run
```

Acesse: **http://localhost:8080**

> Na primeira execução, dados de exemplo (5 livros e 4 usuários) serão inseridos automaticamente.

---

## 📦 Gerar JAR para deploy

```bash
mvn clean package -DskipTests
java -jar target/sistema-biblioteca-1.0.0.jar
```

---

## ☁️ Deploy em servidor na nuvem

### Railway (recomendado — gratuito)
1. Faça push do projeto para o GitHub
2. Acesse https://railway.app e conecte o repositório
3. Adicione um serviço MongoDB no Railway
4. Configure a variável de ambiente:
   ```
   SPRING_DATA_MONGODB_URI=mongodb://...
   ```
5. O Railway detecta o `pom.xml` e faz o build automaticamente

### Render
1. Faça push para o GitHub
2. Crie um novo "Web Service" em https://render.com
3. Build command: `mvn clean package -DskipTests`
4. Start command: `java -jar target/sistema-biblioteca-1.0.0.jar`
5. Configure `SPRING_DATA_MONGODB_URI` como variável de ambiente

### Heroku
```bash
heroku create meu-sistema-biblioteca
heroku addons:create mongolab:sandbox
git push heroku main
```

---

## 🧱 Stack tecnológica

| Tecnologia         | Versão | Função                    |
|--------------------|--------|---------------------------|
| Spring Boot        | 3.2.0  | Framework principal       |
| Spring Data MongoDB| 3.2.0  | ORM para MongoDB          |
| Thymeleaf          | 3.1    | Template engine (View)    |
| MongoDB            | 6+     | Banco de dados NoSQL      |
| Maven              | 3.8+   | Gerenciamento de build    |
| Java               | 17     | Linguagem                 |
| Lombok             | —      | Redução de boilerplate    |

---

## 📊 Diagrama de Classes

Baseado no diagrama UML do projeto:
- `Usuario` (abstrato) → `Aluno`, `Professor`, `Funcionario`
- `Livro` com controle de `quantidadeDisponivel`
- `Emprestimo` com `finalizarEmprestimo()` e cálculo de atraso
- `Reserva` com `validarReserva()` e expiração de 7 dias
- `Multa` com `calcularValor()` (R$ 2,50/dia)

---

## 👨‍💻 Autor

Desenvolvido como projeto acadêmico — Sistemas de Informação  
Atividade: Passo 4 — Aplicação Web com Banco de Dados
