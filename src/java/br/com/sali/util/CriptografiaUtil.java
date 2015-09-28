package br.com.sali.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe que trata da criptografia de senha dos usuários.
 *
 * @author SALI
 */
public class CriptografiaUtil {

    /**
     * Recebe uma senha e criptografa ela. Logo após criptografar, retorna a
     * senha já com a criptorafia.
     *
     * O hash utilizado para a criptografia: SHA-256.
     *
     * @param senha
     * @return
     * @throws java.security.NoSuchAlgorithmException
     */
    public static String criptografaSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(senha.getBytes());
        byte[] bytes = md.digest();

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }
}
