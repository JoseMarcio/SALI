package br.com.sali.bean.forum;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Topico;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.ProfessorRN;
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
public class TopicosProfessorDisponiveisBean implements Serializable{
    
    private List<Topico> topicosDaTurma;
    private TopicoRN topicoRN;

    public TopicosProfessorDisponiveisBean() {
    }

   
    @PostConstruct
    public void init(){
        this.topicosDaTurma = new ArrayList<>();
        this.topicoRN = new TopicoRN();
        this.topicosDaTurma = topicoRN.listarTopicosPorTurma(getProfessorConectado().getTurmaAtual());
        Collections.reverse(this.topicosDaTurma);
    }

    
   
    
    
    /**
     * Retorna o professor autenticado no momento.
     *
     * @return
     */
    public Professor getProfessorConectado() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();

        String emailUsuario = external.getRemoteUser();

        UsuarioRN usuarioRN = new UsuarioRN();
        Usuario usuario = usuarioRN.getUsuarioByEmail(emailUsuario);

        ProfessorRN professorRN = new ProfessorRN();

        Professor professor = professorRN.getProfessorByUsuario(usuario);

        return professor;
    }
    
    /**
     * Direciona a página para criar novo fórum.
     *
     * @return
     */
    public String irNovoTopico() {
        return "/professor/novo-topico?faces-redirect=true";
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
