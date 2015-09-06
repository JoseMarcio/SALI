package br.com.sali.bean;

import br.com.sali.dao.ProfessorDao;
import br.com.sali.modelo.Professor;
import br.com.sali.util.ValidacoesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author SALI
 */
@ManagedBean
public class ProfessorPesquisarBean {

    private String filtroPesquisa;
    private List<Professor> professoresFiltrados = new ArrayList<>();
    private String tipoDePesquisa;
    private ProfessorDao professorDao = new ProfessorDao();

    public ProfessorPesquisarBean() {
    }

    public String getFiltroPesquisa() {
        return filtroPesquisa;
    }

    public void setFiltroPesquisa(String filtroPesquisa) {
        this.filtroPesquisa = filtroPesquisa;
    }

    public List<Professor> getProfessoresFiltrados() {
        return professoresFiltrados;
    }

    public void setProfessoresFiltrados(List<Professor> professoresFiltrados) {
        this.professoresFiltrados = professoresFiltrados;
    }

    public String getTipoDePesquisa() {
        return tipoDePesquisa;
    }

    public void setTipoDePesquisa(String tipoDePesquisa) {
        this.tipoDePesquisa = tipoDePesquisa;
    }

    public String pesquisarProfessor() {
        try {
            professoresFiltrados = listarProfessoresPorFiltro();
            limpar();
        } catch (Exception e) {
            FacesContext f = FacesContext.getCurrentInstance();
            f.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao pesquisar.", ""));
        }

        return null;
    }

    public List<Professor> listarProfessoresPorFiltro() {
        try {
            if (tipoDePesquisa.equals("matricula")) {
                if (ValidacoesUtil.soContemNumeros(filtroPesquisa)) {
                    return professorDao.listarProfessorMatricula(Integer.parseInt(filtroPesquisa));
                } else {
                    FacesContext f = FacesContext.getCurrentInstance();
                    f.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Para pesquisar por matrícula informe somente números.", ""));
                }
            } else {
                return professorDao.listarProfessorNome(filtroPesquisa);
            }
        } catch (Exception e) {
            FacesContext f = FacesContext.getCurrentInstance();
            f.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao pesquisar.", ""));
        }
        return null;
    }

    public String limpar() {
        filtroPesquisa = "";
        return "pesquisarProfessor";
    }
}
