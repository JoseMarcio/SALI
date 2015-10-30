package br.com.sali.regras;

import br.com.sali.dao.TopicoDao;
import br.com.sali.modelo.Topico;
import br.com.sali.modelo.Turma;
import java.util.List;

/**
 *
 * @author SALI
 */
public class TopicoRN {

    private final TopicoDao topicoDao;

    public TopicoRN() {
        this.topicoDao = new TopicoDao();
    }

    /**
     * Salva um tópico relacionado a uma turma.
     *
     * @param topico
     */
    public void salvar(Topico topico) {
        this.topicoDao.salvar(topico);
    }

    /**
     * Lista os topicos por tuma.
     *
     * @param turma
     * @return
     */
    public List<Topico> listarTopicosPorTurma(Turma turma) {
        return this.topicoDao.listarTopicosPorTurma(turma);
    }

    /**
     * Verifica se já existe algum tópico com o mesmo nome que está sendo
     * informado.
     *
     * @param nome
     * @param turma
     * @return
     */
    public boolean isExiteNomeTopico(String nome, Turma turma) {
        return this.topicoDao.isExistenteNomeTopico(nome, turma);
    }

    /**
     * Pegar tópico por id.
     *
     * @param id
     * @return
     */
    public Topico pegarTopicoPorId(Long id) {
        return (Topico) this.topicoDao.getObjectById(Topico.class, id);
    }
}
