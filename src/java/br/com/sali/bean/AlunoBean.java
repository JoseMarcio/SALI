package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
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
public class AlunoBean {

    private String nome;
    private String email;
    private String matricula;
    private Turma turma;
    private String senha;
    private String confirmarSenha;
    private String tipoPesquisa;
    private String valorDePesquisa;
    private static List<Professor> professoresResultadoPesquisa;

    //======================================================================================================================
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
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

//======================================================================================================================
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
     * Registrar um aluno.
     */
    public void registrar() {

        //Verifica se os campos senha e confirmarSenha são iguais.
        if (senha.equals("")) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe uma senha!", ""));
        } else if (confirmarSenha.equals("")) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Confirme a senha digitada!", ""));
        } else {

            // Verifica se as senhas coincidem.
            if (!senha.equals(this.confirmarSenha)) {
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

    public void atualizar() {

    }

    public void excluir() {

    }

    public void pesquisar() {
        if (this.tipoPesquisa.equals("matricula")) {
            if (soContemNumeros(valorDePesquisa)) {

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
        }
    }
    
    
    public void listar(String dados){
        try{
        
        
        }catch(Exception e){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));        
        }
    }
    
}
