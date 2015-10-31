package br.com.sali.bean.forum;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Topico;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.AlunoRN;
import br.com.sali.regras.TopicoRN;
import br.com.sali.regras.UsuarioRN;
import br.com.sali.util.ValidacoesUtil;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;



/**
 *
 * @author SALI
 */
@ManagedBean
@RequestScoped
public class NovoAlunoTopicoBean implements Serializable {

    private TopicoRN topicoRN;
    private Topico topico;

    public NovoAlunoTopicoBean() {
        this.topicoRN = new TopicoRN();
        this.topico = new Topico();
    }
    //==========================================================================

    /**
     * Reinicia a bean.
     *
     */
    public void limpar() {
        this.topicoRN = new TopicoRN();
        this.topico = new Topico();
    }

    /**
     * Salva um novo tópico.
     *
     */
    public void salvar() {
        if (ValidacoesUtil.soTemEspaco(this.topico.getNome())) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome inválido.", "");
            FacesContext.getCurrentInstance().addMessage(null, m);
        } else if (ValidacoesUtil.soTemEspaco(this.topico.getAssunto())) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Assunto inválido.", "");
            FacesContext.getCurrentInstance().addMessage(null, m);
        } else if (ValidacoesUtil.isExistenteNomeTopico(this.topico.getNome(), getAlunoConectado().getTurma())) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Já existe um tópico com esse nome.", "");
            FacesContext.getCurrentInstance().addMessage(null, m);
        } else {
            this.topico.setAutor(getAlunoConectado().getNome());
            this.topico.setTurma(getAlunoConectado().getTurma());
            this.topicoRN.salvar(this.topico);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tópico criado com sucesso!", "");
            FacesContext.getCurrentInstance().addMessage(null, m);
            limpar();
        }

    }

    /**
     * Retorna o aluno autenticado no momento.
     *
     * @return
     */
    public Aluno getAlunoConectado() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();

        String emailUsuario = external.getRemoteUser();

        UsuarioRN usuarioRN = new UsuarioRN();
        Usuario usuario = usuarioRN.getUsuarioByEmail(emailUsuario);

        AlunoRN alunoRN = new AlunoRN();

        Aluno aluno = alunoRN.getAlunoByUsuario(usuario);

        return aluno;
    }

    //==========================================================================
    public TopicoRN getTopicoRN() {
        return topicoRN;
    }

    public void setTopicoRN(TopicoRN topicoRN) {
        this.topicoRN = topicoRN;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

}
