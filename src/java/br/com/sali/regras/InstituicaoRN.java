package br.com.sali.regras;

import br.com.sali.dao.InstituicaoDAO;
import br.com.sali.modelo.Instituicao;

/**
 *
 * @author SALI
 */
public class InstituicaoRN {
    
    private InstituicaoDAO instituicaDAO;
    private final long idMyInstituicao = 1;
    private Instituicao instituicoCadastrada;

    public InstituicaoRN() {
        instituicaDAO = new InstituicaoDAO();
        instituicoCadastrada = instituicaDAO.getInstituicaoById(idMyInstituicao);
    }

    public Instituicao getInstituicoCadastrada() {
        return instituicoCadastrada;
    }

    public void setInstituicoCadastrada(Instituicao instituicoCadastrada) {
        this.instituicoCadastrada = instituicoCadastrada;
    }
    
    
    /**
     * Atualiza os dados da instituição.
     * @param instituicao
     */
    public void atualizarInstituicao(Instituicao instituicao){
        instituicaDAO.atualizar(instituicao);
    }
    
    /**
     * Verifica se o e-mail informado já existe no banco de dados.
     *
     * @param email
     * @return
     */
    public boolean isExistenteEmail(String email) {
        return instituicaDAO.isExistenteEmail(Instituicao.class, email);
    }
}
