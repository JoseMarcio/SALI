package br.com.sali.bean.novosrelatorios;

import br.com.sali.dao.AlunoDAO;
import br.com.sali.modelo.Aluno;
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
public class ExibirRelatorioAluno implements Serializable {

    private Long idAlunoGerar;
    private Aluno alunoGerarRelatorio;
    private AlunoDAO alunoDAO;

    public ExibirRelatorioAluno() {
        this.idAlunoGerar = Integer.toUnsignedLong(0);
        this.alunoDAO = new AlunoDAO();
        this.alunoGerarRelatorio = new Aluno();
    }

    /**
     * Carrega o aluno no qual o relatório será gerado.
     */
    public void carregarAluno() {
        this.alunoGerarRelatorio = (Aluno) alunoDAO.getObjectById(Aluno.class, this.idAlunoGerar);
    }

    /**
     * Passa o id do aluno a ser gerado o relatório.
     */
    public void passarIdAluno() {
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("idAluno", this.idAlunoGerar);
    }

    public Long getIdAlunoGerar() {
        return idAlunoGerar;
    }

    public void setIdAlunoGerar(Long idAlunoGerar) {
        this.idAlunoGerar = idAlunoGerar;
    }

    public Aluno getAlunoGerarRelatorio() {
        return alunoGerarRelatorio;
    }

    public void setAlunoGerarRelatorio(Aluno alunoGerarRelatorio) {
        this.alunoGerarRelatorio = alunoGerarRelatorio;
    }
}
