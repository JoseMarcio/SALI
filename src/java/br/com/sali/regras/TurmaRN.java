package br.com.sali.regras;

import br.com.sali.dao.TurmaDAO;
import br.com.sali.modelo.Turma;
import java.util.List;

/**
 * Realizar Operações com o modelo Turma de modo que sejam aplicadas as devidas
 * regras (se necessário).
 *
 * @author SALI
 */
public class TurmaRN {

    // Atributos.
    private final TurmaDAO turmaDAO;

    // Construtor.
    public TurmaRN() {
        turmaDAO = new TurmaDAO();
    }

    //=========================== Métodos ======================================
    /**
     * Salva uma turma.
     *
     * @param turma
     */
    public void registrarTurma(Turma turma) {
        turmaDAO.salvar(turma);
    }

    /**
     * Atualiza uma Turma do banco de dados.
     *
     * @param turma
     */
    public void atualizarTurma(Turma turma) {
        turmaDAO.atualizar(turma);
    }

    /**
     * Lista as turmas por filtro (nome).
     *
     * @param filtro
     * @return
     */
    public List<Turma> listarTurmasPorFiltro(String filtro) {
        return turmaDAO.listarPorFiltro(Turma.class, filtro);
    }

    /**
     * Exclui uma turma.
     *
     * @param turma
     */
    public void excluirTurma(Turma turma) {
        turmaDAO.excluir(turma);
    }

    /**
     * Verfica se o nome informado já existe no banco de dados. Se existir é
     * retornado "true", senão existir é retornado "false".
     *
     * @param nome
     * @return
     */
    public boolean isExistenteNome(String nome) {
        return turmaDAO.isExistenteNomeTurma(nome);
    }

    /**
     * Lista todas as turmas.
     *
     * @return
     */
    public List<Turma> listarTodas() {
        return turmaDAO.listarTodos(Turma.class);
    }
}
