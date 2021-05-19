package org.htw.prog2.aufgabe1.analysis;

import org.htw.prog2.aufgabe1.files.MutationFile;
import org.htw.prog2.aufgabe1.files.Mutation;
import org.htw.prog2.aufgabe1.files.SequenceFile;

import java.util.HashMap;

public abstract class SequenceAnalysis {

    public SequenceAnalysis(String reference, SequenceFile sequences, MutationFile mutations) {
    }

    public abstract void calculateResistances();

    public HashMap<String, Double> getResistances() {
        return null;
    }

    public String getBestDrug() {
        return "";
    }

    public String getDrugDescriptions() {
        return "";
    }
}
