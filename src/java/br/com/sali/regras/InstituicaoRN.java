package br.com.sali.regras;

import br.com.sali.dao.InstituicaoDAO;
import br.com.sali.modelo.Endereco;
import br.com.sali.modelo.Instituicao;
import br.com.sali.util.CriptografiaUtil;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Trata asRealizar Operações com o modelo Instituição de modo que sejam aplicadas as devidas
 * regras (se necessário).
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
     * @throws java.io.UnsupportedEncodingException
     */
    public void atualizarInstituicao(Instituicao instituicao) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        instituicao.setSenha(CriptografiaUtil.criptografaSenha(instituicao.getSenha()));
        instituicaDAO.atualizar(instituicao);
    }

    /**
     * Verifica se o e-mail informado já existe no banco de dados. Se existir 
     * é retornado "true", senão existir é retornado "false".
     *
     * @param email
     * @return
     */
    public boolean isExistenteEmail(String email) {
        return instituicaDAO.isExistenteEmail(Instituicao.class, email);
    }
    
    
    /**
     * Cria uma Instituição inicial no banco de dados (com dados padrão).
     */
    public void criaInstituicao(){
        Instituicao instituicao = new Instituicao();
        Endereco endereco = new Endereco();
        
        instituicao.setEmail("admin@sali.com");
        instituicao.setSenha("admin");
        instituicao.setNome("Ainda não existe instituição cadastrada.");
       
        endereco.setInstituicao(instituicao);
        instituicao.setEndereco(endereco);
        
        instituicaDAO.salvar(instituicao);
    }
}
