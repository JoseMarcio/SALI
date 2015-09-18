package br.com.sali.util;

import java.io.UnsupportedEncodingException;
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
     * @throws java.io.UnsupportedEncodingException
     */
    public static String criptografaSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        String senhaCriptografada = hexString.toString();
        return senhaCriptografada;
    }
}
