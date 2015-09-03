package br.com.sali.testes;

import br.com.sali.dao.AlunoDao;
import br.com.sali.dao.ProfessorDao;
import br.com.sali.dao.TurmaDao;
import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;

/**
 *
 * @author SALI
 */
public class TesteAluno {
    
    
    public static void main(String[] args) throws Exception {
        
        // CRIAR PROFESSOR, TURMA E ALUNO.
        
        Professor professor1 = new Professor();
        Turma turma1 = new Turma();
        Aluno aluno1 = new Aluno();
        ProfessorDao pfDao = new ProfessorDao();
        TurmaDao tmDao = new TurmaDao();
        AlunoDao alDao = new AlunoDao();
        
        
        
        professor1.setNomeCompleto("JOSÉ NAILTON SHUAISTAIGER");
        professor1.setEmail("shuaysteiger.net@futebol.com");
        professor1.setMatricula(2016);
        professor1.setSenha("12345");
        
        
        turma1.setNome("1° ANO - E");
        turma1.setProfessor(professor1);
        
        aluno1.setNomeCompleto("JOÃO RAIMUNDO");
        aluno1.setMatricula(12345);
        aluno1.setEmail("joao.raimundo@jjsali.com");
        aluno1.setSenha("joaozim");
        aluno1.setTurma(turma1);
        
        pfDao.registrar(professor1);
        tmDao.registrar(turma1);
        alDao.registrar(aluno1);
        
    }
}
