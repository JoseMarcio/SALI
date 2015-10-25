package br.com.sali.bean.licao;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Licao;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.AlunoRN;
import br.com.sali.regras.LicaoRN;
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
public class LicaoAlunoVerBean implements Serializable{
    
    private List<Licao> licoesDaTurma;
    private LicaoRN licaoRN;
    private StreamedContent licao;
    
    @PostConstruct
    public void init(){
        this.licaoRN = new LicaoRN();
        setLicoesDaTurma(licaoRN.listarLicoesPorTurma(getAlunoConectado().getTurma()));
        Collections.reverse(licoesDaTurma);
    }
    
    /**
     * Atualiza a lista de lições da turma.
     * 
     */
    public void atualizarLicoes(){
        this.licoesDaTurma = this.licaoRN.listarLicoesPorTurma(getAlunoConectado().getTurma());
        Collections.reverse(licoesDaTurma);
        
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
