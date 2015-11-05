package br.com.sali.bean;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Turma;
import br.com.sali.regras.AlunoRN;
import br.com.sali.util.ValidacoesUtil;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Managed Bean Alterar Aluno.
 *
 * @author SALI
 */
@ManagedBean(name = "alunoAlterarBean")
@ViewScoped
public class AlunoAlterarBean implements Serializable {

    // Atributos.
    private AlunoRN alunoRN;
    private Aluno alunoSelecionado;
    private String emailAtualAluno;
    private Integer matriculaAtualAluno;
    private String confirmaSenha;
    private String matriculaString;
    private boolean renderPainelMensagem;
    private boolean renderPainelAlterar;

    // Construtor.
    @PostConstruct
    public void init() {
        alunoRN = new AlunoRN();
        alunoSelecionado = new Aluno();
        confirmaSenha = "";
        emailAtualAluno = "";
        matriculaAtualAluno = 0;
        matriculaString = "";
        renderPainelMensagem = true;
        renderPainelAlterar = false;
    }

    //========================= Gets e Sets ====================================
    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public String getEmailAtualAluno() {
        return emailAtualAluno;
    }

    public void setEmailAtualAluno(String emailAtualAluno) {
        this.emailAtualAluno = emailAtualAluno;
    }

    public Integer getMatriculaAtualAluno() {
        return matriculaAtualAluno;
    }

    public void setMatriculaAtualAluno(Integer matriculaAtualAluno) {
        this.matriculaAtualAluno = matriculaAtualAluno;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getMatriculaString() {
        return matriculaString;
    }

    public void setMatriculaString(String matriculaString) {
        this.matriculaString = matriculaString;
    }

    public boolean isRenderPainelMensagem() {
        return renderPainelMensagem;
    }

    public void setRenderPainelMensagem(boolean renderPainelMensagem) {
        this.renderPainelMensagem = renderPainelMensagem;
    }

    public boolean isRenderPainelAlterar() {
        return renderPainelAlterar;
    }

    public void setRenderPainelAlterar(boolean renderPainelAlterar) {
        this.renderPainelAlterar = renderPainelAlterar;
    }

    //======================= Métodos ==========================================
    /**
     * Reinicia os atributos da bean.
     */
    public void limpar() {
        init();
    }

    /**
     * É o que deve acontecer no momento em que for selecionado um aluno por
     * meio do diálodo de pesquisa de alunos.
     *
     * @param event
     */
    public void eventoSelecaoAluno(SelectEvent event) {
        Aluno aluno = (Aluno) event.getObject();
        setAlunoSelecionado(aluno);
        setMatriculaAtualAluno(aluno.getMatricula());
        setMatriculaString(Integer.toString(aluno.getMatricula()));
        setEmailAtualAluno(aluno.getUsuario().getEmail());
        setRenderPainelMensagem(false);
        setRenderPainelAlterar(true);

    }

    /**
     * É o que deve acontecer no momento em que for selecionado uma turma por
     * meio do diálodo de pesquisa de turma.
     *
     * @param event
     */
    public void eventoSelecaoTurma(SelectEvent event) {
        Turma turma = (Turma) event.getObject();
        alunoSelecionado.setTurma(turma);
    }

    
    
    /**
     * Atualiza os dados de acesso ao sistema.
     */
    public void atualizarDadosAcesso(){
    }
    
    
    
    
    /**
     * Atualiza o dados do aluno no banco de dados.
     *
     */
    public void atualizarInformacoes() {
        alunoSelecionado.getUsuario().setEmail(alunoSelecionado.getUsuario().getEmail().toLowerCase());

        if (ValidacoesUtil.soTemEspaco(alunoSelecionado.getNome())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "O nome não pode ser vazio.", ""));

        } else if (!ValidacoesUtil.soTemLetras(alunoSelecionado.getNome())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Formato de nome inválido.", ""));

        } else if (!ValidacoesUtil.isValidaMatricula(matriculaString)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe uma matrícula válida.", ""));

        } else if (ValidacoesUtil.isExistenteMatricula(matriculaString)
                && (matriculaAtualAluno != alunoSelecionado.getMatricula())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matrícula já cadastrada.", ""));

        } else if (ValidacoesUtil.isExistenteEmail(alunoSelecionado.getUsuario().getEmail())
                && (!emailAtualAluno.equals(alunoSelecionado.getUsuario().getEmail()))) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail já cadastrado.", ""));

        } else if (ValidacoesUtil.temEspacoNoTexto(alunoSelecionado.getUsuario().getSenha())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha inválida.", ""));

        } else if (ValidacoesUtil.temEspacoNoTexto(confirmaSenha)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Confirma senha inválida.", ""));

        } else if (!confirmaSenha.equals(alunoSelecionado.getUsuario().getSenha())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não conferem.", ""));

        } else {
            try {

                alunoSelecionado.setMatricula(Integer.parseInt(matriculaString));
                alunoRN.atualizarAluno(alunoSelecionado);
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Sucesso!", "Atualização concluída com sucesso."));
            } catch (NoSuchAlgorithmException ex) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Exceção!", ex.getMessage()));
            }

        }
    }

}
