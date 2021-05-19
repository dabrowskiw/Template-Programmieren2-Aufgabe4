package org.htw.prog2.aufgabe1.files;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mutation {
    String variant;
    HashMap<String, Double> resistances;

    public Mutation(String variant, HashMap<String, Double> resistances) {
        this.variant = variant;
        this.resistances = resistances;
    }

    public String getVariant() {
        return variant;
    }

    public HashMap<String, Double> getResistances() {
        return resistances;
    }

    public String getSequence(String reference) {
        return "";
    }
}
