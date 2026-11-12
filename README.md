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

# Início

Painel principal do sistema, responsável por apresentar uma visão geral do supermercado. Exibe indicadores de vendas, caixas abertos, pedidos pendentes, contas a pagar e receber, notificações e entregas. Também disponibiliza atalhos para acessar rapidamente as principais funcionalidades e módulos do sistema.

# Produtos
A tela de Gerenciamento de Produtos é responsável pelo controle e organização dos produtos do supermercado. Por meio dela, é possível cadastrar, consultar, editar e excluir produtos, além de visualizar informações como nome, código, descrição, preço de custo, preço de venda, estoque, validade, categoria, marca e unidade de medida. No desenvolvimento, foi utilizado o Model/Produtos, responsável por representar os produtos e armazenar seus dados por meio de atributos, getters e setters, e o Control/ProdutosController, responsável pelas operações relacionadas aos produtos e pela comunicação entre o sistema e o banco de dados. O objetivo dessa tela é facilitar o gerenciamento dos produtos e manter suas informações organizadas.
# Gerenciamento de Produtos

# Estoque

# Fornecedores

Módulo responsável pelo gerenciamento completo dos fornecedores e empresas terceirizadas. Possui abas para cadastro de empresas, gerenciamento de categorias, contratos, documentos, avaliações de desempenho, contatos, condições de pagamento, registro de ocorrências e consulta ao histórico de compras. Seu objetivo é centralizar as informações e facilitar o acompanhamento do relacionamento entre o supermercado e seus fornecedores.

# Clientes

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

# Alterar Senha

# Recuperação de Senha

