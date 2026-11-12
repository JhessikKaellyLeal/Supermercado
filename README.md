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

# 🛒 Sistema de Gerenciamento de Supermercado

## 📦 Módulo de Controle de Estoque

O módulo de **Controle de Estoque** faz parte do desenvolvimento de um sistema de gerenciamento de supermercado, desenvolvido com a linguagem Java, utilizando a IDE NetBeans, a biblioteca Swing para construção das interfaces gráficas e o banco de dados MySQL para armazenamento e gerenciamento das informações.

O principal objetivo dessa tela é permitir o controle e o acompanhamento dos produtos armazenados no supermercado, facilitando o gerenciamento das quantidades disponíveis, entradas, saídas, perdas e demais movimentações realizadas no estoque.

A aplicação utiliza o padrão de arquitetura **MVC (Model-View-Controller)**, responsável por organizar o código, separar as responsabilidades e facilitar a manutenção e a expansão do sistema.

---

## 🖥️ Funcionalidades da Tela de Estoque

A tela de estoque foi planejada para oferecer diferentes funcionalidades relacionadas ao gerenciamento dos produtos e das quantidades armazenadas.

### 1. Consulta de Estoque

Permite consultar os produtos cadastrados e visualizar suas informações, facilitando o acompanhamento das quantidades disponíveis.

A consulta utiliza informações relacionadas às seguintes tabelas:
- `estoque`
- `produtos`
- `categorias`
- `marcas`
- `unidadeMedidas`

Também permite realizar pesquisas para localizar produtos específicos.

### 2. Cadastro de Estoque

Permite registrar informações referentes à quantidade atual e à quantidade reservada de determinado produto.

Os principais dados utilizados são:
- Produto;
- Categoria;
- Marca;
- Unidade de medida;
- Quantidade atual;
- Quantidade reservada;
- Data de atualização.

O cadastro utiliza a tabela `estoque`, relacionada à tabela `produtos` e às informações complementares do produto.

### 3. Movimentação de Estoque

Responsável pelo registro das movimentações realizadas nos produtos, permitindo acompanhar as alterações nas quantidades armazenadas.

Entre as operações previstas estão:
- Entrada de mercadorias;
- Saída de mercadorias;
- Registro da quantidade movimentada;
- Data e horário da movimentação;
- Tipo de movimentação;
- Documento de referência.

A funcionalidade utiliza a tabela `movimentacaoEstoque`.

### 4. Perdas de Estoque

Permite registrar produtos que sofreram algum tipo de perda, auxiliando no controle de mercadorias danificadas, vencidas ou inutilizadas.

As informações incluem:
- Produto;
- Quantidade perdida;
- Data do registro;
- Motivo da perda.

A funcionalidade utiliza a tabela `perdasEstoque`.

### 5. Controle de Lotes

Funcionalidade planejada para acompanhar os lotes de produtos cadastrados, permitindo organizar as mercadorias de acordo com seus registros e prazos de validade.

Utiliza a tabela `lotesProdutos`.

### 6. Controle de Validade

Permite acompanhar os prazos de validade dos produtos, facilitando a identificação de mercadorias próximas do vencimento e de produtos vencidos.

Essa funcionalidade poderá utilizar informações das tabelas `produtos` e `lotesProdutos`.

### 7. Produtos Próximos do Vencimento

Funcionalidade destinada à identificação de produtos que estão próximos da data de vencimento, permitindo que o supermercado tome providências para evitar perdas.

### 8. Produtos Vencidos e com Estoque Baixo

Permite identificar mercadorias vencidas e produtos que apresentam quantidades inferiores ao estoque necessário.

Essa funcionalidade auxilia no planejamento de reposições e na prevenção de prejuízos.

### 9. Relatório de Estoque

Funcionalidade planejada para apresentar informações organizadas sobre os produtos e suas respectivas quantidades, possibilitando uma visão geral da situação do estoque.

---

## 🗄️ Banco de Dados MySQL

O sistema utiliza o banco de dados **supermercado**, desenvolvido em MySQL.

As tabelas foram organizadas de acordo com os setores e as funcionalidades do supermercado, estabelecendo relacionamentos por meio de chaves primárias (PRIMARY KEY) e chaves estrangeiras (FOREIGN KEY).

### Tabelas do banco de dados

O banco de dados possui as seguintes tabelas:

### 1. Recursos Humanos

- `cargos`
- `setores`
- `turnos`
- `Funcionario`
- `usuario`
- `diasTrabalhados`
- `escala`
- `ponto`
- `ferias`
- `afastamentos`
- `metas`
- `permissoes`
- `historicoAcessos`

### 2. Produtos e Controle de Estoque

- `categorias`
- `marcas`
- `unidadeMedidas`
- `produtos`
- `codigoBarras`
- `etiquetas`
- `estoque`
- `lotesProdutos`
- `movimentacaoEstoque`
- `perdasEstoque`

### 3. Fornecedores e Financeiro

- `fornecedores`
- `contratosFornecedores`
- `contasPagar`
- `contasReceber`
- `contasFinanceiras`
- `movimentacoesFinanceiras`

### 4. Vendas e Frente de Caixa

- `formasPagamento`
- `condicoesPagamento`
- `cliente`
- `endereco`
- `caixas`
- `aberturaCaixa`
- `fechamentoCaixa`
- `sangria`
- `saldoCaixa`
- `vendas`
- `cupom`
- `pedidos`

### 5. Entregas

- `veiculos`
- `taxasEntrega`
- `entregas`

---

## 🧩 Arquitetura MVC

O projeto utiliza o padrão arquitetural MVC (Model-View-Controller), que divide a aplicação em três partes principais.

### Model

Responsável por representar as entidades do banco de dados, armazenando os atributos e disponibilizando métodos construtores, getters e setters.

### View

Responsável pela interface gráfica do sistema, desenvolvida com Java Swing no NetBeans por meio de formulários JFrame.

A tela principal deste módulo é a `TelaEstoque`, responsável pela interação entre o usuário e as funcionalidades de controle de estoque.

### Controller

Responsável pela comunicação entre os Models e o banco de dados MySQL, executando operações como cadastro, consulta, alteração, exclusão e pesquisa de registros.

A conexão com o banco de dados é realizada por meio da classe `Conexao`, utilizando JDBC.

---

## ☕ Models e Controllers

As classes foram organizadas nos pacotes `Model`, `Control` e `Conection`.

### Models e Controllers já trabalhados

| Model | Controller | Responsabilidade |
|---|---|---|
| `Produtos` | `ProdutosController` | Cadastro, consulta, alteração, exclusão e pesquisa de produtos. |
| `Estoque` | `EstoqueController` | Cadastro e gerenciamento das informações de estoque. |
| `Categorias` | `CategoriasController` | Gerenciamento das categorias dos produtos. |
| `Marcas` | `MarcasController` | Gerenciamento das marcas dos produtos. |
| `UnidadeMedidas` | `UnidadeMedidasController` | Gerenciamento das unidades de medida. |

### Models e Controllers previstos para desenvolvimento futuro

As seguintes classes estão previstas para ampliar as funcionalidades do sistema:

| Model | Controller | Responsabilidade |
|---|---|---|
| `MovimentacaoEstoque` | `MovimentacaoEstoqueController` | Registro e gerenciamento das entradas e saídas de produtos. |
| `PerdasEstoque` | `PerdasEstoqueController` | Registro e consulta das perdas de mercadorias. |
| `LotesProdutos` | `LotesProdutosController` | Gerenciamento dos lotes e informações de validade. |
| `CodigoBarras` | `CodigoBarrasController` | Gerenciamento dos códigos de barras dos produtos. |
| `Etiquetas` | `EtiquetasController` | Gerenciamento das etiquetas dos produtos. |

As demais entidades do banco de dados também poderão receber suas respectivas classes Model e Controller conforme o desenvolvimento dos módulos de Recursos Humanos, Financeiro, Vendas, Fornecedores e Entregas.

**Observação:** os tipos de movimentação e os motivos de perdas são informações armazenadas em campos VARCHAR nas respectivas tabelas, não sendo tabelas independentes do banco de dados.

---

## 🔗 Tecnologias utilizadas

- **Java:** linguagem utilizada no desenvolvimento da aplicação.
- **NetBeans IDE:** ambiente de desenvolvimento.
- **Java Swing:** construção das interfaces gráficas.
- **MySQL:** sistema de gerenciamento do banco de dados.
- **JDBC:** comunicação entre Java e MySQL.
- **MVC:** padrão de organização da aplicação.
- **Git e GitHub:** versionamento e armazenamento do código-fonte.

---

## 🎯 Objetivo do Projeto

O objetivo do projeto é desenvolver um sistema completo de gerenciamento de supermercado, integrando diferentes setores em uma única aplicação.

O módulo de estoque busca proporcionar maior organização e controle das mercadorias, reduzindo erros de gerenciamento, facilitando o acompanhamento dos produtos e contribuindo para uma administração mais eficiente.

O desenvolvimento é realizado de maneira modular, permitindo implementar novas funcionalidades e integrar novos setores ao sistema conforme a evolução do projeto.

---

## 🚀 Desenvolvimento

O projeto encontra-se em desenvolvimento, com funcionalidades implementadas gradualmente.

Novos módulos, telas, Models e Controllers serão adicionados conforme a evolução da aplicação, buscando manter uma estrutura organizada, funcional e de fácil manutenção.



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

A tela permite que o funcionário do supermercado realize o cadastro e o gerenciamento dos pedidos feitos pelos clientes. Por meio dela, é possível registrar as informações do pedido, como número, data, status, CPF do cliente, endereço e horário de entrega. Também permite consultar pedidos cadastrados, visualizar seus detalhes, realizar a separação e conferência dos produtos, alterar o status e cancelar pedidos quando necessário. As informações são armazenadas e consultadas no banco de dados, utilizando a tabela pedidos e suas relações com as tabelas de clientes, usuários e entregas. Essa tela utiliza a classe Pedidos do Model e a classe PedidosController do Control. Nela são divididas em 6 abas:
##Cadastro
##Consulta e Detalhes
##Separação
##Conferência
##Alteração de Status
##Cancelamento


# Entregas

A tela permite que o funcionário do supermercado realize o cadastro e o gerenciamento das entregas dos pedidos feitos pelos clientes. Por meio dela, é possível cadastrar entregadores, veículos, taxas de entrega, endereços e regiões atendidas, além de organizar datas, horários e agendas de entrega. Também permite distribuir as entregas entre os funcionários responsáveis, atualizar seus status, registrar tentativas de entrega e confirmar quando um pedido é recebido pelo cliente. As informações são armazenadas e consultadas no banco de dados, utilizando a tabela entregas e suas relações com as tabelas de clientes, veículos, taxas de entrega e funcionários. Essa tela utiliza a classe Entrega, Endereco, TaxasEntregas, Funcionario e Veiculos do Model e a classe EntregaController, EnderecoController, TaxasEntregasController, FuncionarioController e VeiculosController do Control. Nelas são divididas em 13 abas:
##Entregadores
##Veículos
##Taxas
##Endereços
##Entregas
##Regiões
##Data e Horário
##Agenda
##Distribuição
##Status
##Tentativas
##Confirmação
##Histórico e Relatório


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
# Módulo de Recursos Humanos (RH)

## 1. Sobre o módulo

O módulo de Recursos Humanos (RH) faz parte do sistema de gerenciamento de supermercado, desenvolvido em Java, utilizando Java Swing para a construção das interfaces gráficas e MySQL para o armazenamento dos dados.

Seu objetivo é centralizar o gerenciamento dos funcionários e das informações relacionadas à organização do trabalho, permitindo o controle de cargos, setores, turnos, escalas, registros de ponto, férias, afastamentos e metas.

O módulo também está relacionado ao gerenciamento de usuários, permissões de acesso e histórico de acessos ao sistema.

## 2. Tecnologias utilizadas

- **Java:** linguagem de programação utilizada no desenvolvimento do sistema.
- **Java Swing:** biblioteca utilizada para construir as interfaces gráficas.
- **NetBeans:** ambiente de desenvolvimento utilizado na construção do projeto.
- **MySQL:** sistema gerenciador de banco de dados utilizado para armazenar as informações.
- **JDBC:** tecnologia utilizada para estabelecer a comunicação entre a aplicação Java e o banco de dados.

## 3. Organização do código

O módulo utiliza uma organização baseada em três componentes principais:

- **View:** contém as telas e os componentes gráficos com os quais o usuário interage.
- **Controller:** concentra métodos responsáveis pelas operações e pelo gerenciamento dos dados.
- **Model:** representa as entidades e estruturas de dados utilizadas pelo sistema.

Essa organização busca separar a interface gráfica das operações relacionadas aos dados.

## 4. Funcionalidades do módulo

### 4.1. Gerenciamento de funcionários

Permite trabalhar com os dados dos funcionários, incluindo:

- Cadastro de funcionários;
- Consulta de funcionários;
- Edição de informações;
- Identificação por CPF;
- Associação do funcionário a um cargo, setor e turno;
- Controle da situação do funcionário.

### 4.2. Gerenciamento de cargos

Permite cadastrar e organizar os cargos existentes no supermercado, armazenando informações como:

- Nome e descrição do cargo;
- Salário-base;
- Carga horária;
- Adicional de periculosidade;
- Adicional de insalubridade;
- Adicional noturno;
- Situação do cargo.

### 4.3. Gerenciamento de setores

Permite organizar os setores do supermercado, armazenando o nome, a descrição e a situação de cada setor.

### 4.4. Gerenciamento de turnos

Permite cadastrar os turnos de trabalho, registrando:

- Nome e descrição;
- Horário de início e saída;
- Horários de intervalo;
- Duração do turno;
- Identificação de turno noturno;
- Situação do turno.

### 4.5. Gerenciamento de escalas

Armazena os períodos de escala associados aos funcionários, considerando os dados de funcionário, cargo, setor e turno.

### 4.6. Controle de ponto

Armazena os registros de ponto dos funcionários, incluindo data, horário, justificativa e possibilidade de anexar um arquivo relacionado ao registro.

### 4.7. Gerenciamento de férias

O banco de dados possui estrutura para registrar períodos de férias e suas respectivas situações, além de uma tabela de associação entre funcionários e férias.

### 4.8. Gerenciamento de afastamentos

Permite armazenar informações sobre afastamentos, como:

- Tipo de afastamento;
- Data de início e término;
- Quantidade de dias;
- Situação;
- Data de registro;
- Funcionário relacionado.

### 4.9. Gerenciamento de metas

A estrutura permite registrar metas associadas aos funcionários, contendo:

- Tipo de meta;
- Período inicial e final;
- Meta prevista;
- Resultado atual;
- Porcentagem atingida;
- Situação.

### 4.10. Usuários e permissões

O módulo também se relaciona ao controle de usuários e permissões do sistema.

A estrutura permite:
- Associar um usuário a um funcionário;
- Armazenar nome, CPF, e-mail e senha;
- Controlar o bloqueio de usuários;
- Definir permissões de acesso às diferentes áreas do sistema.

### 4.11. Histórico de acessos

O banco de dados possui uma tabela para armazenar informações sobre os acessos dos usuários, incluindo data, horário, IP do terminal, dispositivo e usuário relacionado.

## 5. Estrutura do banco de dados

O módulo RH utiliza as seguintes tabelas do banco de dados `supermercado`:

| Tabela | Finalidade |
|---|---|
| `Funcionario` | Armazena os dados dos funcionários. |
| `cargos` | Armazena os cargos e suas características. |
| `setores` | Armazena os setores do supermercado. |
| `turnos` | Armazena os horários e características dos turnos. |
| `escala` | Registra os períodos de escala dos funcionários. |
| `ponto` | Armazena os registros de ponto. |
| `ferias` | Armazena os períodos e situações das férias. |
| `Funcionario_has_ferias` | Relaciona funcionários aos registros de férias. |
| `afastamentos` | Armazena os registros de afastamentos. |
| `metas` | Armazena as metas relacionadas aos funcionários. |
| `diasTrabalhados` | Armazena os dias da semana. |
| `Funcionario_has_diasTrabalhados` | Relaciona funcionários aos dias trabalhados. |
| `usuario` | Armazena os dados dos usuários do sistema. |
| `permissoes` | Define as permissões disponíveis para as telas do sistema. |
| `usuario_has_permissoes` | Relaciona usuários às permissões. |
| `historicoAcessos` | Armazena informações sobre os acessos dos usuários. |

## 6. Relacionamentos entre as tabelas

A estrutura do banco

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
1. Tabelas utilizadas
Tabela	Campos utilizados	Finalidade
usuario	idusuario, nome, cpf, email, senha, bloqueio, Funcionario_idFuncionario	Cadastro, consulta, atualização e bloqueio dos usuários.
Funcionario	idFuncionario, nome	Identificação e consulta dos funcionários vinculados aos usuários.
permissoes	idpermissoes, tipo, telaProdutos, telaEstoque, telaCompras, telaClientes, telaPromocoes, telaCaixa, telaVendas, telaPDV, telaPedidos, telaEntregas, telaRh, telaFinaceiro, telaRelatorios	Controle das permissões de acesso aos módulos do sistema.
historicoAcessos	idhistoricoAcessos, dataAcesso, horarioAcesso, terminal_ip, dispositivo, usuario_idusuario	Registro e consulta dos acessos realizados pelos usuários.

2. Utilização das tabelas por área
Área da tela	Tabelas utilizadas
Cadastro de Usuários	usuario, Funcionario
Perfil do Usuário	usuario, Funcionario
Permissões de Acesso	permissoes, usuario, Funcionario
Histórico de Acessos	historicoAcessos, usuario, Funcionario
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
Campo	Utilização
idusuario	Identifica o usuário que está realizando a alteração.
senha	Armazena a senha atual e recebe a nova senha após a validação.
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
