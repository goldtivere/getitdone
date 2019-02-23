/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.getitdone;


import java.util.Random;

/**
 *
 * @author Gold
 */
public class RandomWordEquivalent {
    //this method generates validation code for the phonenumber

    public String generateRandom() {
        Random rnd = new Random();
        int a = 1000 + rnd.nextInt(9000);

        return String.valueOf(a);
    }
    //returns array string of word equivalent

    public static String[] values() {
        String values[] = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        return values;

    }
    // returns word equivalent of code generated

    public String wordEquivalent(String random) {
        String[] p = random.split("(?!^)");
        StringBuilder sdp = new StringBuilder();

        for (String n : p) {
            if (n.equalsIgnoreCase("0")) {
                sdp.append(values()[0]).append(", ");
            } else if (n.equalsIgnoreCase("1")) {
                sdp.append(values()[1]).append(", ");
            } else if (n.equalsIgnoreCase("2")) {
                sdp.append(values()[2]).append(", ");
            } else if (n.equalsIgnoreCase("3")) {
                sdp.append(values()[3]).append(", ");
            } else if (n.equalsIgnoreCase("4")) {
                sdp.append(values()[4]).append(", ");
            } else if (n.equalsIgnoreCase("5")) {
                sdp.append(values()[5]).append(", ");
            } else if (n.equalsIgnoreCase("6")) {
                sdp.append(values()[6]).append(", ");
            } else if (n.equalsIgnoreCase("7")) {
                sdp.append(values()[7]).append(", ");
            } else if (n.equalsIgnoreCase("8")) {
                sdp.append(values()[8]).append(", ");
            } else if (n.equalsIgnoreCase("9")) {
                sdp.append(values()[9]).append(", ");
            }

            System.out.println(n + " Hippee");
        }
        return sdp.toString();
    }

}
