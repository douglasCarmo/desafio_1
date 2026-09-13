
desafio_1
/agencia-viagem-api/
Go to file
t
T
douglasCarmo
douglasCarmo
Add files via upload
f60f63d
 · 
1 minute ago
desafio_1
/agencia-viagem-api/
Name	Last commit message	Last commit date
..
src/main
Add files via upload
1 minute ago
target
Add files via upload
1 minute ago
README.md
Add files via upload
1 minute ago
pom.xml
Add files via upload
1 minute ago
README.md
# API Agência de Viagem — Desafio DSW API RESTful em **Java + Spring Boot** para gestão de destinos de viagem, feita para o desafio da disciplina de Desenvolvimento de Sistemas Web. Como o enunciado deixa claro que **não é necessário banco de dados** nesta atividade, os destinos ficam guardados em memória (dentro do `DestinoService`, em um `Map`). Isso significa que a lista reseta toda vez que a aplicação é reiniciada — e está tudo certo, é assim que o desafio pede. ## Estrutura (camadas) Pensando na analogia do restaurante: - `controller/DestinoController.java` → o **garçom**: recebe a requisição HTTP e devolve a resposta. Não tem regra de negócio. - `service/DestinoService.java` → o **cozinheiro**: tem toda a lógica (cadastrar, pesquisar, calcular média de avaliação, etc.). - `model/Destino.java` → o **prato pronto**: representa um destino de viagem. - `dto/` → os "pedidos" que o cliente da API pode fazer (o que ele pode enviar no corpo da requisição). ## Como rodar ```bash mvn spring-boot:run ``` A API sobe em `http://localhost:8080`. ## Endpoints | Método | Rota | O que faz | |---|---|---| | POST | `/destinos` | Cadastra um novo destino | | GET | `/destinos` | Lista todos os destinos | | GET | `/destinos/pesquisa?nome=&localizacao=` | Pesquisa por nome e/ou localização | | GET | `/destinos/{id}` | Detalhes de um destino específico | | PATCH | `/destinos/{id}/avaliacoes` | Envia uma nota (1 a 10) e recalcula a média | | DELETE | `/destinos/{id}` | Exclui um destino | ### Exemplos de teste (Postman / Insomnia / curl) **Cadastrar:** ``` POST /destinos { "nome": "Florianópolis", "localizacao": "Santa Catarina, Brasil", "descricao": "Praias e trilhas na Ilha da Magia" } ``` **Avaliar:** ``` PATCH /destinos/1/avaliacoes { "nota": 9 } ``` ## O que ainda vale revisar antes de entregar - Rode a aplicação localmente e teste os 6 endpoints (o professor provavelmente vai olhar isso). - Leia o código com calma — como você está no início da disciplina, é importante conseguir explicar o que cada camada faz se o professor perguntar. - Confirme com o professor-tutor se o desenvolvimento individual está ok (o enunciado pede essa confirmação). - Suba num repositório Git e entregue o link pelo AVA, como pede a "Forma de entrega". # desafio_1
