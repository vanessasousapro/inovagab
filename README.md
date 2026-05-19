<h1 align="center">
  🦅 InovaGAB
</h1>

<p align="center">
  <strong>Plataforma de Inovação Corporativa</strong><br/>
  Challenge FIAP × Grupo Águia Branca
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white"/>
  <img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>
  <img src="https://img.shields.io/badge/Backend-Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black"/>
  <img src="https://img.shields.io/badge/Status-Em%20Desenvolvimento-blue?style=for-the-badge"/>
</p>

---

## 📖 Sobre o Projeto

O **InovaGAB** é uma solução mobile nativa para Android desenvolvida como resposta ao **Challenge FIAP × Grupo Águia Branca**, com o objetivo de digitalizar e integrar a gestão de inovação corporativa do grupo.

> *"Não buscamos apenas um app de ideias. Buscamos um sistema capaz de conectar estratégia, execução e resultados, transformando inovação em valor real."*
> — Grupo Águia Branca

O Grupo Águia Branca é um dos maiores conglomerados de transporte e logística do Brasil, com mais de **20 mil colaboradores** e **25+ empresas** organizadas em três divisões: Passageiros, Comércio e Logística.

---

## 🎯 Problema

A gestão integrada da inovação é um desafio nas grandes corporações. O grupo identificou quatro pontos críticos:

| Desafio | Descrição |
|---|---|
| 🔗 Conexão Estratégica | Distância entre o direcionamento da alta gestão e a execução na ponta |
| 🔍 Visibilidade do Funil | Dificuldade em acompanhar a jornada completa da ideia até o resultado |
| 👥 Engajamento Operacional | Colaboradores de base pouco incluídos no processo criativo |
| 📊 Mensuração de Valor | Ausência de demonstração clara do ROI das iniciativas de inovação |

---

## 💡 Solução

O **InovaGAB** estrutura cinco pilares integrados de inovação:

01 DIRECIONAMENTO   → Alinhamento com objetivos estratégicos do Grupo <br>
02 GESTÃO DE IDEIAS → Captura de dores e sugestões de todos os níveis <br>
03 INOVAÇÃO ABERTA  → Conexão com ecossistema externo <br>
04 GESTÃO DE PROJETOS → Iniciativas com cronograma e responsáveis <br>
05 MENSURAÇÃO       → Indicadores e ROI em tempo real <br>

---

## 👤 Perfis de Usuário

| Perfil | Papel | Funcionalidades |
|---|---|---|
| 👷 **Operador** | Registro e Captura | Consultar orientações, cadastrar ideias/problemas, acompanhar status |
| 🧑‍💼 **Gestor** | Gestão e Curadoria | Priorizar e aprovar ideias, cadastrar e acompanhar projetos |
| 👔 **Líder** | Decisão e Visão | Gerenciar orientações estratégicas, visualizar dashboard com ROI |

---

## ⚙️ Funcionalidades

- [x] Autenticação com 3 perfis (Operador, Gestor, Líder)
- [ ] Orientações estratégicas (CRUD para Líder, leitura para demais)
- [ ] Cadastro e consulta de ideias de inovação (Operador)
- [ ] Priorização e aprovação de ideias (Gestor)
- [ ] Cadastro e acompanhamento de projetos (Gestor)
- [ ] Consulta de andamento de projetos (Líder)
- [ ] Dashboard com ROI, redução de custos e produtividade (Líder)

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Finalidade |
|---|---|
| Kotlin | Linguagem principal do app Android |
| Android Studio | IDE de desenvolvimento |
| Firebase Authentication | Login seguro com gestão de sessão por perfil |
| Firebase Firestore | Banco de dados em nuvem em tempo real |
| Material Design 3 | Interface moderna e acessível |
| MVVM | Arquitetura do projeto |

---

## 🏗️ Arquitetura

inovagab/
├── ui/ <br>
│   ├── auth/          # Login e autenticação <br>
│   ├── operador/      # Telas do perfil Operador <br>
│   ├── gestor/        # Telas do perfil Gestor <br>
│   └── lider/         # Telas do perfil Líder (+ Dashboard) <br>
├── model/             # Data classes (Ideia, Projeto, Orientacao, Usuario) <br>
├── repository/        # Comunicação com Firebase <br>
└── viewmodel/         # Lógica de negócio por tela <br>

---

## 📅 Agenda

| Data | Marco |
|---|---|
| 30/04/2025 | Kick-off Challenge Águia Branca |
| 26/05/2025 | **Entrega Sprint 1 — App Android** |
| 2º Semestre | Sprint 2 — Backend robusto (Java/C#) |

---

## 📦 Entregáveis — Sprint 1

- [ ] APK funcional (Android)
- [ ] Código-fonte completo (.zip)
- [ ] Documentação técnica (PDF/PPT)
- [ ] Vídeo demonstrativo (máx. 5 minutos)

---

## 📊 Critérios de Avaliação

| Critério | Peso |
|---|---|
| Adequação ao problema proposto | 20% |
| Implementação técnica funcional | 30% |
| Qualidade do código | 25% |
| Apresentação e documentação | 15% |
| Criatividade e inovação | 10% |

---

## 👩‍💻 Desenvolvedora

Desenvolvido por **Vanessa Sousa** — Estudante de Desenvolvimento de Software — FIAP

---

<p align="center">
  Feito com carinho 💙 para o Challenge FIAP × Grupo Águia Branca
</p>
