package ru.vkd.one;

import javax.crypto.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String str = "";
        String allStr = "";
        Cipher cipher = Cipher.getInstance("AES");
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        SecretKey key = kgen.generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, key);


        try (BufferedReader read = new BufferedReader(new FileReader("src\\test.txt"))) {
            while ((str = read.readLine()) != null) {

                allStr = allStr + str;

                byte[] bytes = cipher.doFinal(allStr.getBytes());
                /*
                for (byte b : bytes) {
                    System.out.print(b);
                }
                */
                enCrypt(bytes,key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enCrypt(byte[] bytes, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
            Cipher deCipher = Cipher.getInstance("AES");
            deCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] deBytes = deCipher.doFinal(bytes);
                for (byte b : deBytes) {
                    System.out.print((char) b);
                }
    }
}


