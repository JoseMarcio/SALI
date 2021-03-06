package br.com.sali.regras;

import br.com.sali.dao.ProfessorDAO;
import br.com.sali.dao.TurmaDAO;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Usuario;
import br.com.sali.util.CriptografiaUtil;
import br.com.sali.util.PermissoesUtil;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Realizar Operações com o modelo Professor de modo que sejam aplicadas as
 * devidas regras (se necessário).
 *
 * @author SALI
 */
public class ProfessorRN {

    // Atributos.
    private final ProfessorDAO professorDAO;
    private final TurmaDAO turmaDAO;

    // Construtor.
    public ProfessorRN() {
        professorDAO = new ProfessorDAO();
        turmaDAO = new TurmaDAO();
    }

    //========================= Métodos ====================================
    /**
     * Salva um professor. Com a senha criptografada.
     *
     * @param professor
     * @throws java.security.NoSuchAlgorithmException
     */
    public void registrarProfessor(Professor professor) throws NoSuchAlgorithmException {
        professor.getUsuario().setPermissao(PermissoesUtil.getPermissaoProfessor());

        professor.getUsuario().setAtivo(true);

        professor.getUsuario().setSenha(CriptografiaUtil.criptografaSenha(professor.getUsuario().getSenha()));

        professorDAO.salvar(professor);
    }

    /**
     * Atualiza um professor do banco de dados. Com a senha criptografada.
     *
     * @param professor
     * @throws java.security.NoSuchAlgorithmException
     */
    public void atualizarProfessor(Professor professor) throws NoSuchAlgorithmException {
        professor.getUsuario().setSenha(CriptografiaUtil.criptografaSenha(professor.getUsuario().getSenha()));

        professorDAO.atualizar(professor);
    }

    /**
     * Lista os professores por filtro (Nome ou Matrícula).
     *
     * @param filtro
     * @return
     */
    public List<Professor> listarProfessoresPorFiltro(String filtro) {
        return this.professorDAO.listarPorFiltro(Professor.class, filtro);
    }

    /**
     * Exclui um professor. Só é possível excluir um professor se ele NÃO for
     * docente em nenhuma turma. Se a exclusão for possível é retornado "true" e
     * o professor é excluído. Senão for possível, é retornado "false" e o
     * professor não é excluído.
     *
     * @param professor
     * @return
     */
    public boolean excluirProfessor(Professor professor) {
        if (turmaDAO.isExisteProfessorTurma(professor)) {
            return false;
        } else {
            professorDAO.excluir(professor);
            return true;
        }
    }

    /**
     * Verifica se a matrícula informada já existe no banco de dados. Se existir
     * é retornado "true", senão existir é retornado "false".
     *
     * @param matricula
     * @return
     */
    public boolean isExisteEssaMatricula(String matricula) {
        return professorDAO.isExisteEssaMatricula(Professor.class, matricula);
    }

    /**
     * Lista todos os professores do banco de dados.
     *
     * @return
     */
    public List<Professor> listarTodos() {
        return professorDAO.listarTodos(Professor.class);
    }

    /**
     * Retorna o professor pelo Usuário.
     *
     * @param usuario
     * @return
     */
    public Professor getProfessorByUsuario(Usuario usuario) {
        return (Professor) professorDAO.getObjectByUsuario(Professor.class, usuario);
    }
}
