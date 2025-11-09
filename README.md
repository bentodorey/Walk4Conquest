Universidade - Iade Faculdade de Design, Tecnologia e Comunicação da Universidade curricular  
Unidades Curriculares - Projeto Desenvolvimento Móvel , Programação Mobile , Programação Orientada por Objetos  , Bases de Dados , Competências Comunicacionais , Matemática Discreta  
Elementos do Grupo  
Bento d'Orey – Nº 20241233  
Martim Fonseca – Nº 20241218  
Vasco Sousa Pinto – Nº 20231182  
Miguel Croca - Nº 20240408  
---



![w4c](https://github.com/user-attachments/assets/906fd288-0a87-4b30-97fa-662df0413153)




Proposta do Projeto
Walk 4 Conquest (W4C)

Link do Repositório GitHub: https://github.com/Miguelcroca/Projeto-Walk-4-Conquest-/blob/main/README.md

_________________________________________________________________

Fitness · Gamificação · Competição · Saúde · GPS · Comunidade
_________________________________________________________________

## 1. Breve Descrição da App e do Problema que Pretende Resolver

A Walk 4 Conquest (W4C) é uma aplicação móvel que promove o fitness com um aspeto social e competitivo.  
O objetivo é criar uma app que incentive a prática de exercício físico, transformando percursos em conquistas competitivas e sociais, promovendo saúde, diversão e espírito de jogo.

Esta ideia surgiu da observação de que muitas pessoas desejam praticar exercício, mas não têm motivação suficiente ou necessitam de incentivo externo, como um treinador ou atividades em grupo. Assim, a Walk 4 Conquest propõe um novo meio de incentivo, recorrendo ao prazer da conquista e ao espírito competitivo individual.

A aplicação permite ao utilizador definir a sua “base” (ex: casa, escritório, escola) e iniciar um percurso. Ao regressar à base, a corrida é dada como concluída e o trajeto é marcado no mapa. Surge então a componente competitiva: cada território conquistado pode ser partilhado, e outros utilizadores podem tentar reconquistá-lo, criando uma dinâmica contínua de desafios.



![poster w4](https://github.com/user-attachments/assets/a9099476-b4ff-4c67-8588-b87ae5fb2493)



_________________________________________________________________


## 2. Objetivos e Motivação

A Walk 4 Conquest pretende:
•⁠  ⁠Incentivar hábitos saudáveis de forma lúdica e competitiva;
•⁠  ⁠Promover a socialização através de partilhas e desafios;
•⁠  ⁠Criar um sistema de “territórios conquistados” para aumentar o envolvimento dos utilizadores;
•⁠  ⁠Desenvolver um protótipo funcional para validação com utilizadores reais.

_________________________________________________________________


## 3. Público-Alvo

O público-alvo são jovens e adultos entre 18 e 40 anos, ativos, que praticam corrida ou caminhada e utilizam apps móveis para registar atividade física. Valorizam o exercício, a competição saudável e o convívio digital através de plataformas sociais.

_________________________________________________________________


## 4. Pesquisa de Mercado

Strava  Registo GPS, estatísticas, rede social ; Foco competitivo, mas sem conquistas territoriais 
Nike Run Club  Treinos guiados, partilhas sociais ; Menos personalização e desafios entre utilizadores 
Zombies Run!  Gamificação com storytelling ; Limitado a um tipo de desafio, sem componente territorial 
INTVL  Corridas competitivas por território;  partilhas sociais. Conceito inovador, mas vulnerável a fraudes e elevado consumo de bateria; limitações de privacidade. 

_________________________________________________________________


## 5. Guiões de Teste (Casos de Utilização)

Caso 1 – Core: Conquista de Território
1.⁠ ⁠O utilizador cria conta e autentica-se.  
2.⁠ ⁠Define a sua base (casa, escola, escritório).  
3.⁠ ⁠Inicia percurso com o GPS ativo.  
4.⁠ ⁠Percorre trajeto e regressa à base.  
5.⁠ ⁠A app marca o percurso e regista o território conquistado.  
6.⁠ ⁠O utilizador recebe pontos ou estatísticas da conquista.
Caso 2 – Partilha Social
1.⁠ ⁠Após conquistar território, o utilizador seleciona “Partilhar”.  
2.⁠ ⁠Escolhe se quer partilhar com amigos, público ou grupo.  
3.⁠ ⁠A app publica o trajeto e resultados.  
4.⁠ ⁠Amigos podem comentar ou desafiar o percurso.

Caso 3 – Competição entre Utilizadores
1.⁠ ⁠O utilizador visualiza no mapa territórios conquistados por outros.  
2.⁠ ⁠Seleciona um território para “reconquistar”.  
3.⁠ ⁠Faz um percurso maior até ao mesmo local.  
4.⁠ ⁠Se o trajeto for superior, o território muda de dono.  
5.⁠ ⁠A app atualiza o ranking e notifica ambos os utilizadores.

_________________________________________________________________

## 6. Descrição da Solução a Implementar


A aplicação permitirá:
•⁠  ⁠Criação e autenticação da conta;
•⁠  ⁠Definição da base pessoal;
•⁠  ⁠Registo de percursos via GPS;
•⁠  ⁠Criação de territórios conquistados;
•⁠  ⁠Partilhas sociais e sistema de ranking;
•⁠  ⁠Funcionalidade de competição territorial.

Enquadramento nas Unidades Curriculares
| Unidade Curricular | Contributo |
|--------------------|------------|
| Programação Móvel | Desenvolvimento da app (frontend e integração GPS) |
| Engenharia de Software | Levantamento de requisitos e modelo de domínio |
| Gestão de Projetos | Planeamento, Gantt e monitorização |
| Base de Dados | Estrutura de dados de utilizadores, percursos e territórios |
| Interface e UX | Design da aplicação e protótipo interativo |

Requisitos Técnicos (provisórios)

•⁠  ⁠O sistema deve permitir criar conta e autenticação.  
•⁠  ⁠O utilizador deve poder definir a sua base.  
•⁠  ⁠A app deve registar percursos via GPS.  
•⁠  ⁠Cada percurso deve ser convertido num território.  
•⁠  ⁠Outros utilizadores podem tentar reconquistá-lo.  
•⁠  ⁠O utilizador deve poder partilhar conquistas.  
•⁠  ⁠Deve existir um ranking de conquistas.

•⁠  ⁠Desempenho: registo GPS em tempo real com baixo consumo de bateria.  
•⁠  ⁠Segurança: proteção de dados pessoais e localização.  
•⁠  ⁠Escalabilidade: suporte para grande número de utilizadores.  
•⁠  ⁠Confiabilidade: disponibilidade mínima de 99%.  
•⁠  ⁠Privacidade: controlo sobre visibilidade das conquistas.

Arquitetura da Solução (provisória)


Componentes principais:
•⁠  ⁠App móvel (Frontend);
•⁠  ⁠Servidor / API REST (Backend);
•⁠  ⁠Base de dados (armazenamento de utilizadores, percursos e territórios);
•⁠  ⁠Serviço de mapas e GPS (Google Maps API).

Tecnologias a Utilizar (provisórias)

| Camada | Tecnologia sugerida |
|--------|---------------------|
| Mobile | Flutter / React Native |
| Backend | Node.js / Firebase |
| Base de Dados | Firestore / MySQL |
| Mapas e GPS | Google Maps API |
| Autenticação | Firebase Auth |



![mockup](https://github.com/user-attachments/assets/5fc6c53e-868d-4264-b439-f95f348f289f)


---

## 7. Guiões e personas

1.⁠ ⁠Joana Martins – A Iniciante 

Idade: 27 anos
Profissão: Designer gráfica
Nível de corrida: Principiante
Objetivo: Perder peso e criar rotina de exercício
Motivação: Sentir-se mais saudável e com energia
Frustrações: Falta de consistência, dificuldade em sentir-se motivada
Necessidades: Planos simples, feedback motivador
Comportamento: Corre 2-3x por semana, gosta de apps intuitivas e com notificações


2.⁠ ⁠Miguel Pereira – O Explorador

Idade: 34 anos
Profissão: Engenheiro informático
Nível de corrida: Intermédio / Avançado
Objetivo: Descubrir vistas e zonas que até então ainda não foi
Motivação: Superar-se e comparar desempenho com amigos
Frustrações: Já não sabe por aonde ainda não esteve
Necessidades: mapa com os sitios onde já andou
Comportamento: Corre 4-5x por semana, participa em provas por todo Portugal


3.⁠ ⁠Sofia Almeida – A Social Runner

Idade: 22 anos
Profissão: Estudante universitária
Nível de corrida: Intermédio
Objetivo: Manter a forma e socializar
Motivação: Correr com amigos e partilhar resultados
Frustrações: Falta de motivação sozinha, horários incompatíveis
Necessidades: Funções sociais, eventos de grupo e motivação gamificada
Comportamento: Corre 2x por semana, gosta de apps com comunidades


4.⁠ ⁠Carlos Ramos – O Veterano da Corrida
Idade: 52 anos
Profissão: Professor
Nível de corrida: Experiente
Objetivo: Manter a forma física e evitar lesões zonas com grandes elevações
Motivação: Saúde e bem-estar
Frustrações: Lesões recorrentes, apps confusas que mandam por sitios estranhos
Necessidades: Interface simples
Comportamento: Corre 3x por semana, valoriza apps simples e fiáveis


---


## 8. Plano e Calendário


<img width="813" height="354" alt="image" src="https://github.com/user-attachments/assets/9b089774-95ab-47e3-8b45-7a14b1ecae1b" />



---

## 9. Conclusão

O projeto Walk 4 Conquest pretende combinar a componente de jogo, fitness e socialização através de uma abordagem competitiva e divertida. O objetivo principal é criar um protótipo funcional que permita testar a motivação gerada pela conquista de territórios e interação entre utilizadores. A aplicação tem como objetivo aumentar a prática de atividade física de forma sustentável e envolvente.

---

## 10. Bibliografia

Bump (aplicação)  
ChatGPT (site)  
https://www.jaba-recordati.pt/pt/transact-lat/dicas-de-lifestyle/atividade-fisica-lifestyle-18-descobre-as-10-melhores-apps-de-fitness  
Territorial.io (aplicação)
