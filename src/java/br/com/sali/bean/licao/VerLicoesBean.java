package br.com.sali.bean.licao;

import br.com.sali.modelo.Licao;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.LicaoRN;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.UsuarioRN;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author SALI
 */
@ManagedBean
@ViewScoped
public class VerLicoesBean implements Serializable{
    
    private List<Licao> licoesDaTurma;
    private LicaoRN licaoRN;
    private StreamedContent licao;
    
    @PostConstruct
    public void init(){
        this.licaoRN = new LicaoRN();
        setLicoesDaTurma(licaoRN.listarLicoesPorTurma(getProfessorConectado().getTurmaAtual()));
        Collections.reverse(licoesDaTurma);
    }
    
    /**
     * Atualiza a lista de lições da turma.
     * 
     */
    public void atualizarLicoes(){
        this.licoesDaTurma = this.licaoRN.listarLicoesPorTurma(getProfessorConectado().getTurmaAtual());
        Collections.reverse(licoesDaTurma);
        
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
    
    
    //==========================================================================

    public List<Licao> getLicoesDaTurma() {
        return licoesDaTurma;
    }

    public void setLicoesDaTurma(List<Licao> licoesDaTurma) {
        this.licoesDaTurma = licoesDaTurma;
    }

    public LicaoRN getLicaoRN() {
        return licaoRN;
    }

    public void setLicaoRN(LicaoRN licaoRN) {
        this.licaoRN = licaoRN;
    }

    public void setLicao(StreamedContent licao) {
        this.licao = licao;
    }
    
    
}
