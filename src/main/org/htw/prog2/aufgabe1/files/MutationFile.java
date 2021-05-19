package org.htw.prog2.aufgabe1.files;

import java.util.LinkedList;

public class MutationFile implements HIVFile {
    LinkedList<String> drugs = new LinkedList<>();
    LinkedList<Mutation> mutations = new LinkedList<>();

    public void addDrug(String drug) {
        drugs.add(drug);
    }

    public LinkedList<String> getDrugs() {
        return drugs;
    }

    public void addMutation(Mutation variant) {
        mutations.add(variant);
    }

    public LinkedList<Mutation> getMutations() {
        return mutations;
    }

    public int getNumberOfMutations() {
        return mutations.size();
    }
}
