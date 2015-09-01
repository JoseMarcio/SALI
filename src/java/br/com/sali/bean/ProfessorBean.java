package br.com.sali.bean;

import br.com.sali.dao.ProfessorDao;
import br.com.sali.modelo.Professor;
import br.com.sali.util.CriptografiaUtil;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author SALI
 */
@ManagedBean
@RequestScoped
public class ProfessorBean {

    private String nome;
    private String matricula;
    private String email;
    private String senha;
    private String valorDePesquisa;
    private Professor professorSelecionado;
    private List<Professor> professoresFiltrados;
    private ProfessorDao professorDao;

    public Professor getProfessorSelecionado() {
        return professorSelecionado;
    }

    public void setProfessorSelecionado(Professor professorSelecionado) {
        this.professorSelecionado = professorSelecionado;
    }

    public List<Professor> getProfessoresFiltrados() {
        return professoresFiltrados;
    }

    public void setProfessoresFiltrados(List<Professor> professoresFiltrados) {
        this.professoresFiltrados = professoresFiltrados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public ProfessorDao getProfessorDao() {
        return professorDao;
    }

    public void setProfessorDao(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getValorDePesquisa() {
        return valorDePesquisa;
    }

    public void setValorDePesquisa(String valorDePesquisa) {
        this.valorDePesquisa = valorDePesquisa;
    }

    /**
     * Verifica se a String contém somente números;
     *
     * @param texto
     * @return
     */
    public static boolean soContemNumeros(String texto) {
        if (texto == null) {
            return false;
        }
        for (char letra : texto.toCharArray()) {
            if (letra < '0' || letra > '9') {
                return false;
            }
        }
        return true;

    }

    /**
     * Registra um novo professor no banco de dados.
     */
    public void registrar() {
        try {
            int matriculaInt = Integer.parseInt(matricula);
            CriptografiaUtil criptografia = new CriptografiaUtil();
            senha = criptografia.criptografaSenha(senha);

            Professor professor = new Professor();
            professor.setNomeCompleto(nome);
            professor.setMatricula(matriculaInt);
            professor.setEmail(email);
            professor.setSenha(senha);

            professorDao = new ProfessorDao();
            professorDao.registrar(professor);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor registrado com sucesso.!", ""));

        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), ""));
        }
    }

    public void excluir(Professor professor) {
        try {
            professorDao = new ProfessorDao();
            professorDao.excluir(professor);
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
        }
    }

    public void listar() {

        try {
            professorDao = new ProfessorDao();

            if (soContemNumeros(valorDePesquisa)) {
                int matriculaInteiro = Integer.parseInt(valorDePesquisa);
                professoresFiltrados = professorDao.listarProfessorMatricula(matriculaInteiro);
            } else {
                professoresFiltrados = professorDao.listarProfessorNome(valorDePesquisa);
            }

        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
        }
    }

    public void atualizar() {
        try {
        } catch (Exception e) {
        }
    }

}
