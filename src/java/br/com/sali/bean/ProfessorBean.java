package br.com.sali.bean;

import br.com.sali.dao.ProfessorDao;
import br.com.sali.modelo.Professor;
import br.com.sali.util.CriptografiaUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

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
    private String comfirmarSenha;
    private String tipoPesquisa;
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

    //############################################################################################################
    // GETS E SETS.
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

    public String getComfirmarSenha() {
        return comfirmarSenha;
    }

    public void setComfirmarSenha(String comfirmarSenha) {
        this.comfirmarSenha = comfirmarSenha;
    }

    public String getTipoPesquisa() {
        return tipoPesquisa;
    }

    public void setTipoPesquisa(String tipoPesquisa) {
        this.tipoPesquisa = tipoPesquisa;
    }

    public String getValorDePesquisa() {
        return valorDePesquisa;
    }

    public void setValorDePesquisa(String valorDePesquisa) {
        this.valorDePesquisa = valorDePesquisa;
    }

    //############################################################################################################
    /**
     * Verifica se o e-mail informado é válido.
     *
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {
        if ((email == null) || (email.trim().length() == 0)) {
            return false;
        }

        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
            professor.setMatrícula(matriculaInt);
            professor.setEmail(email);
            professor.setSenha(senha);

     //       professorDao = new ProfessorDao();
    //      professorDao.registrar(professor);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor registrado com sucesso.!", ""));

        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), ""));
        }
    }

    
    /**
     * Pesquisa um professor desejado por nome ou matrícula.
     */
    public void pesquisar() {

        if (this.tipoPesquisa.equals("matricula")) {
            if (soContemNumeros(valorDePesquisa)) {

                professoresFiltrados = professorDao.listarProfessor();

            } else {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe somente números para o campo matrícula!", ""));
            }
        } else {

            //Execute a pesquisa por nome.
            String tetxo = "TIPO DE PESQUISA: " + tipoPesquisa + "\nVALOR PESQUISADO: " + valorDePesquisa;
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, tetxo, ""));

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

    public void listar(String dados) {

        try {
            professorDao = new ProfessorDao();

            if (soContemNumeros(dados)) {
                int matriculaInteiro = Integer.parseInt(dados);
                professoresFiltrados = professorDao.listarProfessorMatricula(matriculaInteiro);
            } else {
                professoresFiltrados = professorDao.listarProfessorNome(dados);
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
