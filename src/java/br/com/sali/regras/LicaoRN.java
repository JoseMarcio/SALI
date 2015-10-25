package br.com.sali.regras;

import br.com.sali.dao.LicaoDao;
import br.com.sali.modelo.Licao;
import br.com.sali.modelo.Turma;
import java.util.List;

/**
 *
 * @author SALI
 */
public class LicaoRN {

    private final LicaoDao licaoDao;

    public LicaoRN() {
        licaoDao = new LicaoDao();
    }

    /**
     * Salva uma nova lição no banco de dados.
     *
     * @param licao
     */
    public void salvarLicao(Licao licao) {
        this.licaoDao.salvar(licao);
    }

    /**
     * Lista todas as lições da turma informada.
     *
     * @param turma
     * @return
     */
    public List<Licao> listarLicoesPorTurma(Turma turma) {
        return licaoDao.listarLicoesPorTuma(turma);
    }
    
    
    /**
     * Verifica se já existe o nome da lição informado.
     * 
     * @param nome
     * @param turma
     * @return 
     */
    public boolean isExisteNomeLicao(String nome, Turma turma){
        return this.licaoDao.isExistenteNomeLição(nome, turma);
    }
}
