/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Control.AfastamentosController;
import Control.CargosController;
import Control.FeriasController;
import Control.FuncionarioController;
import Control.MetasController;
import Control.PontoController;
import Control.SetoresController;
import Control.FuncionarioController;
import Control.CargosController;
import Control.EscalaController;
import Control.TurnosController;
import Model.Afastamentos;
import Model.Cargos;
import Model.Escala;
import Model.Ferias;
import Model.Setores;
import Model.Turnos;
import Model.Usuario;
import Model.Funcionario;
import Model.Metas;
import Model.Ponto;
import View.TelaPedidos;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import java.util.function.Function;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author ArthurGiuvannucci
 */
public class Tela_Funcionarios_RH extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Tela_Funcionarios_RH.class.getName());

    private MenuPadrao menu;  
    private Usuario usuario;
    private final Map<JComboBox<String>, List<Integer>> idsPorCombo = new HashMap<>();
    private static final String SELECIONE = "Selecione...";
    
    
    public Tela_Funcionarios_RH(Usuario usuario) {
        initComponents();
        carregarComboBoxes();
        carregarTiposMeta();
        
        
       this.usuario= usuario;
        this.menu=MenuPadrao.instalar(
        this,
        usuario,
        "funcionariosRH",
        this::navegar,
        this::dispose
    );
        this.setExtendedState(Tela_Funcionarios_RH.MAXIMIZED_BOTH);
        
       
    }
    
// MÉTODO DE ACESSAR AS TELAS
public void navegar(String idtela) {

    switch (idtela) {

        case "inicio":
            menu.abrirTela(this, new TelaHome(usuario));
            break;

        case "gerenciamentoProdutos":
            menu.abrirTela(this, new TelaGerenciamentoProdutos(usuario));
            break;

        case "promocoes":
            menu.abrirTela(this, new TelaPromocoes(usuario));
            break;

        case "estoque":
            menu.abrirTela(this, new TelaEstoque(usuario));
            break;

        case "fornecedoresTerceirizados":
            menu.abrirTela(this, new TelaFornecedoresTerceirizados(usuario));
            break;

        case "clientes":
            menu.abrirTela(this, new TelasClientes(usuario));
            break;

        case "pedidos":
            menu.abrirTela(this, new TelaPedidos(usuario));
            break;

        case "entregas":
            menu.abrirTela(this, new TelaEntregas(usuario));
            break;

        case "pdvRealizacao":
            menu.abrirTela(this, new TelaPdvRealizacao(usuario));
            break;

        case "vendas":
            menu.abrirTela(this, new TelaVendas(usuario));
            break;

        case "caixa":
            menu.abrirTela(this, new TelaCaixa(usuario));
            break;

        case "financeiro":
            menu.abrirTela(this, new TelaFinanceiro(usuario));
            break;

        case "funcionariosRH":
            menu.abrirTela(this, new Tela_Funcionarios_RH(usuario));
            break;

        case "perfilUsuarios":
            menu.abrirTela(this, new TelaPerfilUsuarios(usuario));
            break;

        case "solicitacao":
            menu.abrirTela(this, new TelaSolicitacao(usuario));
            break;

        case "alterarSenha":
            menu.abrirTela(this, new AlterarSenha());
            break;

        case "recuperacaoSenha":
            // Tela utilizada no fluxo de recuperação de senha.
            // menu.abrirTela(this, new RecuperacaoSenha());
            break;

        case "sair":
        case "logout":
            menu.abrirTela(this, new TelaLoginUsuarios());
            break;

        default:
            JOptionPane.showMessageDialog(
                this,
                "Tela não encontrada: " + idtela,
                "Navegação",
                JOptionPane.WARNING_MESSAGE
            );
            break;
    }
}
 
private int buscarIdCargo(String nomeCargo) throws SQLException {

    CargosController controller = new CargosController();

    List<Cargos> lista = controller.listar();

    for (Cargos cargo : lista) {

        if (cargo.getNome().equalsIgnoreCase(nomeCargo)) {
            return cargo.getIdcargos();
        }
    }

    return -1;
}   
private int buscarIdSetor(String nomeSetor) throws SQLException {

    SetoresController controller = new SetoresController();

    List<Setores> lista = controller.listar();

    for (Setores setor : lista) {

        if (setor.getNome().equalsIgnoreCase(nomeSetor)) {
            return setor.getIdsetores();
        }
    }

    return -1;
}
private int buscarIdTurno(String nomeTurno) throws SQLException {

    TurnosController controller = new TurnosController();

    List<Turnos> lista = controller.listar();

    for (Turnos turno : lista) {

        if (turno.getNome().equalsIgnoreCase(nomeTurno)) {
            return turno.getIdturnos();
        }
    }

    return -1;
}


private void cadastrarFuncionario() {

    try {

        // Capturar os dados dos campos
        String nome = tfNomeFuncionario.getText().trim();
        String cpf = ftfCPF.getText().replaceAll("[^0-9]", "");
        String telefone = ftfTelefone.getText().replaceAll("[^0-9]", "");
        String dataNascimento = ftfDataNascimento.getText().trim();
        String dataAdmissao = ftfDataAdimissao.getText().trim();

        // Validar campos obrigatórios
        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()
                || dataNascimento.isEmpty() || dataAdmissao.isEmpty()
                || cbCargo.getSelectedItem() == null
                || cbSetorFuncionarios.getSelectedItem() == null
                || cbTurnoFuncionario.getSelectedItem() == null
                || cbStatus.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar CPF
        if (cpf.length() != 11) {
            JOptionPane.showMessageDialog(this,
                    "Informe um CPF válido!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Buscar os IDs pelos nomes selecionados
        String nomeCargo = cbCargo.getSelectedItem().toString();
        String nomeSetor = cbSetorFuncionarios.getSelectedItem().toString();
        String nomeTurno = cbTurnoFuncionario.getSelectedItem().toString();

        int idCargo = buscarIdCargo(nomeCargo);
        int idSetor = buscarIdSetor(nomeSetor);
        int idTurno = buscarIdTurno(nomeTurno);

        if (idCargo == -1 || idSetor == -1 || idTurno == -1) {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível identificar o cargo, setor ou turno selecionado!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Capturar status
        int status = cbStatus.getSelectedIndex();

        // Criar Model
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setTelefone(telefone);
        funcionario.setDataNascimento(dataNascimento);
        funcionario.setDataAdmissao(dataAdmissao);
        funcionario.setStatusFuncionario(status);
        funcionario.setCargos_idcargos(idCargo);
        funcionario.setSetores_idsetores(idSetor);
        funcionario.setTurnos_idturnos(idTurno);

        // Cadastrar
        FuncionarioController controller = new FuncionarioController();

        controller.cadastrar(funcionario);

        JOptionPane.showMessageDialog(this,
                "Funcionário cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        // limparCamposCadastro();
        // listarFuncionarios();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar funcionário: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
}


private void limparCamposFuncionario() {

    // Limpar campos de texto
    tfCodigoFuncionarios.setText("");
    tfNomeFuncionario.setText("");
    tfEmail.setText("");
    txBarraPesquisaFuncionarios.setText("");

    // Limpar campos formatados
    ftfCPF.setValue(null);
    ftfDataNascimento.setValue(null);
    ftfTelefone.setValue(null);
    ftfDataAdimissao.setValue(null);

    // Limpar ComboBoxes
    cbCargo.setSelectedIndex(-1);
    cbSetorFuncionarios.setSelectedIndex(-1);
    cbTurnoFuncionario.setSelectedIndex(-1);
    cbStatus.setSelectedIndex(-1);
    cbStatusPF.setSelectedIndex(-1);

    // Desmarcar Checkbox
    chbFuncionarioAtivo.setState(false);

    // Limpar seleção da tabela
    TbCadastroFuncionario.clearSelection();
}     



private void cadastrarSetor() {

    try {

        // Capturar os dados
        String nome = tfNomeSetor.getText().trim();
        String descricao = tfDescricaoSetores.getText().trim();

        // Validar status selecionado
        if (cbStatusSetores22.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(this,
                    "Selecione o status do setor!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int status = cbStatusSetores22.getSelectedItem()
                .toString().equalsIgnoreCase("Ativo") ? 1 : 0;

        // Validar campos obrigatórios
        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Informe o nome do setor!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar tamanho do nome
        if (nome.length() > 45) {

            JOptionPane.showMessageDialog(this,
                    "O nome do setor deve possuir no máximo 45 caracteres!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar tamanho da descrição
        if (descricao.length() > 200) {

            JOptionPane.showMessageDialog(this,
                    "A descrição deve possuir no máximo 200 caracteres!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Criar Model
        Setores setor = new Setores();

        setor.setNome(nome);
        setor.setDescricao(descricao);
        setor.setStatus(status);

        // Cadastrar
        SetoresController controller = new SetoresController();

        controller.cadastrar(setor);

        JOptionPane.showMessageDialog(this,
                "Setor cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        limparCamposSetores();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar setor: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
}


private void limparCamposSetores() {
    tfCodigoSetores.setText("");
    tfNomeSetor.setText("");
    tfDescricaoSetores.setText("");

    cbStatusSetores22.setSelectedIndex(0);
    chbCargoAtivo1.setSelected(false);
    tfCodigoSetores.setText("");

    tfNomeSetor.requestFocus();

}


private void cadastrarCargo() {

    try {

        // Capturar os dados dos campos
        String nome = tfNomeCargo.getText().trim();
        String descricao = tfDescricao.getText().trim();

        // Validar campos obrigatórios antes da conversão
        if (nome.isEmpty()
                || ftfSalarioBase.getText().trim().isEmpty()
                || ftfCargaHorariaSemanalh.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Converter valores numéricos
        double salarioBase = Double.parseDouble(
                ftfSalarioBase.getText().trim().replace(",", "."));

        int cargaHoraria = Integer.parseInt(
                ftfCargaHorariaSemanalh.getText().trim());

        double adicionalPericulosidade =
                ftfAdicionalPericulocidade.getText().trim().isEmpty()
                ? 0.0
                : Double.parseDouble(
                        ftfAdicionalPericulocidade.getText().trim().replace(",", "."));

        double adicionalInsalubridade =
                ftfAdicionalInsalubridade.getText().trim().isEmpty()
                ? 0.0
                : Double.parseDouble(
                        ftfAdicionalInsalubridade.getText().trim().replace(",", "."));

        // Capturar status
        int status = chbCargoAtivo.isSelected() ? 1 : 0;

        // Validar tamanho do nome
        if (nome.length() > 45) {

            JOptionPane.showMessageDialog(this,
                    "O nome do cargo deve possuir no máximo 45 caracteres!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar tamanho da descrição
        if (descricao.length() > 300) {

            JOptionPane.showMessageDialog(this,
                    "A descrição deve possuir no máximo 300 caracteres!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar salário
        if (salarioBase <= 0) {

            JOptionPane.showMessageDialog(this,
                    "Informe um salário-base válido!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar carga horária
        if (cargaHoraria <= 0) {

            JOptionPane.showMessageDialog(this,
                    "Informe uma carga horária válida!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (adicionalPericulosidade < 0 || adicionalInsalubridade < 0) {

            JOptionPane.showMessageDialog(this,
                    "Os adicionais não podem ser negativos!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Criar Model
        Cargos cargo = new Cargos();

        cargo.setNome(nome);
        cargo.setDescricao(descricao);
        cargo.setSalarioBase(salarioBase);
        cargo.setCargaHoraria(cargaHoraria);
        cargo.setAdicionalPericulosidade(adicionalPericulosidade);
        cargo.setAdicionalInsalubridade(adicionalInsalubridade);
        cargo.setStatusCargo(status);

        // Cadastrar
        CargosController controller = new CargosController();

        controller.cadastrar(cargo);

        JOptionPane.showMessageDialog(this,
                "Cargo cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        limparCamposCargos();

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this,
                "Verifique os valores numéricos informados!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar cargo: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
}


private void limparCamposCargos() {

    tfCodigoCargos.setText("");
    tfNomeCargo.setText("");
    tfDescricao.setText("");

    ftfSalarioBase.setText("");
    ftfCargaHorariaSemanalh.setText("");
    ftfAdicionalPericulocidade.setText("");
    ftfAdicionalInsalubridade.setText("");

    chbCargoAtivo.setSelected(false);

    cbSetorCargos.setSelectedIndex(0);
    cbNivelHierarquia.setSelectedIndex(0);

    tfNomeCargo.requestFocus();
}    


private void cadastrarTurno() {

    try {

        // Capturar os dados dos campos
        String nome = tfNomeEscala.getText().trim();
        String descricao = tfDescricaoEscala.getText().trim();

        String horaInicio = ftfHoraInicio.getText().trim();
        String horaSaida = ftfHoraFim.getText().trim();

        String intervaloInicio = ftfIntervaloInicio.getText().trim();
        String intervaloFinal = ftfIntervaloFim.getText().trim();

        String duracaoTexto = ftfDuracaoTurno.getText().trim();

        // Validar campos obrigatórios
        if (nome.isEmpty() || horaInicio.isEmpty()
                || horaSaida.isEmpty() || intervaloInicio.isEmpty()
                || intervaloFinal.isEmpty() || duracaoTexto.isEmpty()
                || cbStatusSetores.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar tamanho do nome
        if (nome.length() > 45) {

            JOptionPane.showMessageDialog(this,
                    "O nome do turno deve possuir no máximo 45 caracteres!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar tamanho da descrição
        if (descricao.length() > 200) {

            JOptionPane.showMessageDialog(this,
                    "A descrição deve possuir no máximo 200 caracteres!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Converter duração
        int duracaoTurno = Integer.parseInt(duracaoTexto);

        if (duracaoTurno <= 0) {

            JOptionPane.showMessageDialog(this,
                    "Informe uma duração válida para o turno!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar horários
        java.time.format.DateTimeFormatter formato =
                java.time.format.DateTimeFormatter.ofPattern("HH:mm");

        java.time.LocalTime.parse(horaInicio, formato);
        java.time.LocalTime.parse(horaSaida, formato);
        java.time.LocalTime.parse(intervaloInicio, formato);
        java.time.LocalTime.parse(intervaloFinal, formato);

        // Capturar turno noturno e status
        int turnoNoturno = cbTurno.isSelected() ? 1 : 0;

        int status = cbStatusSetores.getSelectedItem()
                .toString().equalsIgnoreCase("Ativo") ? 1 : 0;

        // Criar Model
        Turnos turno = new Turnos();

        turno.setNome(nome);
        turno.setDescricao(descricao);
        turno.setHoraInicio(horaInicio);
        turno.setHoraSaida(horaSaida);
        turno.setIntervaloInicio(intervaloInicio);
        turno.setIntervaloFinal(intervaloFinal);
        turno.setDuracaoTurno(duracaoTurno);
        turno.setTurnoNoturno(turnoNoturno);
        turno.setStatus(status);

        // Cadastrar
        TurnosController controller = new TurnosController();

        controller.cadastrar(turno);

        JOptionPane.showMessageDialog(this,
                "Turno cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        limparCamposTurnos();

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this,
                "Informe uma duração válida para o turno!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);

    } catch (java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(this,
                "Informe os horários no formato HH:mm (exemplo: 07:00)!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar turno: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
}


private void limparCamposTurnos() {

    tfCodigoEscala.setText("");
    tfNomeEscala.setText("");
    tfDescricaoEscala.setText("");

    ftfHoraInicio.setText("");
    ftfHoraFim.setText("");

    ftfIntervaloInicio.setText("");
    ftfIntervaloFim.setText("");

    ftfDuracaoTurno.setText("");

    cbTurno.setSelected(false);

    cbStatusSetores.setSelectedIndex(0);

    cbSegunda.setSelected(false);
    cbTerca.setSelected(false);
    cbQuarta.setSelected(false);
    cbQuinta.setSelected(false);
    cbSabado.setSelected(false);
    cbDomingo.setSelected(false);
    jCheckBox8.setSelected(false);

    tfNomeEscala.requestFocus();
}



private void cadastrarEscala() {

    try {

        // Capturar os períodos
        String periodoInicial = ftfPeriodoInicio.getText().trim();
        String periodoFinal = ftfPeriodoFim.getText().trim();

        // Capturar os IDs selecionados
        Integer idFuncionario = obterIdSelecionado(cbFuncionarioEscala);
        Integer idSetor = obterIdSelecionado(cbSetorEscala);
        Integer idTurno = obterIdSelecionado(cbTurnoEscala);

        // Validar campos obrigatórios
        if (periodoInicial.isEmpty() || periodoFinal.isEmpty()
                || idFuncionario == null
                || idSetor == null
                || idTurno == null) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Criar Model
        Escala escala = new Escala();

        escala.setPeriodoInicial(periodoInicial);
        escala.setPeriodoFinal(periodoFinal);
        escala.setFuncionario_idFuncionario(idFuncionario);
        escala.setFuncionario_setores_idsetores(idSetor);
        escala.setFuncionario_turnos_idturnos(idTurno);

        // Cadastrar
        EscalaController controller = new EscalaController();

        controller.cadastrar(escala);

        JOptionPane.showMessageDialog(this,
                "Escala cadastrada com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        // Limpar os campos
        limparCamposEscala();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar escala: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
}



private void limparCamposEscala() {

    // Limpar períodos
    ftfPeriodoInicio.setValue(null);
    ftfPeriodoFim.setValue(null);

    // Limpar ComboBoxes
    cbSetorEscala.setSelectedIndex(0);
    cbTurnoEscala.setSelectedIndex(0);
    cbFuncionarioEscala.setSelectedIndex(0);

    // Limpar seleções das tabelas
    tbEscalaSemanal1.clearSelection();
    tbFuncionariosTurnos1.clearSelection();
    tbFuncionariosTurnos2.clearSelection();

}

private Integer obterIdSelecionado(JComboBox<String> cb) {

    List<Integer> ids = idsPorCombo.get(cb);

    if (ids == null) {
        return null;
    }

    int indice = cb.getSelectedIndex();

    if (indice <= 0 || indice >= ids.size()) {
        return null;
    }

    return ids.get(indice);
}

private int buscarIdFuncionario(String nomeFuncionario) throws SQLException {

    FuncionarioController controller = new FuncionarioController();

    List<Funcionario> funcionarios = controller.listar();

    for (Funcionario funcionario : funcionarios) {

        if (funcionario.getNome().equalsIgnoreCase(nomeFuncionario)) {
            return funcionario.getIdFuncionario();
        }
    }

    return -1;
}

private void cadastrarPonto() {

    try {

        // Capturar os dados dos campos
        String data = ftfData.getText().trim();
        String horario = lbHorario.getText().trim();
        String justificativa = txaJustificativaPonto.getText().trim();

        // Validar campos obrigatórios
        if (data.isEmpty() || horario.isEmpty()
                || cbFuncionarioEscala1.getSelectedItem() == null
                || cbSetorEscala1.getSelectedItem() == null
                || cbTurnoPonto.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar justificativa
        if (justificativa.length() > 200) {

            JOptionPane.showMessageDialog(this,
                    "A justificativa deve possuir no máximo 200 caracteres!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Recuperar nomes selecionados
        String nomeFuncionario =
                cbFuncionarioEscala1.getSelectedItem().toString();

        String nomeSetor =
                cbSetorEscala1.getSelectedItem().toString();

        String nomeTurno =
                cbTurnoPonto.getSelectedItem().toString();

        // Buscar IDs reais
        int idFuncionario = buscarIdFuncionario(nomeFuncionario);
        int idSetor = buscarIdSetor(nomeSetor);
        int idTurno = buscarIdTurno(nomeTurno);

        // Buscar cargo vinculado ao funcionário
        FuncionarioController funcionarioController =
                new FuncionarioController();

        Funcionario funcionario =
                funcionarioController.buscarPorId(idFuncionario);

        if (funcionario == null) {

            JOptionPane.showMessageDialog(this,
                    "Funcionário não encontrado!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idCargo = funcionario.getCargos_idcargos();

        // Validar IDs
        if (idFuncionario <= 0 || idCargo <= 0
                || idSetor <= 0 || idTurno <= 0) {

            JOptionPane.showMessageDialog(this,
                    "Não foi possível identificar os dados selecionados!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Arquivo opcional
        byte[] arquivo = null;

        // Criar Model
        Ponto ponto = new Ponto();

        ponto.setData(data);
        ponto.setHorario(horario);
        ponto.setJustificativa(justificativa);
        ponto.setArquivo(arquivo);
        ponto.setFuncionario_idFuncionario(idFuncionario);
        ponto.setFuncionario_cargos_idcargos(idCargo);
        ponto.setFuncionario_setores_idsetores(idSetor);
        ponto.setFuncionario_turnos_idturnos(idTurno);

        // Cadastrar
        PontoController controller = new PontoController();

        controller.cadastrar(ponto);

        JOptionPane.showMessageDialog(this,
                "Registro de ponto cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        limparCamposPonto();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao registrar ponto: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
}


private void limparCamposPonto() {

    ftfData.setText("");

    txaJustificativaPonto.setText("");

    cbFuncionarioEscala1.setSelectedIndex(-1);
    cbSetorEscala1.setSelectedIndex(-1);
    cbTurnoPonto.setSelectedIndex(-1);

    ftfData.requestFocus();

}


private void cadastrarAfastamento() {

    try {

        // Capturar os dados dos campos
        String tipo = cbBDferias.getSelectedItem().toString();
        String inicio = ftfPeriodoInicioBD.getText().trim();
        String fim = ftfPeriodoFimBD.getText().trim();
        String situacao = cbSituaçaoFerias.getSelectedItem().toString();

        // Data do registro
        String dataRegistro = java.time.LocalDate.now().toString();

        // Validar campos obrigatórios
        if (cbBDferias.getSelectedItem() == null
                || cbFuncionarioInfracao.getSelectedItem() == null
                || cbSituaçaoFerias.getSelectedItem() == null
                || inicio.isEmpty() || fim.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Converter e validar datas
        java.time.LocalDate dataInicio =
                java.time.LocalDate.parse(inicio);

        java.time.LocalDate dataFim =
                java.time.LocalDate.parse(fim);

        if (dataFim.isBefore(dataInicio)) {

            JOptionPane.showMessageDialog(this,
                    "A data final não pode ser anterior à data inicial!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Buscar funcionário selecionado
        String nomeFuncionario =
                cbFuncionarioInfracao.getSelectedItem().toString();

        int idFuncionario = buscarIdFuncionario(nomeFuncionario);

        if (idFuncionario <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Funcionário não encontrado!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Buscar dados do funcionário
        FuncionarioController funcionarioController =
                new FuncionarioController();

        Funcionario funcionario =
                funcionarioController.buscarPorId(idFuncionario);

        if (funcionario == null) {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível localizar os dados do funcionário!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idCargo = funcionario.getCargos_idcargos();
        int idSetor = funcionario.getSetores_idsetores();
        int idTurno = funcionario.getTurnos_idturnos();

        // Calcular quantidade de dias
        long quantidadeDias =
                java.time.temporal.ChronoUnit.DAYS.between(
                        dataInicio, dataFim) + 1;

        String dias = String.valueOf(quantidadeDias);

        // Criar Model
        Afastamentos afastamento = new Afastamentos();

        afastamento.setTipo(tipo);
        afastamento.setInicio(inicio);
        afastamento.setFinalAfastamento(fim);
        afastamento.setDias(dias);
        afastamento.setSituacao(situacao);
        afastamento.setDataRegistro(dataRegistro);
        afastamento.setFuncionario_idFuncionario(idFuncionario);
        afastamento.setFuncionario_cargos_idcargos(idCargo);
        afastamento.setFuncionario_setores_idsetores(idSetor);
        afastamento.setFuncionario_turnos_idturnos(idTurno);

        // Cadastrar
        AfastamentosController controller =
                new AfastamentosController();

        controller.cadastrar(afastamento);

        JOptionPane.showMessageDialog(this,
                "Afastamento cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

    } catch (java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(this,
                "Informe as datas no formato correto!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar afastamento: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
}


private void limparCamposAfastamento() {
    ftfInicioPeriodo.setText("");
    ftfFimPeriodo.setText("");
   
    cbSetorInfracoa.setSelectedIndex(0);
    cbTurnoInfracao.setSelectedIndex(0);
    cbFuncionarioInfracao.setSelectedIndex(0);
}


private void cadastrarFerias() {

    try {

        // Capturar os dados dos campos
        String inicio = ftfPeriodoInicioBD.getText().trim();
        String fim = ftfPeriodoFimBD.getText().trim();

        String situacao = cbSituaçaoFerias.getSelectedItem() != null
                ? cbSituaçaoFerias.getSelectedItem().toString()
                : "";

        // Validar campos obrigatórios
        if (inicio.isEmpty() || fim.isEmpty() || situacao.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Validar período
        java.time.LocalDate dataInicio =
                java.time.LocalDate.parse(inicio);

        java.time.LocalDate dataFim =
                java.time.LocalDate.parse(fim);

        if (dataFim.isBefore(dataInicio)) {

            JOptionPane.showMessageDialog(
                    this,
                    "A data final não pode ser anterior à data inicial!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Criar Model
        Ferias ferias = new Ferias();

        ferias.setInicio(inicio);
        ferias.setFinalFerias(fim);
        ferias.setSituacao(situacao);

        // Cadastrar
        FeriasController controller = new FeriasController();

        controller.cadastrar(ferias);

        JOptionPane.showMessageDialog(
                this,
                "Férias cadastradas com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        limparCamposFerias();

    } catch (java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(
                this,
                "Informe as datas no formato correto!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao cadastrar férias: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
}


private void limparCamposFerias() {
   ftfInicioPeriodo.setText("");
    ftfFimPeriodo.setText("");
    cbBDferias.setSelectedIndex(0);
    cbFuncionarioBDFerias.setSelectedIndex(0);
    cbSituaçaoSaldo.setSelectedIndex(0);
    cbSituaçaoFerias.setSelectedIndex(0);
}


private void registrarMeta() {

    try {

        // Capturar os dados dos campos
        String inicioTxt = ftfPeriodoInicioHistorico1.getText().trim();
        String fimTxt = ftfPeriodoFimMetas.getText().trim();
        String metaTxt = txMetasPrevistas.getText().trim().replace(",", ".");

        // Recuperar os IDs selecionados
        Integer idFuncionario = getIdSelecionado(cbFuncionarioMetas);
        Integer idCargo = getIdSelecionado(cbCargoRelatorio);
        Integer idSetor = getIdSelecionado(cbSetorMetas);
        Integer idTurno = getIdSelecionado(cbTurnoPonto);

        // Validar campos obrigatórios
        if (inicioTxt.replaceAll("[^0-9]", "").isEmpty()
                || fimTxt.replaceAll("[^0-9]", "").isEmpty()
                || metaTxt.isEmpty()
                || cbTipoMeta.getSelectedIndex() <= 0
                || idFuncionario == null
                || idCargo == null
                || idSetor == null
                || idTurno == null) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar datas
        DateTimeFormatter fmt = DateTimeFormatter
                .ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);

        LocalDate inicio;
        LocalDate fim;

        try {
            inicio = LocalDate.parse(inicioTxt, fmt);
            fim = LocalDate.parse(fimTxt, fmt);

        } catch (DateTimeParseException ex) {

            JOptionPane.showMessageDialog(this,
                    "Informe datas válidas no formato dd/MM/aaaa!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar período
        if (fim.isBefore(inicio)) {

            JOptionPane.showMessageDialog(this,
                    "O período final não pode ser anterior ao inicial!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Converter meta prevista
        double metaPrevista;

        try {
            metaPrevista = Double.parseDouble(metaTxt);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this,
                    "Informe uma meta numérica válida!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar meta
        if (!Double.isFinite(metaPrevista) || metaPrevista <= 0) {

            JOptionPane.showMessageDialog(this,
                    "A meta deve ser maior que zero!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Valores iniciais
        String tipo = cbTipoMeta.getSelectedItem().toString();
        double resultadoAtual = 0.0;
        double porcentagemAtingido = 0.0;
        String situacao = "Em andamento";

        // Criar Model
        Metas meta = new Metas();

        meta.setTipo(tipo);
        meta.setPeriodoInicial(inicio.toString());
        meta.setPeriodoFinal(fim.toString());
        meta.setMetaPrevista(metaPrevista);
        meta.setResultadoAtual(resultadoAtual);
        meta.setPorcentagemAtingida(porcentagemAtingido);
        meta.setSituacao(situacao);
        meta.setFuncionario_idFuncionario(idFuncionario);
        
       

        // Cadastrar
        MetasController controller = new MetasController();

        controller.cadastrar(meta);

        JOptionPane.showMessageDialog(this,
                "Meta registrada com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        limparCamposMeta();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao registrar meta: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
}


private void limparCamposMeta() {
    ftfPeriodoInicioHistorico1.setText(""); 
    ftfPeriodoFimMetas.setText("");
    txMetasPrevistas.setText(""); 
    cbTipoMeta.setSelectedIndex(0);
    cbFuncionarioMetas.setSelectedIndex(0);
    cbSetorMetas.setSelectedIndex(0);
    cbSituaçaoSaldoMetas.setSelectedIndex(0);
    cbSituaçaoFeriasMetas.setSelectedIndex(0);
}

// 1. Método para preencher os ComboBoxes
private <T> void preencherCombo(
        JComboBox<String> cb,  List<T> lista, Function<T, Integer> getId, Function<T, String> getNome) {

    DefaultComboBoxModel<String> modelo =
            new DefaultComboBoxModel<>();

    List<Integer> ids = new ArrayList<>();

    modelo.addElement(SELECIONE);
    ids.add(null);

    if (lista != null) {
        for (T item : lista) {
            modelo.addElement(getNome.apply(item));
            ids.add(getId.apply(item));
        }
    }

    cb.setModel(modelo);
    idsPorCombo.put(cb, ids);
    cb.setSelectedIndex(0);
}


private void carregarTurnos() {

    try {

        TurnosController controller = new TurnosController();

        List<Turnos> lista = controller.listar();

        System.out.println("CARREGANDO TURNOS...");
        System.out.println("Turnos encontrados: " + lista.size());

        for (Turnos t : lista) {
            System.out.println(
                "ID: " + t.getIdturnos() +
                " | Nome: " + t.getNome()
            );
        }

        for (JComboBox<String> cb : List.of(cbTurnoFuncionario,
                cbSelecioneTurno,
                cbTurnoEscala,
                cbTurnoPonto,
                cbTurnoInfracao
        )) {

            preencherCombo(
                cb,
                lista,
                Turnos::getIdturnos,
                Turnos::getNome
            );
        }

    } catch (Exception e) {

        erroCarregar("turnos", e);
        e.printStackTrace();
    }
}

private void erroCarregar(String lista, Exception e) {
    JOptionPane.showMessageDialog(this, "Erro ao carregar " + lista + ": " + e.getMessage(),
            "Erro", JOptionPane.ERROR_MESSAGE);
}

private void carregarCargos() {
    try {

        CargosController controller = new CargosController();
        List<Cargos> lista = controller.listar();

        for (JComboBox<String> cb : List.of(cbCargo, cbCargoRelatorio)) {

            cb.removeAllItems();
            cb.addItem("Selecione");

            for (Cargos cargo : lista) {
                cb.addItem(cargo.getNome());
            }
        }

    } catch (SQLException e) {
        erroCarregar("cargos", e);
    }
}
 
private void carregarSetores() {
    try {

        SetoresController controller = new SetoresController();
        List<Setores> lista = controller.listar();

        for (JComboBox<String> cb : List.of(
                cbSetorCargos,
                cbSetorCargos1,
                cbSetorEscala,
                cbSetorEscala1,
                cbSetorInfracoa,
                cbSetorFuncionarios,
                cbSetorHistorico,
                cbSetorMetas,
                cbSetorRelatorio)) {

            cb.removeAllItems();
            cb.addItem("Selecione");

            for (Setores setor : lista) {
                cb.addItem(setor.getNome());
            }
        }

    } catch (SQLException e) {
        erroCarregar("setores", e);
    }
}
 
private void carregarTiposMeta() {

    try {

        MetasController controller = new MetasController();
        List<Metas> lista = controller.listar();

        cbTipoMeta.removeAllItems();
        cbTipoMeta.addItem("Selecione");

        for (Metas meta : lista) {

            String tipo = meta.getTipo();

            boolean existe = false;

            for (int i = 0; i < cbTipoMeta.getItemCount(); i++) {
                if (cbTipoMeta.getItemAt(i).equalsIgnoreCase(tipo)) {
                    existe = true;
                    break;
                }
            }

            if (!existe && tipo != null && !tipo.trim().isEmpty()) {
                cbTipoMeta.addItem(tipo);
            }
        }

    } catch (SQLException e) {
        erroCarregar("tipos de metas", e);
    }
}


private void carregarFuncionarios() {

    try {

        FuncionarioController controller = new FuncionarioController();
        List<Funcionario> lista = controller.listar();

        for (JComboBox<String> cb : List.of(
                cbFuncionarioBDFerias,
                cbFuncionarioEscala,
                cbFuncionarioEscala1,
                cbFuncionarioInfracao,
                cbFuncionarioHistorico,
                cbFuncionarioMetas,
                cbFuncionarioRelatorio)) {

            cb.removeAllItems();
            cb.addItem("Selecione");

            for (Funcionario funcionario : lista) {
                cb.addItem(funcionario.getNome());
            }
        }

    } catch (SQLException e) {
        erroCarregar("funcionários", e);
    }
}




// ================= LISTAS FIXAS (1 método por tipo) =================

private void preencherComboFixo(
        JComboBox<String> cb,
        String... itens) {

    DefaultComboBoxModel<String> modelo =
            new DefaultComboBoxModel<>();

    modelo.addElement(SELECIONE);

    for (String item : itens) {
        modelo.addElement(item);
    }

    cb.setModel(modelo);
    cb.setSelectedIndex(0);
}


private void carregarStatus() {

    for (JComboBox<String> cb : List.of(cbStatus,
            cbStatusPF,
            cbStatusPF1,
            cbStatusSetores22,
            cbSetorPai,
            cbStatusSetores
    )) {
        preencherComboFixo(cb,
                "Ativo",
                "Inativo",
                "Em andamento",
                "Concluída",
                "Cancelada"
        );
    }
}

private void carregarSituacaoFerias() {

    for (JComboBox<String> cb : List.of(cbSituaçaoFerias,
            cbSituaçaoFeriasHistorico,
            cbSituaçaoFeriasMetas,
            cbBDferias
    )) {
        preencherComboFixo(cb,
                "Agendada",
                "Aprovada",
                "Em andamento",
                "Concluída",
                "Cancelada"
        );
    }
}

private void carregarSituacaoSaldo() {

    for (JComboBox<String> cb : List.of(cbSituaçaoSaldo,
            cbSituaçaoSaldoHistorico,
            cbSituaçaoSaldoMetas
    )) {
        preencherComboFixo(cb,
                "Disponível",
                "Parcial",
                "Vencido",
                "Utilizado"
        );
    }
}

private void carregarNivelHierarquia() {

    for (JComboBox<String> cb : List.of(
            cbNivelHierarquia,
            cbNivelHierarquia1
    )) {
        preencherComboFixo(cb,
                "Diretoria",
                "Gerência",
                "Supervisão",
                "Operacional"
        );
    }
}

private void carregarFiltrosRelatorio() {

    preencherComboFixo(
            cbSituaçãoRelatorio,
            "Todos",
            "Ativos",
            "Inativos"
    );

    preencherComboFixo(
            cbTipoRelatoio,
            "Funcionários",
            "Escala",
            "Férias",
            "Afastamentos",
            "Ponto"
    );

    preencherComboFixo(
            cbFormatoRelatorio,
            "PDF",
            "Excel (XLSX)",
            "CSV"
    );
}

private Integer getIdSelecionado(JComboBox<String> combo) {

    List<Integer> ids = idsPorCombo.get(combo);

    if (ids == null) {
        return null;
    }

    int indice = combo.getSelectedIndex();

    if (indice <= 0 || indice >= ids.size()) {
        return null;
    }

    return ids.get(indice);
}
 
// ================= CHAMADA ÚNICA (opcional) =================
 
private void carregarComboBoxes() {
    carregarCargos();
    carregarSetores();
    carregarTurnos();
    carregarFuncionarios();
    carregarStatus();
    carregarSituacaoFerias();
    carregarSituacaoSaldo();
    carregarNivelHierarquia();
    carregarFiltrosRelatorio();
}
 


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        telaRH = new javax.swing.JTabbedPane();
        pagFuncionarios = new javax.swing.JPanel();
        areaCadastro = new javax.swing.JPanel();
        pagOpcoesConfing = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        lbCodigo = new javax.swing.JLabel();
        lbNomeFuncionario = new javax.swing.JLabel();
        lbCPF = new javax.swing.JLabel();
        lbDataNascimento = new javax.swing.JLabel();
        lbTurno = new javax.swing.JLabel();
        tfCodigoFuncionarios = new javax.swing.JTextField();
        tfNomeFuncionario = new javax.swing.JTextField();
        cbTurnoFuncionario = new javax.swing.JComboBox<>();
        lbTelefone = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        lbCargo = new javax.swing.JLabel();
        cbCargo = new javax.swing.JComboBox<>();
        lbSetorFuncionarios = new javax.swing.JLabel();
        cbSetorFuncionarios = new javax.swing.JComboBox<>();
        lbDataAdmissao = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();
        chbFuncionarioAtivo = new java.awt.Checkbox();
        ftfDataNascimento = new javax.swing.JFormattedTextField();
        ftfCPF = new javax.swing.JFormattedTextField();
        ftfDataAdimissao = new javax.swing.JFormattedTextField();
        ftfTelefone = new javax.swing.JFormattedTextField();
        lbPesquisarFuncionario = new javax.swing.JLabel();
        txBarraPesquisaFuncionarios = new javax.swing.JTextField();
        lbStatus = new javax.swing.JLabel();
        cbStatusPF = new javax.swing.JComboBox<>();
        lbAvisoBarraPesquisa = new javax.swing.JLabel();
        jScrollPane1010000 = new javax.swing.JScrollPane();
        TbCadastroFuncionario = new javax.swing.JTable();
        pagSetores = new javax.swing.JPanel();
        tfNomeSetor = new javax.swing.JTextField();
        lbStatus2 = new javax.swing.JLabel();
        cbStatusSetores22 = new javax.swing.JComboBox<>();
        cbNivelHierarquia1 = new javax.swing.JComboBox<>();
        lbDescricoaSetores = new javax.swing.JLabel();
        tfCodigoSetores = new javax.swing.JTextField();
        lbPesquisarFuncionario2 = new javax.swing.JLabel();
        lbGerenteResponsavel = new javax.swing.JLabel();
        lbNomeSetor = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbSetorCargos1 = new javax.swing.JComboBox<>();
        lbSetorPai = new javax.swing.JLabel();
        tfDescricaoSetores = new javax.swing.JTextField();
        txBarraPesquisaFuncionarios2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TbCadastroSetores = new javax.swing.JTable();
        btnNovo2 = new javax.swing.JButton();
        btnSalvar2 = new javax.swing.JButton();
        btnEditar2 = new javax.swing.JButton();
        btnExcluir2 = new javax.swing.JButton();
        btnLimpar2 = new javax.swing.JButton();
        btnPesquisar2 = new javax.swing.JButton();
        btnVoltar2 = new javax.swing.JButton();
        lbAtivoSetores = new javax.swing.JLabel();
        chbCargoAtivo1 = new javax.swing.JCheckBox();
        cbSetorPai = new javax.swing.JComboBox<>();
        lbSetorpai = new javax.swing.JLabel();
        lbAvisoBarraPesquisa2 = new javax.swing.JLabel();
        pagCargos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbNomeCargo = new javax.swing.JLabel();
        lbDescricaoCargos = new javax.swing.JLabel();
        lbSetroCargos = new javax.swing.JLabel();
        lbNivelHierarquia = new javax.swing.JLabel();
        lbSalarioBase = new javax.swing.JLabel();
        lbCargaHoraiaSem = new javax.swing.JLabel();
        lbAdicionalPericulocidade = new javax.swing.JLabel();
        lbAdicionalInsalubridade = new javax.swing.JLabel();
        lbCargoAtivo = new javax.swing.JLabel();
        lbPesquisarFuncionario1 = new javax.swing.JLabel();
        txBarraPesquisaFuncionarios1 = new javax.swing.JTextField();
        lbStatus1 = new javax.swing.JLabel();
        cbStatusPF1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TbCadastroCargos = new javax.swing.JTable();
        lbAvisoBarraPesquisa1 = new javax.swing.JLabel();
        tfCodigoCargos = new javax.swing.JTextField();
        tfNomeCargo = new javax.swing.JTextField();
        tfDescricao = new javax.swing.JTextField();
        cbSetorCargos = new javax.swing.JComboBox<>();
        cbNivelHierarquia = new javax.swing.JComboBox<>();
        chbCargoAtivo = new javax.swing.JCheckBox();
        btnNovo1 = new javax.swing.JButton();
        btnSalvar1 = new javax.swing.JButton();
        btnEditar1 = new javax.swing.JButton();
        btnExcluir1 = new javax.swing.JButton();
        btnLimpar1 = new javax.swing.JButton();
        btnPesquisar1 = new javax.swing.JButton();
        btnVoltar1 = new javax.swing.JButton();
        ftfSalarioBase = new javax.swing.JTextField();
        ftfAdicionalInsalubridade = new javax.swing.JTextField();
        ftfCargaHorariaSemanalh = new javax.swing.JTextField();
        ftfAdicionalPericulocidade = new javax.swing.JTextField();
        pagTurnos = new javax.swing.JPanel();
        lbCodigoTurno = new javax.swing.JLabel();
        tfCodigoEscala = new javax.swing.JTextField();
        lbNomeTurno = new javax.swing.JLabel();
        tfNomeEscala = new javax.swing.JTextField();
        lbDescricoaEscala = new javax.swing.JLabel();
        tfDescricaoEscala = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lbHoraInicio = new javax.swing.JLabel();
        lbHoraFim = new javax.swing.JLabel();
        lbIntervaloInicio = new javax.swing.JLabel();
        lbIntervaloFim = new javax.swing.JLabel();
        lbDuracaoTurno = new javax.swing.JLabel();
        lbTurnoNoturno = new javax.swing.JLabel();
        lbDiaSemana = new javax.swing.JLabel();
        ftfHoraInicio = new javax.swing.JFormattedTextField();
        ftfHoraFim = new javax.swing.JFormattedTextField();
        ftfIntervaloInicio = new javax.swing.JFormattedTextField();
        ftfIntervaloFim = new javax.swing.JFormattedTextField();
        cbTurno = new javax.swing.JCheckBox();
        ftfDuracaoTurno = new javax.swing.JFormattedTextField();
        lbAtivoTurnos = new javax.swing.JLabel();
        cbSegunda = new javax.swing.JCheckBox();
        cbQuarta = new javax.swing.JCheckBox();
        cbTerca = new javax.swing.JCheckBox();
        cbDomingo = new javax.swing.JCheckBox();
        cbQuinta = new javax.swing.JCheckBox();
        cbSabado = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        btnNovo3 = new javax.swing.JButton();
        btnSalvar3 = new javax.swing.JButton();
        btnEditar3 = new javax.swing.JButton();
        btnExcluir3 = new javax.swing.JButton();
        btnLimpar3 = new javax.swing.JButton();
        btnPesquisar3 = new javax.swing.JButton();
        btnVoltar3 = new javax.swing.JButton();
        lbTurnosCadastrados = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbTurnosCadastrados = new javax.swing.JTable();
        lbFuncionarioTurnoSelecionado = new javax.swing.JLabel();
        lbSelecioneTurno = new javax.swing.JLabel();
        cbSelecioneTurno = new javax.swing.JComboBox<>();
        btnAdicFuncionario = new javax.swing.JButton();
        btnAtualizarFuncionario = new javax.swing.JButton();
        btnExcluFuncionario = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbFuncionariosTurnos = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        txPesquisarTurno = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbStatusSetores = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        pagEscala = new javax.swing.JPanel();
        lbEscalaSemanal = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbEscalaSemanal1 = new javax.swing.JTable();
        lbDetalhesEscala = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbFuncionariosTurnos1 = new javax.swing.JTable();
        btnCopiarEscala = new javax.swing.JButton();
        btnEditarEscala = new javax.swing.JButton();
        btnExcluirEscala = new javax.swing.JButton();
        btnAdicionarEscala = new javax.swing.JButton();
        btnPuplicarEscala = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbFuncionariosTurnos2 = new javax.swing.JTable();
        btnLimparEscala1 = new javax.swing.JButton();
        btnPesquisarEscala = new javax.swing.JButton();
        ftfPeriodoInicio = new javax.swing.JFormattedTextField();
        ftfPeriodoFim = new javax.swing.JFormattedTextField();
        cbSetorEscala = new javax.swing.JComboBox<>();
        cbTurnoEscala = new javax.swing.JComboBox<>();
        cbFuncionarioEscala = new javax.swing.JComboBox<>();
        lbFuncionarioEscala = new javax.swing.JLabel();
        lbTurnoEscala = new javax.swing.JLabel();
        lbSetor = new javax.swing.JLabel();
        lbPeriodoEscala = new javax.swing.JLabel();
        lbSinalSeta = new javax.swing.JLabel();
        lbResumoEscala = new javax.swing.JLabel();
        btnSalvarEscala = new javax.swing.JButton();
        pagInfraçoes = new javax.swing.JPanel();
        pagInfracoes = new javax.swing.JPanel();
        lbPeriodoInfraçoes = new javax.swing.JLabel();
        ftfInicioPeriodo = new javax.swing.JFormattedTextField();
        ftfFimPeriodo = new javax.swing.JFormattedTextField();
        lbSetorInfraçoes = new javax.swing.JLabel();
        cbSetorInfracoa = new javax.swing.JComboBox<>();
        lbTurnoEscalaInfraçoes = new javax.swing.JLabel();
        cbTurnoInfracao = new javax.swing.JComboBox<>();
        lbFuncionarioEscalaInfraçoes = new javax.swing.JLabel();
        cbFuncionarioInfracao = new javax.swing.JComboBox<>();
        btnPesquisarInfracoes = new javax.swing.JButton();
        btnLimparInfracoes = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbOcorrencias = new javax.swing.JTable();
        lbResumoOcorrencias = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbResumoOcorrencias = new javax.swing.JTable();
        ldAçoes1 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnJustificar = new javax.swing.JButton();
        btnAprovar = new javax.swing.JButton();
        btnEditarInfarçoes = new javax.swing.JButton();
        btnExcluirInfraçoes = new javax.swing.JButton();
        btnEncerrar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbDetalheOcorrencia = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnReprovar = new javax.swing.JButton();
        btnRegistrarRetorno = new javax.swing.JButton();
        lbInicio = new javax.swing.JLabel();
        lbFimPeriodo = new javax.swing.JLabel();
        pagPonto = new javax.swing.JPanel();
        lbPeriodoPonto = new javax.swing.JLabel();
        ftfData = new javax.swing.JFormattedTextField();
        ftfPeriodoFim1 = new javax.swing.JFormattedTextField();
        lbSetorPonto = new javax.swing.JLabel();
        cbSetorEscala1 = new javax.swing.JComboBox<>();
        lbTurnoEscalaPonto = new javax.swing.JLabel();
        cbTurnoPonto = new javax.swing.JComboBox<>();
        lbFuncionarioEscalaPonto = new javax.swing.JLabel();
        cbFuncionarioEscala1 = new javax.swing.JComboBox<>();
        btnPesquisarEscala1 = new javax.swing.JButton();
        btnLimparEscala2 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbEscalaSemanal2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ldAçoes = new javax.swing.JLabel();
        btnSalvarPonto = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lbHorario = new javax.swing.JLabel();
        lbJustificativa = new javax.swing.JLabel();
        scJustificativa = new javax.swing.JScrollPane();
        txaJustificativaPonto = new javax.swing.JTextArea();
        btnAdicionarArquivo = new javax.swing.JButton();
        pagBancodeDados = new javax.swing.JPanel();
        lbPeriodoBD = new javax.swing.JLabel();
        ftfPeriodoInicioBD = new javax.swing.JFormattedTextField();
        ftfPeriodoFimBD = new javax.swing.JFormattedTextField();
        lbBDFerias = new javax.swing.JLabel();
        cbBDferias = new javax.swing.JComboBox<>();
        cbSituaçaoSaldo = new javax.swing.JComboBox<>();
        lbSituaçaoSaldoBD = new javax.swing.JLabel();
        lbFuncionarioBDFerias = new javax.swing.JLabel();
        cbFuncionarioBDFerias = new javax.swing.JComboBox<>();
        lbResumoOcorrencias1 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbResumoBancoDados = new javax.swing.JTable();
        btnPesquisarBD = new javax.swing.JButton();
        btnLimparBD = new javax.swing.JButton();
        lbBancoDadosFerias = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tbBancodadosFerias = new javax.swing.JTable();
        lbRegistrosFerias = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tbInformaçoesFerias = new javax.swing.JTable();
        btnRegistrarBD = new javax.swing.JButton();
        ldAçoesBD = new javax.swing.JLabel();
        btnJHistoricoFeriasBD = new javax.swing.JButton();
        btnAprovarBD = new javax.swing.JButton();
        btnReprovarBD = new javax.swing.JButton();
        btnEditarBD = new javax.swing.JButton();
        btnExcluirBD = new javax.swing.JButton();
        btnHistoricoAjustesBD = new javax.swing.JButton();
        btnRegistrarRetornoBD = new javax.swing.JButton();
        lbSituaçaoFeriasBD = new javax.swing.JLabel();
        cbSituaçaoFerias = new javax.swing.JComboBox<>();
        pagMetas = new javax.swing.JPanel();
        lbPeriodoHistorico1 = new javax.swing.JLabel();
        ftfPeriodoInicioHistorico1 = new javax.swing.JFormattedTextField();
        cbSetorMetas = new javax.swing.JComboBox<>();
        lbSetorMetas = new javax.swing.JLabel();
        lbFuncionarioMetas = new javax.swing.JLabel();
        cbFuncionarioMetas = new javax.swing.JComboBox<>();
        cbSituaçaoSaldoMetas = new javax.swing.JComboBox<>();
        lbSituaçaoSaldoMetas = new javax.swing.JLabel();
        cbSituaçaoFeriasMetas = new javax.swing.JComboBox<>();
        lbSituaçaoFeriasMetas = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tbHistoricoDesenpenho1 = new javax.swing.JTable();
        lbHIstoricoDesenpenho1 = new javax.swing.JLabel();
        lbDetalhesFuncionario1 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tbDetalhesFuncionario1 = new javax.swing.JTable();
        PnDesempenhoMensal1 = new javax.swing.JPanel();
        lbProdutividade1 = new javax.swing.JLabel();
        btnPesquisarMetas = new javax.swing.JButton();
        btnLimparMetas = new javax.swing.JButton();
        lbAtitude1 = new javax.swing.JLabel();
        lbTrabalhoEquipe1 = new javax.swing.JLabel();
        btnImprimirHistorico1 = new javax.swing.JButton();
        btnExporHistorico1 = new javax.swing.JButton();
        lbAçao1 = new javax.swing.JLabel();
        btnVisualizaçaoHistorico1 = new javax.swing.JButton();
        btnCompararColaboradores1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        lbInicioPeriodo = new javax.swing.JLabel();
        lbPeriodoFim = new javax.swing.JLabel();
        ftfPeriodoFimMetas = new javax.swing.JFormattedTextField();
        lbTipoMeta = new javax.swing.JLabel();
        cbTipoMeta = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txMetasPrevistas = new javax.swing.JTextField();
        btnSalvarMetas = new javax.swing.JButton();
        pagRelatorios = new javax.swing.JPanel();
        lbPeriodoRelatorio = new javax.swing.JLabel();
        lbFuncionarioRelatorio = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        ftfPeriodoRelatorio = new javax.swing.JFormattedTextField();
        cbFuncionarioRelatorio = new javax.swing.JComboBox<>();
        cbSetorRelatorio = new javax.swing.JComboBox<>();
        cbCargoRelatorio = new javax.swing.JComboBox<>();
        cbTipoRelatoio = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cbFormatoRelatorio = new javax.swing.JComboBox<>();
        cbSituaçãoRelatorio = new javax.swing.JComboBox<>();
        cbIncluirDesligados = new javax.swing.JCheckBox();
        cbAgruparSetor = new javax.swing.JCheckBox();
        btnPesquisarRelatorio = new javax.swing.JButton();
        btnLimparRelatorio = new javax.swing.JButton();
        jScrollPane25 = new javax.swing.JScrollPane();
        tbRelatoriosDiponiveis = new javax.swing.JTable();
        lbRelatorioDisponiveis = new javax.swing.JLabel();
        lbHistoricoRelatorios = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        tbHistoricoRelatorioGerados = new javax.swing.JTable();
        lbGeraçaoRelatorio = new javax.swing.JLabel();
        pagHistorico = new javax.swing.JPanel();
        PnDesempenhoMensal = new javax.swing.JPanel();
        lbPeriodoHistorico = new javax.swing.JLabel();
        ftfPeriodoInicioHistorico = new javax.swing.JFormattedTextField();
        ftfPeriodoFimHistorico = new javax.swing.JFormattedTextField();
        lbBDFerias1 = new javax.swing.JLabel();
        cbSetorHistorico = new javax.swing.JComboBox<>();
        lbFuncionarioBDFerias1 = new javax.swing.JLabel();
        cbFuncionarioHistorico = new javax.swing.JComboBox<>();
        lbSituaçaoSaldoBD1 = new javax.swing.JLabel();
        cbSituaçaoSaldoHistorico = new javax.swing.JComboBox<>();
        lbSituaçaoFeriasBD1 = new javax.swing.JLabel();
        cbSituaçaoFeriasHistorico = new javax.swing.JComboBox<>();
        btnPesquisarHistorico = new javax.swing.JButton();
        btnLimparHistorico = new javax.swing.JButton();
        lbHIstoricoDesenpenho = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tbHistoricoDesenpenho = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        tbDetalhesFuncionario = new javax.swing.JTable();
        lbDetalhesFuncionario = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbTrabalhoEquipe = new javax.swing.JLabel();
        lbProdutividade = new javax.swing.JLabel();
        lbQualidade = new javax.swing.JLabel();
        lbAtitude = new javax.swing.JLabel();
        btnCompararColaboradores = new javax.swing.JButton();
        btnExporHistorico = new javax.swing.JButton();
        btnImprimirHistorico = new javax.swing.JButton();
        btnVisualizaçaoHistorico = new javax.swing.JButton();
        lbAçao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        telaRH.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        pagFuncionarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pagFuncionarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNovo.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\NOVO.png")); // NOI18N
        btnNovo.setText("Novo");

        btnSalvar.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\SALVAR.png")); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        btnEditar.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EDITAR.png")); // NOI18N
        btnEditar.setText("Editar");

        btnExcluir.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EXCLUIR.png")); // NOI18N
        btnExcluir.setText("Excluir");

        btnLimpar.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimpar.setText("Limpar");

        btnPesquisar.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisar.setText("Pesquisar");

        btnVoltar.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\seta-esquerda.png")); // NOI18N
        btnVoltar.setText("Voltar");

        javax.swing.GroupLayout pagOpcoesConfingLayout = new javax.swing.GroupLayout(pagOpcoesConfing);
        pagOpcoesConfing.setLayout(pagOpcoesConfingLayout);
        pagOpcoesConfingLayout.setHorizontalGroup(
            pagOpcoesConfingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagOpcoesConfingLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(btnPesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        pagOpcoesConfingLayout.setVerticalGroup(
            pagOpcoesConfingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagOpcoesConfingLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pagOpcoesConfingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        lbCodigo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbCodigo.setText("Código");

        lbNomeFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbNomeFuncionario.setText("Nome Completo*");

        lbCPF.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbCPF.setText("CPF*");

        lbDataNascimento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbDataNascimento.setText("Data de Nascimento*");

        lbTurno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTurno.setText("Turno");

        tfCodigoFuncionarios.addActionListener(this::tfCodigoFuncionariosActionPerformed);

        cbTurnoFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbTelefone.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTelefone.setText("Telefone");

        lbEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbEmail.setText("E-mail");

        tfEmail.addActionListener(this::tfEmailActionPerformed);

        lbCargo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbCargo.setText("Cargo*");

        cbCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbSetorFuncionarios.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbSetorFuncionarios.setText("Setor*");

        cbSetorFuncionarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "????" }));

        lbDataAdmissao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbDataAdmissao.setText("Data de admissão");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Status");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Desativado", " " }));
        cbStatus.addActionListener(this::cbStatusActionPerformed);

        chbFuncionarioAtivo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        chbFuncionarioAtivo.setLabel("Funcionário ativo");

        try {
            ftfDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            ftfCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfCPF.addActionListener(this::ftfCPFActionPerformed);

        try {
            ftfDataAdimissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        ftfTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        javax.swing.GroupLayout areaCadastroLayout = new javax.swing.GroupLayout(areaCadastro);
        areaCadastro.setLayout(areaCadastroLayout);
        areaCadastroLayout.setHorizontalGroup(
            areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(areaCadastroLayout.createSequentialGroup()
                        .addComponent(lbTurno)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(areaCadastroLayout.createSequentialGroup()
                        .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(areaCadastroLayout.createSequentialGroup()
                                .addComponent(ftfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(areaCadastroLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbEmail)))
                                    .addComponent(lbDataAdmissao))
                                .addGap(46, 46, 46)
                                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCargo)
                                    .addComponent(cbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(areaCadastroLayout.createSequentialGroup()
                                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbTurnoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfCodigoFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbCodigo)
                                    .addComponent(lbTelefone))
                                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(areaCadastroLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbNomeFuncionario))
                                        .addGap(164, 164, 164)
                                        .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbCPF)
                                            .addComponent(ftfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(areaCadastroLayout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(ftfDataAdimissao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(106, 106, 106)
                        .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(areaCadastroLayout.createSequentialGroup()
                                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbSetorFuncionarios)
                                    .addComponent(cbSetorFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbFuncionarioAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 68, Short.MAX_VALUE))
                            .addGroup(areaCadastroLayout.createSequentialGroup()
                                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDataNascimento)
                                    .addComponent(ftfDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(pagOpcoesConfing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        areaCadastroLayout.setVerticalGroup(
            areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaCadastroLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo)
                    .addComponent(lbNomeFuncionario)
                    .addComponent(lbCPF)
                    .addComponent(lbDataNascimento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCodigoFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefone)
                    .addComponent(lbEmail)
                    .addComponent(lbCargo)
                    .addComponent(lbSetorFuncionarios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSetorFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTurno)
                    .addComponent(lbDataAdmissao)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(areaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbTurnoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ftfDataAdimissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chbFuncionarioAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(pagOpcoesConfing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pagFuncionarios.add(areaCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 1209, -1));

        lbPesquisarFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbPesquisarFuncionario.setText("Pesquisa funcionário");
        pagFuncionarios.add(lbPesquisarFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 396, -1, 29));
        pagFuncionarios.add(txBarraPesquisaFuncionarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 402, 617, -1));

        lbStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbStatus.setText("Status");
        pagFuncionarios.add(lbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(837, 396, 60, 29));

        cbStatusPF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pagFuncionarios.add(cbStatusPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(903, 402, 300, -1));

        lbAvisoBarraPesquisa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbAvisoBarraPesquisa.setText("⚠️ Campos obrigatórios: Nome completo,CPF, Cargo e Setor.");
        pagFuncionarios.add(lbAvisoBarraPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 443, 1170, 49));

        TbCadastroFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "CPF", "Cargo", "Setor", "Turno", "Adimissão", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1010000.setViewportView(TbCadastroFuncionario);

        pagFuncionarios.add(jScrollPane1010000, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 1170, 199));

        telaRH.addTab("Funcionários", pagFuncionarios);

        pagSetores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        tfNomeSetor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbStatus2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbStatus2.setText("Status");

        cbStatusSetores22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbStatusSetores22.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbNivelHierarquia1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbNivelHierarquia1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbDescricoaSetores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDescricoaSetores.setText("Descrição");

        tfCodigoSetores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbPesquisarFuncionario2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbPesquisarFuncionario2.setText("Pesquisa funcionário");

        lbGerenteResponsavel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbGerenteResponsavel.setText("Gerente Responsável");

        lbNomeSetor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbNomeSetor.setText("Nome do setor");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Código");

        cbSetorCargos1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSetorCargos1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbSetorPai.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSetorPai.setText("Setor pai");

        tfDescricaoSetores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txBarraPesquisaFuncionarios2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        TbCadastroSetores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TbCadastroSetores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome do setor", "Setor pai", "Gerente responsavel", "Descrição ", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(TbCadastroSetores);

        btnNovo2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNovo2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\NOVO.png")); // NOI18N
        btnNovo2.setText("Novo");

        btnSalvar2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvar2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\SALVAR.png")); // NOI18N
        btnSalvar2.setText("Salvar");
        btnSalvar2.addActionListener(this::btnSalvar2ActionPerformed);

        btnEditar2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditar2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EDITAR.png")); // NOI18N
        btnEditar2.setText("Editar");

        btnExcluir2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluir2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EXCLUIR.png")); // NOI18N
        btnExcluir2.setText("Excluir");

        btnLimpar2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimpar2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimpar2.setText("Limpar");
        btnLimpar2.addActionListener(this::btnLimpar2ActionPerformed);

        btnPesquisar2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisar2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisar2.setText("Pesquisar");

        btnVoltar2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnVoltar2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\seta-esquerda.png")); // NOI18N
        btnVoltar2.setText("Voltar");
        btnVoltar2.addActionListener(this::btnVoltar2ActionPerformed);

        lbAtivoSetores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbAtivoSetores.setText("Ativo");

        chbCargoAtivo1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        chbCargoAtivo1.setText("Ativo");

        cbSetorPai.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSetorPai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbSetorpai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbSetorpai.setText("Setor pai");

        lbAvisoBarraPesquisa2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbAvisoBarraPesquisa2.setText("⚠️ Campos obrigatórios: Nome do cargo");

        javax.swing.GroupLayout pagSetoresLayout = new javax.swing.GroupLayout(pagSetores);
        pagSetores.setLayout(pagSetoresLayout);
        pagSetoresLayout.setHorizontalGroup(
            pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagSetoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(lbAvisoBarraPesquisa2, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pagSetoresLayout.createSequentialGroup()
                        .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagSetoresLayout.createSequentialGroup()
                                .addComponent(lbPesquisarFuncionario2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txBarraPesquisaFuncionarios2, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(lbStatus2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbStatusSetores22, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(lbSetorpai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pagSetoresLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pagSetoresLayout.createSequentialGroup()
                                        .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(tfCodigoSetores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(85, 85, 85)
                                        .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbNomeSetor)
                                            .addComponent(tfNomeSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(149, 149, 149)
                                        .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbDescricoaSetores)
                                            .addComponent(tfDescricaoSetores)))
                                    .addGroup(pagSetoresLayout.createSequentialGroup()
                                        .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pagSetoresLayout.createSequentialGroup()
                                                .addComponent(btnNovo2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(103, 103, 103)
                                                .addComponent(btnSalvar2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lbSetorPai)
                                                .addComponent(cbSetorCargos1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pagSetoresLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnVoltar2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42))
                                            .addGroup(pagSetoresLayout.createSequentialGroup()
                                                .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pagSetoresLayout.createSequentialGroup()
                                                        .addGap(60, 60, 60)
                                                        .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(lbGerenteResponsavel)
                                                            .addComponent(cbNivelHierarquia1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(55, 55, 55)
                                                        .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(lbAtivoSetores)
                                                            .addComponent(chbCargoAtivo1)))
                                                    .addGroup(pagSetoresLayout.createSequentialGroup()
                                                        .addGap(113, 113, 113)
                                                        .addComponent(btnEditar2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(97, 97, 97)
                                                        .addComponent(btnExcluir2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(90, 90, 90)
                                                        .addComponent(btnLimpar2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 344, Short.MAX_VALUE)))))
                                .addGap(54, 54, 54)))
                        .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPesquisar2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSetorPai, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pagSetoresLayout.setVerticalGroup(
            pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagSetoresLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbNomeSetor)
                    .addComponent(lbDescricoaSetores))
                .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagSetoresLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNomeSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfCodigoSetores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pagSetoresLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfDescricaoSetores, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSetorPai)
                    .addComponent(lbGerenteResponsavel)
                    .addComponent(lbAtivoSetores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSetorCargos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNivelHierarquia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbCargoAtivo1))
                .addGap(133, 133, 133)
                .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95)
                .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbSetorPai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbSetorpai))
                    .addGroup(pagSetoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPesquisarFuncionario2)
                        .addComponent(txBarraPesquisaFuncionarios2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbStatusSetores22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbStatus2)))
                .addGap(18, 18, 18)
                .addComponent(lbAvisoBarraPesquisa2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        telaRH.addTab("Setores", pagSetores);

        pagCargos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Código");

        lbNomeCargo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbNomeCargo.setText("Nome do cargo ");

        lbDescricaoCargos.setText("Descrição");

        lbSetroCargos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSetroCargos.setText("Setor");

        lbNivelHierarquia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbNivelHierarquia.setText("Nível hierárquico");

        lbSalarioBase.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSalarioBase.setText("Salario base");

        lbCargaHoraiaSem.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbCargaHoraiaSem.setText("Carga hóraria semanal (horas)");

        lbAdicionalPericulocidade.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbAdicionalPericulocidade.setText("Adiciona de periculocidade (%)");

        lbAdicionalInsalubridade.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbAdicionalInsalubridade.setText("Adicional de insalubridade (%)");

        lbCargoAtivo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbCargoAtivo.setText("Cargo ativo");

        lbPesquisarFuncionario1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbPesquisarFuncionario1.setText("Pesquisa funcionário");

        lbStatus1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbStatus1.setText("Status");

        cbStatusPF1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        TbCadastroCargos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome do Cargo", "Setor", "Nivel", "Carga hóraria (h/sem)", "Salario base", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TbCadastroCargos);

        lbAvisoBarraPesquisa1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbAvisoBarraPesquisa1.setText("⚠️ Campos obrigatórios: Nome do cargo");

        tfCodigoCargos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        tfNomeCargo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        tfDescricao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbSetorCargos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSetorCargos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbNivelHierarquia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbNivelHierarquia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        chbCargoAtivo.setText("Ativo");

        btnNovo1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNovo1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\NOVO.png")); // NOI18N
        btnNovo1.setText("Novo");

        btnSalvar1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvar1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\SALVAR.png")); // NOI18N
        btnSalvar1.setText("Salvar");
        btnSalvar1.addActionListener(this::btnSalvar1ActionPerformed);

        btnEditar1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditar1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EDITAR.png")); // NOI18N
        btnEditar1.setText("Editar");

        btnExcluir1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluir1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EXCLUIR.png")); // NOI18N
        btnExcluir1.setText("Excluir");

        btnLimpar1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimpar1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimpar1.setText("Limpar");
        btnLimpar1.addActionListener(this::btnLimpar1ActionPerformed);

        btnPesquisar1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisar1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisar1.setText("Pesquisar");

        btnVoltar1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnVoltar1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\seta-esquerda.png")); // NOI18N
        btnVoltar1.setText("Voltar");

        javax.swing.GroupLayout pagCargosLayout = new javax.swing.GroupLayout(pagCargos);
        pagCargos.setLayout(pagCargosLayout);
        pagCargosLayout.setHorizontalGroup(
            pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagCargosLayout.createSequentialGroup()
                .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagCargosLayout.createSequentialGroup()
                        .addGap(768, 768, 768)
                        .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDescricaoCargos)
                            .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSalarioBase)
                            .addComponent(ftfSalarioBase, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pagCargosLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbAvisoBarraPesquisa1, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(282, Short.MAX_VALUE))
            .addGroup(pagCargosLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagCargosLayout.createSequentialGroup()
                        .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagCargosLayout.createSequentialGroup()
                                .addComponent(lbSetroCargos)
                                .addGap(310, 310, 310)
                                .addComponent(lbNivelHierarquia))
                            .addGroup(pagCargosLayout.createSequentialGroup()
                                .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(tfCodigoCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(138, 138, 138)
                                .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNomeCargo)
                                    .addComponent(tfNomeCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pagCargosLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pagCargosLayout.createSequentialGroup()
                                        .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbSetorCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ftfCargaHorariaSemanalh, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(154, 154, 154)
                                        .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbAdicionalPericulocidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbNivelHierarquia, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ftfAdicionalPericulocidade)))
                                    .addComponent(lbCargaHoraiaSem))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbAdicionalInsalubridade)
                            .addComponent(ftfAdicionalInsalubridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93)
                        .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chbCargoAtivo)
                            .addComponent(lbCargoAtivo))
                        .addGap(115, 115, 115))
                    .addGroup(pagCargosLayout.createSequentialGroup()
                        .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagCargosLayout.createSequentialGroup()
                                .addComponent(btnNovo1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(btnSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pagCargosLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(134, 134, 134)
                                .addComponent(btnExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114)
                                .addComponent(btnLimpar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(btnPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)))
                        .addComponent(btnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
            .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pagCargosLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lbPesquisarFuncionario1)
                    .addGap(6, 6, 6)
                    .addComponent(txBarraPesquisaFuncionarios1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(lbStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(cbStatusPF1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pagCargosLayout.setVerticalGroup(
            pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagCargosLayout.createSequentialGroup()
                .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagCargosLayout.createSequentialGroup()
                        .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagCargosLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagCargosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbDescricaoCargos)))
                        .addGap(4, 4, 4)
                        .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pagCargosLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lbNomeCargo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfCodigoCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNomeCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbSetroCargos, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbNivelHierarquia))
                    .addComponent(lbSalarioBase, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagCargosLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(lbCargoAtivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chbCargoAtivo))
                    .addGroup(pagCargosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagCargosLayout.createSequentialGroup()
                                .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbNivelHierarquia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ftfSalarioBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbSetorCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbCargaHoraiaSem)
                                    .addComponent(lbAdicionalPericulocidade)))
                            .addComponent(lbAdicionalInsalubridade, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagCargosLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(ftfAdicionalInsalubridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pagCargosLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(ftfCargaHorariaSemanalh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pagCargosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ftfAdicionalPericulocidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(111, 111, 111)
                .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 446, Short.MAX_VALUE)
                .addComponent(lbAvisoBarraPesquisa1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
            .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pagCargosLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbPesquisarFuncionario1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pagCargosLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(pagCargosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txBarraPesquisaFuncionarios1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbStatusPF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        telaRH.addTab("Cargos", pagCargos);

        pagTurnos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbCodigoTurno.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbCodigoTurno.setText("Código");

        tfCodigoEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbNomeTurno.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbNomeTurno.setText("Nome do turno");

        tfNomeEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbDescricoaEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDescricoaEscala.setText("Descrição");

        tfDescricaoEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Horário do turno");

        lbHoraInicio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbHoraInicio.setText("Hora do Início");

        lbHoraFim.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbHoraFim.setText("Hora fim ");

        lbIntervaloInicio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbIntervaloInicio.setText("Intervalo Início");

        lbIntervaloFim.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbIntervaloFim.setText("Intervalo fim");

        lbDuracaoTurno.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDuracaoTurno.setText("Duração do turno");

        lbTurnoNoturno.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTurnoNoturno.setText("Turno noturno?");

        lbDiaSemana.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDiaSemana.setText("Dia da semana");

        ftfHoraInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        ftfHoraInicio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        ftfHoraFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        ftfHoraFim.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        ftfIntervaloInicio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        ftfIntervaloFim.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbTurno.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbTurno.setText("Sim");
        cbTurno.addActionListener(this::cbTurnoActionPerformed);

        ftfDuracaoTurno.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbAtivoTurnos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbAtivoTurnos.setText("Ativo");

        cbSegunda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSegunda.setText("Segunda- feira");
        cbSegunda.addActionListener(this::cbSegundaActionPerformed);

        cbQuarta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbQuarta.setText("Quarta-feira");

        cbTerca.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbTerca.setText("Terça-feira");

        cbDomingo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbDomingo.setText("Domingo");

        cbQuinta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbQuinta.setText("Quinta-feria");

        cbSabado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSabado.setText("Sábado");

        jCheckBox8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jCheckBox8.setText("Ativo");

        btnNovo3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNovo3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\NOVO.png")); // NOI18N
        btnNovo3.setText("Novo");

        btnSalvar3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvar3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\SALVAR.png")); // NOI18N
        btnSalvar3.setText("Salvar");
        btnSalvar3.addActionListener(this::btnSalvar3ActionPerformed);

        btnEditar3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditar3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EDITAR.png")); // NOI18N
        btnEditar3.setText("Editar");

        btnExcluir3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluir3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EXCLUIR.png")); // NOI18N
        btnExcluir3.setText("Excluir");

        btnLimpar3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimpar3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimpar3.setText("Limpar");
        btnLimpar3.addActionListener(this::btnLimpar3ActionPerformed);

        btnPesquisar3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisar3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisar3.setText("Pesquisar");

        btnVoltar3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnVoltar3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\VOLTAR.png")); // NOI18N
        btnVoltar3.setText("Voltar");
        btnVoltar3.addActionListener(this::btnVoltar3ActionPerformed);

        lbTurnosCadastrados.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTurnosCadastrados.setText("Turnos cadastrados");

        tbTurnosCadastrados.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbTurnosCadastrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome do Turno", "Horario", "Intervalo", "Duração", "Dias da semana", "Descrição"
            }
        ));
        jScrollPane4.setViewportView(tbTurnosCadastrados);

        lbFuncionarioTurnoSelecionado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbFuncionarioTurnoSelecionado.setText("Funcionários do turno selecionado ");

        lbSelecioneTurno.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSelecioneTurno.setText("Selecione o turno");

        cbSelecioneTurno.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSelecioneTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAdicFuncionario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAdicFuncionario.setText("Adicionar funcionário");

        btnAtualizarFuncionario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAtualizarFuncionario.setText("Atualizar");
        btnAtualizarFuncionario.addActionListener(this::btnAtualizarFuncionarioActionPerformed);

        btnExcluFuncionario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluFuncionario.setText("Excluir funcionário");
        btnExcluFuncionario.addActionListener(this::btnExcluFuncionarioActionPerformed);

        tbFuncionariosTurnos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbFuncionariosTurnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Nome do Turno", "Horario", "Intervalo"
            }
        ));
        jScrollPane5.setViewportView(tbFuncionariosTurnos);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Pesquisar turno");

        txPesquisarTurno.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText("Status");

        cbStatusSetores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbStatusSetores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Dia da semana");

        jComboBox4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pagTurnosLayout = new javax.swing.GroupLayout(pagTurnos);
        pagTurnos.setLayout(pagTurnosLayout);
        pagTurnosLayout.setHorizontalGroup(
            pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagTurnosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagTurnosLayout.createSequentialGroup()
                        .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagTurnosLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox8)
                                    .addComponent(lbDiaSemana)
                                    .addComponent(lbAtivoTurnos)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pagTurnosLayout.createSequentialGroup()
                                            .addGap(9, 9, 9)
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txPesquisarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(30, 30, 30)
                                            .addComponent(jLabel18)
                                            .addGap(18, 18, 18)
                                            .addComponent(cbStatusSetores, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel19)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pagTurnosLayout.createSequentialGroup()
                                            .addGap(676, 676, 676)
                                            .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lbFuncionarioTurnoSelecionado)
                                                .addGroup(pagTurnosLayout.createSequentialGroup()
                                                    .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lbSelecioneTurno)
                                                        .addComponent(cbSelecioneTurno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(btnAdicFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(27, 27, 27)
                                                    .addComponent(btnExcluFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(29, 29, 29)
                                                    .addComponent(btnAtualizarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(pagTurnosLayout.createSequentialGroup()
                                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCodigoTurno)
                                    .addComponent(tfCodigoEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(85, 85, 85)
                                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNomeTurno)
                                    .addComponent(tfNomeEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(149, 149, 149)
                                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDescricoaEscala)
                                    .addComponent(tfDescricaoEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pagTurnosLayout.createSequentialGroup()
                        .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pagTurnosLayout.createSequentialGroup()
                                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbHoraInicio)
                                    .addComponent(ftfHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbHoraFim)
                                    .addComponent(ftfHoraFim, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbIntervaloInicio)
                                    .addComponent(ftfIntervaloInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbIntervaloFim)
                                    .addComponent(ftfIntervaloFim, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDuracaoTurno)
                                    .addComponent(ftfDuracaoTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbTurnoNoturno)
                                    .addComponent(cbTurno))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pagTurnosLayout.createSequentialGroup()
                                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pagTurnosLayout.createSequentialGroup()
                                            .addComponent(cbSegunda)
                                            .addGap(36, 36, 36)
                                            .addComponent(cbTerca)
                                            .addGap(34, 34, 34)
                                            .addComponent(cbQuarta)
                                            .addGap(41, 41, 41)
                                            .addComponent(cbQuinta)
                                            .addGap(45, 45, 45)
                                            .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pagTurnosLayout.createSequentialGroup()
                                                    .addComponent(btnExcluir3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(95, 95, 95)
                                                    .addComponent(btnLimpar3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(pagTurnosLayout.createSequentialGroup()
                                                    .addGap(13, 13, 13)
                                                    .addComponent(cbSabado)
                                                    .addGap(47, 47, 47)
                                                    .addComponent(cbDomingo))))
                                        .addComponent(lbTurnosCadastrados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pagTurnosLayout.createSequentialGroup()
                                        .addComponent(btnNovo3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(97, 97, 97)
                                        .addComponent(btnSalvar3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(82, 82, 82)
                                        .addComponent(btnEditar3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPesquisar3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(122, 122, 122)))
                        .addComponent(btnVoltar3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))))
        );
        pagTurnosLayout.setVerticalGroup(
            pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagTurnosLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigoTurno)
                    .addComponent(lbNomeTurno)
                    .addComponent(lbDescricoaEscala))
                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagTurnosLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNomeEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfCodigoEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pagTurnosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfDescricaoEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pagTurnosLayout.createSequentialGroup()
                        .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbHoraInicio)
                            .addComponent(lbHoraFim)
                            .addComponent(lbIntervaloInicio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfHoraFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfIntervaloInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pagTurnosLayout.createSequentialGroup()
                        .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbIntervaloFim)
                            .addComponent(lbDuracaoTurno)
                            .addComponent(lbTurnoNoturno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfIntervaloFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTurno)
                            .addComponent(ftfDuracaoTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36)
                .addComponent(lbDiaSemana)
                .addGap(18, 18, 18)
                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSegunda)
                    .addComponent(cbQuarta)
                    .addComponent(cbTerca)
                    .addComponent(cbQuinta)
                    .addComponent(cbDomingo)
                    .addComponent(cbSabado))
                .addGap(45, 45, 45)
                .addComponent(lbAtivoTurnos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox8)
                .addGap(18, 18, 18)
                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalvar3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNovo3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesquisar3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVoltar3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTurnosCadastrados)
                    .addComponent(lbFuncionarioTurnoSelecionado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pagTurnosLayout.createSequentialGroup()
                        .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAdicFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnExcluFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAtualizarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pagTurnosLayout.createSequentialGroup()
                                .addComponent(lbSelecioneTurno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSelecioneTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                .addGroup(pagTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txPesquisarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cbStatusSetores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        telaRH.addTab("Turnos", pagTurnos);

        pagEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbEscalaSemanal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbEscalaSemanal.setText("Turnos cadastrados");

        tbEscalaSemanal1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbEscalaSemanal1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "funcionarios", "Setor", "Seg", "ter", "Qua", "Qui", "Sex", "Sab", "Dom"
            }
        ));
        jScrollPane6.setViewportView(tbEscalaSemanal1);

        lbDetalhesEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDetalhesEscala.setText("Detalhes da escala selecionada");

        tbFuncionariosTurnos1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbFuncionariosTurnos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Período", "Setor", "Turno", "Funcionário", "Status", "Criador por", "Criado em", "Publicado em"
            }
        ));
        jScrollPane7.setViewportView(tbFuncionariosTurnos1);

        btnCopiarEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCopiarEscala.setText("Copiar escala");

        btnEditarEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditarEscala.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EDITAR.png")); // NOI18N
        btnEditarEscala.setText("Editar escala");

        btnExcluirEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluirEscala.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EXCLUIR.png")); // NOI18N
        btnExcluirEscala.setText("Excluir escala");

        btnAdicionarEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAdicionarEscala.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\NOVO.png")); // NOI18N
        btnAdicionarEscala.setText("Adicionar escala");
        btnAdicionarEscala.addActionListener(this::btnAdicionarEscalaActionPerformed);

        btnPuplicarEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPuplicarEscala.setText("Publicar escala");

        tbFuncionariosTurnos2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbFuncionariosTurnos2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Turno", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab", "Dom", "Total"
            }
        ));
        jScrollPane8.setViewportView(tbFuncionariosTurnos2);

        btnLimparEscala1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimparEscala1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimparEscala1.setText("Limpar");
        btnLimparEscala1.addActionListener(this::btnLimparEscala1ActionPerformed);

        btnPesquisarEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisarEscala.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisarEscala.setText("Pesquisar");
        btnPesquisarEscala.addActionListener(this::btnPesquisarEscalaActionPerformed);

        ftfPeriodoInicio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        ftfPeriodoFim.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbSetorEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSetorEscala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbTurnoEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbTurnoEscala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbFuncionarioEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbFuncionarioEscala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbFuncionarioEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbFuncionarioEscala.setText("Funcionario");

        lbTurnoEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTurnoEscala.setText("Turno");

        lbSetor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSetor.setText("Setor");

        lbPeriodoEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbPeriodoEscala.setText("Período");

        lbSinalSeta.setText("->");

        lbResumoEscala.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbResumoEscala.setText("Resumo da escala");

        btnSalvarEscala.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\SALVAR.png")); // NOI18N
        btnSalvarEscala.setText("Salvar");
        btnSalvarEscala.addActionListener(this::btnSalvarEscalaActionPerformed);

        javax.swing.GroupLayout pagEscalaLayout = new javax.swing.GroupLayout(pagEscala);
        pagEscala.setLayout(pagEscalaLayout);
        pagEscalaLayout.setHorizontalGroup(
            pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagEscalaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(pagEscalaLayout.createSequentialGroup()
                        .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagEscalaLayout.createSequentialGroup()
                                .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPeriodoEscala)
                                    .addGroup(pagEscalaLayout.createSequentialGroup()
                                        .addComponent(ftfPeriodoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbSinalSeta, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ftfPeriodoFim, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbSetorEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbSetor))
                                .addGap(54, 54, 54)
                                .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbTurnoEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbTurnoEscala))
                                .addGap(64, 64, 64)
                                .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbFuncionarioEscala)
                                    .addGroup(pagEscalaLayout.createSequentialGroup()
                                        .addComponent(cbFuncionarioEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(283, 283, 283)
                                        .addComponent(btnPesquisarEscala)
                                        .addGap(74, 74, 74)
                                        .addComponent(btnLimparEscala1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(lbEscalaSemanal, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pagEscalaLayout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagEscalaLayout.createSequentialGroup()
                .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pagEscalaLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnSalvarEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(btnAdicionarEscala)
                        .addGap(112, 112, 112)
                        .addComponent(btnEditarEscala)
                        .addGap(120, 120, 120)
                        .addComponent(btnExcluirEscala)
                        .addGap(86, 86, 86)
                        .addComponent(btnCopiarEscala)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pagEscalaLayout.createSequentialGroup()
                        .addComponent(lbDetalhesEscala)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbResumoEscala)))
                .addGap(63, 63, 63)
                .addComponent(btnPuplicarEscala)
                .addGap(40, 40, 40))
        );
        pagEscalaLayout.setVerticalGroup(
            pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagEscalaLayout.createSequentialGroup()
                .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagEscalaLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimparEscala1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisarEscala))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagEscalaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFuncionarioEscala)
                            .addComponent(lbTurnoEscala)
                            .addComponent(lbSetor)
                            .addComponent(lbPeriodoEscala))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfPeriodoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfPeriodoFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSetorEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTurnoEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFuncionarioEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSinalSeta))
                        .addGap(54, 54, 54)))
                .addComponent(lbEscalaSemanal)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionarEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCopiarEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPuplicarEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvarEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDetalhesEscala)
                    .addComponent(lbResumoEscala))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pagEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(167, Short.MAX_VALUE))
        );

        telaRH.addTab("Escala", pagEscala);

        pagInfraçoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbPeriodoInfraçoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbPeriodoInfraçoes.setText("Período");

        lbSetorInfraçoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSetorInfraçoes.setText("Setor");

        cbSetorInfracoa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSetorInfracoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbTurnoEscalaInfraçoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTurnoEscalaInfraçoes.setText("Turno");

        cbTurnoInfracao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbTurnoInfracao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbFuncionarioEscalaInfraçoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbFuncionarioEscalaInfraçoes.setText("Funcionario");

        cbFuncionarioInfracao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbFuncionarioInfracao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbFuncionarioInfracao.addActionListener(this::cbFuncionarioInfracaoActionPerformed);

        btnPesquisarInfracoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisarInfracoes.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisarInfracoes.setText("Pesquisar");
        btnPesquisarInfracoes.addActionListener(this::btnPesquisarInfracoesActionPerformed);

        btnLimparInfracoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimparInfracoes.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimparInfracoes.setText("Limpar");
        btnLimparInfracoes.addActionListener(this::btnLimparInfracoesActionPerformed);

        tbOcorrencias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbOcorrencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Data / Período", "Funcionário", "Matrícula", "Setor", "Tipo", "Duração", "Justificativa / Motivo", "Situação"
            }
        ));
        jScrollPane12.setViewportView(tbOcorrencias);

        lbResumoOcorrencias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbResumoOcorrencias.setText("Resumo de ocorrências (muda comforme o tipo)");

        tbResumoOcorrencias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbResumoOcorrencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Total de ocorrências", "Pendencias de aprovação", "Justificadas", "Não justificadas", "Impactos em horas", "Em andamento"
            }
        ));
        jScrollPane13.setViewportView(tbResumoOcorrencias);

        ldAçoes1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ldAçoes1.setText("Ações");

        btnRegistrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\+.png")); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        btnJustificar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnJustificar.setText("Justificar");

        btnAprovar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAprovar.setText("Aprovar");

        btnEditarInfarçoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditarInfarçoes.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EDITAR.png")); // NOI18N
        btnEditarInfarçoes.setText("Editar");

        btnExcluirInfraçoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluirInfraçoes.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EXCLUIR.png")); // NOI18N
        btnExcluirInfraçoes.setText("Excluir");
        btnExcluirInfraçoes.addActionListener(this::btnExcluirInfraçoesActionPerformed);

        btnEncerrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEncerrar.setText("Encerrar");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Ocorrências ");

        tbDetalheOcorrencia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbDetalheOcorrencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Funcionario", "Setor", "Cargo", "Data", "Situação", "Registrado por"
            }
        ));
        jScrollPane14.setViewportView(tbDetalheOcorrencia);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Detalhe da ocorrêmcia selecionada");

        btnReprovar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnReprovar.setText("Reprovar");

        btnRegistrarRetorno.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegistrarRetorno.setText("Registrar Retorno");

        lbInicio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbInicio.setText("Inicio");

        lbFimPeriodo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbFimPeriodo.setText("Fim");

        javax.swing.GroupLayout pagInfracoesLayout = new javax.swing.GroupLayout(pagInfracoes);
        pagInfracoes.setLayout(pagInfracoesLayout);
        pagInfracoesLayout.setHorizontalGroup(
            pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagInfracoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagInfracoesLayout.createSequentialGroup()
                        .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pagInfracoesLayout.createSequentialGroup()
                                .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPeriodoInfraçoes)
                                    .addComponent(ftfInicioPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbInicio))
                                .addGap(23, 23, 23)
                                .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbFimPeriodo)
                                    .addComponent(ftfFimPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pagInfracoesLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnPesquisarInfracoes)
                                        .addGap(28, 28, 28)
                                        .addComponent(btnLimparInfracoes))
                                    .addGroup(pagInfracoesLayout.createSequentialGroup()
                                        .addGap(86, 86, 86)
                                        .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbSetorInfracoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbSetorInfraçoes))
                                        .addGap(54, 54, 54)
                                        .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbTurnoInfracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbTurnoEscalaInfraçoes))
                                        .addGap(64, 64, 64)
                                        .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbFuncionarioEscalaInfraçoes)
                                            .addComponent(cbFuncionarioInfracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jScrollPane13)
                            .addGroup(pagInfracoesLayout.createSequentialGroup()
                                .addComponent(lbResumoOcorrencias)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))
                    .addGroup(pagInfracoesLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagInfracoesLayout.createSequentialGroup()
                        .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagInfracoesLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE))
                        .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagInfracoesLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnAprovar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnJustificar, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                    .addComponent(btnReprovar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(81, 81, 81)
                                .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnEditarInfarçoes, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(btnEncerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(btnRegistrarRetorno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(btnExcluirInfraçoes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagInfracoesLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ldAçoes1)
                                .addGap(372, 372, 372))))))
        );
        pagInfracoesLayout.setVerticalGroup(
            pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagInfracoesLayout.createSequentialGroup()
                .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagInfracoesLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimparInfracoes)
                            .addComponent(btnPesquisarInfracoes)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagInfracoesLayout.createSequentialGroup()
                        .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pagInfracoesLayout.createSequentialGroup()
                                .addComponent(lbPeriodoInfraçoes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbInicio)
                                    .addComponent(lbFimPeriodo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ftfInicioPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ftfFimPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pagInfracoesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbFuncionarioEscalaInfraçoes)
                                    .addComponent(lbTurnoEscalaInfraçoes)
                                    .addComponent(lbSetorInfraçoes))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbSetorInfracoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTurnoInfracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbFuncionarioInfracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbResumoOcorrencias)
                        .addGap(10, 10, 10)))
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pagInfracoesLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pagInfracoesLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(ldAçoes1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarInfarçoes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnJustificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcluirInfraçoes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAprovar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pagInfracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnReprovar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(btnRegistrarRetorno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pagInfraçoesLayout = new javax.swing.GroupLayout(pagInfraçoes);
        pagInfraçoes.setLayout(pagInfraçoesLayout);
        pagInfraçoesLayout.setHorizontalGroup(
            pagInfraçoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagInfraçoesLayout.createSequentialGroup()
                .addComponent(pagInfracoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 121, Short.MAX_VALUE))
        );
        pagInfraçoesLayout.setVerticalGroup(
            pagInfraçoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagInfraçoesLayout.createSequentialGroup()
                .addComponent(pagInfracoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 108, Short.MAX_VALUE))
        );

        telaRH.addTab("Infrações / Afastamentos", pagInfraçoes);

        pagPonto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbPeriodoPonto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbPeriodoPonto.setText("Data");

        ftfData.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        ftfPeriodoFim1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbSetorPonto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSetorPonto.setText("Setor");

        cbSetorEscala1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSetorEscala1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbTurnoEscalaPonto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTurnoEscalaPonto.setText("Turno");

        cbTurnoPonto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbTurnoPonto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbFuncionarioEscalaPonto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbFuncionarioEscalaPonto.setText("Funcionario");

        cbFuncionarioEscala1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbFuncionarioEscala1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnPesquisarEscala1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisarEscala1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisarEscala1.setText("Pesquisar");
        btnPesquisarEscala1.addActionListener(this::btnPesquisarEscala1ActionPerformed);

        btnLimparEscala2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimparEscala2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimparEscala2.setText("Limpar");
        btnLimparEscala2.addActionListener(this::btnLimparEscala2ActionPerformed);

        tbEscalaSemanal2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbEscalaSemanal2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "funcionarios", "Setor", "Seg", "ter", "Qua", "Qui", "Sex", "Sab", "Dom", "Title 10", "Title 11", "Title 12", "Title 13"
            }
        ));
        jScrollPane9.setViewportView(tbEscalaSemanal2);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Resumo de marcações");

        jTable1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Dias trabalhados ", "Horas trabalhadas", "Horas extras", "Faltas", "Atrasos", "Saldos Banco de Horas"
            }
        ));
        jScrollPane10.setViewportView(jTable1);

        ldAçoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ldAçoes.setText("Ações");

        btnSalvarPonto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarPonto.setText("Salvar");
        btnSalvarPonto.addActionListener(this::btnSalvarPontoActionPerformed);

        jButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton2.setText("Justificar atraso/falta");

        jButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EXCLUIR.png")); // NOI18N
        jButton3.setText("Excluir marcação");

        jButton4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton4.setText("Ajustar marcação");

        jButton5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton5.setText("Abonar falta");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jButton6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton6.setText("Exportar relatório");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Controle de ponto");

        jTable2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Funcionario", "Setor", "Cargo", "Periodo solicitado", "Dias úteis", "Periodo aquisitado", "Data da solicitação", "Situação atual", "Descriação", "Aprovado por", "Data da decisão", "Title 12"
            }
        ));
        jScrollPane11.setViewportView(jTable2);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Detalhe das ferias selecionadas");

        lbHorario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbHorario.setText("Horario");

        lbJustificativa.setText("Justificativa");

        txaJustificativaPonto.setColumns(20);
        txaJustificativaPonto.setRows(5);
        scJustificativa.setViewportView(txaJustificativaPonto);

        btnAdicionarArquivo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAdicionarArquivo.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\document.png")); // NOI18N
        btnAdicionarArquivo.setText("Arquivo");
        btnAdicionarArquivo.addActionListener(this::btnAdicionarArquivoActionPerformed);

        javax.swing.GroupLayout pagPontoLayout = new javax.swing.GroupLayout(pagPonto);
        pagPonto.setLayout(pagPontoLayout);
        pagPontoLayout.setHorizontalGroup(
            pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagPontoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagPontoLayout.createSequentialGroup()
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPeriodoPonto)
                            .addComponent(ftfData, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ftfPeriodoFim1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbHorario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSetorEscala1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSetorPonto))
                        .addGap(54, 54, 54)
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTurnoPonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTurnoEscalaPonto))
                        .addGap(64, 64, 64)
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFuncionarioEscalaPonto)
                            .addComponent(cbFuncionarioEscala1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagPontoLayout.createSequentialGroup()
                                .addComponent(lbJustificativa)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pagPontoLayout.createSequentialGroup()
                                .addComponent(scJustificativa, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPesquisarEscala1)
                                .addGap(28, 28, 28)
                                .addComponent(btnLimparEscala2)))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagPontoLayout.createSequentialGroup()
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane10)
                            .addGroup(pagPontoLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pagPontoLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1244, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagPontoLayout.createSequentialGroup()
                                .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pagPontoLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane11))
                                .addGap(18, 18, 18)
                                .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ldAçoes)
                                    .addGroup(pagPontoLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnAdicionarArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnSalvarPonto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                        .addGap(81, 81, 81)
                                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(8, 8, 8)))
                        .addContainerGap())))
        );
        pagPontoLayout.setVerticalGroup(
            pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagPontoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagPontoLayout.createSequentialGroup()
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFuncionarioEscalaPonto)
                            .addComponent(lbTurnoEscalaPonto)
                            .addComponent(lbSetorPonto)
                            .addComponent(lbPeriodoPonto)
                            .addComponent(lbHorario)
                            .addComponent(lbJustificativa))
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagPontoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ftfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ftfPeriodoFim1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbSetorEscala1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTurnoPonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbFuncionarioEscala1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pagPontoLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(scJustificativa, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(144, 144, 144))
                    .addGroup(pagPontoLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimparEscala2)
                            .addComponent(btnPesquisarEscala1))
                        .addGap(134, 134, 134)))
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagPontoLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pagPontoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(ldAçoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalvarPonto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(pagPontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnAdicionarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        telaRH.addTab("Ponto", pagPonto);

        pagBancodeDados.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbPeriodoBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbPeriodoBD.setText("Período");

        ftfPeriodoInicioBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        ftfPeriodoFimBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbBDFerias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbBDFerias.setText("Setor");

        cbBDferias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbBDferias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbSituaçaoSaldo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSituaçaoSaldo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbSituaçaoSaldoBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSituaçaoSaldoBD.setText("Situação do saldo");

        lbFuncionarioBDFerias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbFuncionarioBDFerias.setText("Funcionário");

        cbFuncionarioBDFerias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbFuncionarioBDFerias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbResumoOcorrencias1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbResumoOcorrencias1.setText("Resumo de ocorrências (muda comforme o tipo)");

        tbResumoBancoDados.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbResumoBancoDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Saldo total do periodo", "Saldo negativo", "Ajustes pendentes", "Ferias solicitadas", "Férias em analise", "Férias aprovadas"
            }
        ));
        jScrollPane15.setViewportView(tbResumoBancoDados);

        btnPesquisarBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisarBD.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisarBD.setText("Pesquisar");
        btnPesquisarBD.addActionListener(this::btnPesquisarBDActionPerformed);

        btnLimparBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimparBD.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimparBD.setText("Limpar");
        btnLimparBD.addActionListener(this::btnLimparBDActionPerformed);

        lbBancoDadosFerias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbBancoDadosFerias.setText("Banco de Dados");

        tbBancodadosFerias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbBancodadosFerias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Funcionário", "Matrícula", "Setor", "Salado atual", "Situação do saldo", "Férias solicitadas", "Dias", "Situação férias", "Último ajuste"
            }
        ));
        jScrollPane16.setViewportView(tbBancodadosFerias);

        lbRegistrosFerias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbRegistrosFerias.setText("Informações Ferias");

        tbInformaçoesFerias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbInformaçoesFerias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Funcionario", "Setor", "Cargo", "Jornada", "Saldo anterior", "Crédito / Débito", "Saldo atual", "Período solicitado", "Dias úteis ", "Período aquisitivo", "Situação atual", "Aprovado por ", "Data da decisão"
            }
        ));
        jScrollPane17.setViewportView(tbInformaçoesFerias);

        btnRegistrarBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegistrarBD.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\SALVAR.png")); // NOI18N
        btnRegistrarBD.setText("Registrar");
        btnRegistrarBD.addActionListener(this::btnRegistrarBDActionPerformed);

        ldAçoesBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ldAçoesBD.setText("Ações");

        btnJHistoricoFeriasBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnJHistoricoFeriasBD.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\history 24.png")); // NOI18N
        btnJHistoricoFeriasBD.setText("Historico de férias");

        btnAprovarBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAprovarBD.setText("Aprovar");

        btnReprovarBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnReprovarBD.setText("Reprovar");

        btnEditarBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditarBD.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EDITAR.png")); // NOI18N
        btnEditarBD.setText("Editar");

        btnExcluirBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluirBD.setText("Excluir");
        btnExcluirBD.addActionListener(this::btnExcluirBDActionPerformed);

        btnHistoricoAjustesBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnHistoricoAjustesBD.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\history 24.png")); // NOI18N
        btnHistoricoAjustesBD.setText("Historico de ajustes");

        btnRegistrarRetornoBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRegistrarRetornoBD.setText("Registrar Retorno");

        lbSituaçaoFeriasBD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSituaçaoFeriasBD.setText("Situação das férias");

        cbSituaçaoFerias.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSituaçaoFerias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pagBancodeDadosLayout = new javax.swing.GroupLayout(pagBancodeDados);
        pagBancodeDados.setLayout(pagBancodeDadosLayout);
        pagBancodeDadosLayout.setHorizontalGroup(
            pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                        .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                                .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPeriodoBD)
                                    .addComponent(ftfPeriodoInicioBD, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addComponent(ftfPeriodoFimBD, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbBDferias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbBDFerias))
                                .addGap(44, 44, 44)
                                .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbFuncionarioBDFerias)
                                    .addComponent(cbFuncionarioBDFerias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(177, 177, 177)
                                .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbSituaçaoSaldoBD)
                                    .addComponent(cbSituaçaoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                                        .addComponent(lbSituaçaoFeriasBD)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                                        .addComponent(cbSituaçaoFerias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnPesquisarBD)
                                        .addGap(28, 28, 28)
                                        .addComponent(btnLimparBD))))
                            .addComponent(jScrollPane15)
                            .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                                .addComponent(lbResumoOcorrencias1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))
                    .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                        .addComponent(lbBancoDadosFerias)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagBancodeDadosLayout.createSequentialGroup()
                        .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                                .addComponent(lbRegistrosFerias)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ldAçoesBD)
                            .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAprovarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRegistrarRetornoBD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnJHistoricoFeriasBD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRegistrarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(81, 81, 81)
                                .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnReprovarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnExcluirBD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnHistoricoAjustesBD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEditarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(14, 14, 14))))
        );
        pagBancodeDadosLayout.setVerticalGroup(
            pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimparBD)
                            .addComponent(btnPesquisarBD)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagBancodeDadosLayout.createSequentialGroup()
                        .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFuncionarioBDFerias)
                            .addComponent(lbSituaçaoSaldoBD)
                            .addComponent(lbBDFerias)
                            .addComponent(lbPeriodoBD)
                            .addComponent(lbSituaçaoFeriasBD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfPeriodoInicioBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfPeriodoFimBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbBDferias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSituaçaoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFuncionarioBDFerias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSituaçaoFerias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(lbResumoOcorrencias1)
                        .addGap(10, 10, 10)))
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBancoDadosFerias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lbRegistrosFerias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pagBancodeDadosLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(ldAçoesBD)
                        .addGap(8, 8, 8)
                        .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegistrarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAprovarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReprovarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnJHistoricoFeriasBD, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHistoricoAjustesBD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(pagBancodeDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExcluirBD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegistrarRetornoBD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        telaRH.addTab("Banco de Dados/Ferias", pagBancodeDados);

        pagMetas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbPeriodoHistorico1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbPeriodoHistorico1.setText("Período");

        ftfPeriodoInicioHistorico1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbSetorMetas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSetorMetas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbSetorMetas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSetorMetas.setText("Setor");

        lbFuncionarioMetas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbFuncionarioMetas.setText("Funcionário");

        cbFuncionarioMetas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbFuncionarioMetas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbSituaçaoSaldoMetas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSituaçaoSaldoMetas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbSituaçaoSaldoMetas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSituaçaoSaldoMetas.setText("Situação do saldo");

        cbSituaçaoFeriasMetas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSituaçaoFeriasMetas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbSituaçaoFeriasMetas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSituaçaoFeriasMetas.setText("Situação das férias");

        tbHistoricoDesenpenho1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbHistoricoDesenpenho1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Tipo", "Meta", "Responsável / equipe", "Setor", "Periodo", "Meta prevista", "Resultado atual", "Atingido", "Situação", "Progresso"
            }
        ));
        jScrollPane20.setViewportView(tbHistoricoDesenpenho1);

        lbHIstoricoDesenpenho1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbHIstoricoDesenpenho1.setText("Historico de desenpenho dos funcionarios");

        lbDetalhesFuncionario1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDetalhesFuncionario1.setText("Detalhes do colaborador selecionado");

        tbDetalhesFuncionario1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbDetalhesFuncionario1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Matricula", "Funcionário", "Setor", "Cargo", "Data de admissão", "Data de desligamento", "Situação vinculo", "Jornada"
            }
        ));
        jScrollPane21.setViewportView(tbDetalhesFuncionario1);

        PnDesempenhoMensal1.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout PnDesempenhoMensal1Layout = new javax.swing.GroupLayout(PnDesempenhoMensal1);
        PnDesempenhoMensal1.setLayout(PnDesempenhoMensal1Layout);
        PnDesempenhoMensal1Layout.setHorizontalGroup(
            PnDesempenhoMensal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        PnDesempenhoMensal1Layout.setVerticalGroup(
            PnDesempenhoMensal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 154, Short.MAX_VALUE)
        );

        btnPesquisarMetas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisarMetas.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnPesquisarMetas.setText("Pesquisar");
        btnPesquisarMetas.addActionListener(this::btnPesquisarMetasActionPerformed);

        btnLimparMetas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimparMetas.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\history 24.png")); // NOI18N
        btnLimparMetas.setText("Limpar");
        btnLimparMetas.addActionListener(this::btnLimparMetasActionPerformed);

        btnImprimirHistorico1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnImprimirHistorico1.setText("Imprimir histórico");

        btnExporHistorico1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExporHistorico1.setText("Exportar histórico");

        lbAçao1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbAçao1.setText("Ações");

        btnVisualizaçaoHistorico1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnVisualizaçaoHistorico1.setText("Visualizar hitórico completo");

        btnCompararColaboradores1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCompararColaboradores1.setText("comparar colaboradores");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Acompanhamento da meta");

        jTable3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane22.setViewportView(jTable3);

        lbInicioPeriodo.setText("Inicio");

        lbPeriodoFim.setText("Fim");

        ftfPeriodoFimMetas.setText(" ");

        lbTipoMeta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTipoMeta.setText("Tipo de Meta");

        cbTipoMeta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Metas Previstas");

        txMetasPrevistas.addActionListener(this::txMetasPrevistasActionPerformed);

        btnSalvarMetas.setText("jButton1");

        javax.swing.GroupLayout pagMetasLayout = new javax.swing.GroupLayout(pagMetas);
        pagMetas.setLayout(pagMetasLayout);
        pagMetasLayout.setHorizontalGroup(
            pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagMetasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagMetasLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDetalhesFuncionario1)
                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PnDesempenhoMensal1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagMetasLayout.createSequentialGroup()
                                .addComponent(lbTrabalhoEquipe1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(lbProdutividade1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(lbAtitude1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagMetasLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(279, 279, 279)))
                        .addGap(18, 18, 18)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbAçao1)
                            .addComponent(btnCompararColaboradores1)
                            .addComponent(btnVisualizaçaoHistorico1)
                            .addComponent(btnExporHistorico1)
                            .addComponent(btnImprimirHistorico1))
                        .addGap(42, 42, 42))
                    .addGroup(pagMetasLayout.createSequentialGroup()
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagMetasLayout.createSequentialGroup()
                                .addGap(225, 225, 225)
                                .addComponent(lbSetorMetas))
                            .addGroup(pagMetasLayout.createSequentialGroup()
                                .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbInicioPeriodo)
                                    .addComponent(ftfPeriodoInicioHistorico1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPeriodoFim)
                                    .addComponent(ftfPeriodoFimMetas, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSetorMetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbFuncionarioMetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbFuncionarioMetas))
                        .addGap(48, 48, 48)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTipoMeta)
                            .addComponent(cbTipoMeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSituaçaoSaldoMetas)
                            .addComponent(cbSituaçaoSaldoMetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSituaçaoFeriasMetas)
                            .addComponent(cbSituaçaoFeriasMetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(pagMetasLayout.createSequentialGroup()
                                .addComponent(txMetasPrevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimparMetas)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalvarMetas, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60))))
                    .addGroup(pagMetasLayout.createSequentialGroup()
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPeriodoHistorico1)
                            .addComponent(lbHIstoricoDesenpenho1)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 1341, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagMetasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPesquisarMetas)
                .addGap(171, 171, 171))
        );
        pagMetasLayout.setVerticalGroup(
            pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagMetasLayout.createSequentialGroup()
                .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pagMetasLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSituaçaoSaldoMetas)
                            .addComponent(lbSituaçaoFeriasMetas)
                            .addComponent(lbSetorMetas)
                            .addComponent(lbFuncionarioMetas)
                            .addComponent(lbTipoMeta)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSituaçaoFeriasMetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSituaçaoSaldoMetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTipoMeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFuncionarioMetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txMetasPrevistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimparMetas)
                            .addComponent(cbSetorMetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalvarMetas)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pagMetasLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lbPeriodoHistorico1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbInicioPeriodo)
                            .addComponent(lbPeriodoFim))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfPeriodoInicioHistorico1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfPeriodoFimMetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(btnPesquisarMetas)
                .addGap(1, 1, 1)
                .addComponent(lbHIstoricoDesenpenho1)
                .addGap(18, 18, 18)
                .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagMetasLayout.createSequentialGroup()
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagMetasLayout.createSequentialGroup()
                                .addComponent(lbDetalhesFuncionario1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(pagMetasLayout.createSequentialGroup()
                                .addComponent(lbAçao1)
                                .addGap(18, 18, 18)
                                .addComponent(btnVisualizaçaoHistorico1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCompararColaboradores1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnExporHistorico1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnImprimirHistorico1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 119, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagMetasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pagMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbProdutividade1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTrabalhoEquipe1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbAtitude1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PnDesempenhoMensal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(152, 152, 152))
        );

        telaRH.addTab("Metas", pagMetas);

        pagRelatorios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbPeriodoRelatorio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbPeriodoRelatorio.setText("Período");

        lbFuncionarioRelatorio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbFuncionarioRelatorio.setText("Funcionário");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Setor");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Cargo");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Tipo de relatório");

        ftfPeriodoRelatorio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        ftfPeriodoRelatorio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbFuncionarioRelatorio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbFuncionarioRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbSetorRelatorio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSetorRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbCargoRelatorio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbCargoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbTipoRelatoio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbTipoRelatoio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Situação");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setText("Formato");

        cbFormatoRelatorio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbFormatoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbSituaçãoRelatorio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSituaçãoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbIncluirDesligados.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbIncluirDesligados.setText("Incluir desligados");
        cbIncluirDesligados.addActionListener(this::cbIncluirDesligadosActionPerformed);

        cbAgruparSetor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbAgruparSetor.setText("Agrupar por setor");
        cbAgruparSetor.addActionListener(this::cbAgruparSetorActionPerformed);

        btnPesquisarRelatorio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisarRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisarRelatorio.setText("Pesquisar");

        btnLimparRelatorio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimparRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimparRelatorio.setText("Limpar");

        tbRelatoriosDiponiveis.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbRelatoriosDiponiveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Relatório", "Descrição", "Categoria", "Última geração", "", "", "", ""
            }
        ));
        jScrollPane25.setViewportView(tbRelatoriosDiponiveis);

        lbRelatorioDisponiveis.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbRelatorioDisponiveis.setText("Relatorios disponiveis");

        lbHistoricoRelatorios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbHistoricoRelatorios.setText("Historico de relatorios gerados");

        tbHistoricoRelatorioGerados.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbHistoricoRelatorioGerados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Data e hora", "Relatórios", "Período ", "Formato", "Gerado por", "Tamanho", "", " ", "", ""
            }
        ));
        jScrollPane23.setViewportView(tbHistoricoRelatorioGerados);

        lbGeraçaoRelatorio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbGeraçaoRelatorio.setText("Filtro para geração de relatorios");

        javax.swing.GroupLayout pagRelatoriosLayout = new javax.swing.GroupLayout(pagRelatorios);
        pagRelatorios.setLayout(pagRelatoriosLayout);
        pagRelatoriosLayout.setHorizontalGroup(
            pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagRelatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane25)
                    .addComponent(jScrollPane23)
                    .addGroup(pagRelatoriosLayout.createSequentialGroup()
                        .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagRelatoriosLayout.createSequentialGroup()
                                .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPeriodoRelatorio)
                                    .addComponent(jLabel20)
                                    .addComponent(cbSituaçãoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ftfPeriodoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pagRelatoriosLayout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbFuncionarioRelatorio)
                                            .addComponent(cbFuncionarioRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(168, 168, 168)
                                        .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(cbSetorRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(174, 174, 174)
                                        .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(cbCargoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pagRelatoriosLayout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pagRelatoriosLayout.createSequentialGroup()
                                                .addComponent(cbFormatoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(162, 162, 162)
                                                .addComponent(cbIncluirDesligados)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbAgruparSetor))
                                            .addComponent(jLabel21))))
                                .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pagRelatoriosLayout.createSequentialGroup()
                                        .addGap(193, 193, 193)
                                        .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbTipoRelatoio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16)))
                                    .addGroup(pagRelatoriosLayout.createSequentialGroup()
                                        .addGap(125, 125, 125)
                                        .addComponent(btnPesquisarRelatorio)
                                        .addGap(88, 88, 88)
                                        .addComponent(btnLimparRelatorio))))
                            .addComponent(lbRelatorioDisponiveis)
                            .addComponent(lbHistoricoRelatorios)
                            .addComponent(lbGeraçaoRelatorio))
                        .addGap(0, 289, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pagRelatoriosLayout.setVerticalGroup(
            pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagRelatoriosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbGeraçaoRelatorio)
                .addGap(18, 18, 18)
                .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPeriodoRelatorio)
                    .addComponent(lbFuncionarioRelatorio)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfPeriodoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFuncionarioRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSetorRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCargoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoRelatoio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pagRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFormatoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSituaçãoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbIncluirDesligados)
                    .addComponent(cbAgruparSetor)
                    .addComponent(btnPesquisarRelatorio)
                    .addComponent(btnLimparRelatorio))
                .addGap(35, 35, 35)
                .addComponent(lbRelatorioDisponiveis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(lbHistoricoRelatorios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        telaRH.addTab("Relatórios", pagRelatorios);

        pagHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pagHistorico.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnDesempenhoMensal.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout PnDesempenhoMensalLayout = new javax.swing.GroupLayout(PnDesempenhoMensal);
        PnDesempenhoMensal.setLayout(PnDesempenhoMensalLayout);
        PnDesempenhoMensalLayout.setHorizontalGroup(
            PnDesempenhoMensalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
        PnDesempenhoMensalLayout.setVerticalGroup(
            PnDesempenhoMensalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pagHistorico.add(PnDesempenhoMensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 626, -1, 334));

        lbPeriodoHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbPeriodoHistorico.setText("Período");
        pagHistorico.add(lbPeriodoHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        ftfPeriodoInicioHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pagHistorico.add(ftfPeriodoInicioHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 34, 105, -1));

        ftfPeriodoFimHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pagHistorico.add(ftfPeriodoFimHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 34, 107, -1));

        lbBDFerias1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbBDFerias1.setText("Setor");
        pagHistorico.add(lbBDFerias1, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 6, -1, -1));

        cbSetorHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSetorHistorico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pagHistorico.add(cbSetorHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 34, -1, -1));

        lbFuncionarioBDFerias1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbFuncionarioBDFerias1.setText("Funcionário");
        pagHistorico.add(lbFuncionarioBDFerias1, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 6, -1, -1));

        cbFuncionarioHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbFuncionarioHistorico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pagHistorico.add(cbFuncionarioHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 34, -1, -1));

        lbSituaçaoSaldoBD1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSituaçaoSaldoBD1.setText("Situação do saldo");
        pagHistorico.add(lbSituaçaoSaldoBD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 6, -1, -1));

        cbSituaçaoSaldoHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSituaçaoSaldoHistorico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pagHistorico.add(cbSituaçaoSaldoHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 34, -1, -1));

        lbSituaçaoFeriasBD1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbSituaçaoFeriasBD1.setText("Situação das férias");
        pagHistorico.add(lbSituaçaoFeriasBD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(759, 6, -1, -1));

        cbSituaçaoFeriasHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbSituaçaoFeriasHistorico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pagHistorico.add(cbSituaçaoFeriasHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(759, 34, -1, -1));

        btnPesquisarHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisarHistorico.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisarHistorico.setText("Pesquisar");
        pagHistorico.add(btnPesquisarHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(953, 34, -1, -1));

        btnLimparHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimparHistorico.setIcon(new javax.swing.ImageIcon("C:\\Users\\ArthurGiuvannucci\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimparHistorico.setText("Limpar");
        pagHistorico.add(btnLimparHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(1096, 34, -1, -1));

        lbHIstoricoDesenpenho.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbHIstoricoDesenpenho.setText("Historico de desenpenho dos funcionarios");
        pagHistorico.add(lbHIstoricoDesenpenho, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 90, -1, -1));

        tbHistoricoDesenpenho.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbHistoricoDesenpenho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Funcionário", "Matrícula", "Setor", "Salado atual", "Situação do saldo", "Férias solicitadas", "Dias", "Situação férias", "Último ajuste"
            }
        ));
        jScrollPane19.setViewportView(tbHistoricoDesenpenho);

        pagHistorico.add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 118, 1341, -1));

        tbDetalhesFuncionario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbDetalhesFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Matricula", "Funcionário", "Setor", "Cargo", "Data de admissão", "Data de desligamento", "Situação vinculo", "Jornada"
            }
        ));
        jScrollPane18.setViewportView(tbDetalhesFuncionario);

        pagHistorico.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 626, 635, 334));

        lbDetalhesFuncionario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDetalhesFuncionario.setText("Detalhes do colaborador selecionado");
        pagHistorico.add(lbDetalhesFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 604, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Indicadores de desenpenho");
        pagHistorico.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 620, -1, -1));
        pagHistorico.add(lbTrabalhoEquipe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 800, 120, 40));
        pagHistorico.add(lbProdutividade, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 650, 120, 40));
        pagHistorico.add(lbQualidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 700, 120, 40));
        pagHistorico.add(lbAtitude, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 750, 120, 40));

        btnCompararColaboradores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCompararColaboradores.setText("comparar colaboradores");
        pagHistorico.add(btnCompararColaboradores, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 1030, -1, 40));

        btnExporHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExporHistorico.setText("Exportar histórico");
        pagHistorico.add(btnExporHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 1030, -1, 40));

        btnImprimirHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnImprimirHistorico.setText("Imprimir histórico");
        pagHistorico.add(btnImprimirHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 1030, -1, 40));

        btnVisualizaçaoHistorico.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnVisualizaçaoHistorico.setText("Visualizar hitórico completo");
        pagHistorico.add(btnVisualizaçaoHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1030, -1, 40));

        lbAçao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbAçao.setText("Ações");
        pagHistorico.add(lbAçao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1000, -1, -1));

        telaRH.addTab("Histórico", pagHistorico);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(telaRH)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(telaRH)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbAgruparSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAgruparSetorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAgruparSetorActionPerformed

    private void cbIncluirDesligadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIncluirDesligadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbIncluirDesligadosActionPerformed

    private void btnExcluirBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirBDActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnExcluFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluFuncionarioActionPerformed

    private void btnAtualizarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtualizarFuncionarioActionPerformed

    private void btnVoltar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVoltar3ActionPerformed

    private void cbSegundaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSegundaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSegundaActionPerformed

    private void cbTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTurnoActionPerformed

    private void btnVoltar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVoltar2ActionPerformed

    private void cbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStatusActionPerformed

    private void tfEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailActionPerformed

    private void tfCodigoFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodigoFuncionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodigoFuncionariosActionPerformed

    private void btnExcluirInfraçoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirInfraçoesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirInfraçoesActionPerformed

    private void btnAdicionarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarArquivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionarArquivoActionPerformed

    private void txMetasPrevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMetasPrevistasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txMetasPrevistasActionPerformed

    private void btnLimparMetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparMetasActionPerformed
        // TODO add your handling code here:
        limparCamposMeta();
        
    }//GEN-LAST:event_btnLimparMetasActionPerformed

    private void btnLimpar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpar2ActionPerformed
        // TODO add your handling code here:
        
        limparCamposSetores();
        
    }//GEN-LAST:event_btnLimpar2ActionPerformed

    private void btnLimpar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpar1ActionPerformed
        // TODO add your handling code here:
       limparCamposCargos();
        
    }//GEN-LAST:event_btnLimpar1ActionPerformed

    private void btnLimpar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpar3ActionPerformed
        // TODO add your handling code here:
        
        limparCamposTurnos();
        
    }//GEN-LAST:event_btnLimpar3ActionPerformed

    private void btnPesquisarEscala1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarEscala1ActionPerformed
        // TODO add your handling code here:
        
       
        
    }//GEN-LAST:event_btnPesquisarEscala1ActionPerformed

    private void btnPesquisarBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarBDActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_btnPesquisarBDActionPerformed

    private void btnPesquisarInfracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarInfracoesActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_btnPesquisarInfracoesActionPerformed

    private void btnLimparInfracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparInfracoesActionPerformed
        // TODO add your handling code here:
        limparCamposAfastamento();
        
    }//GEN-LAST:event_btnLimparInfracoesActionPerformed

    private void btnLimparEscala2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparEscala2ActionPerformed
        // TODO add your handling code here:
         limparCamposPonto();
        
    }//GEN-LAST:event_btnLimparEscala2ActionPerformed

    private void btnPesquisarEscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarEscalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarEscalaActionPerformed

    private void btnLimparEscala1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparEscala1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparEscala1ActionPerformed

    private void btnLimparBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparBDActionPerformed
        // TODO add your handling code here:
        limparCamposFerias();
        
    }//GEN-LAST:event_btnLimparBDActionPerformed

    private void btnPesquisarMetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarMetasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarMetasActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        cadastrarFuncionario();
        
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void ftfCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftfCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftfCPFActionPerformed

    private void btnSalvar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvar2ActionPerformed
        // TODO add your handling code here:
        cadastrarSetor();
    }//GEN-LAST:event_btnSalvar2ActionPerformed

    private void btnSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvar1ActionPerformed
        // TODO add your handling code here:
        cadastrarCargo();
        
    }//GEN-LAST:event_btnSalvar1ActionPerformed

    private void btnSalvar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvar3ActionPerformed
        // TODO add your handling code here:
        cadastrarTurno();
        
    }//GEN-LAST:event_btnSalvar3ActionPerformed

    private void btnSalvarPontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarPontoActionPerformed

    private void btnAdicionarEscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarEscalaActionPerformed
        // TODO add your handling code here:
        cadastrarEscala();
        
    }//GEN-LAST:event_btnAdicionarEscalaActionPerformed

    private void cbFuncionarioInfracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFuncionarioInfracaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFuncionarioInfracaoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        
        cadastrarAfastamento();
        
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnSalvarEscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarEscalaActionPerformed
        // TODO add your handling code here:
     cadastrarEscala();   
        
    }//GEN-LAST:event_btnSalvarEscalaActionPerformed

    private void btnRegistrarBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarBDActionPerformed
        // TODO add your handling code here:
        cadastrarFerias();
        
    }//GEN-LAST:event_btnRegistrarBDActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Usuario usuario = new Usuario();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Tela_Funcionarios_RH(usuario).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnDesempenhoMensal;
    private javax.swing.JPanel PnDesempenhoMensal1;
    private javax.swing.JTable TbCadastroCargos;
    private javax.swing.JTable TbCadastroFuncionario;
    private javax.swing.JTable TbCadastroSetores;
    private javax.swing.JPanel areaCadastro;
    private javax.swing.JButton btnAdicFuncionario;
    private javax.swing.JButton btnAdicionarArquivo;
    private javax.swing.JButton btnAdicionarEscala;
    private javax.swing.JButton btnAprovar;
    private javax.swing.JButton btnAprovarBD;
    private javax.swing.JButton btnAtualizarFuncionario;
    private javax.swing.JButton btnCompararColaboradores;
    private javax.swing.JButton btnCompararColaboradores1;
    private javax.swing.JButton btnCopiarEscala;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditar1;
    private javax.swing.JButton btnEditar2;
    private javax.swing.JButton btnEditar3;
    private javax.swing.JButton btnEditarBD;
    private javax.swing.JButton btnEditarEscala;
    private javax.swing.JButton btnEditarInfarçoes;
    private javax.swing.JButton btnEncerrar;
    private javax.swing.JButton btnExcluFuncionario;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JButton btnExcluir2;
    private javax.swing.JButton btnExcluir3;
    private javax.swing.JButton btnExcluirBD;
    private javax.swing.JButton btnExcluirEscala;
    private javax.swing.JButton btnExcluirInfraçoes;
    private javax.swing.JButton btnExporHistorico;
    private javax.swing.JButton btnExporHistorico1;
    private javax.swing.JButton btnHistoricoAjustesBD;
    private javax.swing.JButton btnImprimirHistorico;
    private javax.swing.JButton btnImprimirHistorico1;
    private javax.swing.JButton btnJHistoricoFeriasBD;
    private javax.swing.JButton btnJustificar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnLimpar1;
    private javax.swing.JButton btnLimpar2;
    private javax.swing.JButton btnLimpar3;
    private javax.swing.JButton btnLimparBD;
    private javax.swing.JButton btnLimparEscala1;
    private javax.swing.JButton btnLimparEscala2;
    private javax.swing.JButton btnLimparHistorico;
    private javax.swing.JButton btnLimparInfracoes;
    private javax.swing.JButton btnLimparMetas;
    private javax.swing.JButton btnLimparRelatorio;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnNovo1;
    private javax.swing.JButton btnNovo2;
    private javax.swing.JButton btnNovo3;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnPesquisar1;
    private javax.swing.JButton btnPesquisar2;
    private javax.swing.JButton btnPesquisar3;
    private javax.swing.JButton btnPesquisarBD;
    private javax.swing.JButton btnPesquisarEscala;
    private javax.swing.JButton btnPesquisarEscala1;
    private javax.swing.JButton btnPesquisarHistorico;
    private javax.swing.JButton btnPesquisarInfracoes;
    private javax.swing.JButton btnPesquisarMetas;
    private javax.swing.JButton btnPesquisarRelatorio;
    private javax.swing.JButton btnPuplicarEscala;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrarBD;
    private javax.swing.JButton btnRegistrarRetorno;
    private javax.swing.JButton btnRegistrarRetornoBD;
    private javax.swing.JButton btnReprovar;
    private javax.swing.JButton btnReprovarBD;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSalvar1;
    private javax.swing.JButton btnSalvar2;
    private javax.swing.JButton btnSalvar3;
    private javax.swing.JButton btnSalvarEscala;
    private javax.swing.JButton btnSalvarMetas;
    private javax.swing.JButton btnSalvarPonto;
    private javax.swing.JButton btnVisualizaçaoHistorico;
    private javax.swing.JButton btnVisualizaçaoHistorico1;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btnVoltar1;
    private javax.swing.JButton btnVoltar2;
    private javax.swing.JButton btnVoltar3;
    private javax.swing.JCheckBox cbAgruparSetor;
    private javax.swing.JComboBox<String> cbBDferias;
    private javax.swing.JComboBox<String> cbCargo;
    private javax.swing.JComboBox<String> cbCargoRelatorio;
    private javax.swing.JCheckBox cbDomingo;
    private javax.swing.JComboBox<String> cbFormatoRelatorio;
    private javax.swing.JComboBox<String> cbFuncionarioBDFerias;
    private javax.swing.JComboBox<String> cbFuncionarioEscala;
    private javax.swing.JComboBox<String> cbFuncionarioEscala1;
    private javax.swing.JComboBox<String> cbFuncionarioHistorico;
    private javax.swing.JComboBox<String> cbFuncionarioInfracao;
    private javax.swing.JComboBox<String> cbFuncionarioMetas;
    private javax.swing.JComboBox<String> cbFuncionarioRelatorio;
    private javax.swing.JCheckBox cbIncluirDesligados;
    private javax.swing.JComboBox<String> cbNivelHierarquia;
    private javax.swing.JComboBox<String> cbNivelHierarquia1;
    private javax.swing.JCheckBox cbQuarta;
    private javax.swing.JCheckBox cbQuinta;
    private javax.swing.JCheckBox cbSabado;
    private javax.swing.JCheckBox cbSegunda;
    private javax.swing.JComboBox<String> cbSelecioneTurno;
    private javax.swing.JComboBox<String> cbSetorCargos;
    private javax.swing.JComboBox<String> cbSetorCargos1;
    private javax.swing.JComboBox<String> cbSetorEscala;
    private javax.swing.JComboBox<String> cbSetorEscala1;
    private javax.swing.JComboBox<String> cbSetorFuncionarios;
    private javax.swing.JComboBox<String> cbSetorHistorico;
    private javax.swing.JComboBox<String> cbSetorInfracoa;
    private javax.swing.JComboBox<String> cbSetorMetas;
    private javax.swing.JComboBox<String> cbSetorPai;
    private javax.swing.JComboBox<String> cbSetorRelatorio;
    private javax.swing.JComboBox<String> cbSituaçaoFerias;
    private javax.swing.JComboBox<String> cbSituaçaoFeriasHistorico;
    private javax.swing.JComboBox<String> cbSituaçaoFeriasMetas;
    private javax.swing.JComboBox<String> cbSituaçaoSaldo;
    private javax.swing.JComboBox<String> cbSituaçaoSaldoHistorico;
    private javax.swing.JComboBox<String> cbSituaçaoSaldoMetas;
    private javax.swing.JComboBox<String> cbSituaçãoRelatorio;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JComboBox<String> cbStatusPF;
    private javax.swing.JComboBox<String> cbStatusPF1;
    private javax.swing.JComboBox<String> cbStatusSetores;
    private javax.swing.JComboBox<String> cbStatusSetores22;
    private javax.swing.JCheckBox cbTerca;
    private javax.swing.JComboBox<String> cbTipoMeta;
    private javax.swing.JComboBox<String> cbTipoRelatoio;
    private javax.swing.JCheckBox cbTurno;
    private javax.swing.JComboBox<String> cbTurnoEscala;
    private javax.swing.JComboBox<String> cbTurnoFuncionario;
    private javax.swing.JComboBox<String> cbTurnoInfracao;
    private javax.swing.JComboBox<String> cbTurnoPonto;
    private javax.swing.JCheckBox chbCargoAtivo;
    private javax.swing.JCheckBox chbCargoAtivo1;
    private java.awt.Checkbox chbFuncionarioAtivo;
    private javax.swing.JTextField ftfAdicionalInsalubridade;
    private javax.swing.JTextField ftfAdicionalPericulocidade;
    private javax.swing.JFormattedTextField ftfCPF;
    private javax.swing.JTextField ftfCargaHorariaSemanalh;
    private javax.swing.JFormattedTextField ftfData;
    private javax.swing.JFormattedTextField ftfDataAdimissao;
    private javax.swing.JFormattedTextField ftfDataNascimento;
    private javax.swing.JFormattedTextField ftfDuracaoTurno;
    private javax.swing.JFormattedTextField ftfFimPeriodo;
    private javax.swing.JFormattedTextField ftfHoraFim;
    private javax.swing.JFormattedTextField ftfHoraInicio;
    private javax.swing.JFormattedTextField ftfInicioPeriodo;
    private javax.swing.JFormattedTextField ftfIntervaloFim;
    private javax.swing.JFormattedTextField ftfIntervaloInicio;
    private javax.swing.JFormattedTextField ftfPeriodoFim;
    private javax.swing.JFormattedTextField ftfPeriodoFim1;
    private javax.swing.JFormattedTextField ftfPeriodoFimBD;
    private javax.swing.JFormattedTextField ftfPeriodoFimHistorico;
    private javax.swing.JFormattedTextField ftfPeriodoFimMetas;
    private javax.swing.JFormattedTextField ftfPeriodoInicio;
    private javax.swing.JFormattedTextField ftfPeriodoInicioBD;
    private javax.swing.JFormattedTextField ftfPeriodoInicioHistorico;
    private javax.swing.JFormattedTextField ftfPeriodoInicioHistorico1;
    private javax.swing.JFormattedTextField ftfPeriodoRelatorio;
    private javax.swing.JTextField ftfSalarioBase;
    private javax.swing.JFormattedTextField ftfTelefone;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane1010000;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lbAdicionalInsalubridade;
    private javax.swing.JLabel lbAdicionalPericulocidade;
    private javax.swing.JLabel lbAtitude;
    private javax.swing.JLabel lbAtitude1;
    private javax.swing.JLabel lbAtivoSetores;
    private javax.swing.JLabel lbAtivoTurnos;
    private javax.swing.JLabel lbAvisoBarraPesquisa;
    private javax.swing.JLabel lbAvisoBarraPesquisa1;
    private javax.swing.JLabel lbAvisoBarraPesquisa2;
    private javax.swing.JLabel lbAçao;
    private javax.swing.JLabel lbAçao1;
    private javax.swing.JLabel lbBDFerias;
    private javax.swing.JLabel lbBDFerias1;
    private javax.swing.JLabel lbBancoDadosFerias;
    private javax.swing.JLabel lbCPF;
    private javax.swing.JLabel lbCargaHoraiaSem;
    private javax.swing.JLabel lbCargo;
    private javax.swing.JLabel lbCargoAtivo;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbCodigoTurno;
    private javax.swing.JLabel lbDataAdmissao;
    private javax.swing.JLabel lbDataNascimento;
    private javax.swing.JLabel lbDescricaoCargos;
    private javax.swing.JLabel lbDescricoaEscala;
    private javax.swing.JLabel lbDescricoaSetores;
    private javax.swing.JLabel lbDetalhesEscala;
    private javax.swing.JLabel lbDetalhesFuncionario;
    private javax.swing.JLabel lbDetalhesFuncionario1;
    private javax.swing.JLabel lbDiaSemana;
    private javax.swing.JLabel lbDuracaoTurno;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEscalaSemanal;
    private javax.swing.JLabel lbFimPeriodo;
    private javax.swing.JLabel lbFuncionarioBDFerias;
    private javax.swing.JLabel lbFuncionarioBDFerias1;
    private javax.swing.JLabel lbFuncionarioEscala;
    private javax.swing.JLabel lbFuncionarioEscalaInfraçoes;
    private javax.swing.JLabel lbFuncionarioEscalaPonto;
    private javax.swing.JLabel lbFuncionarioMetas;
    private javax.swing.JLabel lbFuncionarioRelatorio;
    private javax.swing.JLabel lbFuncionarioTurnoSelecionado;
    private javax.swing.JLabel lbGeraçaoRelatorio;
    private javax.swing.JLabel lbGerenteResponsavel;
    private javax.swing.JLabel lbHIstoricoDesenpenho;
    private javax.swing.JLabel lbHIstoricoDesenpenho1;
    private javax.swing.JLabel lbHistoricoRelatorios;
    private javax.swing.JLabel lbHoraFim;
    private javax.swing.JLabel lbHoraInicio;
    private javax.swing.JLabel lbHorario;
    private javax.swing.JLabel lbInicio;
    private javax.swing.JLabel lbInicioPeriodo;
    private javax.swing.JLabel lbIntervaloFim;
    private javax.swing.JLabel lbIntervaloInicio;
    private javax.swing.JLabel lbJustificativa;
    private javax.swing.JLabel lbNivelHierarquia;
    private javax.swing.JLabel lbNomeCargo;
    private javax.swing.JLabel lbNomeFuncionario;
    private javax.swing.JLabel lbNomeSetor;
    private javax.swing.JLabel lbNomeTurno;
    private javax.swing.JLabel lbPeriodoBD;
    private javax.swing.JLabel lbPeriodoEscala;
    private javax.swing.JLabel lbPeriodoFim;
    private javax.swing.JLabel lbPeriodoHistorico;
    private javax.swing.JLabel lbPeriodoHistorico1;
    private javax.swing.JLabel lbPeriodoInfraçoes;
    private javax.swing.JLabel lbPeriodoPonto;
    private javax.swing.JLabel lbPeriodoRelatorio;
    private javax.swing.JLabel lbPesquisarFuncionario;
    private javax.swing.JLabel lbPesquisarFuncionario1;
    private javax.swing.JLabel lbPesquisarFuncionario2;
    private javax.swing.JLabel lbProdutividade;
    private javax.swing.JLabel lbProdutividade1;
    private javax.swing.JLabel lbQualidade;
    private javax.swing.JLabel lbRegistrosFerias;
    private javax.swing.JLabel lbRelatorioDisponiveis;
    private javax.swing.JLabel lbResumoEscala;
    private javax.swing.JLabel lbResumoOcorrencias;
    private javax.swing.JLabel lbResumoOcorrencias1;
    private javax.swing.JLabel lbSalarioBase;
    private javax.swing.JLabel lbSelecioneTurno;
    private javax.swing.JLabel lbSetor;
    private javax.swing.JLabel lbSetorFuncionarios;
    private javax.swing.JLabel lbSetorInfraçoes;
    private javax.swing.JLabel lbSetorMetas;
    private javax.swing.JLabel lbSetorPai;
    private javax.swing.JLabel lbSetorPonto;
    private javax.swing.JLabel lbSetorpai;
    private javax.swing.JLabel lbSetroCargos;
    private javax.swing.JLabel lbSinalSeta;
    private javax.swing.JLabel lbSituaçaoFeriasBD;
    private javax.swing.JLabel lbSituaçaoFeriasBD1;
    private javax.swing.JLabel lbSituaçaoFeriasMetas;
    private javax.swing.JLabel lbSituaçaoSaldoBD;
    private javax.swing.JLabel lbSituaçaoSaldoBD1;
    private javax.swing.JLabel lbSituaçaoSaldoMetas;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbStatus1;
    private javax.swing.JLabel lbStatus2;
    private javax.swing.JLabel lbTelefone;
    private javax.swing.JLabel lbTipoMeta;
    private javax.swing.JLabel lbTrabalhoEquipe;
    private javax.swing.JLabel lbTrabalhoEquipe1;
    private javax.swing.JLabel lbTurno;
    private javax.swing.JLabel lbTurnoEscala;
    private javax.swing.JLabel lbTurnoEscalaInfraçoes;
    private javax.swing.JLabel lbTurnoEscalaPonto;
    private javax.swing.JLabel lbTurnoNoturno;
    private javax.swing.JLabel lbTurnosCadastrados;
    private javax.swing.JLabel ldAçoes;
    private javax.swing.JLabel ldAçoes1;
    private javax.swing.JLabel ldAçoesBD;
    private javax.swing.JPanel pagBancodeDados;
    private javax.swing.JPanel pagCargos;
    private javax.swing.JPanel pagEscala;
    private javax.swing.JPanel pagFuncionarios;
    private javax.swing.JPanel pagHistorico;
    private javax.swing.JPanel pagInfracoes;
    private javax.swing.JPanel pagInfraçoes;
    private javax.swing.JPanel pagMetas;
    private javax.swing.JPanel pagOpcoesConfing;
    private javax.swing.JPanel pagPonto;
    private javax.swing.JPanel pagRelatorios;
    private javax.swing.JPanel pagSetores;
    private javax.swing.JPanel pagTurnos;
    private javax.swing.JScrollPane scJustificativa;
    private javax.swing.JTable tbBancodadosFerias;
    private javax.swing.JTable tbDetalheOcorrencia;
    private javax.swing.JTable tbDetalhesFuncionario;
    private javax.swing.JTable tbDetalhesFuncionario1;
    private javax.swing.JTable tbEscalaSemanal1;
    private javax.swing.JTable tbEscalaSemanal2;
    private javax.swing.JTable tbFuncionariosTurnos;
    private javax.swing.JTable tbFuncionariosTurnos1;
    private javax.swing.JTable tbFuncionariosTurnos2;
    private javax.swing.JTable tbHistoricoDesenpenho;
    private javax.swing.JTable tbHistoricoDesenpenho1;
    private javax.swing.JTable tbHistoricoRelatorioGerados;
    private javax.swing.JTable tbInformaçoesFerias;
    private javax.swing.JTable tbOcorrencias;
    private javax.swing.JTable tbRelatoriosDiponiveis;
    private javax.swing.JTable tbResumoBancoDados;
    private javax.swing.JTable tbResumoOcorrencias;
    private javax.swing.JTable tbTurnosCadastrados;
    private javax.swing.JTabbedPane telaRH;
    private javax.swing.JTextField tfCodigoCargos;
    private javax.swing.JTextField tfCodigoEscala;
    private javax.swing.JTextField tfCodigoFuncionarios;
    private javax.swing.JTextField tfCodigoSetores;
    private javax.swing.JTextField tfDescricao;
    private javax.swing.JTextField tfDescricaoEscala;
    private javax.swing.JTextField tfDescricaoSetores;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfNomeCargo;
    private javax.swing.JTextField tfNomeEscala;
    private javax.swing.JTextField tfNomeFuncionario;
    private javax.swing.JTextField tfNomeSetor;
    private javax.swing.JTextField txBarraPesquisaFuncionarios;
    private javax.swing.JTextField txBarraPesquisaFuncionarios1;
    private javax.swing.JTextField txBarraPesquisaFuncionarios2;
    private javax.swing.JTextField txMetasPrevistas;
    private javax.swing.JTextField txPesquisarTurno;
    private javax.swing.JTextArea txaJustificativaPonto;
    // End of variables declaration//GEN-END:variables

   
   
}
