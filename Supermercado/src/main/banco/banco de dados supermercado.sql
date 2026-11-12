-- CRIAÇÃO DO BANCO DE DADOS

CREATE DATABASE supermercado;

-- INICIALIZAR O BANCO DE DADOS CRIADO
USE supermercado;


-- CRIANDO AS TABELAS QUE NÃO TEM CHAVE ESTRANGEIRA
-- DROP TABLE nomeTabela;
CREATE TABLE IF NOT EXISTS cargos (
  idcargos INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  descricao VARCHAR(300) NULL,
  salarioBase DOUBLE NOT NULL,
  cargaHoraria INT NOT NULL,
  adicionalPericulosidade DOUBLE NULL,
  adicionalInsalubridade DOUBLE NULL,
  statusCargo TINYINT NOT NULL,
  adicionalNoturno DOUBLE NULL
);

CREATE TABLE IF NOT EXISTS setores (
  idsetores INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  descricao VARCHAR(200) NULL,
  status TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS turnos (
  idturnos INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  descricao VARCHAR(200) NULL,
  horaInicio VARCHAR(45) NOT NULL,
  horaSaida VARCHAR(45) NOT NULL,
  intervaloInicio VARCHAR(45) NOT NULL,
  intervaloFinal VARCHAR(45) NOT NULL,
  duracaoTurno INT NOT NULL,
  turnoNoturno TINYINT NULL,
  status TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS Funcionario (
  idFuncionario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(200) NOT NULL,
  dataNascimento VARCHAR(45) NOT NULL,
  cpf VARCHAR(45) NOT NULL,
  telefone VARCHAR(45) NOT NULL,
  dataAdmissao VARCHAR(45) NOT NULL,
  statusFuncionario TINYINT NOT NULL,
  cargos_idcargos INT NOT NULL,
  setores_idsetores INT NOT NULL,
  turnos_idturnos INT NOT NULL,
UNIQUE KEY cpf_UNIQUE (cpf),
  CONSTRAINT fk_Funcionario_cargos_idcargos FOREIGN KEY (cargos_idcargos) REFERENCES cargos (idcargos),
  CONSTRAINT fk_Funcionario_setores_idsetores FOREIGN KEY (setores_idsetores) REFERENCES setores (idsetores),
  CONSTRAINT fk_Funcionario_turnos_idturnos FOREIGN KEY (turnos_idturnos) REFERENCES turnos (idturnos)
);

CREATE TABLE IF NOT EXISTS usuario (
  idusuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(200) NOT NULL,
  cpf VARCHAR(45) NOT NULL,
  email VARCHAR(200) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  bloqueio TINYINT NULL,
  Funcionario_idFuncionario INT NOT NULL,
UNIQUE KEY cpf_UNIQUE (cpf),
  UNIQUE KEY email_UNIQUE (email),
  CONSTRAINT fk_usuario_Funcionario_idFuncionario FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario (idFuncionario)
);

CREATE TABLE IF NOT EXISTS diasTrabalhados (
  iddiasTrabalhados INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS escala (
  idescala INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  periodoInicial VARCHAR(45) NOT NULL,
  periodoFinal VARCHAR(45) NOT NULL,
  Funcionario_idFuncionario INT NOT NULL,
  Funcionario_cargos_idcargos INT NOT NULL,
  Funcionario_setores_idsetores INT NOT NULL,
  Funcionario_turnos_idturnos INT NOT NULL,
CONSTRAINT fk_escala_Funcionario_idFuncionario FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario (idFuncionario)
);

CREATE TABLE IF NOT EXISTS ponto (
  idponto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  data VARCHAR(45) NOT NULL,
  horario VARCHAR(45) NOT NULL,
  justificativa VARCHAR(200) NULL,
  arquivo LONGBLOB NULL,
  Funcionario_idFuncionario INT NOT NULL,
  Funcionario_cargos_idcargos INT NOT NULL,
  Funcionario_setores_idsetores INT NOT NULL,
  Funcionario_turnos_idturnos INT NOT NULL,
CONSTRAINT fk_ponto_Funcionario_idFuncionario FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario (idFuncionario)
);

CREATE TABLE IF NOT EXISTS ferias (
  idferias INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  inicio VARCHAR(45) NOT NULL,
  final VARCHAR(45) NOT NULL,
  situacao VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS afastamentos (
  idafastamentos INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tipo VARCHAR(45) NOT NULL,
  inicio VARCHAR(45) NOT NULL,
  final VARCHAR(45) NOT NULL,
  dias VARCHAR(45) NOT NULL,
  situacao VARCHAR(45) NOT NULL,
  dataRegistro VARCHAR(45) NOT NULL,
  Funcionario_idFuncionario INT NOT NULL,
  Funcionario_cargos_idcargos INT NOT NULL,
  Funcionario_setores_idsetores INT NOT NULL,
  Funcionario_turnos_idturnos INT NOT NULL,
CONSTRAINT fk_afastamentos_Funcionario_idFuncionario FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario (idFuncionario)
);

CREATE TABLE IF NOT EXISTS metas (
  idmetas INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tipo VARCHAR(45) NOT NULL,
  periodoInicial VARCHAR(45) NOT NULL,
  periodoFinal VARCHAR(45) NOT NULL,
  metaPrevista DOUBLE NOT NULL,
  resultadoAtual DOUBLE NULL,
  porcentagemAtingido DOUBLE NULL,
  situacao VARCHAR(45) NOT NULL,
  Funcionario_idFuncionario INT NOT NULL,
  Funcionario_cargos_idcargos INT NOT NULL,
  Funcionario_setores_idsetores INT NOT NULL,
  Funcionario_turnos_idturnos INT NOT NULL,
CONSTRAINT fk_metas_Funcionario_idFuncionario FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario (idFuncionario)
);
CREATE TABLE IF NOT EXISTS endereco (
  idEndereco INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  bairro VARCHAR(100) NOT NULL,
  cep VARCHAR(45) NOT NULL,
  cidade VARCHAR(100) NOT NULL,
  estado VARCHAR(45) NOT NULL,
  rua VARCHAR(45) NOT NULL,
  numero VARCHAR(45) NOT NULL,
  complemento VARCHAR(100) NULL,
  cliente_idcliente INT NOT NULL
);
CREATE TABLE IF NOT EXISTS permissoes (
  idpermissoes INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tipo VARCHAR(45) NOT NULL,
  telaProdutos TINYINT NOT NULL,
  telaEstoque TINYINT NOT NULL,
  telaCompras TINYINT NOT NULL,
  telaClientes TINYINT NOT NULL,
  telaPromocoes TINYINT NOT NULL,
  telaCaixa TINYINT NOT NULL,
  telaVendas TINYINT NOT NULL,
  telaPDV TINYINT NOT NULL,
  telaPedidos TINYINT NOT NULL,
  telaEntregas TINYINT NOT NULL,
  telaRh TINYINT NOT NULL,
  telaFinaceiro TINYINT NOT NULL,
  telaRelatorios TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS historicoAcessos (
  idhistoricoAcessos INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  dataAcesso VARCHAR(45) NOT NULL,
  horarioAcesso VARCHAR(45) NOT NULL,
  terminal_ip VARCHAR(45) NOT NULL,
  dispositivo VARCHAR(45) NOT NULL,
  usuario_idusuario INT NOT NULL,
CONSTRAINT fk_historicoAcessos_usuario_idusuario FOREIGN KEY (usuario_idusuario) REFERENCES usuario (idusuario)
);

CREATE TABLE IF NOT EXISTS categorias (
  idcategorias INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(200) NOT NULL,
  descricao VARCHAR(300) NULL,
  status TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS marcas (
  idmarcas INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(200) NOT NULL,
  status TINYINT NOT NULL,
  descricao VARCHAR(300) NULL
);

CREATE TABLE IF NOT EXISTS unidadeMedidas (
  idunidadeMedidas INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  codigo INT NOT NULL,
  nome VARCHAR(45) NOT NULL,
  status TINYINT NOT NULL,
  descricao VARCHAR(300) NULL
);

CREATE TABLE IF NOT EXISTS produtos (
  idprodutos INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(200) NOT NULL,
  codigo VARCHAR(200) NOT NULL,
  descricao VARCHAR(300) NULL,
  precoCusto DOUBLE NOT NULL,
  precoVenda DOUBLE NOT NULL,
  situacao TINYINT NOT NULL,
  estoque INT NOT NULL,
  validade VARCHAR(45) NOT NULL,
  imagem LONGBLOB NULL,
  categorias_idcategorias INT NOT NULL,
  marcas_idmarcas INT NOT NULL,
  unidadeMedidas_idunidadeMedidas INT NOT NULL,
CONSTRAINT fk_produtos_categorias_idcategorias FOREIGN KEY (categorias_idcategorias) REFERENCES categorias (idcategorias),
  CONSTRAINT fk_produtos_marcas_idmarcas FOREIGN KEY (marcas_idmarcas) REFERENCES marcas (idmarcas),
  CONSTRAINT fk_produtos_unidadeMedidas_idunidadeMedidas FOREIGN KEY (unidadeMedidas_idunidadeMedidas) REFERENCES unidadeMedidas (idunidadeMedidas)
);

CREATE TABLE IF NOT EXISTS codigoBarras (
  idcodigoBarras INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  codigo VARCHAR(200) NOT NULL,
  imagem LONGBLOB NULL,
  tipo VARCHAR(45) NOT NULL,
  status TINYINT NOT NULL,
  descricao VARCHAR(300) NULL,
  produtos_idprodutos INT NOT NULL,
  produtos_categorias_idcategorias INT NOT NULL,
  produtos_marcas_idmarcas INT NOT NULL,
  produtos_unidadeMedidas_idunidadeMedidas INT NOT NULL,
CONSTRAINT fk_codigoBarras_produtos_idprodutos FOREIGN KEY (produtos_idprodutos) REFERENCES produtos (idprodutos)
);

CREATE TABLE IF NOT EXISTS etiquetas (
  idetiquetas INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tipo VARCHAR(45) NOT NULL,
  imagem LONGBLOB NOT NULL
);

CREATE TABLE IF NOT EXISTS fornecedores (
  idfornecedores INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nomeResponsavel VARCHAR(200) NOT NULL,
  telefone VARCHAR(45) NOT NULL,
  email VARCHAR(200) NOT NULL,
  ramal VARCHAR(45) NULL,
  cnpj VARCHAR(45) NOT NULL,
  razaoSocial VARCHAR(200) NOT NULL,
  endereco VARCHAR(300) NOT NULL,
  tipo VARCHAR(45) NULL,
  cep VARCHAR(45) NOT NULL,
  inscricaoestadual VARCHAR(45) NOT NULL,
UNIQUE KEY cnpj_UNIQUE (cnpj)
);

CREATE TABLE IF NOT EXISTS contratosFornecedores (
  idcontratosFornecedores INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  numero INT NOT NULL,
  dataInicio VARCHAR(45) NOT NULL,
  dataFinal VARCHAR(45) NOT NULL,
  valorMensal DOUBLE NOT NULL,
  tipo VARCHAR(45) NOT NULL,
  descricao VARCHAR(300) NULL,
  prazo INT NOT NULL,
  renovacaoAutomatica TINYINT NOT NULL,
  valorTotal DOUBLE NOT NULL,
  multa DOUBLE NOT NULL,
  clausulaRecisao VARCHAR(45) NOT NULL,
  observacao VARCHAR(45) NULL,
  arquivo LONGBLOB NOT NULL,
  fornecedores_idfornecedores INT NOT NULL,
CONSTRAINT fk_contratosFornecedores_fornecedores_idfornecedores FOREIGN KEY (fornecedores_idfornecedores) REFERENCES fornecedores (idfornecedores)
);

CREATE TABLE IF NOT EXISTS formasPagamento (
  idformasPagamento INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS condicoesPagamento (
  idcondicoesPagamento INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  quantidadeParcelas INT NOT NULL,
  intervaloDias INT NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente (
  idcliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(200) NOT NULL,
  cpf VARCHAR(45) NOT NULL,
  rg VARCHAR(45) NULL,
  dataNascimento VARCHAR(45) NULL,
  genero VARCHAR(45) NULL,
  email VARCHAR(200) NULL,
  telefone VARCHAR(45) NOT NULL,
  status TINYINT NOT NULL,
  senha VARCHAR(45) NULL,
UNIQUE KEY cpf_UNIQUE (cpf)
);

CREATE TABLE IF NOT EXISTS endereco (
  idEndereco INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  bairro VARCHAR(100) NOT NULL,
  cep VARCHAR(45) NOT NULL,
  cidade VARCHAR(100) NOT NULL,
  estado VARCHAR(45) NOT NULL,
  rua VARCHAR(45) NOT NULL,
  numero VARCHAR(45) NOT NULL,
  cliente_idcliente INT NOT NULL,
CONSTRAINT fk_endereco_cliente_idcliente FOREIGN KEY (cliente_idcliente) REFERENCES cliente (idcliente)
);

CREATE TABLE IF NOT EXISTS caixas (
  idcaixas INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  tipo VARCHAR(45) NOT NULL,
  status TINYINT NOT NULL,
  observacao VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS aberturaCaixa (
  idaberturaCaixa INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  dataAbertura VARCHAR(45) NOT NULL,
  horarioAbertura VARCHAR(45) NOT NULL,
  valorInicial DOUBLE NOT NULL,
  caixas_idcaixas INT NOT NULL,
  Funcionario_idFuncionario INT NOT NULL,
  Funcionario_cargos_idcargos INT NOT NULL,
  Funcionario_setores_idsetores INT NOT NULL,
  Funcionario_turnos_idturnos INT NOT NULL,
CONSTRAINT fk_aberturaCaixa_caixas_idcaixas FOREIGN KEY (caixas_idcaixas) REFERENCES caixas (idcaixas),
  CONSTRAINT fk_aberturaCaixa_Funcionario_idFuncionario FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario (idFuncionario)
);

CREATE TABLE IF NOT EXISTS fechamentoCaixa (
  idfechamentoCaixa INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  dataFechamento VARCHAR(45) NOT NULL,
  horarioFechamento VARCHAR(45) NOT NULL,
  valorFinal DOUBLE NOT NULL,
  aberturaCaixa_idaberturaCaixa INT NOT NULL,
  Funcionario_idFuncionario INT NOT NULL,
  Funcionario_cargos_idcargos INT NOT NULL,
  Funcionario_setores_idsetores INT NOT NULL,
  Funcionario_turnos_idturnos INT NOT NULL,
CONSTRAINT fk_fechamentoCaixa_aberturaCaixa_idaberturaCaixa FOREIGN KEY (aberturaCaixa_idaberturaCaixa) REFERENCES aberturaCaixa (idaberturaCaixa),
  CONSTRAINT fk_fechamentoCaixa_Funcionario_idFuncionario FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario (idFuncionario)
);

CREATE TABLE IF NOT EXISTS sangria (
  idsangria INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  dataSolicitacao VARCHAR(45) NOT NULL,
  motivo VARCHAR(45) NOT NULL,
  observacao VARCHAR(45) NULL,
  valorSolicitado DOUBLE NOT NULL,
  valorRetirado DOUBLE NOT NULL,
  horaSolicitacao VARCHAR(45) NOT NULL,
  aberturaCaixa_idaberturaCaixa INT NOT NULL,
  Funcionario_idFuncionario INT NOT NULL,
  Funcionario_cargos_idcargos INT NOT NULL,
  Funcionario_setores_idsetores INT NOT NULL,
  Funcionario_turnos_idturnos INT NOT NULL,
CONSTRAINT fk_sangria_aberturaCaixa_idaberturaCaixa FOREIGN KEY (aberturaCaixa_idaberturaCaixa) REFERENCES aberturaCaixa (idaberturaCaixa),
  CONSTRAINT fk_sangria_Funcionario_idFuncionario FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario (idFuncionario)
);

CREATE TABLE IF NOT EXISTS saldoCaixa (
  idsaldoCaixa INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  qttNotaDuzentos INT NOT NULL,
  qttNotaCem INT NOT NULL,
  qttNotaVinte INT NOT NULL,
  qttNotaCinquenta INT NOT NULL,
  qttNotaDez INT NOT NULL,
  qttNotaCinco INT NOT NULL,
  qttNotaDois INT NOT NULL,
  qttMoedaUm INT NOT NULL,
  qttMoedaCinquenta INT NOT NULL,
  qttMoedaVinteCinco INT NOT NULL,
  qttMoedaDez INT NOT NULL,
  qttMoedaCinco INT NOT NULL,
  valorSaldo DOUBLE NULL,
  aberturaCaixa_idaberturaCaixa INT NOT NULL,
CONSTRAINT fk_saldoCaixa_aberturaCaixa_idaberturaCaixa FOREIGN KEY (aberturaCaixa_idaberturaCaixa) REFERENCES aberturaCaixa (idaberturaCaixa)
);

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
  promocao_idpromocao INT NOT NULL,
CONSTRAINT fk_cupom_promocao_idpromocao FOREIGN KEY (promocao_idpromocao) REFERENCES promocao (idpromocao)
);

CREATE TABLE IF NOT EXISTS vendas (
  idvendas INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  dataVenda VARCHAR(45) NOT NULL,
  horarioVenda VARCHAR(45) NOT NULL,
  valorTotal DOUBLE NOT NULL,
  valorPago DOUBLE NOT NULL,
  troco DOUBLE NOT NULL,
  quantidadeItens INT NOT NULL,
  statusVenda VARCHAR(45) NOT NULL,
  cliente_idcliente INT NOT NULL,
  usuario_idusuario INT NOT NULL,
  caixas_idcaixas INT NOT NULL,
  cupom_idcupom INT NOT NULL,
CONSTRAINT fk_vendas_cliente_idcliente FOREIGN KEY (cliente_idcliente) REFERENCES cliente (idcliente),
  CONSTRAINT fk_vendas_usuario_idusuario FOREIGN KEY (usuario_idusuario) REFERENCES usuario (idusuario),
  CONSTRAINT fk_vendas_caixas_idcaixas FOREIGN KEY (caixas_idcaixas) REFERENCES caixas (idcaixas),
  CONSTRAINT fk_vendas_cupom_idcupom FOREIGN KEY (cupom_idcupom) REFERENCES cupom (idcupom)
);

CREATE TABLE IF NOT EXISTS veiculos (
  idveiculos INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tipoVeiculo VARCHAR(45) NOT NULL,
  marca VARCHAR(45) NOT NULL,
  modelo VARCHAR(45) NOT NULL,
  anoVeiculo INT NOT NULL,
  cor VARCHAR(45) NOT NULL,
  capacidadeCarga DOUBLE NOT NULL,
  statusVeiculo VARCHAR(45) NOT NULL,
  placa VARCHAR(45) NOT NULL,
  observacoes VARCHAR(45) NULL
);

CREATE TABLE IF NOT EXISTS taxasEntrega (
  idtaxasEntrega INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  regiao VARCHAR(45) NOT NULL,
  tipoTaxa VARCHAR(45) NOT NULL,
  valorTaxa DOUBLE NOT NULL,
  observacao VARCHAR(45) NULL
);

CREATE TABLE IF NOT EXISTS entregas (
  identregas INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  dataEntrega VARCHAR(45) NOT NULL,
  horarioEntrega VARCHAR(45) NOT NULL,
  statusEntrega VARCHAR(45) NOT NULL,
  cliente_idcliente INT NOT NULL,
  veiculos_idveiculos INT NOT NULL,
  taxasEntrega_idtaxasEntrega INT NOT NULL,
  Funcionario_idFuncionario INT NOT NULL,
  Funcionario_cargos_idcargos INT NOT NULL,
  Funcionario_setores_idsetores INT NOT NULL,
  Funcionario_turnos_idturnos INT NOT NULL,
CONSTRAINT fk_entregas_cliente_idcliente FOREIGN KEY (cliente_idcliente) REFERENCES cliente (idcliente),
  CONSTRAINT fk_entregas_veiculos_idveiculos FOREIGN KEY (veiculos_idveiculos) REFERENCES veiculos (idveiculos),
  CONSTRAINT fk_entregas_taxasEntrega_idtaxasEntrega FOREIGN KEY (taxasEntrega_idtaxasEntrega) REFERENCES taxasEntrega (idtaxasEntrega),
  CONSTRAINT fk_entregas_Funcionario_idFuncionario FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario (idFuncionario)
);

CREATE TABLE IF NOT EXISTS pedidos (
  idpedidos INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  numeroPedido VARCHAR(45) NOT NULL,
  datapedido VARCHAR(45) NOT NULL,
  statusPedido VARCHAR(45) NOT NULL,
  observacao VARCHAR(200) NULL,
  cliente_idcliente INT NOT NULL,
  usuario_idusuario INT NOT NULL,
  entregas_identregas INT NOT NULL,
CONSTRAINT fk_pedidos_cliente_idcliente FOREIGN KEY (cliente_idcliente) REFERENCES cliente (idcliente),
  CONSTRAINT fk_pedidos_usuario_idusuario FOREIGN KEY (usuario_idusuario) REFERENCES usuario (idusuario),
  CONSTRAINT fk_pedidos_entregas_identregas FOREIGN KEY (entregas_identregas) REFERENCES entregas (identregas)
);

CREATE TABLE IF NOT EXISTS contasPagar (
  idcontasPagar INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  valor DOUBLE NOT NULL,
  descricao VARCHAR(200) NOT NULL,
  dataVencimento VARCHAR(45) NOT NULL,
  dataPagamento VARCHAR(45) NOT NULL,
  situacao VARCHAR(45) NOT NULL,
  fornecedores_idfornecedores INT NOT NULL,
CONSTRAINT fk_contasPagar_fornecedores_idfornecedores FOREIGN KEY (fornecedores_idfornecedores) REFERENCES fornecedores (idfornecedores)
);

CREATE TABLE IF NOT EXISTS contasReceber (
  idcontasReceber INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  valorReceber DOUBLE NOT NULL,
  descricao VARCHAR(200) NULL,
  dataVencimento VARCHAR(45) NOT NULL,
  dataRecebimento VARCHAR(45) NOT NULL,
  situacao VARCHAR(45) NOT NULL,
  cliente_idcliente INT NOT NULL,
CONSTRAINT fk_contasReceber_cliente_idcliente FOREIGN KEY (cliente_idcliente) REFERENCES cliente (idcliente)
);

CREATE TABLE IF NOT EXISTS contasFinanceiras (
  idcontasFinanceiras INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  tipo VARCHAR(45) NOT NULL,
  agencia VARCHAR(45) NOT NULL,
  numeroConta VARCHAR(45) NOT NULL,
  banco VARCHAR(45) NOT NULL,
  ativo TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS movimentacoesFinanceiras (
  idmovimentacoesFinanceiras INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tipo VARCHAR(45) NOT NULL,
  valor DOUBLE NOT NULL,
  dataHora VARCHAR(45) NOT NULL,
  descricao VARCHAR(200) NOT NULL,
  origem VARCHAR(200) NOT NULL,
  contasFinanceiras_idcontasFinanceiras INT NOT NULL,
 FOREIGN KEY (contasFinanceiras_idcontasFinanceiras) REFERENCES contasFinanceiras (idcontasFinanceiras)
);

CREATE TABLE IF NOT EXISTS estoque (
  idestoque INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  quantidadeAtual INT NOT NULL,
  quantidadeReservada INT NOT NULL,
  dataAtualizacao VARCHAR(45) NOT NULL,
  produtos_idprodutos INT NOT NULL,
  produtos_categorias_idcategorias INT NOT NULL,
  produtos_marcas_idmarcas INT NOT NULL,
  produtos_unidadeMedidas_idunidadeMedidas INT NOT NULL,
CONSTRAINT fk_estoque_produtos_idprodutos FOREIGN KEY (produtos_idprodutos) REFERENCES produtos (idprodutos)
);

CREATE TABLE IF NOT EXISTS lotesProdutos (
  idlotesProdutos INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  numeroLote VARCHAR(45) NOT NULL,
  datafabricacao VARCHAR(45) NOT NULL,
  dataVencimento VARCHAR(45) NOT NULL,
  quantidadeLote INT NOT NULL,
  produtos_idprodutos INT NOT NULL,
  produtos_categorias_idcategorias INT NOT NULL,
  produtos_marcas_idmarcas INT NOT NULL,
  produtos_unidadeMedidas_idunidadeMedidas INT NOT NULL,
CONSTRAINT fk_lotesProdutos_produtos_idprodutos FOREIGN KEY (produtos_idprodutos) REFERENCES produtos (idprodutos)
);

CREATE TABLE IF NOT EXISTS movimentacaoEstoque (
  idmovimentacaoEstoque INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tipoMovimentacao VARCHAR(45) NOT NULL,
  quantidade INT NOT NULL,
  dataMovimentacao VARCHAR(45) NOT NULL,
  horaMovimentacao VARCHAR(45) NOT NULL,
  documentoReferencia LONGBLOB NOT NULL,
  produtos_idprodutos INT NOT NULL,
  produtos_categorias_idcategorias INT NOT NULL,
  produtos_marcas_idmarcas INT NOT NULL,
  produtos_unidadeMedidas_idunidadeMedidas INT NOT NULL,
 FOREIGN KEY (produtos_idprodutos) REFERENCES produtos (idprodutos)
);

CREATE TABLE IF NOT EXISTS perdasEstoque (
  idperdasEstoque INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  quantidade INT NOT NULL,
  dataRegistro VARCHAR(45) NOT NULL,
  motivo VARCHAR(45) NOT NULL,
  produtos_idprodutos INT NOT NULL,
  produtos_categorias_idcategorias INT NOT NULL,
  produtos_marcas_idmarcas INT NOT NULL,
  produtos_unidadeMedidas_idunidadeMedidas INT NOT NULL,
  lotesProdutos_idlotesProdutos INT NOT NULL,
 FOREIGN KEY (produtos_idprodutos) REFERENCES produtos (idprodutos),
  FOREIGN KEY (lotesProdutos_idlotesProdutos) REFERENCES lotesProdutos (idlotesProdutos)
);

CREATE TABLE IF NOT EXISTS Funcionario_has_ferias (
  Funcionario_idFuncionario INT NOT NULL,
  Funcionario_cargos_idcargos INT NOT NULL,
  Funcionario_setores_idsetores INT NOT NULL,
  Funcionario_turnos_idturnos INT NOT NULL,
  ferias_idferias INT NOT NULL,
  PRIMARY KEY (Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos, ferias_idferias),
  FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario (idFuncionario),
 FOREIGN KEY (ferias_idferias) REFERENCES ferias (idferias)
);

CREATE TABLE IF NOT EXISTS Funcionario_has_diasTrabalhados (
  Funcionario_idFuncionario INT NOT NULL,
  Funcionario_cargos_idcargos INT NOT NULL,
  Funcionario_setores_idsetores INT NOT NULL,
  Funcionario_turnos_idturnos INT NOT NULL,
  diasTrabalhados_iddiasTrabalhados INT NOT NULL,
  PRIMARY KEY (Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos, diasTrabalhados_iddiasTrabalhados),
   FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario (idFuncionario),
   FOREIGN KEY (diasTrabalhados_iddiasTrabalhados) REFERENCES diasTrabalhados (iddiasTrabalhados)
);

CREATE TABLE IF NOT EXISTS compras (
  idcompras INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  valorTotal DOUBLE NOT NULL,
  situacao VARCHAR(45) NOT NULL,
  dataCompra VARCHAR(45) NOT NULL,
  fornecedores_idfornecedores INT NOT NULL,
  Funcionario_idFuncionario INT NOT NULL,
  Funcionario_cargos_idcargos INT NOT NULL,
  Funcionario_setores_idsetores INT NOT NULL,
  Funcionario_turnos_idturnos INT NOT NULL,
CONSTRAINT fk_compras_fornecedores_idfornecedores FOREIGN KEY (fornecedores_idfornecedores) REFERENCES fornecedores (idfornecedores),
  CONSTRAINT fk_compras_Funcionario_idFuncionario FOREIGN KEY (Funcionario_idFuncionario) REFERENCES Funcionario (idFuncionario)
);

CREATE TABLE IF NOT EXISTS itensCompra (
  iditensCompra INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  quantidade INT NOT NULL,
  precoUnitario DOUBLE NOT NULL,
  desconto DOUBLE NOT NULL,
  compras_idcompras INT NOT NULL,
  produtos_idprodutos INT NOT NULL,
  produtos_categorias_idcategorias INT NOT NULL,
  produtos_marcas_idmarcas INT NOT NULL,
  produtos_unidadeMedidas_idunidadeMedidas INT NOT NULL,
CONSTRAINT fk_itensCompra_compras_idcompras FOREIGN KEY (compras_idcompras) REFERENCES compras (idcompras),
  CONSTRAINT fk_itensCompra_produtos_idprodutos FOREIGN KEY (produtos_idprodutos) REFERENCES produtos (idprodutos)
);

CREATE TABLE IF NOT EXISTS itensVendas (
  iditensVendas INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  quantidade INT NOT NULL,
  precoUnitario DOUBLE NOT NULL,
  desconto DOUBLE NOT NULL,
  valorTotal DOUBLE NOT NULL,
  vendas_idvendas INT NOT NULL,
CONSTRAINT fk_itensVendas_vendas_idvendas FOREIGN KEY (vendas_idvendas) REFERENCES vendas (idvendas)
);

CREATE TABLE IF NOT EXISTS itensPedidos (
  iditensPedidos INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  quantidade INT NOT NULL,
  precoUnitario DOUBLE NOT NULL,
  desconto DOUBLE NOT NULL,
  pedidos_idpedidos INT NOT NULL,
  produtos_idprodutos INT NOT NULL,
  produtos_categorias_idcategorias INT NOT NULL,
  produtos_marcas_idmarcas INT NOT NULL,
  produtos_unidadeMedidas_idunidadeMedidas INT NOT NULL,
CONSTRAINT fk_itensPedidos_pedidos_idpedidos FOREIGN KEY (pedidos_idpedidos) REFERENCES pedidos (idpedidos),
  CONSTRAINT fk_itensPedidos_produtos_idprodutos FOREIGN KEY (produtos_idprodutos) REFERENCES produtos (idprodutos)
);

CREATE TABLE IF NOT EXISTS pagamentoVenda (
  idpagamentoVendas INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  valor DOUBLE NOT NULL,
  valorRecebido DOUBLE NOT NULL,
  troco DOUBLE NOT NULL,
  situacao VARCHAR(45) NOT NULL,
  referenciaTransacao VARCHAR(45) NOT NULL,
  vendas_idvendas INT NOT NULL,
  vendas_cupom_idcupom INT NOT NULL,
  formasPagamento_idformasPagamento INT NOT NULL,
  condicoesPagamento_idcondicoesPagamento INT NOT NULL,
  contasFinanceiras_idcontasFinanceiras INT NOT NULL,
CONSTRAINT fk_pagamentoVenda_vendas_idvendas FOREIGN KEY (vendas_idvendas) REFERENCES vendas (idvendas),
   FOREIGN KEY (formasPagamento_idformasPagamento) REFERENCES formasPagamento (idformasPagamento),
   FOREIGN KEY (condicoesPagamento_idcondicoesPagamento) REFERENCES condicoesPagamento (idcondicoesPagamento),
 FOREIGN KEY (contasFinanceiras_idcontasFinanceiras) REFERENCES contasFinanceiras (idcontasFinanceiras)
);

CREATE TABLE IF NOT EXISTS parcelasPagar (
  idparcelasPagar INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  numeroParcela INT NOT NULL,
  valorParcela DOUBLE NOT NULL,
  dataVencimento VARCHAR(45) NOT NULL,
  dataPagamento VARCHAR(45) NULL,
  valorPago DOUBLE NULL,
  statusParcela VARCHAR(45) NOT NULL,
  observacao VARCHAR(200) NOT NULL,
  contasPagar_idcontasPagar INT NOT NULL,
CONSTRAINT fk_parcelasPagar_contasPagar_idcontasPagar FOREIGN KEY (contasPagar_idcontasPagar) REFERENCES contasPagar (idcontasPagar)
);

CREATE TABLE IF NOT EXISTS parcelasReceber (
  idparcelasReceber INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  numeroParcela INT NOT NULL,
  valorParcela DOUBLE NOT NULL,
  dataVencimento VARCHAR(45) NOT NULL,
  dataPagamento VARCHAR(45) NULL,
  valorRecebido DOUBLE NULL,
  statusParcela VARCHAR(45) NOT NULL,
  observacao VARCHAR(200) NOT NULL,
  contasReceber_idcontasReceber INT NOT NULL,
CONSTRAINT fk_parcelasReceber_contasReceber_idcontasReceber FOREIGN KEY (contasReceber_idcontasReceber) REFERENCES contasReceber (idcontasReceber)
);

CREATE TABLE IF NOT EXISTS usuario_has_permissoes (
  usuario_idusuario INT NOT NULL,
  permissoes_idpermissoes INT NOT NULL,
  PRIMARY KEY (usuario_idusuario, permissoes_idpermissoes),
   FOREIGN KEY (usuario_idusuario) REFERENCES usuario (idusuario),
 FOREIGN KEY (permissoes_idpermissoes) REFERENCES permissoes (idpermissoes)
);

CREATE TABLE IF NOT EXISTS produtos_has_etiquetas (
  produtos_idprodutos INT NOT NULL,
  produtos_categorias_idcategorias INT NOT NULL,
  produtos_marcas_idmarcas INT NOT NULL,
  produtos_unidadeMedidas_idunidadeMedidas INT NOT NULL,
  etiquetas_idetiquetas INT NOT NULL,
  PRIMARY KEY (produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas, etiquetas_idetiquetas),
   FOREIGN KEY (produtos_idprodutos) REFERENCES produtos (idprodutos),
   FOREIGN KEY (etiquetas_idetiquetas) REFERENCES etiquetas (idetiquetas)
);

CREATE TABLE IF NOT EXISTS promocao_has_produtos (
  promocao_idpromocao INT NOT NULL,
  produtos_idprodutos INT NOT NULL,
  produtos_categorias_idcategorias INT NOT NULL,
  produtos_marcas_idmarcas INT NOT NULL,
  produtos_unidadeMedidas_idunidadeMedidas INT NOT NULL,
  PRIMARY KEY (promocao_idpromocao, produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas),
  CONSTRAINT fk_promocao_has_produtos_promocao_idpromocao FOREIGN KEY (promocao_idpromocao) REFERENCES promocao (idpromocao),
  CONSTRAINT fk_promocao_has_produtos_produtos_idprodutos FOREIGN KEY (produtos_idprodutos) REFERENCES produtos (idprodutos)
);
CREATE TABLE IF NOT EXISTS veiculo (
    idveiculo INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) NOT NULL UNIQUE,
    modelo VARCHAR(100) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    ano YEAR NOT NULL,
    cor VARCHAR(30) NOT NULL,
    situacao TINYINT NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS recuperacao_senha (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_idcliente INT NOT NULL,

    -- Código enviado por e-mail
    codigo_hash CHAR(64) NOT NULL,
    expiracao DATETIME NOT NULL,
    tentativas INT DEFAULT 0,
    utilizado TINYINT DEFAULT 0,

    -- Token de autorização para redefinir a senha
    token_hash CHAR(64) NULL,
    token_expiracao DATETIME NULL,
    token_utilizado TINYINT DEFAULT 0,

    -- Data de criação
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Relacionamento com cliente
    FOREIGN KEY (cliente_idcliente)
    REFERENCES cliente(idcliente)
    ON DELETE CASCADE
);

-- Tabela do histórico de senhas
CREATE TABLE historico_senhas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_idcliente INT NOT NULL,
    senha VARCHAR(45) NOT NULL,
    data_utilizacao DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (cliente_idcliente)
    REFERENCES cliente(idcliente)
    ON DELETE CASCADE,

    INDEX idx_historico_cliente (cliente_idcliente)
);

-- INSERTS DE EXEMPLO PARA TODAS AS TABELAS DO BANCO supermercado
-- Execute no banco supermercado. IDs de relacionamentos assumem que o registro referenciado terá ID = 1.
-- Os campos LONGBLOB obrigatórios recebem um byte de exemplo: UNHEX('00').
USE supermercado;

-- Tabela: cargos
INSERT INTO cargos (nome, descricao, salarioBase, cargaHoraria, adicionalPericulosidade, adicionalInsalubridade, statusCargo, adicionalNoturno)
VALUES ('Exemplo', 'Cadastro de exemplo', 100.00, 1, 100.00, 100.00, 1, 100.00);

-- Tabela: setores
INSERT INTO setores (nome, descricao, status)
VALUES ('Exemplo', 'Cadastro de exemplo', 1);

-- Tabela: turnos
INSERT INTO turnos (nome, descricao, horaInicio, horaSaida, intervaloInicio, intervaloFinal, duracaoTurno, turnoNoturno, status)
VALUES ('Exemplo', 'Cadastro de exemplo', '08:00:00', '17:00:00', '12:00:00', '13:00:00', 1, 1, 1);

-- Tabela: Funcionario
INSERT INTO Funcionario (nome, dataNascimento, cpf, telefone, dataAdmissao, statusFuncionario, cargos_idcargos, setores_idsetores, turnos_idturnos)
VALUES ('Exemplo', '2000-01-15', '00000000000', '63999990000', '2026-01-10', 1, 1, 1, 1);

-- Tabela: usuario
INSERT INTO usuario (nome, cpf, email, senha, bloqueio, Funcionario_idFuncionario)
VALUES ('Exemplo', '00000000000', 'exemplo@teste.com', '123456', 1, 1);

-- Tabela: diasTrabalhados
INSERT INTO diasTrabalhados (nome)
VALUES ('Exemplo');

-- Tabela: escala
INSERT INTO escala (periodoInicial, periodoFinal, Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos)
VALUES ('2026-10-01', '2026-10-31', 1, 1, 1, 1);

-- Tabela: ponto
INSERT INTO ponto (data, horario, justificativa, arquivo, Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos)
VALUES ('2026-10-28', '10:00:00', 'Não informado', 'Exemplo', 1, 1, 1, 1);

-- Tabela: ferias
INSERT INTO ferias (inicio, final, situacao)
VALUES ('2026-11-01', '2026-11-30', 'Ativo');

-- Tabela: afastamentos
INSERT INTO afastamentos (tipo, inicio, final, dias, situacao, dataRegistro, Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos)
VALUES ('Geral', '2026-11-01', '2026-11-30', 'Exemplo', 'Ativo', '2026-10-28', 1, 1, 1, 1);

-- Tabela: metas
INSERT INTO metas (tipo, periodoInicial, periodoFinal, metaPrevista, resultadoAtual, porcentagemAtingido, situacao, Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos)
VALUES ('Geral', '2026-10-01', '2026-10-31', 100.00, 100.00, 100.00, 'Ativo', 1, 1, 1, 1);

-- Tabela: permissoes
INSERT INTO permissoes (tipo, telaProdutos, telaEstoque, telaCompras, telaClientes, telaPromocoes, telaCaixa, telaVendas, telaPDV, telaPedidos, telaEntregas, telaRh, telaFinaceiro, telaRelatorios)
VALUES ('Geral', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

-- Tabela: historicoAcessos
INSERT INTO historicoAcessos (dataAcesso, horarioAcesso, terminal_ip, dispositivo, usuario_idusuario)
VALUES ('2026-10-28', '10:00:00', '192.168.0.10', 'Computador', 1);

-- Tabela: categorias
INSERT INTO categorias (nome, descricao, status)
VALUES ('Exemplo', 'Cadastro de exemplo', 1);

-- Tabela: marcas
INSERT INTO marcas (nome, status, descricao)
VALUES ('Exemplo', 1, 'Cadastro de exemplo');

-- Tabela: unidadeMedidas
INSERT INTO unidadeMedidas (codigo, nome, status, descricao)
VALUES (1, 'Exemplo', 1, 'Cadastro de exemplo');

-- Tabela: produtos
INSERT INTO produtos (nome, codigo, descricao, precoCusto, precoVenda, situacao, estoque, validade, imagem, categorias_idcategorias, marcas_idmarcas, unidadeMedidas_idunidadeMedidas)
VALUES ('Exemplo', '7891234567890', 'Cadastro de exemplo', 100.00, 100.00, 1, 1, '2027-10-28', 'Exemplo', 1, 1, 1);

-- Tabela: codigoBarras
INSERT INTO codigoBarras (codigo, imagem, tipo, status, descricao, produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas)
VALUES ('7891234567890', 'Exemplo', 'Geral', 1, 'Cadastro de exemplo', 1, 1, 1, 1);

-- Tabela: etiquetas
INSERT INTO etiquetas (tipo, imagem)
VALUES ('Geral', 'Exemplo');

-- Tabela: fornecedores
INSERT INTO fornecedores (nomeResponsavel, telefone, email, ramal, cnpj, razaoSocial, endereco, tipo, cep, inscricaoestadual)
VALUES ('Responsável Exemplo', '63999990000', 'exemplo@teste.com', 'Exemplo', '00000000000100', 'Empresa Exemplo Ltda', 'Rua de Exemplo, 100', 'Geral', '77800000', 'ISENTO');

-- Tabela: contratosFornecedores
INSERT INTO contratosFornecedores (numero, dataInicio, dataFinal, valorMensal, tipo, descricao, prazo, renovacaoAutomatica, valorTotal, multa, clausulaRecisao, observacao, arquivo, fornecedores_idfornecedores)
VALUES (1, '2026-10-28', '2026-10-28', 100.00, 'Geral', 'Cadastro de exemplo', 1, 1, 100.00, 100.00, 'Conforme contrato', 'Registro de exemplo', 'Exemplo', 1);

-- Tabela: formasPagamento
INSERT INTO formasPagamento (nome)
VALUES ('Exemplo');

-- Tabela: condicoesPagamento
INSERT INTO condicoesPagamento (quantidadeParcelas, intervaloDias)
VALUES (1, 1);

-- Tabela: cliente
INSERT INTO cliente (nome, cpf, rg, dataNascimento, genero, email, telefone, status, senha)
VALUES ('Exemplo', '00000000000', '000000000', '2000-01-15', 'Não informado', 'exemplo@teste.com', '63999990000', 1, '123456');

-- Tabela: endereco
INSERT INTO endereco (bairro, cep, cidade, estado, rua, numero, cliente_idcliente)
VALUES ('Centro', '77800000', 'Araguaína', 'TO', 'Rua de Exemplo', '100', 1);

-- Tabela: caixas
INSERT INTO caixas (nome, tipo, status, observacao)
VALUES ('Exemplo', 'Geral', 1, 'Registro de exemplo');

-- Tabela: aberturaCaixa
INSERT INTO aberturaCaixa (dataAbertura, horarioAbertura, valorInicial, caixas_idcaixas, Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos)
VALUES ('2026-10-28', '08:00:00', 100.00, 1, 1, 1, 1, 1);

-- Tabela: fechamentoCaixa
INSERT INTO fechamentoCaixa (dataFechamento, horarioFechamento, valorFinal, aberturaCaixa_idaberturaCaixa, Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos)
VALUES ('2026-10-28', '18:00:00', 100.00, 1, 1, 1, 1, 1);

-- Tabela: sangria
INSERT INTO sangria (dataSolicitacao, motivo, observacao, valorSolicitado, valorRetirado, horaSolicitacao, aberturaCaixa_idaberturaCaixa, Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos)
VALUES ('2026-10-28', 'Ajuste de estoque', 'Registro de exemplo', 100.00, 100.00, '10:00:00', 1, 1, 1, 1, 1);

-- Tabela: saldoCaixa
INSERT INTO saldoCaixa (qttNotaDuzentos, qttNotaCem, qttNotaVinte, qttNotaCinquenta, qttNotaDez, qttNotaCinco, qttNotaDois, qttMoedaUm, qttMoedaCinquenta, qttMoedaVinteCinco, qttMoedaDez, qttMoedaCinco, valorSaldo, aberturaCaixa_idaberturaCaixa)
VALUES (1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100.00, 1);

-- Tabela: promocao
INSERT INTO promocao (nome, tipo, status, dataInicio, dataFinal, regras, desconto, qttMinima)
VALUES ('Exemplo', 'Geral', 1, '2026-10-28', '2026-10-28', 'Aplicável conforme condições', 1, 1);

-- Tabela: cupom
INSERT INTO cupom (codigoCupom, tipoDesconto, valorDesconto, valorMinimoCompra, qttMaximaUso, limitePorCliente, tipoUso, dataInicio, dataFinal, observacao, promocao_idpromocao)
VALUES ('BEMVINDO', 'Percentual', 100.00, 100.00, 1, 1, 'Único', '2026-10-28', '2026-10-28', 'Registro de exemplo', 1);

-- Tabela: vendas
INSERT INTO vendas (dataVenda, horarioVenda, valorTotal, valorPago, troco, quantidadeItens, statusVenda, cliente_idcliente, usuario_idusuario, caixas_idcaixas, cupom_idcupom)
VALUES ('2026-10-28', '10:00:00', 100.00, 100.00, 0.00, 1, 'Concluída', 1, 1, 1, 1);

-- Tabela: veiculos
INSERT INTO veiculos (tipoVeiculo, marca, modelo, anoVeiculo, cor, capacidadeCarga, statusVeiculo, placa, observacoes)
VALUES ('Utilitário', 'Marca Exemplo', 'Modelo X', 2026, 'Branca', 100.00, 'Disponível', 'ABC1D23', 'Exemplo');

-- Tabela: taxasEntrega
INSERT INTO taxasEntrega (regiao, tipoTaxa, valorTaxa, observacao)
VALUES ('Centro', 'Entrega', 100.00, 'Registro de exemplo');

-- Tabela: entregas
INSERT INTO entregas (dataEntrega, horarioEntrega, statusEntrega, cliente_idcliente, veiculos_idveiculos, taxasEntrega_idtaxasEntrega, Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos)
VALUES ('2026-10-29', '14:00:00', 'Pendente', 1, 1, 1, 1, 1, 1, 1);

-- Tabela: pedidos
INSERT INTO pedidos (numeroPedido, datapedido, statusPedido, observacao, cliente_idcliente, usuario_idusuario, entregas_identregas)
VALUES ('Exemplo', '2026-10-28', 'Pendente', 'Registro de exemplo', 1, 1, 1);

-- Tabela: contasPagar
INSERT INTO contasPagar (valor, descricao, dataVencimento, dataPagamento, situacao, fornecedores_idfornecedores)
VALUES (100.00, 'Cadastro de exemplo', '2026-11-30', '2026-10-28', 'Ativo', 1);

-- Tabela: contasReceber
INSERT INTO contasReceber (valorReceber, descricao, dataVencimento, dataRecebimento, situacao, cliente_idcliente)
VALUES (100.00, 'Cadastro de exemplo', '2026-11-30', '2026-10-28', 'Ativo', 1);

-- Tabela: contasFinanceiras
INSERT INTO contasFinanceiras (nome, tipo, agencia, numeroConta, banco, ativo)
VALUES ('Exemplo', 'Geral', '0001', '12345-6', 'Banco Exemplo', 1);

-- Tabela: movimentacoesFinanceiras
INSERT INTO movimentacoesFinanceiras (tipo, valor, dataHora, descricao, origem, contasFinanceiras_idcontasFinanceiras)
VALUES ('Geral', 100.00, '2026-10-28 10:00:00', 'Cadastro de exemplo', 'Sistema', 1);

-- Tabela: estoque
INSERT INTO estoque (quantidadeAtual, quantidadeReservada, dataAtualizacao, produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas)
VALUES (1, 1, '2026-10-28', 1, 1, 1, 1);

-- Tabela: lotesProdutos
INSERT INTO lotesProdutos (numeroLote, datafabricacao, dataVencimento, quantidadeLote, produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas)
VALUES ('Exemplo', '2026-10-01', '2026-11-30', 1, 1, 1, 1, 1);

-- Tabela: movimentacaoEstoque
INSERT INTO movimentacaoEstoque (tipoMovimentacao, quantidade, dataMovimentacao, horaMovimentacao, documentoReferencia, produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas)
VALUES ('Entrada', 1, '2026-10-28', '10:00:00', 'Exemplo', 1, 1, 1, 1);

-- Tabela: perdasEstoque
INSERT INTO perdasEstoque (quantidade, dataRegistro, motivo, produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas, lotesProdutos_idlotesProdutos)
VALUES (1, '2026-10-28', 'Ajuste de estoque', 1, 1, 1, 1, 1);

-- Tabela: Funcionario_has_ferias
INSERT INTO Funcionario_has_ferias (Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos, ferias_idferias)
VALUES (1, 1, 1, 1, 1);

-- Tabela: Funcionario_has_diasTrabalhados
INSERT INTO Funcionario_has_diasTrabalhados (Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos, diasTrabalhados_iddiasTrabalhados)
VALUES (1, 1, 1, 1, 1);

-- Tabela: compras
INSERT INTO compras (valorTotal, situacao, dataCompra, fornecedores_idfornecedores, Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos)
VALUES (100.00, 'Ativo', '2026-10-28', 1, 1, 1, 1, 1);

-- Tabela: itensCompra
INSERT INTO itensCompra (quantidade, precoUnitario, desconto, compras_idcompras, produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas)
VALUES (1, 100.00, 100.00, 1, 1, 1, 1, 1);

-- Tabela: itensVendas
INSERT INTO itensVendas (quantidade, precoUnitario, desconto, valorTotal, vendas_idvendas)
VALUES (1, 100.00, 100.00, 100.00, 1);

-- Tabela: itensPedidos
INSERT INTO itensPedidos (quantidade, precoUnitario, desconto, pedidos_idpedidos, produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas)
VALUES (1, 100.00, 100.00, 1, 1, 1, 1, 1);

-- Tabela: pagamentoVenda
INSERT INTO pagamentoVenda (valor, valorRecebido, troco, situacao, referenciaTransacao, vendas_idvendas, vendas_cupom_idcupom, formasPagamento_idformasPagamento, condicoesPagamento_idcondicoesPagamento, contasFinanceiras_idcontasFinanceiras)
VALUES (100.00, 100.00, 0.00, 'Ativo', 'TRANS001', 1, 1, 1, 1, 1);

-- Tabela: parcelasPagar
INSERT INTO parcelasPagar (numeroParcela, valorParcela, dataVencimento, dataPagamento, valorPago, statusParcela, observacao, contasPagar_idcontasPagar)
VALUES (1, 100.00, '2026-11-30', '2026-10-28', 100.00, 'Pendente', 'Registro de exemplo', 1);

-- Tabela: parcelasReceber
INSERT INTO parcelasReceber (numeroParcela, valorParcela, dataVencimento, dataPagamento, valorRecebido, statusParcela, observacao, contasReceber_idcontasReceber)
VALUES (1, 100.00, '2026-11-30', '2026-10-28', 100.00, 'Pendente', 'Registro de exemplo', 1);

-- Tabela: usuario_has_permissoes
INSERT INTO usuario_has_permissoes (usuario_idusuario, permissoes_idpermissoes)
VALUES (1, 1);

-- Tabela: produtos_has_etiquetas
INSERT INTO produtos_has_etiquetas (produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas, etiquetas_idetiquetas)
VALUES (1, 1, 1, 1, 1);

-- Tabela: promocao_has_produtos
INSERT INTO promocao_has_produtos (promocao_idpromocao, produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas)
VALUES (1, 1, 1, 1, 1);


INSERT INTO veiculo
(placa, modelo, marca, ano, cor, situacao)
VALUES
('QWE1A23', 'Fiorino Endurance', 'Fiat', 2023, 'Branco', 1),
('RTY2B34', 'Kangoo Express', 'Renault', 2022, 'Branco', 1),
('UIO3C45', 'Ducato Cargo', 'Fiat', 2024, 'Branco', 1),
('PAS4D56', 'Master Furgão', 'Renault', 2023, 'Branco', 1),
('FGH5E67', 'Expert Cargo', 'Peugeot', 2024, 'Branco', 1);




INSERT INTO categorias (nome, descricao, status) VALUES
('Mercearia', 'Alimentos em geral', 1),
('Bebidas', 'Bebidas alcoólicas e não alcoólicas', 1),
('Laticínios', 'Leites e derivados', 1),
('Hortifruti', 'Frutas, verduras e legumes', 1),
('Carnes', 'Carnes bovinas, suínas e aves', 1),
('Limpeza', 'Produtos de limpeza doméstica', 1),
('Higiene Pessoal', 'Produtos de higiene e cuidados pessoais', 1),
('Congelados', 'Alimentos congelados', 1),
('Padaria', 'Pães e produtos de panificação', 1),
('Biscoitos e Doces', 'Biscoitos, chocolates e guloseimas', 1);




INSERT INTO marcas (nome, status, descricao) VALUES
('Nestlé', 1, 'Alimentos e bebidas'),
('Coca-Cola', 1, 'Bebidas'),
('Ambev', 1, 'Bebidas'),
('Italac', 1, 'Laticínios'),
('Piracanjuba', 1, 'Laticínios'),
('Sadia', 1, 'Alimentos e carnes'),
('Perdigão', 1, 'Alimentos e carnes'),
('Seara', 1, 'Alimentos e carnes'),
('Ypê', 1, 'Produtos de limpeza'),
('Omo', 1, 'Produtos de limpeza'),
('Colgate', 1, 'Higiene pessoal'),
('Dove', 1, 'Higiene pessoal'),
('Unilever', 1, 'Produtos de consumo'),
('Bauducco', 1, 'Biscoitos e panificação'),
('Marilan', 1, 'Biscoitos'),
('Tio João', 1, 'Alimentos'),
('Camil', 1, 'Alimentos'),
('Qualitá', 1, 'Marca própria de supermercado'),
('Heinz', 1, 'Alimentos'),
('Del Valle', 1, 'Bebidas');



INSERT INTO unidadeMedidas (codigo, nome, status, descricao) VALUES
(1, 'Unidade', 1, 'Produto vendido por unidade'),
(2, 'Quilograma', 1, 'Produto vendido por kg'),
(3, 'Litro', 1, 'Produto vendido por litro'),
(4, 'Pacote', 1, 'Produto vendido por pacote'),
(5, 'Caixa', 1, 'Produto vendido por caixa'),
(6, 'Grama', 1, 'Produto vendido por grama'),
(7, 'Mililitro', 1, 'Produto vendido por ml');



INSERT INTO produtos
(nome, codigo, descricao, precoCusto, precoVenda, situacao,
 estoque, validade, imagem, categorias_idcategorias,
 marcas_idmarcas, unidadeMedidas_idunidadeMedidas)
VALUES

-- MERCEARIA (1)
('Arroz Tipo 1 5kg', 'SUP-001', 'Arroz branco tipo 1 pacote 5kg', 22.50, 29.99, 1, 50, '2027-10-15', NULL, 1, 16, 4),
('Feijão Carioca 1kg', 'SUP-002', 'Feijão carioca tipo 1', 5.80, 7.99, 1, 60, '2027-08-20', NULL, 1, 17, 4),
('Açúcar Refinado 1kg', 'SUP-003', 'Açúcar refinado pacote 1kg', 3.50, 4.99, 1, 70, '2027-09-10', NULL, 1, 18, 4),
('Óleo de Soja 900ml', 'SUP-004', 'Óleo vegetal de soja', 5.20, 7.49, 1, 80, '2027-06-15', NULL, 1, 18, 7),
('Macarrão Espaguete 500g', 'SUP-005', 'Macarrão tipo espaguete', 3.20, 4.79, 1, 55, '2027-11-20', NULL, 1, 18, 4),
('Farinha de Trigo 1kg', 'SUP-006', 'Farinha de trigo tradicional', 4.10, 5.99, 1, 40, '2027-07-15', NULL, 1, 18, 4),
('Sal Refinado 1kg', 'SUP-007', 'Sal refinado iodado', 1.50, 2.49, 1, 65, '2028-01-10', NULL, 1, 18, 4),

-- BEBIDAS (2)
('Coca-Cola 2L', 'SUP-008', 'Refrigerante sabor cola 2 litros', 7.20, 9.99, 1, 45, '2027-04-10', NULL, 2, 2, 3),
('Guaraná Antarctica 2L', 'SUP-009', 'Refrigerante de guaraná', 5.80, 8.49, 1, 40, '2027-05-12', NULL, 2, 3, 3),
('Suco Del Valle Uva 1L', 'SUP-010', 'Bebida sabor uva', 4.50, 6.99, 1, 35, '2027-06-20', NULL, 2, 20, 3),
('Água Mineral 1,5L', 'SUP-011', 'Água mineral sem gás', 1.50, 2.49, 1, 100, '2028-05-10', NULL, 2, 18, 3),
('Cerveja Brahma 350ml', 'SUP-012', 'Cerveja lata 350ml', 2.80, 4.49, 1, 90, '2027-03-15', NULL, 2, 3, 7),
('Café Tradicional 500g', 'SUP-013', 'Café torrado e moído', 15.00, 19.99, 1, 30, '2027-09-15', NULL, 1, 18, 4),
('Achocolatado em Pó 400g', 'SUP-014', 'Achocolatado em pó', 7.50, 10.99, 1, 25, '2027-08-10', NULL, 1, 1, 4),

-- LATICÍNIOS (3)
('Leite Integral 1L', 'SUP-015', 'Leite integral UHT', 4.20, 5.99, 1, 70, '2027-02-15', NULL, 3, 4, 3),
('Leite Desnatado 1L', 'SUP-016', 'Leite desnatado UHT', 4.30, 6.19, 1, 35, '2027-02-20', NULL, 3, 5, 3),
('Manteiga 200g', 'SUP-017', 'Manteiga com sal', 8.00, 10.99, 1, 25, '2027-01-15', NULL, 3, 4, 4),
('Requeijão Cremoso 200g', 'SUP-018', 'Requeijão tradicional', 5.50, 7.99, 1, 30, '2027-01-20', NULL, 3, 5, 4),
('Iogurte Morango 170g', 'SUP-019', 'Iogurte sabor morango', 2.50, 3.99, 1, 50, '2027-01-10', NULL, 3, 4, 4),
('Creme de Leite 200g', 'SUP-020', 'Creme de leite tradicional', 2.80, 3.99, 1, 40, '2027-06-10', NULL, 3, 1, 4),

-- HORTIFRUTI (4)
('Banana Prata', 'SUP-021', 'Banana prata fresca', 3.00, 4.99, 1, 100, '2026-11-15', NULL, 4, 18, 2),
('Maçã Fuji', 'SUP-022', 'Maçã Fuji fresca', 5.00, 7.99, 1, 80, '2026-11-18', NULL, 4, 18, 2),
('Laranja Pera', 'SUP-023', 'Laranja pera fresca', 2.50, 4.49, 1, 90, '2026-11-17', NULL, 4, 18, 2),
('Batata Inglesa', 'SUP-024', 'Batata inglesa selecionada', 3.20, 5.49, 1, 100, '2026-12-10', NULL, 4, 18, 2),
('Cenoura', 'SUP-025', 'Cenoura fresca', 2.00, 3.99, 1, 60, '2026-12-05', NULL, 4, 18, 2),
('Tomate', 'SUP-026', 'Tomate para salada', 3.50, 5.99, 1, 70, '2026-11-15', NULL, 4, 18, 2),
('Cebola', 'SUP-027', 'Cebola branca', 2.50, 4.49, 1, 65, '2026-12-15', NULL, 4, 18, 2),

-- CARNES (5)
('Peito de Frango 1kg', 'SUP-028', 'Peito de frango congelado', 13.00, 17.99, 1, 40, '2027-05-10', NULL, 5, 6, 2),
('Coxa de Frango 1kg', 'SUP-029', 'Coxa de frango congelada', 8.50, 12.99, 1, 35, '2027-05-15', NULL, 5, 7, 2),
('Carne Moída 1kg', 'SUP-030', 'Carne bovina moída', 22.00, 29.99, 1, 25, '2026-11-20', NULL, 5, 18, 2),
('Linguiça Toscana 1kg', 'SUP-031', 'Linguiça suína temperada', 16.00, 21.99, 1, 30, '2027-04-15', NULL, 5, 7, 2),
('Salsicha 500g', 'SUP-032', 'Salsicha tradicional', 6.50, 9.49, 1, 40, '2027-03-10', NULL, 5, 8, 4),

-- LIMPEZA (6)
('Detergente Ypê 500ml', 'SUP-033', 'Detergente líquido neutro', 1.80, 2.99, 1, 100, '2028-01-10', NULL, 6, 9, 7),
('Água Sanitária 1L', 'SUP-034', 'Água sanitária tradicional', 2.50, 3.99, 1, 50, '2027-12-10', NULL, 6, 9, 3),
('Desinfetante 2L', 'SUP-035', 'Desinfetante perfumado', 4.50, 6.99, 1, 45, '2027-11-10', NULL, 6, 9, 3),
('Sabão em Pó 1,6kg', 'SUP-036', 'Sabão em pó para roupas', 12.00, 16.99, 1, 30, '2028-02-10', NULL, 6, 10, 4),
('Amaciante 2L', 'SUP-037', 'Amaciante de roupas', 7.50, 10.99, 1, 35, '2027-12-15', NULL, 6, 10, 3),
('Esponja de Aço', 'SUP-038', 'Esponja de aço para limpeza', 1.50, 2.49, 1, 80, '2029-01-10', NULL, 6, 18, 1),
('Limpador Multiuso 500ml', 'SUP-039', 'Limpador de superfícies', 3.50, 5.49, 1, 45, '2028-01-10', NULL, 6, 9, 7),

-- HIGIENE PESSOAL (7)
('Creme Dental Colgate 90g', 'SUP-040', 'Creme dental anticáries', 3.50, 5.49, 1, 60, '2028-10-10', NULL, 7, 11, 4),
('Sabonete Dove 90g', 'SUP-041', 'Sabonete hidratante', 3.00, 4.99, 1, 50, '2028-08-10', NULL, 7, 12, 4),
('Shampoo 350ml', 'SUP-042', 'Shampoo para uso diário', 8.00, 11.99, 1, 30, '2028-05-10', NULL, 7, 13, 7),
('Condicionador 350ml', 'SUP-043', 'Condicionador para cabelos', 8.50, 12.49, 1, 25, '2028-05-15', NULL, 7, 13, 7),
('Papel Higiênico 12 Rolos', 'SUP-044', 'Papel higiênico folha dupla', 13.00, 17.99, 1, 40, '2029-01-10', NULL, 7, 18, 5),
('Desodorante Aerosol 150ml', 'SUP-045', 'Desodorante corporal', 8.00, 11.99, 1, 35, '2028-06-10', NULL, 7, 12, 7),

-- CONGELADOS (8)
('Pizza Mussarela 450g', 'SUP-046', 'Pizza congelada sabor mussarela', 12.00, 17.99, 1, 20, '2027-04-10', NULL, 8, 6, 4),
('Batata Congelada 1kg', 'SUP-047', 'Batata pré-frita congelada', 10.00, 14.99, 1, 25, '2027-06-10', NULL, 8, 7, 4),
('Hambúrguer Bovino 672g', 'SUP-048', 'Hambúrguer bovino congelado', 13.00, 18.99, 1, 30, '2027-05-10', NULL, 8, 8, 4),
('Nuggets de Frango 300g', 'SUP-049', 'Empanado de frango congelado', 8.00, 11.99, 1, 30, '2027-05-20', NULL, 8, 6, 4),
('Pão de Queijo 400g', 'SUP-050', 'Pão de queijo congelado', 9.00, 13.49, 1, 20, '2027-03-10', NULL, 8, 18, 4),

-- PADARIA (9)
('Pão de Forma 450g', 'SUP-051', 'Pão de forma tradicional', 5.00, 7.49, 1, 35, '2026-11-15', NULL, 9, 14, 4),
('Bisnaguinha 300g', 'SUP-052', 'Pão tipo bisnaguinha', 4.50, 6.99, 1, 30, '2026-11-15', NULL, 9, 14, 4),
('Pão Integral 400g', 'SUP-053', 'Pão de forma integral', 6.50, 9.49, 1, 25, '2026-11-16', NULL, 9, 14, 4),
('Torrada Tradicional 160g', 'SUP-054', 'Torradas crocantes', 4.00, 6.49, 1, 30, '2027-05-10', NULL, 9, 14, 4),
('Bolo de Chocolate 300g', 'SUP-055', 'Bolo pronto sabor chocolate', 7.00, 10.49, 1, 20, '2026-12-10', NULL, 9, 14, 4),

-- BISCOITOS E DOCES (10)
('Biscoito Recheado Chocolate 130g', 'SUP-056', 'Biscoito recheado sabor chocolate', 2.00, 3.49, 1, 70, '2027-07-10', NULL, 10, 15, 4),
('Biscoito Cream Cracker 350g', 'SUP-057', 'Biscoito salgado tradicional', 4.00, 6.49, 1, 45, '2027-06-10', NULL, 10, 15, 4),
('Wafer Chocolate 110g', 'SUP-058', 'Biscoito wafer sabor chocolate', 2.50, 3.99, 1, 50, '2027-08-10', NULL, 10, 15, 4),
('Chocolate ao Leite 90g', 'SUP-059', 'Chocolate ao leite', 5.00, 7.99, 1, 40, '2027-09-10', NULL, 10, 1, 4),
('Bombom Sortido 250g', 'SUP-060', 'Caixa de bombons sortidos', 10.00, 14.99, 1, 25, '2027-08-10', NULL, 10, 1, 4),
('Leite Condensado 395g', 'SUP-061', 'Leite condensado tradicional', 4.50, 6.49, 1, 35, '2027-10-10', NULL, 10, 1, 4),
('Goiabada 300g', 'SUP-062', 'Doce de goiaba em barra', 4.00, 5.99, 1, 25, '2027-09-10', NULL, 10, 18, 4),
('Gelatina Morango 25g', 'SUP-063', 'Gelatina em pó sabor morango', 1.20, 1.99, 1, 60, '2027-12-10', NULL, 10, 18, 4),
('Maionese 500g', 'SUP-064', 'Maionese tradicional', 5.00, 7.49, 1, 30, '2027-06-10', NULL, 1, 19, 4),
('Ketchup 400g', 'SUP-065', 'Ketchup tradicional', 5.50, 8.49, 1, 30, '2027-07-10', NULL, 1, 19, 4),

-- PRODUTOS ADICIONAIS
('Aveia em Flocos 170g', 'SUP-066', 'Aveia em flocos tradicional', 3.50, 5.49, 1, 30, '2027-10-10', NULL, 1, 1, 4),
('Milho Verde Lata 170g', 'SUP-067', 'Milho verde em conserva', 2.80, 4.49, 1, 40, '2027-08-10', NULL, 1, 18, 4),
('Ervilha Lata 170g', 'SUP-068', 'Ervilha em conserva', 2.70, 4.29, 1, 35, '2027-08-15', NULL, 1, 18, 4),
('Farinha de Mandioca 1kg', 'SUP-069', 'Farinha de mandioca torrada', 5.00, 7.49, 1, 30, '2027-09-10', NULL, 1, 18, 4),
('Molho de Tomate 300g', 'SUP-070', 'Molho de tomate tradicional', 1.80, 2.99, 1, 50, '2027-07-10', NULL, 1, 18, 4);




SELECT COUNT(*) AS totalProdutos FROM produtos;

SELECT
    p.idprodutos,
    p.nome,
    p.codigo,
    p.precoVenda,
    p.estoque,
    c.nome AS categoria,
    m.nome AS marca,
    u.nome AS unidade
FROM produtos p
INNER JOIN categorias c
    ON p.categorias_idcategorias = c.idcategorias
INNER JOIN marcas m
    ON p.marcas_idmarcas = m.idmarcas
INNER JOIN unidadeMedidas u
    ON p.unidadeMedidas_idunidadeMedidas = u.idunidadeMedidas
WHERE p.codigo BETWEEN 'SUP-001' AND 'SUP-070'
ORDER BY p.codigo;

INSERT INTO cargos (
    nome,
    descricao,
    salarioBase,
    cargaHoraria,
    adicionalPericulosidade,
    adicionalInsalubridade,
    statusCargo,
    adicionalNoturno
) VALUES
(
    'Operador de Caixa',
    'Responsável pelo atendimento aos clientes, registro de produtos e recebimento de pagamentos.',
    1800.00,
    44,
    0.00,
    0.00,
    1,
    0.00
),
(
    'Repositor de Mercadorias',
    'Responsável pela reposição, organização e verificação da validade dos produtos nas prateleiras.',
    1750.00,
    44,
    0.00,
    0.00,
    1,
    0.00
),
(
    'Açougueiro',
    'Responsável pelo corte, preparo, armazenamento e atendimento no setor de carnes.',
    2200.00,
    44,
    0.00,
    200.00,
    1,
    0.00
),
(
    'Vigia Noturno',
    'Responsável pela vigilância e segurança das instalações do supermercado durante o período noturno.',
    2000.00,
    44,
    0.00,
    0.00,
    1,
    300.00
);

SELECT * FROM CARGOS;

INSERT INTO setores (
    nome,
    descricao,
    status
) VALUES
(
    'Administrativo',
    'Responsável pela gestão administrativa, documentos e organização interna do supermercado.',
    1
),
(
    'Recursos Humanos',
    'Responsável pela contratação, cadastro, treinamento e gestão dos funcionários.',
    1
),
(
    'Financeiro',
    'Responsável pelo controle de contas a pagar, contas a receber, pagamentos e fluxo de caixa.',
    1
),
(
    'Operacional',
    'Responsável pela operação diária, atendimento aos clientes, reposição e organização da loja.',
    1
);

SELECT * FROM SETORES;

INSERT INTO turnos (
    nome,
    descricao,
    horaInicio,
    horaSaida,
    intervaloInicio,
    intervaloFinal,
    duracaoTurno,
    turnoNoturno,
    status
) VALUES
(
    'Manhã',
    'Turno da manhã, responsável pelas atividades iniciais do supermercado.',
    '06:00',
    '14:00',
    '10:00',
    '11:00',
    8,
    0,
    1
),
(
    'Tarde',
    'Turno da tarde, responsável pelo atendimento e operação do supermercado.',
    '12:00',
    '20:00',
    '16:00',
    '17:00',
    8,
    0,
    1
),
(
    'Noite',
    'Turno da noite, responsável pelo atendimento e encerramento das atividades.',
    '14:00',
    '22:00',
    '18:00',
    '19:00',
    8,
    0,
    1
),
(
    'Madrugada',
    'Turno da madrugada, responsável pela segurança, limpeza e organização do supermercado.',
    '22:00',
    '06:00',
    '02:00',
    '03:00',
    8,
    1,
    1
);

SELECT * FROM TURNOS;

INSERT INTO Funcionario (
    nome,
    dataNascimento,
    cpf,
    telefone,
    dataAdmissao,
    statusFuncionario,
    cargos_idcargos,
    setores_idsetores,
    turnos_idturnos
) VALUES
(
    'Carlos Eduardo Silva',
    '12/04/1995',
    '11111111101',
    '(63) 99111-1111',
    '10/01/2026',
    1,
    1,
    4,
    1
),
(
    'Mariana Oliveira Santos',
    '25/08/1998',
    '22222222202',
    '(63) 99222-2222',
    '15/02/2026',
    1,
    2,
    3,
    2
),
(
    'Rafael Almeida Costa',
    '03/11/1992',
    '33333333303',
    '(63) 99333-3333',
    '01/03/2026',
    1,
    3,
    1,
    4
);
SELECT * FROM FUNCIONARIO;


INSERT INTO usuario (
    nome,
    cpf,
    email,
    senha,
    bloqueio,
    Funcionario_idFuncionario
) VALUES
(
    'Carlos Eduardo Silva',
    '11111111101',
    'carlos.silva@supermercado.com',
    'Carlos@123',
    0,
    1
);

SELECT * FROM USUARIO;















