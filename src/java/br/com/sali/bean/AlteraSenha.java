package br.com.sali.bean;

import br.com.sali.modelo.Usuario;
import br.com.sali.regras.UsuarioRN;
import br.com.sali.util.CriptografiaUtil;
import br.com.sali.util.ValidacoesUtil;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean
public class AlteraSenha {

    private String senha;
    private String novaSenha;
    private String confirmacaoSenha;
    private Usuario usuario;
    private UsuarioRN usuarioRN;

    
    @PostConstruct
    public void init() {
        senha = "";
        novaSenha = "";
        confirmacaoSenha = "";
        
        
        usuarioRN = new UsuarioRN();

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();

        String emailUsuario = external.getRemoteUser();

        usuario = usuarioRN.getUsuarioByEmail(emailUsuario);

    }

    // =========================================================================
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    // =========================================================================
    
    /**
     * Abre um diálogo para alteração de senha do usuário.
     */
    public void abrirAlterarSenha() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 400);

        RequestContext.getCurrentInstance().openDialog("/professor-aluno/alterar-senha", options, null);
    }
    
    
    /**
     * Fecha o diálogo de alteração de senha do usuário.
     */
    public void fechadialogo(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    /**
     * Exibe mensagem de sucesso após atualizar a senha do usuário.
     */
    public void exibeMensagem(){
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Sucesso!", "Atualização concluída com sucesso."));
    }
    
    /**
     * Reinicia os atributos do bean.
     */
    public void limpar(){
        init();
    }
    
    
    /**
     * Altera a senha do usuário.
     */
    public void salvarAlteracoes() {
        if (ValidacoesUtil.temEspacoNoTexto(senha)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha inválida.", ""));

        } else if (ValidacoesUtil.temEspacoNoTexto(novaSenha)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nova senha inválida.", ""));

        } else if (ValidacoesUtil.temEspacoNoTexto(confirmacaoSenha)) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Confirmação de senha inválida.", ""));

        } else {
            
            try {
                senha = CriptografiaUtil.criptografaSenha(senha);
            } catch (NoSuchAlgorithmException ex) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Exceção!", ex.getMessage()));
            }

            if (!senha.equals(usuario.getSenha())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha incorreta.", ""));
            } else if (!novaSenha.equals(confirmacaoSenha)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha e confirmação de senha incorretas.", ""));
            } else {
                usuario.setSenha(novaSenha);
                try {
                    usuarioRN.alterarSenha(usuario);
                    limpar();
                    fechadialogo();
                    
                    
                    
                    
                } catch (NoSuchAlgorithmException ex) {
                    RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Exceção!", ex.getMessage()));
                }
            }
        }
    }

}
