# Walk4Conquest – Relatório Final do Projeto

![w4c](https://github.com/user-attachments/assets/906fd288-0a87-4b30-97fa-662df0413153)


## 1. Identificação

**Universidade:** Iade Faculdade de Design, Tecnologia e Comunicação da Universidade curricular    
**Curso:** Engenharia Informática  
**Unidades Curriculares Envolvidas:** Projeto Desenvolvimento Móvel , Programação Mobile , Programação Orientada por Objetos  , Bases de Dados , Competências Comunicacionais , Matemática Discreta 

**Elementos do Grupo:**

- Bento d'Orey      – nº 20241233
- Martim Fonseca    – nº 20241218
- Vasco Sousa Pinto – nº 20231182
- Miguel Croca      – nº 20240408

**Nome do Projeto:** Walk4Conquest  
**Repositório GitHub:** https://github.com/bentodorey/Walk4Conquest  

---

## 2. Quadro de Tarefas e Contributo dos Elementos

| Tarefa Principal                                 |   Bento    |   Martim   |   Vasco    |   Miguel   |
|--------------------------------------------------|-----------:|-----------:|-----------:|-----------:|
| Análise de requisitos                            |    25%     |    25%     |    25%     |    25%     |
| Desenho de casos de utilização                   |    50%     |     0%     |    50%     |     0%     |
| Modelação de dados / diagrama de classes         |     0%     |    50%     |     0%     |    50%     |
| Desenvolvimento da app (frontend)                |    70%     |    10%     |    20%     |    10%     |
| Desenvolvimento backend / serviços REST          |    20%     |    40%     |     0%     |    40%     |
| Integração com APIs externas                     |    40%     |    40%     |     0%     |    20%     |
| Testes e correção de erros                       |    30%     |    30%     |    20%     |    20%     |
| Documentação técnica                             |     0%     |    40%     |    30%     |    30%     |
| Manual do utilizador                             |    20%     |    40%     |    40%     |     0%     |
| Gestão do projeto / planeamento                  |    30%     |    30%     |    10%     |    30%     |



---

## 3. Breve Descrição da App e do Problema

O **Walk4Conquest** é uma aplicação móvel que tem como objetivo motivar os utilizadores a correr enquanto exploram mais as suas areas e competem com os seus proximos, levando a competitividade humana a uma life style saudável.

O objetivo é criar uma app que incentive a prática de exercício físico, transformando percursos em conquistas competitivas e sociais, promovendo saúde, diversão e espírito de jogo.

Esta ideia surgiu da observação de que muitas pessoas desejam praticar exercício, mas não têm motivação suficiente ou necessitam de incentivo externo, como um treinador ou atividades em grupo. Assim, a Walk 4 Conquest propõe um novo meio de incentivo, recorrendo ao prazer da conquista e ao espírito competitivo individual.

A aplicação permite ao utilizador definir a sua “base” (ex: casa, escritório, escola) e iniciar um percurso. Ao regressar à base, a corrida é dada como concluída e o trajeto é marcado no mapa. Surge então a componente competitiva: cada território conquistado pode ser partilhado, e outros utilizadores podem tentar reconquistá-lo, criando uma dinâmica contínua de desafios.
A Walk 4 Conquest (W4C) é uma aplicação móvel que promove o fitness com um aspeto social e competitivo.

O problema identificado é falta de motivação para atividade física, os nossos utilizadores até já podem ter tentado outras apps de corrida, mas a ausência de interação social fez com que a motivação para as continuar a usra no long term, morresse cedo.  
A app procura resolver este problema através da conquista de territórios, recompensas, rankings, tudo entre as pessoas que tu aceitares.

---

## 4. Objetivos e Motivação

Os objetivos principais do projeto são:

1. Desenvolver uma aplicação móvel funcional que permita fazer exercício sem desistir.
2. Integrar funcionalidades de geolocalização e mapa para suportar percentagens das áreas conquistadas, registo de percursos
3. Implementar um sistema de gamificação que incentive o utilizador a puxar amigos, familia e até colegas de trabalho a correrem e não o fazerem sozinhos.
4. Praticar metodologias de trabalho em grupo, planeamento e versionamento de código utilizando Git/GitHub.


---

## 5. Identificação do Público‑Alvo

O público‑alvo principal são:

- Estudantes e jovens adultos entre 18-40 anos, com acesso a smartphone e interesse em jogos casuais e atividade física.
- Utilizadores que já utilizam apps de exercício (ex.: contagem de passos) mas procuram uma componente competitiva.
- Pessoas que vivem em zonas urbanas com facilidade de deslocação a pé.
- Pessoas que não conseguem levar até ao fim 


---

## 6. Pesquisa de Mercado / Aplicações Semelhantes

Foram analisadas várias aplicações móveis com funcionalidades semelhantes ou comparáveis:

- **Pokemon GO** – jogo de realidade aumentada baseado em geolocalização e conquista de pontos no mapa.  
  Semelhanças: utilização de mapa, movimento físico do utilizador.  
  Diferenças: foco em colecionar personagens, não em caminhadas estruturadas.

- **Zombies, Run!** – aplicação que incentiva corrida/caminhada através de narrativa.  
  Semelhanças: motivação para exercício usando elementos de jogo.  
  Diferenças: foco em história e áudio, não em conquista de território.

- **Google Fit / Samsung Health** – apps de monitorização de atividade física.  
  Semelhanças: registo de passos/atividade.  
  Diferenças: ausência de forte componente de gamificação e competição territorial.

A partir desta análise concluiu‑se que há espaço para uma solução que combine **monitorização de atividade física** com **conquista de áreas no mapa** e **competição direta entre utilizadores**, abordagem adotada pelo Walk4Conquest.

---

## 7. Descrição da Solução Implementada

### i. Descrição Genérica da Solução

O Walk4Conquest é uma aplicação Android que permite ao utilizador:

- Registar corridas utilizando geolocalização.  
- Visualizar um mapa com zonas/territórios.  
- Conquistar áreas ao deslocar‑se fisicamente até elas.  
- Competir com outros jogadores através de pontos, conquistas ou rankings.  
- Consultar estatísticas básicas da sua atividade.


---

### ii. Enquadramento nas Diversas Unidades Curriculares

- **UC 1 – :Programação Orientada por Objetos** aplicação de conceitos de análise de requisitos, casos de utilização e modelação UML.  
- **UC 2 – Programação Mobile:** desenvolvimento da interface móvel, navegação entre ecrãs e experiência do utilizador.  
- **UC 3 – Bases de Dados:** implementação de serviços web / REST, consumo de APIs externas e gestão de dados persistentes.  
- **UC 4 – Projeto Desenvolvimento Móvel:** planeamento, gestão de projeto, utilização de ferramentas de controlo de versões (Git).
- **UC 5 – Competências Comunicacionais:** aperfeiçoamento da utilização de powerpoint e de transmissão das ideias essenciais.  
- **UC 6 – Matemática Discreta :** resolução de problemas de classificação e de areas.  



---

### iii. Requisitos Técnicos Finais

Principais requisitos técnicos implementados:

- **RF1 – Registo e autenticação de utilizadores** via <<método: email/password, OAuth, etc.>>.  
- **RF2 – Registo de caminhadas** com recurso ao GPS do dispositivo.  
- **RF3 – Visualização de mapa interativo** com <<biblioteca ou serviço usado: Google Maps, Mapbox, etc.>>.  
- **RF4 – Sistema de conquista de territórios** baseado em <<ex.: polígonos no mapa, grelha de células, pontos de interesse>>.  
- **RF5 – Rankings / pontuações entre utilizadores.**  
- **RF6 – Consulta de histórico de conquistas e estatísticas.**

---

### iv. Arquitetura da Solução

A arquitetura segue uma abordagem <<ex.: cliente‑servidor>>:

- **Camada de Apresentação (App Móvel):**
  - Ecrãs de login/registro, mapa, perfil, rankings, etc.
  - Implementação de lógica de interação com o utilizador.

- **Camada de Lógica de Negócio:**
  - Cálculo de pontos e conquistas.
  - Regras de atualização de territórios e rankings.
  - Validação de dados recebidos.

- **Camada de Dados:**
  - <<Descrever: BD local (SQLite/Room), BD remota (MySQL/PostgreSQL/Firebase), etc.>>
  - Modelos de dados para utilizadores, sessões de caminhada, territórios, conquistas, etc.

- **Serviços Externos:**
  - API de mapas / geolocalização.
  - Outros serviços que utilizem (ex.: autenticação externa).

(Incluir no relatório final o diagrama de arquitetura que já tiverem desenhado.)

---

### v. Tecnologias Utilizadas

- Linguagem de programação: Kotlin, Java  
- Plataforma móvel: Android Studio  
- Frameworks / bibliotecas:
  - <<Ex.: Android Jetpack (ViewModel, LiveData, Room)>>  
  - <<Ex.: Retrofit / Volley para chamadas REST>>  
  - <<Ex.: Google Maps SDK / Mapbox>>  
- Base de dados: MySQL 
- Serviços web / backend: <<Node.js, Spring Boot, Firebase Functions, etc., ou “não aplicável” se não tiverem>>  
- Ferramentas de apoio: Git/GitHub, Figma.


---

### vi. Versão Atualizada dos Casos de Utilização

Listagem resumida (os diagramas devem ser anexados como imagens):

- **UC1 – Registar utilizador**  
- **UC2 – Iniciar sessão**  
- **UC3 – Iniciar caminhada**  
- **UC4 – Visualizar mapa e territórios**  
- **UC5 – Conquistar território durante a caminhada**  
- **UC6 – Consultar perfil e estatísticas**  
- **UC7 – Consultar ranking / leaderboard**  
- **UC8 – Terminar sessão**


---

### vii. Versão Atualizada do Diagrama de Classes

Descrever brevemente as principais classes:

- `User` – representa o utilizador (id, nome, email, pontuação, etc.).  
- `Territory` – representa cada área conquistável (id, localização, proprietário atual, etc.).  
- `WalkSession` – representa uma sessão de caminhada (data, duração, distância, utilizador associado).  
- `Conquest` – regista conquistas efetuadas numa sessão.  
- Outras classes relevantes ligadas à interface, serviços, repositórios de dados, etc.


---

### viii. Versão Atualizada do Dicionário de Dados (Modelo ER)

Incluir:

- Tabela/Entidade `User` – campos, tipos, chaves primárias/estrangeiras.  
- Tabela `Territory`.  
- Tabela `WalkSession`.  
- Tabela `Conquest`.  

Para cada campo, descrever:

- Nome  
- Tipo de dados  
- Tamanho  
- Restrição (PK, FK, NOT NULL, etc.)  
- Descrição


---

### ix. Versão Atualizada do Guia de Dados

Explicar como os dados são utilizados e mantidos:

- Política de criação/atualização de utilizadores.  
- Regras para registo de novas sessões de caminhada.  
- Regras de cálculo e atualização de pontos e territórios.  
- Estratégia de backup e recuperação (se aplicável).  
- Tratamento de dados pessoais e preocupação com privacidade (armazenamento mínimo, encriptação, etc., se usarem).

---

### x. Link para Documentação REST

Caso exista API/serviço REST, indicar:

- URL base da API.  
- Endpoints principais (`/login`, `/users`, `/territories`, `/walks`, etc.).  
- Métodos suportados (GET/POST/PUT/DELETE).  
- Formato de dados (JSON, etc.).

Exemplo de referência no relatório:

> A documentação detalhada da API REST encontra‑se disponível em:  




---

### xi. Link para o Manual do Utilizador

> O Manual do Utilizador, contendo descrições passo a passo dos ecrãs e funcionalidades da aplicação, encontra‑se disponível em:  



---

## 8. Planeamento e Calendarização Final

Foi utilizado um planeamento dividido em fases principais:

1. **Fase 1 – Análise e Especificação (Semanas 1–2)**  
   - Recolha de requisitos  
   - Definição de casos de utilização  
   - Pesquisa de mercado

2. **Fase 2 – Desenho e Modelação (Semanas 3–5)**  
   - Modelo de dados (ER)  
   - Diagrama de classes  
   - Protótipo de interfaces

3. **Fase 3 – Implementação (Semanas 5–11)**  
   - Desenvolvimento da app móvel  
   - Integração de mapas e geolocalização  
   - Implementação de lógica de jogo e conquistas  
   - Desenvolvimento / integração de backend (se existir)

4. **Fase 4 – Testes e Integração (Semanas 11–14)**  
   - Testes funcionais  
   - Correção de bugs  
   - Otimizações de desempenho

5. **Fase 5 – Documentação e Entrega (Semana 15)**  
   - Relatório final  
   - Manual do utilizador  
   - Preparação da apresentação

### Quadro Resumo de Planeamento (tipo Gantt simplificado)

| Fase / Semana                  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9\10 | 11\12 | 13\14 | 15 | 
|--------------------------------|---|---|---|---|---|---|---|---|------|-------|-------|----|
| Análise e Especificação        | █ | █ |   |   |   |   |   |   |      |       |       |    |
| Desenho e Modelação            |   |   | █ | █ | █ |   |   |   |      |       |       |    |
| Implementação App              |   |   |   |   | █ | █ | █ | █ | █ █  |  █    |       |    |
| Testes e Integração            |   |   |   |   |   |   |   |   |      |  █ █  |  █  █ |    |
| Documentação e Entrega         |   |   |   |   |   |   |   |   |      |       |       |  █ |


---

## 9. Conclusões (opcional, mas normalmente útil)

- Resumo dos resultados alcançados. Melhor compreenção do que está por detrás até de uma aplicação simples, a importância de um código organizado. Inicialmente, o grupo tinha grandes ambições e ideias que até eram boas, mas com o tempo muitas tiveram que ser cortadas pois não havia ou tempo ou resources suficientes para tudo. 
- Principais dificuldades técnicas e organizacionais. Configuração de algumas API's e a marcação das áreas.
- Possíveis melhorias e trabalho futuro, novas funcionalidades: criação de varios tipos de grupos para competições exemplo, grupo de familia, grupo do trabalho e grupo de amigos. Criação de desafios temporários, por exemplo no natal correr em um percurso cuja a area fique um boneco de neve.   

---

## 10. Bibliografia e Referências

Listar em formato consistente (APA, IEEE ou outro):

- Documentação oficial das bibliotecas usadas.  
- Artigos, livros ou páginas web relevantes para a conceção do projeto.  
- Tutoriais que tenham sido particularmente importantes.

