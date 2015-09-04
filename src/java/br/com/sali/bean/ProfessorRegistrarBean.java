package br.com.sali.bean;

import br.com.sali.dao.ProfessorDao;
import br.com.sali.modelo.Professor;
import br.com.sali.util.CriptografiaUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author SALI
 */
@ManagedBean
public class ProfessorRegistrarBean {

    private Professor professor = new Professor();
    private ProfessorDao professorDao = new ProfessorDao();
    private CriptografiaUtil criptografiaUtil = new CriptografiaUtil();
    private String confirmaSenha;

    public ProfessorRegistrarBean() {
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public ProfessorDao getProfessorDao() {
        return professorDao;
    }

    public void setProfessorDao(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }

    public CriptografiaUtil getCriptografiaUtil() {
        return criptografiaUtil;
    }

    public void setCriptografiaUtil(CriptografiaUtil criptografiaUtil) {
        this.criptografiaUtil = criptografiaUtil;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String registrarProfessor() {
        try {

            if (!professor.getSenha().equals(confirmaSenha)) {
                FacesContext f = FacesContext.getCurrentInstance();
                f.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "As senhas informadas não coincidem.", ""));
            } else {

                if (professorDao.isExistenteMatricula(professor.getMatricula())) {
                    FacesContext f = FacesContext.getCurrentInstance();
                    f.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Essa matrícula já pertence a um usuário.", ""));

                } else {
                    if (professorDao.isExistenteEmail(professor.getEmail())) {
                        FacesContext f = FacesContext.getCurrentInstance();
                        f.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Esse e-mail já pertence a um usuário.", ""));

                    } else {

                        professor.setSenha(criptografiaUtil.criptografaSenha(professor.getSenha()));
                        professorDao.registrar(professor);
                        FacesContext f = FacesContext.getCurrentInstance();
                        f.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor registrado com sucesso.", ""));
                        limpar();

                    }

                }

            }

        } catch (Exception e) {
            FacesContext f = FacesContext.getCurrentInstance();
            f.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao tentar registrar professor.", ""));

        }
        return null;
    }

    public String limpar() {
        professor = new Professor();
        professorDao = new ProfessorDao();
        criptografiaUtil = new CriptografiaUtil();
        confirmaSenha = "";
        return "registrarProfessor";
    }

    public String direcionaPesquisarProfessor() {
        return "pesquisarProfessor";
    }

}
