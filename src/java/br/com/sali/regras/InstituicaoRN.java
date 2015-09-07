package br.com.sali.regras;

import br.com.sali.dao.InstituicaoDAO;
import br.com.sali.modelo.Instituicao;

/**
 *
 * @author SALI
 */
public class InstituicaoRN {
    
    private InstituicaoDAO instituicaDAO;

    public InstituicaoRN() {
        instituicaDAO = new InstituicaoDAO();
    }
    
    
    /**
     * Atualiza os dados da instituição.
     * @param instituicao
     */
    public void atualizarInstituicao(Instituicao instituicao){
        instituicaDAO.atualizar(instituicao);
    }
}
