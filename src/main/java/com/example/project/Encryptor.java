package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        int col = (int)Math.ceil(messageLen * 1.0 / rows);
        if (col == 0) {
            return 1;
        }
        else {
            return col;
        }
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] encryptArray = new String[rows][determineColumns(message.length(), rows)];
        int idx = 0;
        for (String[] i : encryptArray) {
            for (int k = 0; k < i.length; k++) {
                if (idx < message.length()) {
                    i[k] = message.substring(idx, idx+1);
                }
                else {
                    i[k] = "=";
                }
                idx++;
            }
        }
        return encryptArray;
    }

    public static String encryptMessage(String message, int rows){
        String[][] encryptArr = generateEncryptArray(message, rows);
        String returnStr = "";
        for (int k = encryptArr[0].length - 1; k >= 0; k--) {
            for (String[] i : encryptArr) {
                returnStr += i[k];
            }
        }
        return returnStr;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String[][] decryptArr = generateEncryptArray(encryptedMessage, rows);
        int idx = 0;
        String returnStr = "";
        for (int k = decryptArr[0].length - 1; k >= 0; k--) {
            for (String[] i : decryptArr) {
                if (encryptedMessage.length() != 0) {
                    i[k] = encryptedMessage.substring(0, 1);
                    encryptedMessage = encryptedMessage.substring(1);
                }
                else {
                    return "";
                }
            }
        }
        for (String[] i : decryptArr) {
            for (int k = 0; k < decryptArr[0].length; k++) {
                returnStr += i[k];
            }
        }
        if (returnStr.indexOf("=") != -1) {
            return returnStr.substring(0,returnStr.indexOf("="));
        }
        return returnStr;
    }
}