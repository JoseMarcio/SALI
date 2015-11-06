package br.com.sali.bean.instituicao;

import br.com.sali.dao.AlunoDAO;
import br.com.sali.dao.GenericoDAO;
import br.com.sali.dao.ProfessorDAO;
import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.UsuarioRN;
import br.com.sali.util.ValidacoesUtil;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean
@ViewScoped
public class NovaSenhaBean {

    private Long idUsuario;
    private String novaSenha;
    private String confirmacaoSenha;
    private Usuario usuario;
    private UsuarioRN usuarioRN;
    private String nome;

    public void carregarUsuario() {
        novaSenha = "";
        confirmacaoSenha = "";
        nome = "";
        usuarioRN = new UsuarioRN();
        usuario = new Usuario();
        usuario = usuarioRN.getUsuarioById(idUsuario);
        AlunoDAO alunoDAO = new AlunoDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        Aluno aluno = new Aluno();
        Professor professor = new Professor();
        
        aluno = (Aluno) alunoDAO.getObjectByUsuario(Aluno.class, usuario);
        professor = (Professor) professorDAO.getObjectByUsuario(Professor.class, usuario);
        
        if(aluno != null){
            nome = aluno.getNome();
        }
        else{
            nome = professor.getNome();
        }
    }

    // =========================================================================
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioRN getUsuarioRN() {
        return usuarioRN;
    }

    public void setUsuarioRN(UsuarioRN usuarioRN) {
        this.usuarioRN = usuarioRN;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // =========================================================================

    /**
     * Reinicia os atributos do bean.
     */
    public void limpar() {
        confirmacaoSenha = "";
        novaSenha = "";
        nome = "";
        usuario = new Usuario();
    }

    /**
     * Altera a senha do usuário.
     */
    public void salvarAlteracoes() {
        if (ValidacoesUtil.temEspacoNoTexto(novaSenha)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nova senha inválida.", ""));

        } else if (ValidacoesUtil.temEspacoNoTexto(confirmacaoSenha)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Confirmação de senha inválida.", ""));

        } else {
            if (!novaSenha.equals(confirmacaoSenha)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha e confirmação de senha incorretas.", ""));
            } else {
                usuario.setSenha(novaSenha);
                try {
                    usuarioRN.alterarSenha(usuario);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Atualização concluída com sucesso!", ""));
                    limpar();

                } catch (NoSuchAlgorithmException ex) {
                    RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Exceção!", ex.getMessage()));
                }
            }
        }
    }

}
