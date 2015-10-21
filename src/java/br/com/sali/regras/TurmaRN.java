package br.com.sali.regras;

import br.com.sali.dao.AlunoDAO;
import br.com.sali.dao.ProfessorDAO;
import br.com.sali.dao.TurmaDAO;
import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Professor;
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
     * @return
     */
    public boolean excluirTurma(Turma turma) {
        ProfessorDAO professorDAO = new ProfessorDAO();
        if (professorDAO.listarProfessoresPorTurmaAtual(turma).isEmpty()) {
            turmaDAO.excluir(turma);
            return true;
        } else {
            return false;
        }

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

    /**
     * Verifica se é possivel gerar o relatório da turma informada.
     *
     * @param turma
     * @return
     */
    public boolean isPossivelGerarRelatorioTurma(Turma turma) {
        AlunoDAO alunoDAO = new AlunoDAO();
        AlunoRN alunoRN = new AlunoRN();
        List<Aluno> alunosDaTurma = alunoDAO.listarAlunosPorTurma(turma);

        if (alunosDaTurma == null || alunosDaTurma.isEmpty()) {
            return false;
        } else {

            for (Aluno aluno : alunosDaTurma) {
                if (alunoRN.isPossivelGerarRelatorioDesseAluno(aluno)) {
                    return true;
                }
            }

            return false;
        }

    }

    /**
     * Lista as turmas filtradas por professor.
     *
     * @param professor
     * @return
     */
    public List<Turma> listarTurmasPorProfessor(Professor professor) {
        return this.turmaDAO.getTurmaProfessor(professor);
    }

    /**
     * Lista as turmas que possuem o professor e o filtro informado.
     *
     * @param professor
     * @param filtro
     * @return
     */
    public List<Turma> listarTurmasPorProfessorEfiltro(Professor professor, String filtro) {
        return this.turmaDAO.listarPorProfessorFiltro(professor, filtro);
    }

}
