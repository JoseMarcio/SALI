package br.com.sali.util;

/**
 *
 * @author SALI
 */
public class PermissoesUtil {

    /**
     * Retorna um uma permissão do tipo: Instituição.
     * @return 
     */
    public static String getPermissaoInstituicao() {
        return "ROLE_INSTITUICAO";
    }

    /**
     * Retorna um uma permissão do tipo: Professor.
     * 
     * @return 
     */
    public static String getPermissaoProfessor() {
        return "ROLE_PROFESSOR";
    }

    /**
     * Retorna um uma permissão do tipo: Aluno.
     * 
     * @return 
     */
    public static String getPermissaoAluno() {
        return "ROLE_ALUNO";
    }

}
