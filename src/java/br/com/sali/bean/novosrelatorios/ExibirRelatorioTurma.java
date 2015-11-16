package br.com.sali.bean.novosrelatorios;

import br.com.sali.dao.TurmaDAO;
import br.com.sali.modelo.Turma;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author SALI
 */
@ManagedBean
@ViewScoped
public class ExibirRelatorioTurma implements Serializable{
    
    private Long idTurmaGerar;
    private Turma turmaGerarRelatorio;
    private TurmaDAO turmaDAO;
    
    public ExibirRelatorioTurma() {
        this.idTurmaGerar = Integer.toUnsignedLong(0);
        this.turmaDAO = new TurmaDAO();
        this.turmaGerarRelatorio = new Turma();
    }
    
    /**
     * Carrega a turma na qual o relatório será gerado.
     */
    public void carregarTurma(){
        this.turmaGerarRelatorio = (Turma) turmaDAO.getObjectById(Turma.class, this.idTurmaGerar);
    }
    
    /**
     * Passa o id da turma a ser gerado o relatório.
     */
    public void passarIdTurma(){
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("idTurma", this.idTurmaGerar);  
    }
    
    public Long getIdTurmaGerar() {
        return idTurmaGerar;
    }

    public void setIdTurmaGerar(Long idTurmaGerar) {
        this.idTurmaGerar = idTurmaGerar;
    }

    public Turma getTurmaGerarRelatorio() {
        return turmaGerarRelatorio;
    }

    public void setTurmaGerarRelatorio(Turma turmaGerarRelatorio) {
        this.turmaGerarRelatorio = turmaGerarRelatorio;
    }
    
    
}
