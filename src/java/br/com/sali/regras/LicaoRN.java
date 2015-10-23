package br.com.sali.regras;

import br.com.sali.dao.LicaoDao;
import br.com.sali.modelo.Licao;

/**
 *
 * @author SALI
 */
public class LicaoRN {

    private final LicaoDao licaoDao;
    
    public LicaoRN(){
        licaoDao = new LicaoDao();
    }

    
    public void salvarLicao(Licao licao){
        this.licaoDao.salvar(licao);
    }
}

