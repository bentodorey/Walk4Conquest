# 🎯 Walk 4 Conquest (W4C)

### Universidade
**IADE – Faculdade de Design, Tecnologia e Comunicação**

### Unidades Curriculares
Projeto Desenvolvimento Móvel · Programação Mobile · Programação Orientada por Objetos · Bases de Dados · Competências Comunicacionais · Matemática Discreta

### Elementos do Grupo
- Bento d'Orey – Nº 20241233  
- Martim Fonseca – Nº 20241218  
- Vasco Sousa Pinto – Nº 20231182  
- Miguel Croca – Nº 20240408  

---

## 🔗 Repositório GitHub
[https://github.com/Miguelcroca/Projeto-Walk-4-Conquest-](https://github.com/Miguelcroca/Projeto-Walk-4-Conquest-/blob/main/README.md)

---

## 💡 1. Breve Descrição

A **Walk 4 Conquest (W4C)** é uma aplicação móvel que combina **fitness, gamificação e socialização**, transformando percursos reais em conquistas competitivas.  
O objetivo é incentivar a prática de exercício físico de forma divertida e social, promovendo hábitos saudáveis e espírito competitivo.

A app permite definir uma base (ex: casa, escola, escritório) e iniciar um percurso. Ao regressar à base, o trajeto é marcado no mapa e convertido num “território conquistado”, que pode ser partilhado e até **reconquistado por outros utilizadores**.

---

## 🎯 2. Objetivos e Motivação

- Incentivar hábitos saudáveis através de uma experiência competitiva.  
- Promover a interação social e o espírito de conquista.  
- Criar um sistema de “territórios” com base em GPS.  
- Desenvolver um protótipo funcional para teste com utilizadores.  

---

## 👥 3. Público-Alvo

Utilizadores entre **18 e 40 anos**, ativos, interessados em exercício físico e em experiências sociais e competitivas, que usam apps para monitorizar treino ou caminhada.

---

## 🔎 4. Pesquisa de Mercado

| Aplicação | Funcionalidades | Limitações |
|------------|-----------------|-------------|
| **Strava** | Registo GPS e rede social | Sem conquistas territoriais |
| **Nike Run Club** | Treinos guiados | Pouca competição direta |
| **Zombies Run!** | Gamificação com storytelling | Limitada a um tipo de desafio |
| **INTVL** | Corridas por território | Elevado consumo de bateria |

---

## 🧠 5. Guiões de Teste

### Caso 1 – Conquista de Território
1. Cria conta e faz login.  
2. Define base (casa, escola, etc).  
3. Inicia corrida com GPS.  
4. Percorre trajeto e regressa.  
5. App regista percurso e marca território.  

### Caso 2 – Partilha Social
1. Após corrida, seleciona **“Partilhar”**.  
2. Escolhe modo (amigos/público).  
3. Publica o trajeto e resultados.  

### Caso 3 – Competição
1. Visualiza territórios de outros.  
2. Escolhe um para reconquistar.  
3. Faz percurso maior.  
4. Ranking é atualizado.  

---

## ⚙️ 6. Descrição da Solução

A app permite:
- Criar conta e autenticação  
- Definir base  
- Registar percursos via GPS  
- Criar e partilhar territórios  
- Competir com outros utilizadores  

### Tecnologias
| Camada | Tecnologia |
|--------|-------------|
| Mobile | Kotlin + Jetpack Compose |
| Backend | Firebase / Node.js |
| Base de Dados | Firestore |
| Mapas | Google Maps API |
| Autenticação | Firebase Auth |

---

## 📊 7. Enquadramento nas Unidades Curriculares

| Unidade Curricular | Contributo |
|--------------------|------------|
| **Programação Móvel** | Desenvolvimento Android |
| **Engenharia de Software** | Requisitos e modelo de domínio |
| **Gestão de Projetos** | Planeamento e Gantt |
| **Base de Dados** | Estrutura e queries |
| **Interface e UX** | Design e protótipos |

---

## 📅 8. Plano e Calendário do Projeto

### Mapa de Gantt
![Mapa de Gantt](https://github.com/user-attachments/assets/635679c9-5e4a-4bf6-8561-f582528aac24)

---

## 🧍‍♀️ 9. Personas

### 👩 Joana Lopes
![Joana Lopes](https://github.com/user-attachments/assets/edcf7043-479f-4ad7-a359-e1846df2f050)

### 👨 João Silva
![João Silva](https://github.com/user-attachments/assets/0aa5b2b0-50b7-4cf1-8f4d-6b7317e28fbc)

---

## 📘 10. Documentação REST (v1)

### Base URL
