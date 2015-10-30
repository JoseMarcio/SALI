package br.com.sali.regras;

import br.com.sali.dao.MenssagemDao;
import br.com.sali.modelo.MenssagemTopico;
import br.com.sali.modelo.Topico;
import java.util.List;

/**
 *
 * @author SALI
 */
public class MenssagemRN {

    private final MenssagemDao menssagemDao;

    public MenssagemRN() {
        this.menssagemDao = new MenssagemDao();
    }

    /**
     * Salva uma nova menssgam no topico.
     *
     * @param menssagemTopico
     */
    public void salvar(MenssagemTopico menssagemTopico) {
        this.menssagemDao.salvar(menssagemTopico);
    }

    /**
     * Lista as menssagens por topico.
     *
     * @param topico
     * @return
     */
    public List<MenssagemTopico> listarMenssagensPorTopico(Topico topico) {
        return this.menssagemDao.listarMenssagensPorTopico(topico);
    }

}
