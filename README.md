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

##Principais funcionalidades:
- Cadastrar novas promoções para os produtos.
- Definir o preço promocional.
- Informar a data de início e término da promoção.
- Gerenciar as promoções cadastradas.
- Facilitar a identificação dos produtos em oferta.

# Tabela do banco utilizada no MYsql

CREATE TABLE IF NOT EXISTS promocao (
  idpromocao INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  tipo VARCHAR(45) NOT NULL,
  status TINYINT NOT NULL,
  dataInicio VARCHAR(45) NOT NULL,
  dataFinal VARCHAR(45) NOT NULL,
  regras VARCHAR(200) NULL,
  desconto INT NOT NULL,
  qttMinima INT NOT NULL
);

## Tela de Promoções / Aba Cupom

Aba responsável pelo cadastro e gerenciamento de cupons de desconto da loja. Permite criar cupons promocionais para oferecer benefícios aos clientes durante suas compras, incentivando o consumo e proporcionando descontos em produtos ou no valor total dos pedidos.

**Principais funcionalidades:**
- Cadastrar novos cupons de desconto.
- Definir códigos exclusivos para os cupons.
- Estabelecer o tipo e o valor do desconto.
- Definir o período de validade dos cupons.
- Configurar regras de utilização e limites de uso.
- Gerenciar os cupons cadastrados.

# Tabela do banco utilizada no MYsql

CREATE TABLE IF NOT EXISTS cupom (
  idcupom INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  codigoCupom VARCHAR(45) NOT NULL,
  tipoDesconto VARCHAR(45) NOT NULL,
  valorDesconto DOUBLE NOT NULL,
  valorMinimoCompra DOUBLE NOT NULL,
  qttMaximaUso INT NOT NULL,
  limitePorCliente INT NOT NULL,
  tipoUso VARCHAR(45) NOT NULL,
  dataInicio VARCHAR(45) NOT NULL,
  dataFinal VARCHAR(45) NOT NULL,
  observacao VARCHAR(200) NULL,
  promocao_idpromocao INT NOT NULL
);

Ambas as duas abas permitem realizar o cadastro de determinadas funcoes dentro do sistema de supermercado

# Pedidos

# Entregas

# Vendas

# PDV

# Venda e Pagamentos

# Caixa

# Financeiro

# Funcionários / RH

# Perfil de Usuários

# Solicitações
A Tela de Solicitação de Compras é responsável pelo gerenciamento das compras do supermercado, permitindo cadastrar, consultar, editar e excluir solicitações. Ela utiliza principalmente cinco tabelas do MySQL: compras, itensCompra, produtos, fornecedores e Funcionario. Sua implementação segue uma separação entre interface gráfica, Models e Controllers, utilizando Java Swing, JDBC e MySQL para realizar as operações de CRUD e manter a integridade dos dados.

# Alterar Senha

# Recuperação de Senha

