# Sistema de Gerenciamento de Supermercado

 # Sobre o projeto

O Sistema de Gerenciamento de Supermercado é uma aplicação desktop desenvolvida em Java com o objetivo de otimizar e centralizar os principais processos administrativos e operacionais de um supermercado.

O sistema foi desenvolvido para facilitar o gerenciamento das atividades do estabelecimento, oferecendo uma interface gráfica intuitiva e recursos para controlar informações, organizar processos e tornar a gestão mais eficiente.

A aplicação utiliza o padrão de arquitetura MVC (Model-View-Controller), separando as responsabilidades entre a camada de dados, interface gráfica e regras de negócio, proporcionando um código mais organizado, modular e de fácil manutenção.

 # Funcionalidades

O sistema contempla módulos destinados ao gerenciamento de diferentes setores do supermercado:

Dashboard: visão geral e acesso aos principais módulos do sistema.

Gestão de clientes: cadastro, consulta, edição e gerenciamento de clientes.

Gestão de produtos: cadastro e manutenção das informações dos produtos.

Controle de estoque: acompanhamento e gerenciamento dos produtos disponíveis.

Fornecedores: gerenciamento de fornecedores e parceiros comerciais.

Compras e pedidos: organização e acompanhamento dos processos de compras e pedidos.

PDV (Ponto de Venda): gerenciamento das operações de venda.

Caixa e pagamentos: controle das movimentações financeiras e pagamentos.

Entregas: gerenciamento e acompanhamento das entregas.

Recursos Humanos: gerenciamento de funcionários, cargos, setores e informações relacionadas.

# Tecnologias utilizadas

Java

Java Swing

MySQL

JDBC

Maven

Apache NetBeans

Git e GitHub

# Arquitetura do projeto

O projeto utiliza o padrão arquitetural MVC:

Model: responsável pela representação e estruturação dos dados.

View: responsável pelas interfaces gráficas e interação com o usuário.

Controller: responsável pelas regras de negócio e comunicação entre as telas e os modelos.

Essa estrutura contribui para a organização do código, facilita a manutenção e permite a evolução independente dos módulos.

# Objetivo

Desenvolver uma solução integrada para gerenciamento de supermercado, aplicando conceitos de programação orientada a objetos, desenvolvimento desktop, integração com banco de dados, arquitetura de software e controle de versão.

O projeto também proporciona a aplicação prática de conhecimentos em desenvolvimento de sistemas, modelagem de banco de dados e construção de interfaces gráficas.

# Login 

A tela solicita ao funcionário do supermercado, e-mail e senha, esta tela busca dentro do banco de dados na tabela (usuários) os dados para validar o login. Caso o e-mail e a senha estejam corretos ele entra no sistema. Esta tela utiliza Usuario do Model, e UsuarioController.

# Home

Painel principal do sistema, responsável por apresentar uma visão geral do supermercado. Exibe indicadores de vendas, caixas abertos, pedidos pendentes, contas a pagar e receber, notificações e entregas. Também disponibiliza atalhos para acessar rapidamente as principais funcionalidades e módulos do sistema .

# Home

Painel principal do sistema, responsável por apresentar uma visão geral do supermercado. Exibe indicadores de vendas, caixas abertos, pedidos pendentes, contas a pagar e receber, notificações e entregas. Também disponibiliza atalhos para acessar rapidamente as principais funcionalidades e módulos do sistema .

# Produtos
A tela de Gerenciamento de Produtos é responsável pelo controle e organização dos produtos do supermercado. Por meio dela, é possível cadastrar, consultar, editar e excluir produtos, além de visualizar informações como nome, código, descrição, preço de custo, preço de venda, estoque, validade, categoria, marca e unidade de medida. No desenvolvimento, foi utilizado o Model/Produtos, responsável por representar os produtos e armazenar seus dados por meio de atributos, getters e setters, e o Control/ProdutosController, responsável pelas operações relacionadas aos produtos e pela comunicação entre o sistema e o banco de dados. O objetivo dessa tela é facilitar o gerenciamento dos produtos e manter suas informações organizadas.

# Gerenciamento de Produtos
O módulo de Produtos é responsável pelo gerenciamento e organização dos produtos do supermercado. Ele possui telas de cadastro, consulta, edição e exclusão de produtos, gerenciamento de estoque, controle de produtos pesáveis, geração de etiquetas e emissão de relatórios. Na tela de Cadastro de Produtos, é possível registrar novos produtos com informações como nome, código, descrição, preços, estoque, validade, categoria, marca e unidade de medida. Utiliza o Model/Produtos e o Control/ProdutosController. Na tela de Gerenciamento de Produtos, é possível consultar, editar e excluir produtos cadastrados, utilizando o Model/Produtos e o Control/ProdutosController. Na tela de Estoque, é possível acompanhar as quantidades disponíveis dos produtos, utilizando o Model/Produtos e o Control/ProdutosController. Na tela de Produtos Pesáveis, é realizado o controle dos produtos vendidos por peso. Na tela de Etiquetas, é possível gerar etiquetas com as informações dos produtos. Já na tela de Relatórios, são apresentadas informações para auxiliar no acompanhamento dos produtos. As classes Model e Control das três últimas telas devem ser identificadas conforme a estrutura do projeto. O objetivo do módulo é facilitar o controle dos produtos e manter suas informações organizadas.
# Estoque

# Fornecedores

Módulo responsável pelo gerenciamento completo dos fornecedores e empresas terceirizadas. Possui abas para cadastro de empresas, gerenciamento de categorias, contratos, documentos, avaliações de desempenho, contatos, condições de pagamento, registro de ocorrências e consulta ao histórico de compras. Seu objetivo é centralizar as informações e facilitar o acompanhamento do relacionamento entre o supermercado e seus fornecedores.

# Clientes
A tela de Clientes permite ao funcionário gerenciar os cadastros e acompanhar as informações relacionadas aos clientes do supermercado. É dividida em cinco abas:
## Cadastro
Permite registrar novos clientes, preenchendo informações pessoais, contato e endereço.
## Consulta
Permite pesquisar e visualizar os clientes cadastrados, utilizando nome, CPF ou código.
## Edição e Desativação
Permite localizar clientes, atualizar seus dados cadastrais e alterar a situação do cadastro para ativo ou inativo.
## Histórico de Compras e Pagamentos
Permite consultar as compras e os pagamentos realizados por cada cliente, visualizar valores, datas, formas de pagamento e situações. Também disponibiliza a geração de comprovantes e exportação do histórico.
## Pedidos e Relatório
Permite consultar os pedidos realizados pelos clientes, acompanhar seus status, aplicar filtros de pesquisa e gerar relatórios.
## Model e Controller
A tela utiliza a classe Cliente, do pacote Model, para representar os dados dos clientes, e a classe ClienteController, do pacote Control, para executar as operações e consultas no banco de dados.
## Tabelas utilizadas
cliente, endereco, vendas, pagamentovenda, formaspagamento e pedidos.

## Tela de Promoções / Aba Promocoes

Tela responsável pelo cadastro e gerenciamento das promoções dos produtos da loja. Permite registrar ofertas, definir preços promocionais e períodos de validade, facilitando a organização e o controle das campanhas promocionais do supermercado.

## Principais funcionalidades:
- Cadastrar novas promoções para os produtos.
- Definir o preço promocional.
- Informar a data de início e término da promoção.
- Gerenciar as promoções cadastradas.
- Facilitar a identificação dos produtos em oferta.

# Tabela do banco utilizada no MYsql

- CREATE TABLE IF NOT EXISTS promocao (
 - idpromocao INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
-  nome VARCHAR(45) NOT NULL,
-  tipo VARCHAR(45) NOT NULL,
-  status TINYINT NOT NULL,
-  dataInicio VARCHAR(45) NOT NULL,
-  dataFinal VARCHAR(45) NOT NULL,
-  regras VARCHAR(200) NULL,
-  desconto INT NOT NULL,
-  qttMinima INT NOT NULL
-);

Model: promocao_model.js – responsável pela comunicação com o banco de dados e pelas operações relacionadas às promoções.

Controller: promocao_controller.js – responsável por processar as requisições, aplicar as regras de negócio e controlar as operações de cadastro e gerenciamento das promoções.

## Tela de Promoções / Aba Cupom

Aba responsável pelo cadastro e gerenciamento de cupons de desconto da loja. Permite criar cupons promocionais para oferecer benefícios aos clientes durante suas compras, incentivando o consumo e proporcionando descontos em produtos ou no valor total dos pedidos.

## Principais funcionalidades:
- Cadastrar novos cupons de desconto.
- Definir códigos exclusivos para os cupons.
- Estabelecer o tipo e o valor do desconto.
- Definir o período de validade dos cupons.
- Configurar regras de utilização e limites de uso.
- Gerenciar os cupons cadastrados.

# Tabela do banco utilizada no MYsql

- CREATE TABLE IF NOT EXISTS cupom (
-  idcupom INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
-  codigoCupom VARCHAR(45) NOT NULL,
-  tipoDesconto VARCHAR(45) NOT NULL,
-  valorDesconto DOUBLE NOT NULL,
-  valorMinimoCompra DOUBLE NOT NULL,
-  qttMaximaUso INT NOT NULL,
-  limitePorCliente INT NOT NULL,
-  tipoUso VARCHAR(45) NOT NULL,
-  dataInicio VARCHAR(45) NOT NULL,
-  dataFinal VARCHAR(45) NOT NULL,
-  observacao VARCHAR(200) NULL,
-  promocao_idpromocao INT NOT NULL
- );

Model: cupom_model.js – responsável pela comunicação com o banco de dados e pelas operações relacionadas aos cupons.

Controller: cupom_controller.js – responsável por processar as requisições e controlar as operações de cadastro e gerenciamento dos cupons.


Ambas as duas abas permitem realizar o cadastro de determinadas funcoes dentro do sistema de supermercado


# Pedidos

# Entregas

# Vendas
A tela de Vendas foi desenvolvida em Java Swing e tem como objetivo permitir a consulta e o acompanhamento das vendas realizadas no supermercado, reunindo informações comerciais e financeiras em uma única interface. Ela apresenta áreas destinadas à consulta de vendas, filtros por período, informações de operadores e produtos, formas de pagamento, cancelamentos, estornos, comprovantes, bandeiras de cartões, parcelamentos, taxas e contas bancárias.

## Integração com o Banco de Dados
A tela realiza consultas ao banco de dados MySQL, principalmente às seguintes tabelas:
- vendas: fornece os dados principais da venda, como identificador, data, horário, valor total, valor pago, troco, quantidade de itens e status.
- cliente: fornece o nome do cliente relacionado à venda.
- usuario: fornece o nome do operador responsável pela venda.
- caixas: fornece a identificação do caixa utilizado.
- cupom: fornece o código do cupom associado à venda.
- itensVendas: armazena os itens vinculados a cada venda, incluindo quantidade, preço unitário, desconto e valor total.
- pagamentoVenda: relaciona os pagamentos realizados às vendas e às formas de pagamento.
- formasPagamento: fornece os nomes das formas de pagamento utilizadas.
- categorias: disponibiliza os nomes das categorias para seleção nos filtros.
- permissoes: fornece os tipos de níveis de acesso.
- contasFinanceiras: disponibiliza informações de bancos e tipos de contas financeiras.
Essas consultas permitem apresentar informações das vendas e seus respectivos relacionamentos, além de preencher tabelas e listas de seleção da interface

## Utilização do VendasController
O VendasController é responsável pela comunicação entre a tela e o banco de dados no que diz respeito aos registros de vendas. Entre seus métodos estão:
- listar(): consulta os registros da tabela vendas e relaciona cada venda aos dados do cliente, usuário, caixa e cupom. As informações retornadas são utilizadas para preencher as tabelas de vendas da interface.
- listarVendasPorFormaPagamento(): consulta as tabelas pagamentoVenda, formasPagamento e vendas, agrupando os resultados por forma de pagamento. Retorna a quantidade de vendas, o valor médio e o valor total de cada forma de pagamento.
- pesquisar(String textoPesquisa): permite pesquisar vendas utilizando informações como número da venda, nome do cliente, nome do operador, código do cupom ou status.
- cadastrar(), atualizar() e excluir(): métodos responsáveis pelas operações de inclusão, alteração e exclusão de registros de vendas.
- totalVendas(): consulta a quantidade total de registros existentes na tabela de vendas.
Na implementação atual da TelaVendas, os métodos listar() e listarVendasPorFormaPagamento() são utilizados para carregar os dados apresentados nas tabelas. Os demais métodos fazem parte das operações disponibilizadas pelo controller, mas não estão todos integrados às ações da interface.

## Utilização do Model Vendas
A classe Model.Vendas representa os registros da tabela vendas e organiza os dados recebidos do banco de dados por meio de atributos, construtores, métodos getters e setters.
Os principais atributos são:
- idvendas: identificador da venda.
- dataVenda e horarioVenda: data e horário em que a venda foi realizada.
- valorTotal: valor total da venda.
- valorPago: valor recebido pelo pagamento.
- troco: valor devolvido ao cliente.
- quantidadeItens: quantidade de itens da venda.
- statusVenda: situação atual da venda.
- cliente_idcliente, usuario_idusuario, caixas_idcaixas e cupom_idcupom: identificadores relacionados às demais tabelas do banco de dados.
O modelo também possui os atributos nomeCliente, nomeUsuario, nomeCaixa e codigoCupom, utilizados para armazenar informações obtidas por meio dos relacionamentos realizados nas consultas SQL. Dessa forma, a tela consegue apresentar nomes e códigos associados à venda, em vez de exibir somente os identificadores das chaves estrangeiras.

## Funcionamento da Interface
As informações são organizadas em tabelas (JTable) e listas de seleção (JComboBox). O carregamento das tabelas utiliza DefaultTableModel, enquanto os dados são obtidos por meio dos controllers correspondentes. Na inicialização da tela, são carregadas as listas de seleção, as tabelas de vendas e de itens de venda, os dados agrupados por forma de pagamento e as contas bancárias. A interface também utiliza componentes como JFrame, JPanel, JTabbedPane, JTextField, JFormattedTextField e JButton.

## Objetivo
A tela de Vendas busca centralizar as informações das operações comerciais do supermercado, facilitando a visualização dos registros, o acompanhamento dos valores, a identificação dos responsáveis pelas vendas e a consulta das informações relacionadas aos pagamentos. Sua estrutura também reúne recursos de apoio ao controle financeiro e administrativo das vendas.

# PDV

# Venda e Pagamentos

# Caixa

O módulo Caixa permite controlar os pontos de atendimento e as movimentações financeiras realizadas pelos operadores. Nele é possível cadastrar caixas, realizar aberturas e fechamentos, registrar sangrias e suprimentos, acompanhar movimentações, realizar conferências, calcular saldos, identificar diferenças de caixa e consultar o histórico de operações.

### Model (Modelo)
O Model é responsável por representar os dados de cada tabela do banco de dados. Ele contém os atributos, métodos de acesso e as informações necessárias para realizar operações com os registros.

No módulo Caixa, os Models são responsáveis por armazenar informações de caixas, movimentações, operadores, suprimentos, sangrias, conferências, aberturas e fechamentos. Eles permitem que o sistema consulte, cadastre, atualize e exclua informações no banco de dados MySQL.

### Controller (Controlador)
O Controller é responsável por controlar a comunicação entre a View e o Model. Ele recebe as informações enviadas pelas telas, realiza validações, executa as operações solicitadas e encaminha os dados para serem armazenados ou consultados no banco de dados.

No módulo Caixa, os Controllers controlam as operações de cadastro, edição, exclusão, pesquisa, abertura, fechamento e consulta de movimentações. Também são responsáveis por buscar informações para preencher as tabelas e ComboBoxes das telas.

### View (Visualização)
A View representa a parte visual do sistema, ou seja, as telas que o usuário utiliza para realizar as operações. Ela contém campos de preenchimento, botões, tabelas, caixas de seleção e filtros de pesquisa.

No módulo Caixa, a View permite que o operador ou administrador visualize informações, cadastre registros, consulte movimentações, realize conferências e acompanhe as operações dos caixas.

Funcionamento das telas do módulo Caixa
A seguir, está a descrição das principais telas e suas funcionalidades dentro do padrão MVC.
1. Movimentações
# Financeiro

# Funcionários / RH
# Módulo de Recursos Humanos (RH) — Sistema de Supermercado

## 1. Visão geral

O módulo de Recursos Humanos (RH) é responsável por organizar e administrar informações relacionadas aos funcionários e à estrutura de trabalho do supermercado.

A interface foi desenvolvida em **Java**, utilizando a biblioteca **Java Swing**, por meio de uma tela principal chamada `Tela_Funcionarios_RH.java`.

A tela utiliza componentes gráficos, incluindo um `JTabbedPane`, que organiza as funcionalidades em diferentes abas, facilitando o acesso e a administração das informações.

O módulo utiliza o banco de dados MySQL para armazenar os registros e segue uma organização baseada em três partes principais:

- **View (Interface):** apresenta as telas e permite a interação do usuário.
- **Controller (Controle):** executa as operações e faz a comunicação com o banco de dados.
- **Model (Modelo):** representa os dados e atributos das entidades do sistema.

## 2. Funcionalidades da tela de RH

A tela principal reúne funcionalidades relacionadas à administração dos recursos humanos do supermercado.

### 2.1. Funcionários

Permite trabalhar com os dados cadastrais dos funcionários, incluindo:

- Nome;
- CPF;
- Telefone;
- Data de nascimento;
- Data de admissão;
- Cargo;
- Setor;
- Turno;
- Status do funcionário.

O cadastro utiliza informações selecionadas nos campos da interface e encaminha os dados ao `FuncionarioController`.

### 2.2. Cargos

Permite cadastrar e administrar os cargos existentes no supermercado.

As informações utilizadas incluem:

- Nome do cargo;
- Descrição;
- Salário-base;
- Carga horária;
- Adicional de periculosidade;
- Adicional de insalubridade;
- Adicional noturno;
- Status do cargo.

### 2.3. Setores

Permite organizar os setores da empresa, registrando:

- Nome do setor;
- Descrição;
- Status.

### 2.4. Turnos

Permite cadastrar e administrar os horários de trabalho.

Os dados utilizados incluem:

- Nome do turno;
- Descrição;
- Horário de início;
- Horário de saída;
- Início e término do intervalo;
- Duração do turno;
- Indicação de turno noturno;
- Status.

### 2.5. Escalas

Permite registrar e consultar escalas de trabalho dos funcionários.

O cadastro trabalha com:

- Período inicial;
- Período final;
- Identificação do funcionário.

O Controller também possui operações para consultar, atualizar e excluir escalas, além de obter o ID de um funcionário a partir de seu nome.

### 2.6. Controle de ponto

Permite registrar informações relacionadas ao ponto dos funcionários.

Os dados utilizados são:

- Data;
- Horário;
- Justificativa;
- Arquivo anexado;
- Identificação do funcionário;
- Cargo;
- Setor;
- Turno.

O sistema trabalha com o arquivo anexado em formato de bytes (`byte[]`), permitindo que o Controller encaminhe seu conteúdo ao banco de dados.

### 2.7. Afastamentos

Permite registrar informações sobre afastamentos dos funcionários.

Os dados utilizados incluem:

- Tipo de afastamento;
- Data de início;
- Data final;
- Quantidade de dias;
- Situação;
- Data de registro;
- Funcionário;
- Cargo;
- Setor;
- Turno.

### 2.8. Férias

A interface possui uma funcionalidade de cadastro de férias, utilizando:

- Data de início;
- Data final;
- Situação.

**Observação:** o código da tela chama um `FeriasController`, mas esse Controller não foi enviado para análise. Portanto, suas operações internas e sua implementação não estão documentadas nesta versão.

### 2.9. Metas

Permite registrar e acompanhar metas associadas aos funcionários.

Os dados utilizados incluem:

- Tipo de meta;
- Período inicial;
- Período final;
- Meta prevista;
- Resultado atual;
- Porcentagem atingida;
- Situação;
- Funcionário;
- Cargo;
- Setor;
- Turno.

## 3. Controllers utilizados

Os Controllers são responsáveis por receber as informações da interface, executar as operações necessárias e realizar a comunicação com o banco de dados.

A seguir estão os Controllers identificados no projeto.

### 3.1. FuncionarioController

Responsável pelas operações relacionadas aos funcionários.

**Principais métodos:**

| Método | Função |
|---|---|
| `cadastrar()` | Cadastra um funcionário. |
| `consultarPorCPF()` | Consulta um funcionário pelo CPF. |
| `consultarPorID()` | Consulta um funcionário pelo identificador. |
| `listarFuncionarios()` | Retorna os registros de funcionários. |
| `listar()` | Retorna uma lista de funcionários. |
| `atualizar()` | Atualiza os dados de um funcionário. |
| `excluir()` | Exclui um funcionário. |

### 3.2. CargosController

Responsável pelo gerenciamento dos cargos.

| Método | Função |
|---|---|
| `cadastrar()` | Cadastra um cargo. |
| `listar()` | Lista os cargos cadastrados. |
| `atualizar()` | Atualiza um cargo. |
| `excluir()` | Exclui um cargo. |

### 3.3. SetoresController

Responsável pelo gerenciamento dos setores.

| Método | Função |
|---|---|
| `cadastrar()` | Cadastra um setor. |
| `listar()` | Lista os setores cadastrados. |
| `atualizar()` | Atualiza um setor. |
| `excluir()` | Exclui um setor. |

### 3.4. TurnosController

Responsável pelo gerenciamento dos turnos de trabalho.

| Método | Função |
|---|---|
| `cadastrar()` | Cadastra um turno. |
| `listar()` | Lista os turnos. |
| `listarAtivos()` | Retorna os turnos ativos. |
| `consultarPorId()` | Consulta um turno pelo ID. |
| `pesquisarPorNome()` | Pesquisa turnos pelo nome. |
| `atualizar()` | Atualiza os dados de um turno. |
| `alterarStatus()` | Altera o status de um turno. |
| `excluir()` | Exclui um turno. |
| `consultarLista()` | Realiza uma consulta de turnos. |
| `montarTurno()` | Monta um objeto de turno a partir dos dados consultados. |

### 3.5. EscalaController

Responsável pelo gerenciamento das escalas de trabalho.

| Método | Função |
|---|---|
| `cadastrar()` | Cadastra uma escala. |
| `consultarPorID()` | Consulta uma escala pelo ID. |
| `listarEscalas()` | Lista as escalas cadastradas. |
| `atualizar()` | Atualiza uma escala. |
| `excluir()` | Exclui uma escala. |
| `obterIdFuncionarioPorNome()` | Obtém o ID do funcionário pelo nome. |

### 3.6. PontoController

Responsável pelas operações relacionadas ao controle de ponto.

| Método | Função |
|---|---|
| `cadastrar()` | Registra um ponto. |
| `listar()` | Lista os registros de ponto. |
| `atualizar()` | Atualiza um registro de ponto. |
| `excluir()` | Exclui um registro de ponto. |

### 3.7. AfastamentosController

Responsável pelo gerenciamento dos afastamentos.

| Método | Função |
|---|---|
| `cadastrar()` | Registra um afastamento. |
| `listar()` | Lista os afastamentos. |
| `atualizar()` | Atualiza um afastamento. |
| `excluir()` | Exclui um afastamento. |
| `IdsFuncionario()` | Obtém os IDs relacionados ao funcionário a partir do nome. |

### 3.8. MetasController

Responsável pelo gerenciamento das metas dos funcionários.

| Método | Função |
|---|---|
| `cadastrar()` | Cadastra uma meta. |
| `listar()` | Lista as metas. |
| `atualizar()` | Atualiza uma meta. |
| `excluir()` | Exclui uma meta. |
| `listarTiposMeta()` | Retorna os tipos de meta disponíveis. |

## 4. Models utilizados

Os Models representam as entidades e seus respectivos atributos, organizando os dados utilizados pelos Controllers.

As classes identificadas no arquivo enviado foram:

### 4.1. Cargos

Representa os cargos existentes no supermercado.

Atributos:

- `idcargos`
- `nome`
- `descricao`
- `salariobase`
- `cargahorario`
- `adicionalpericulosidade`
- `adicionalinsalubridade`
- `adicionalnoturno`
- `statuscargo`

### 4.2. Setores

Representa os setores da empresa.

Atributos:

- `idsetores`
- `nome`
- `descricao`
- `status`

### 4.3. Turnos

Representa os turnos de trabalho.

Atributos:

- `idturnos`
- `nome`
- `descricao`
- `horaInicio`
- `horaSaida`
- `intervaloInicio`
- `intervaloFinal`
- `duracaoTurno`
- `turnoNoturno`
- `status`

### 4.4. Escala

Representa os períodos de escala dos funcionários.

Atributos:

- `iddiasTrabalhados`
- `Funcionario_idFuncionario`
- `Funcionario_cargos_idcargos`
- `Funcionario_setores_idsetores`
- `Funcionario_turnos_idturnos`
- `periodoInicial`
- `periodoFinal`

### 4.5. Ponto

Representa os registros de ponto dos funcionários.

Atributos:

- `idponto`
- `Funcionario_idFuncionario`
- `Funcionario_cargos_idcargos`
- `Funcionario_setores_idsetores`
- `Funcionario_turnos_idturnos`
- `data`
- `horario`
- `justificativa`
- `arquivo`

### 4.6. Afastamentos

Representa os registros de afastamento dos funcionários.

Atributos:

- `idafastamentos`
- `Funcionario_idFuncionario`
- `Funcionario_cargos_idcargos`
- `Funcionario_setores_idsetores`
- `Funcionario_turnos_idturnos`
- `tipo`
- `inicio`
- `finalAfastamentos`
- `dias`
- `situacao`
- `dataRegistro`

### 4.7. Metas

Representa as metas associadas aos funcionários.

Atributos:

- `idmetas`
- `Funcionario_idFuncionario`
- `Funcionario_cargos_idcargos`
- `Funcionario_setores_idsetores`
- `Funcionario_turnos_idturnos`
- `tipo`
- `periodoInicial`
- `periodoFinal`
- `situacao`
- `metaPrevista`
- `resultadoAtual`
- `porcentagemAtingido`

**Observação:** os arquivos `Funcionario.java` e `Ferias.java` não estavam presentes no ZIP enviado. Por isso, seus atributos não foram incluídos nesta documentação.

## 5. Comunicação entre as camadas

O funcionamento do módulo segue o fluxo abaixo:

1. O usuário interage com a tela `Tela_Funcionarios_RH.java`.
2. A interface captura os valores preenchidos nos campos.
3. A tela chama o método correspondente do Controller.
4. O Controller executa a operação utilizando a conexão com o banco de dados.
5. O banco de dados armazena ou retorna as informações.
6. O resultado é utilizado pela aplicação para atualizar ou apresentar os dados na interface.

### Exemplo de fluxo

```text
Usuário
   |
   v
Tela_Funcionarios_RH.java
   |
   v
FuncionarioController
   |
   v
Conexao / MySQL
   |
   v
Banco de dados
```

Os Controllers utilizam classes como `Connection`, `PreparedStatement` e `ResultSet`, da biblioteca JDBC, para executar consultas e operações no banco de dados.

## 6. Organização dos arquivos

Com base nos arquivos enviados, a organização identificada é:

```text
src/
├── Control/
│   ├── FuncionarioController.java
│   ├── CargosController.java
│   ├── SetoresController.java
│   ├── TurnosController.java
│   ├── EscalaController.java
│   ├── PontoController.java
│   ├── AfastamentosController.java
│   └── MetasController.java
│
├── Model/
│   ├── Cargos.java
│   ├── Setores.java
│   ├── Turnos.java
│   ├── Escala.java
│   ├── Ponto.java
│   ├── Afastamentos.java
│   └── Metas.java
│
├── Conection/
│   └── Conexao.java
│
└── View/
    └── Tela_Funcionarios_RH.java
```

*Observação: a estrutura acima representa os arquivos identificados e os pacotes utilizados nos códigos enviados. A localização exata da classe de conexão e da tela deve ser conferida na estrutura completa do projeto.*

## 7. Tecnologias utilizadas

- **Java:** linguagem de programação utilizada no desenvolvimento.
- **Java Swing:** biblioteca utilizada para construir a interface gráfica.
- **NetBeans:** ambiente de desenvolvimento integrado utilizado no projeto.
- **MySQL:** sistema gerenciador de banco de dados.
- **JDBC:** tecnologia utilizada para realizar a comunicação entre Java e MySQL.

## 8. Considerações finais

O módulo de Recursos Humanos centraliza diferentes operações administrativas relacionadas aos funcionários do supermercado.

Sua estrutura permite organizar informações de funcionários, cargos, setores e turnos, além de disponibilizar funcionalidades relacionadas a escalas, ponto, afastamentos, férias e metas.

A separação entre interface, Controllers e Models contribui para a organização do código, facilita a manutenção e permite que cada parte do sistema tenha uma responsabilidade específica.

# Perfil de Usuários
Funcionamento da Tela Perfil dos Usuários
A tela Perfil dos Usuários funciona como um ambiente de gerenciamento das contas dos funcionários que utilizam o sistema do supermercado.

Quando o funcionário responsável acessa essa tela, ele pode consultar os usuários cadastrados, selecionar uma conta e realizar operações de gerenciamento.

O funcionamento acontece da seguinte forma:

## Cadastro de Usuários: o funcionário responsável pode cadastrar novas contas de acesso, preenchendo as informações necessárias, como nome, CPF, e-mail e senha.

## Consulta e Atualização: os usuários cadastrados são apresentados em uma tabela. Ao selecionar um registro, suas informações são carregadas nos campos correspondentes, permitindo consultar e atualizar os dados.

## Controle de Bloqueio: o responsável pode bloquear ou desbloquear uma conta, impedindo ou permitindo que determinado usuário realize o login no sistema.

## Gerenciamento de Permissões: é possível selecionar um usuário e definir quais áreas do sistema ele poderá acessar, como produtos, estoque, vendas, clientes, financeiro e relatórios.

## Histórico de Acessos: o sistema registra as entradas dos usuários, armazenando informações como data, horário, terminal e dispositivo. O responsável pode consultar esses registros e filtrar o histórico pelo funcionário selecionado.

Dessa forma, a tela permite que o funcionário responsável tenha controle sobre as contas cadastradas, mantenha as informações atualizadas, organize os níveis de acesso e acompanhe a utilização do sistema, contribuindo para a segurança e organização do supermercado.

Models e Controllers necessários
Model	Controller	Função
Usuario	UsuarioController	Cadastrar, listar, atualizar e bloquear usuários.
Funcionario	FuncionarioController	Consultar funcionários e preencher as ComboBoxes.
Permissoes	PermissoesController	Consultar, cadastrar e atualizar permissões de acesso.
HistoricoAcessos	HistoricoAcessosController	Registrar, listar e filtrar o histórico de acessos.
Tabela: usuario
Campo	Utilização
idusuario	Identifica o usuário cadastrado.
cpf	Localiza a conta do usuário.
senha	Armazena a nova senha após a recuperação.
# Solicitações
A Tela de Solicitação de Compras é responsável pelo gerenciamento das compras do supermercado, permitindo cadastrar, consultar, editar e excluir solicitações. Ela utiliza principalmente cinco tabelas do MySQL: compras, itensCompra, produtos, fornecedores e Funcionario. Sua implementação segue uma separação entre interface gráfica, Models e Controllers, utilizando Java Swing, JDBC e MySQL para realizar as operações de CRUD e manter a integridade dos dados.

# Alterar Senha
Anexo do usuário
agora faça para a tela recuperação de senha
Para a tela Recuperar Senha (TelaRecuperarSenha), você precisa de 1 Model e 1 Controller principal, pois a recuperação da senha está relacionada à tabela usuario.

1. Model e Controller necessários
Classe

Responsabilidade

Usuario.java

Representa os dados do usuário, incluindo CPF e senha.

UsuarioController.java

Localiza o usuário pelo CPF, valida os dados e atualiza a senha.

2. Funcionamento da tela
A tela Recuperar Senha permite que o usuário redefina sua senha de acesso ao sistema utilizando seu CPF como identificação.

## CPF

O usuário informa o CPF cadastrado no sistema. O Controller consulta a tabela usuario para verificar se existe uma conta associada ao documento informado.

## Nova Senha

O usuário informa a nova senha que deseja utilizar para acessar o sistema.

## Confirmar Nova Senha

O usuário digita novamente a nova senha. O sistema verifica se os dois campos possuem o mesmo conteúdo.

## Botão Salvar Nova Senha

Ao clicar, o sistema realiza as seguintes verificações:

Verifica se todos os campos foram preenchidos.

Valida se o CPF está cadastrado.

Verifica se a nova senha e a confirmação são iguais.

Verifica se a nova senha atende aos critérios de segurança.

Atualiza a senha do usuário no banco de dados.

Apresenta uma mensagem informando o resultado da operação.

## Botão Voltar ao Login

Retorna à tela de login sem realizar alterações na senha.
# Recuperação de Senha
1. Model e Controller necessários
Classe	Responsabilidade
Usuario.java	Representa os dados do usuário, incluindo sua senha.
UsuarioController.java	Valida a senha atual e realiza a alteração da nova senha.

2. Funcionamento da tela
A tela Alterar Senha permite que o funcionário que está utilizando o sistema modifique sua senha de acesso, garantindo que somente ele possa realizar essa alteração mediante a confirmação da senha atual.

Senha Atual

O funcionário informa sua senha atual. O sistema consulta o banco de dados e verifica se ela corresponde à senha cadastrada para sua conta.

Nova Senha

O funcionário informa a nova senha que deseja utilizar para acessar o sistema.

Confirmar Nova Senha

O funcionário digita novamente a nova senha. O sistema verifica se os dois campos possuem o mesmo conteúdo.

Botões Mostrar

Permitem visualizar ou ocultar o conteúdo digitado nos campos de senha.

Botão Cancelar

Cancela a operação e limpa os campos da tela, sem realizar alterações no banco de dados.

Botão Salvar Nova Senha

Realiza as seguintes verificações:
1. Verifica se todos os campos foram preenchidos.
2. Confirma se a senha atual está correta.
3. Verifica se a nova senha é diferente da atual.
4. Confirma se a nova senha e sua confirmação são iguais.
5. Atualiza a senha do usuário no banco de dados.

Tabela: usuario
Campo	Utilização
idusuario	Identifica o usuário cadastrado.
cpf	Localiza a conta do usuário.
senha	Armazena a nova senha após a recuperação.
