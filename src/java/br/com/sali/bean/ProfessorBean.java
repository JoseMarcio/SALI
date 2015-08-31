package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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

    private List<Professor> professoresFiltrados;

    private Professor professor;
    private Professor professor1;
    private Professor professor2;
    private Professor professor3;
    private Professor professor4;
    private Professor professor5;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
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

        //Verifica se os campos senha e confirmarSenha são iguais.
        if (senha.equals("")) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe uma senha!", ""));
        } else if (comfirmarSenha.equals("")) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Confirme a senha digitada!", ""));
        } else {

            // Verifica se as senhas coincidem.
            if (!senha.equals(this.comfirmarSenha)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não coincidem!", ""));
            } else {

                if (soContemNumeros(matricula)) {

                    if (isEmailValid(email)) {

                        //VERIFICAR SE JA TEM ALGUM COM O MESMO EMAIL
                        // VERIFICA SE JA TEM ALGUM COM O MESMO MATRICULA
                        String tetxo = "NOME: " + nome + "\nEmal: " + email + "\nMATRICULA: " + matricula + "\nSENHA: " + senha;
                        FacesContext facesContext = FacesContext.getCurrentInstance();
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, tetxo, ""));

                    } else {
                        FacesContext facesContext = FacesContext.getCurrentInstance();
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe um E-mail válido!", ""));
                    }

                } else {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe somente números para o campo matrícula!", ""));
                }
            }

        }

    }

    public void atualizar(Professor professor) {

    }

    public void excluir(Professor professor) {

    }

    /**
     * Pesquisa um professor desejado por nome ou matrícula.
     */
    public void pesquisar() {

        if (this.tipoPesquisa.equals("matricula")) {
            if (soContemNumeros(valorDePesquisa)) {

                professor = new Professor();
                professor1 = new Professor();
                professor2 = new Professor();
                professor3 = new Professor();
                professor4 = new Professor();
                professor5 = new Professor();

                professor.setMatrícula(7667567);
                professor.setNomeCompleto("Joao");
                professoresFiltrados.add(professor);

                professor1.setMatrícula(1050);
                professor1.setNomeCompleto("Maria");
                professoresFiltrados.add(professor);

                professor2.setMatrícula(67567);
                professor2.setNomeCompleto("Zé");
                professoresFiltrados.add(professor);

                professor3.setMatrícula(255);
                professor4.setNomeCompleto("Jonas");
                professoresFiltrados.add(professor);

                professor4.setMatrícula(123);
                professor5.setNomeCompleto("Raimunda");
                professoresFiltrados.add(professor);

                //Execute a pesquisa por matrícula.
                String tetxo = "TIPO DE PESQUISA: " + tipoPesquisa + "\nVALOR PESQUISADO: " + valorDePesquisa;
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, tetxo, ""));

            } else {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe somente números para o campo matrícula!", ""));
            }
        } else {

            //Execute a pesquisa por nome.
            String tetxo = "TIPO DE PESQUISA: " + tipoPesquisa + "\nVALOR PESQUISADO: " + valorDePesquisa;
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, tetxo, ""));

            professor = new Professor();
            professor1 = new Professor();
            professor2 = new Professor();
            professor3 = new Professor();
            professor4 = new Professor();
            professor5 = new Professor();

            professor.setMatrícula(7667567);
            professor.setNomeCompleto("Joao");
            professoresFiltrados.add(professor);

            professor1.setMatrícula(1050);
            professor1.setNomeCompleto("Maria");
            professoresFiltrados.add(professor);

            professor2.setMatrícula(67567);
            professor2.setNomeCompleto("Zé");
            professoresFiltrados.add(professor);

            professor3.setMatrícula(255);
            professor4.setNomeCompleto("Jonas");
            professoresFiltrados.add(professor);

            professor4.setMatrícula(123);
            professor5.setNomeCompleto("Raimunda");
            professoresFiltrados.add(professor);
        }

    }

    public void abrirDialogo() {

        Map<String, Object> opcoes = new HashMap();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("draggable", false);
        opcoes.put("contentHeight", 500);

        RequestContext.getCurrentInstance().openDialog("alterarExcluirProfessor", opcoes, null);

    }

}
