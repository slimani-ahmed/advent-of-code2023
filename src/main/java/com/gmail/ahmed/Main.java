package com.gmail.ahmed;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String nomFichier = "src/main/resources/input.txt";

        try (BufferedReader lecteur = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            int somme = 0;
            while ((ligne = lecteur.readLine()) != null) {
                //System.out.println(ligne);
                somme +=  getFirstLast(ligne);
            }
            System.out.println(somme);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getFirstLast(String ligne) {
        char[] digits = new char[ligne.toCharArray().length];
        int idnx = 0;
        for (char caractere : ligne.toCharArray()) {
            if (Character.isDigit(caractere)){
                digits[idnx] = caractere;
                idnx++;
            }
        }
        return Integer.parseInt(String.format("%s%s", digits[0], digits[idnx - 1]));
    }
}