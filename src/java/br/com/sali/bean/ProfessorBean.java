package br.com.sali.bean;

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
    private String comfirmarSenha;
    private String pesquisaMatricula;
    private String pesquisaNome;
    private String tipoPesquisa;
    private String valorDePesquisa;

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

    public String getPesquisaMatricula() {
        return pesquisaMatricula;
    }

    public void setPesquisaMatricula(String pesquisaMatricula) {
        this.pesquisaMatricula = pesquisaMatricula;
    }

    public String getPesquisaNome() {
        return pesquisaNome;
    }

    public void setPesquisaNome(String pesquisaNome) {
        this.pesquisaNome = pesquisaNome;
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

                        //Comando do botão.
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

    public void atualizar() {

    }

    public void excluir() {

    }

    /**
     * Pesquisa um professor desejado por nome ou matrícula.
     */    
    public void pesquisar() {

        if (this.tipoPesquisa.equals("matricula")) {
            if (soContemNumeros(valorDePesquisa)) {
                
                //Execute a pesquisa por matrícula.
                
            } else {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe somente números para o campo matrícula!", ""));
            }
        } else {
            
            //Execute a pesquisa por nome.

        }

    }

}
