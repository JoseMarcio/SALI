package br.com.sali.regras;

import br.com.sali.dao.ProfessorDAO;
import br.com.sali.dao.TurmaDAO;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.util.CriptografiaUtil;
import java.util.List;

/**
 *
 * @author SALI
 */
public class ProfessorRN {

    private final ProfessorDAO professorDAO;
    private final TurmaDAO turmaDAO;

    public ProfessorRN() {
        professorDAO = new ProfessorDAO();
        turmaDAO = new TurmaDAO();
    }

    // Método concluido.
    /**
     * Salva um professor.
     *
     * @param professor
     */
    public void registrarProfessor(Professor professor) {
        professor.setSenha(CriptografiaUtil.criptografaSenha(professor.getSenha()));
        this.professorDAO.salvar(professor);
    }

    /**
     * Atualiza um professor do banco de dados.
     *
     * @param professor
     */
    public void atualizarProfessor(Professor professor) {
        professor.setSenha(CriptografiaUtil.criptografaSenha(professor.getSenha()));
        this.professorDAO.atualizar(professor);
    }

    /**
     * Lista os professores por filtro (Nome ou Matrícula).
     *
     * @param filtro
     * @return
     */
    public List<Professor> listarProfessores(String filtro) {
        return this.professorDAO.listar(Professor.class, filtro);
    }

    
    /**
     * Exclui um professor.
     *
     * @param professor
     * @return
     */
    public boolean excluirProfessor(Professor professor) {
       if(turmaDAO.isExisteProfessorTurma(professor)){
           return false;
       }
       else{
           this.professorDAO.excluir(professor);
           return true;
       }
    }

    
    /**
     * Verifica se o e-mail informado já existe no banco de dados.
     *
     * @param email
     * @return
     */
    public boolean isExistenteEmail(String email) {
        return this.professorDAO.isExistenteEmail(Professor.class, email);
    }

    /**
     * Verifica se a matrícula informada já existe no banco de dados.
     *
     * @param matricula
     * @return
     */
    public boolean isExisteEssaMatricula(String matricula) {
        return this.professorDAO.isExisteEssaMatricula(Professor.class, matricula);
    }
    
    
    /**
     * Lista todos os professores do banco de dados.
     * @return 
     */
    public List<Professor> listarTodosProfessores(){
        return this.professorDAO.listarTodos(Professor.class);
    }
}
