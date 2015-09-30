package br.com.sali.regras;

import br.com.sali.dao.AlunoDAO;
import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Instituicao;
import br.com.sali.modelo.Usuario;
import br.com.sali.util.CriptografiaUtil;
import br.com.sali.util.PermissoesUtil;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Realizar Operações com o modelo Aluno de modo que sejam aplicadas as devidas
 * regras (se necessário).
 *
 * @author SALI
 */
public class AlunoRN {

    // Atributos.
    private final AlunoDAO alunoDAO;
    private final TurmaRN turmaRN;

    // Construtor.
    public AlunoRN() {
        alunoDAO = new AlunoDAO();
        turmaRN = new TurmaRN();
    }

    //============================= Gets e Sets ==================================
    /**
     * Salva um aluno. Com a senha criptografada.
     *
     * @param aluno
     * @throws java.security.NoSuchAlgorithmException
     */
    public void registrarAluno(Aluno aluno) throws NoSuchAlgorithmException{
        aluno.getUsuario().setPermissao(PermissoesUtil.getPermissaoAluno());
        aluno.getUsuario().setAtivo(true);
        
        aluno.getUsuario().setSenha(CriptografiaUtil.criptografaSenha(aluno.getUsuario().getSenha()));
        
        alunoDAO.salvar(aluno);
    }

    /**
     * Atualiza um aluno do banco de dados. Com a senha criptografada.
     *
     * @param aluno
     * @throws java.security.NoSuchAlgorithmException
     */
    public void atualizarAluno(Aluno aluno) throws NoSuchAlgorithmException {
        aluno.getUsuario().setSenha(CriptografiaUtil.criptografaSenha(aluno.getUsuario().getSenha()));
        
        alunoDAO.atualizar(aluno);
    }

    /**
     * Lista os alunos por filtro (nome ou matrícula).
     *
     * @param filtro
     * @return
     */
    public List<Aluno> listarAlunosPorFiltro(String filtro) {
        return alunoDAO.listarPorFiltro(Aluno.class, filtro);
    }

    /**
     * Listar todos os alunos.
     *
     * @return
     */
    public List<Aluno> listarTodos() {
        return alunoDAO.listarTodos(Aluno.class);
    }

    /**
     * Exclui um aluno.
     *
     * @param aluno
     */
    public void excluirAluno(Aluno aluno) {
        alunoDAO.excluir(aluno);
    }

    
    /**
     * Verifica se a matrícula informada já existe no banco de dados. Se existir
     * é retornado "true", senão existir é retornado "false".
     *
     * @param matricula
     * @return
     */
    public boolean isExisteEssaMatricula(String matricula) {
        return alunoDAO.isExisteEssaMatricula(Aluno.class, matricula);
    }

    
     /**
     * Retorna o aluno pelo Usuário.
     * @param usuario
     * @return 
     */
    public Aluno getAlunoByUsuario(Usuario usuario){
        return (Aluno) alunoDAO.getObjectByUsuario(Aluno.class, usuario);
    }
}
