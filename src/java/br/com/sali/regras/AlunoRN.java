package br.com.sali.regras;

import br.com.sali.dao.AlunoDAO;
import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Turma;
import br.com.sali.util.CriptografiaUtil;
import java.util.List;

/**
 *
 * @author SALI
 */
public class AlunoRN {

    private AlunoDAO alunoDAO;
    private CriptografiaUtil criptografiaUtil;
    private TurmaRN turmaRN;

    public AlunoRN() {
        alunoDAO = new AlunoDAO();
        criptografiaUtil = new CriptografiaUtil();
        turmaRN = new TurmaRN();
    }

    /**
     * Salva um aluno.
     *
     * @param aluno
     */
    public void registrarAluno(Aluno aluno) {
        aluno.setSenha(criptografiaUtil.criptografaSenha(aluno.getSenha()));
        alunoDAO.salvar(aluno);
    }

    /**
     * Atualiza um aluno do banco de dados.
     *
     * @param aluno
     */
    public void atualizarAluno(Aluno aluno) {
        alunoDAO.atualizar(aluno);
    }

    /**
     * Lista os alunos solicitados.
     *
     * @param filtro
     * @return
     */
    public List<Aluno> listarAlunos(String filtro) {
        return alunoDAO.listar(Aluno.class, filtro);
    }

    
    /**
     * Listar todos os alunos.
     * @return 
     */
    public List<Aluno> listarTodos(){
        return alunoDAO.listarTodos(Aluno.class);
    }
    
    
    
    /**
     * Exclui um aluno, para isso o mesmo é retirado da lista de alunos que a
     * sua turma possui.
     *
     *
     * @param aluno
     */
    public void excluirAluno(Aluno aluno) {
                alunoDAO.excluir(aluno);
    }

    /**
     * Verifica se o e-mail informado já existe no banco de dados.
     *
     * @param email
     * @return
     */
    public boolean isExistenteEmail(String email) {
        return alunoDAO.isExistenteEmail(Aluno.class, email);
    }

    /**
     * Verifica se a matrícula informada já existe no banco de dados.
     *
     * @param matricula
     * @return
     */
    public boolean isExisteEssaMatricula(String matricula) {
        return alunoDAO.isExisteEssaMatricula(Aluno.class, matricula);
    }

}
