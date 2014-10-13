package com.uft.lattes.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

/**
 * Classe com diversas funções úteis em todo o contexto da aplicação
 *
 * @author Marcelo Claudio
 * @version 2.0
 */
public abstract class ApplicationUtilies {

    /**
     * Obtém um Entity Manager presente na sessão
     *
     * @since 1.0
     * @return Entity Manager presente na sessão
     */
    public static EntityManager catchEntityManager() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        EntityManager entityManager = (EntityManager) FacesContext.getCurrentInstance().
                getApplication().getELResolver().getValue(elContext, null, "entityManager");
        return entityManager;
    }


    public static String generateMD5(String aToken) {
        MessageDigest aMessageDigest = null;
        try {
            aMessageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Algoritmo solicitado não encontrado.");
        }
        BigInteger hash = new BigInteger(1, aMessageDigest.digest(aToken.getBytes()));
        String aMD5 = hash.toString(16);
        return aMD5;
    }

    public static String removerAcentos(String acentuada) {
        //System.out.println("acentuada: " + acentuada);
        CharSequence cs = new StringBuilder(acentuada);
        String normalizada = Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        //normalizada = normalizada.replace(" ", "-");
//        normalizada = normalizada.replaceAll("\\.", " ");
//        normalizada = normalizada.replaceAll("\\/", " ");
//        normalizada = normalizada.replaceAll("\\+", " ");
//        normalizada = normalizada.replaceAll("\\:", " ");
//        normalizada = normalizada.replaceAll("\\=", " ");
//        normalizada = normalizada.replaceAll("\\,", " ");
//        normalizada = normalizada.replaceAll("\\'", " ");
//        normalizada = normalizada.replaceAll("\"", " ");
//        normalizada = normalizada.replaceAll("\\)", " ");
        normalizada = normalizada.replaceAll("\\W", " "); 
        normalizada = normalizada.replaceAll("\\d", " ");
        normalizada = normalizada.toLowerCase();
        return normalizada;
    }
    
     public static byte[] readFully(InputStream stream) throws IOException {
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int bytesRead;
        while ((bytesRead = stream.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }

    public static String getextension(String aFilename) {
        return aFilename.substring(aFilename.lastIndexOf('.') + 1);
    }

    public static void informationMessage(String title, String content) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(title, content));
    }
}