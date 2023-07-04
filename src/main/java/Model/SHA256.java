/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author danie
 */
public class SHA256 {
    /*public static void main(String[] args) {
        String input = "Hola, mundo!";
        String sha256Hash = calculateSHA256(input);
        System.out.println("SHA-256 hash: " + sha256Hash);
    }*/

    public String calculateSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String truncateString(String str, int maxLength) {
        if (str.length() > maxLength) {
            return str.substring(0, maxLength);
        }
        return str;
    }
    
    
    public String Char25(String input)
    {
        String sha256Hash = calculateSHA256(input);
        String truncatedHash = truncateString(sha256Hash, 25);
        //System.out.println("Truncated SHA-256 hash: " + truncatedHash);
        return truncatedHash;
        
    }
}
