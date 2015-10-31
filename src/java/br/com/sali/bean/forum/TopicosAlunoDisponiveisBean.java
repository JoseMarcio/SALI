package br.com.sali.bean.forum;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Topico;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.AlunoRN;
import br.com.sali.regras.TopicoRN;
import br.com.sali.regras.UsuarioRN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class TopicosAlunoDisponiveisBean implements Serializable{
    
    private List<Topico> topicosDaTurma;
    private TopicoRN topicoRN;

    public TopicosAlunoDisponiveisBean() {
    }
   
    @PostConstruct
    public void init(){
        this.topicosDaTurma = new ArrayList<>();
        this.topicoRN = new TopicoRN();
        this.topicosDaTurma = topicoRN.listarTopicosPorTurma(getAlunoConectado().getTurma());
        Collections.reverse(this.topicosDaTurma);
    }

    //==========================================================================  
    
    
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
    
    /**
     * Direciona a página para criar novo tópico.
     *
     * @return
     */
    public String irNovoTopico() {
        return "/aluno/novo-topico?faces-redirect=true";
    }
    //==========================================================================
     public List<Topico> getTopicosDaTurma() {
        return topicosDaTurma;
    }

    public void setTopicosDaTurma(List<Topico> topicosDaTurma) {
        this.topicosDaTurma = topicosDaTurma;
    }

    public TopicoRN getTopicoRN() {
        return topicoRN;
    }

    public void setTopicoRN(TopicoRN topicoRN) {
        this.topicoRN = topicoRN;
    }
    
    
    
}
