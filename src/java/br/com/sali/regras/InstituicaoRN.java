package br.com.sali.regras;

import br.com.sali.dao.InstituicaoDAO;
import br.com.sali.modelo.Endereco;
import br.com.sali.modelo.Instituicao;
import br.com.sali.modelo.Usuario;
import br.com.sali.util.CriptografiaUtil;
import br.com.sali.util.PermissoesUtil;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import org.primefaces.context.RequestContext;

/**
 * Trata asRealizar Operações com o modelo Instituição de modo que sejam
 * aplicadas as devidas regras (se necessário).
 *
 * @author SALI
 */
public class InstituicaoRN {

    // Atributos.
    private final InstituicaoDAO instituicaDAO;
    private final long idMyInstituicao = 1;
    private Instituicao instituicoCadastrada;

    // Construtor.
    public InstituicaoRN() {
        instituicaDAO = new InstituicaoDAO();
        instituicoCadastrada = (Instituicao) instituicaDAO.getObjectById(Instituicao.class, idMyInstituicao);
        if (instituicoCadastrada == null) {
            try {
                criaInstituicao();
            } catch (NoSuchAlgorithmException ex) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Exceção!", ex.getMessage()));
            }
        }
    }

    //========================= Gets e Sets ====================================
    public Instituicao getInstituicoCadastrada() {
        return instituicoCadastrada;
    }

    public void setInstituicoCadastrada(Instituicao instituicoCadastrada) {
        this.instituicoCadastrada = instituicoCadastrada;
    }

    //=========================== Métodos ======================================
    /**
     * Atualiza os dados da instituição.
     *
     * @param instituicao
     * @throws java.security.NoSuchAlgorithmException
     */
    public void atualizarInstituicao(Instituicao instituicao) throws NoSuchAlgorithmException {
        instituicao.getUsuario().setSenha(CriptografiaUtil.criptografaSenha(instituicao.getUsuario().getSenha()));
        instituicaDAO.atualizar(instituicao);
    }

    /**
     * Cria uma Instituição inicial no banco de dados (com dados padrão).
     *
     * @throws java.security.NoSuchAlgorithmException
     */
    public void criaInstituicao() throws NoSuchAlgorithmException {

        Instituicao instituicao = new Instituicao();
        Endereco endereco = new Endereco();
        Usuario usuario = new Usuario();

        // Criando um usuário para a instituição.
        String email = "adm@sali.com";
        usuario.setAtivo(true);
        usuario.setEmail(email.toLowerCase());
        usuario.setSenha(CriptografiaUtil.criptografaSenha("adm"));
        usuario.setPermissao(PermissoesUtil.getPermissaoInstituicao());

        instituicao.setUsuario(usuario);

        instituicao.setEndereco(endereco);

        instituicaDAO.salvar(instituicao);

    }
    
    
    /**
     * Retorna a instituição pelo Usuário.
     * @param usuario
     * @return 
     */
    public Instituicao getInstituicaoByUsuario(Usuario usuario){
        return (Instituicao) instituicaDAO.getObjectByUsuario(Instituicao.class, usuario);
    }
}
