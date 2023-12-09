package com.gmail.ahmed;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String nomFichier = "src/main/resources/input.txt";

        try (BufferedReader lecteur = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            int somme = 0;
            while ((ligne = lecteur.readLine()) != null) {
                somme +=  getFirstLastFromLetter(ligne);
            }
            System.out.println(somme);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getFirstLastFromLetter(String ligne) {
        List<String> digitsLitters =
                Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        for (String digitLetter: digitsLitters) {
            int indiceMap = ligne.indexOf(digitLetter);
            while (indiceMap != -1) {
                treeMap.put(indiceMap, digitLetter);
                indiceMap = ligne.indexOf(digitLetter, indiceMap +1);
            }
        }

        int indxForFirst = treeMap.isEmpty() ? ligne.length() : treeMap.firstKey();
        String first = extractDigitFromFirst(ligne, indxForFirst);
        if (first == null){
            first = mappingDigit(treeMap.firstEntry().getValue());
        }

        int idnxForLast = treeMap.isEmpty() ? 0 : treeMap.lastEntry().getKey() + treeMap.lastEntry().getValue().length();
        String last = extractDigitFromLast(ligne, idnxForLast);
        if (last == null) {
            last = mappingDigit(treeMap.lastEntry().getValue());
        }

        return Integer.parseInt(first + last);
    }
    private static String extractDigitFromFirst(String ligne, int indxForFirst) {
        char[] digits = ligne.toCharArray();
        for (int i = 0; i < indxForFirst ; i++) {
            if (Character.isDigit(digits[i])){
                return String.valueOf(digits[i]);
            }
        }
        return null;
    }

    private static String extractDigitFromLast(String ligne, int idnxForLast) {
        char[] digits = ligne.toCharArray();
        for (int i = ligne.length() - 1; i >= idnxForLast ; i--) {
            if (Character.isDigit(digits[i])){
                return String.valueOf(digits[i]);
            }
        }
        return null;
    }
    private static String mappingDigit(String letters) {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");

        return map.get(letters);
    }
}