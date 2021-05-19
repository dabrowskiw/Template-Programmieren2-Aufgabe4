package org.htw.prog2.aufgabe1.analysis;

import org.htw.prog2.aufgabe1.files.Mutation;
import org.htw.prog2.aufgabe1.files.MutationFile;
import org.htw.prog2.aufgabe1.files.SequenceFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FullLengthSequenceAnalysisTest {
    static MutationFile mutationFile;
    static String reference;

    @BeforeAll
    static void init() {
        mutationFile = new MutationFile();
        mutationFile.addDrug("D1");
        mutationFile.addDrug("D2");
        mutationFile.addDrug("D3");
        HashMap<String, Double> resistances = new HashMap<>();
        resistances.put("D1", 12.5);
        resistances.put("D2", 2.5);
        resistances.put("D3", 1.0);
        mutationFile.addMutation(new Mutation("2M", resistances));
        resistances = new HashMap<>();
        resistances.put("D1", 10.5);
        resistances.put("D2", 20.0);
        resistances.put("D3", 1.0);
        mutationFile.addMutation(new Mutation("2L,3M", resistances));
        reference = "MKYLM";
    }

    @Test
    void calculateResistancesNoResistances() {
        SequenceFile sequenceFile = new SequenceFile();
        sequenceFile.addSequence("MKYLM");
        HashMap<String, Double> expected = new HashMap<>();
        expected.put("D1", 0.0);
        expected.put("D2", 0.0);
        expected.put("D3", 0.0);
        assertEquals(expected, new FullLengthSequenceAnalysis(reference, sequenceFile, mutationFile).getResistances());
    }

    @Test
    void calculateResistancesOneResistance() {
        SequenceFile sequenceFile = new SequenceFile();
        sequenceFile.addSequence("MKYLM");
        sequenceFile.addSequence("MMYLM");
        HashMap<String, Double> expected = new HashMap<>();
        expected.put("D1", 12.5);
        expected.put("D2", 2.5);
        expected.put("D3", 1.0);
        assertEquals(expected, new FullLengthSequenceAnalysis(reference, sequenceFile, mutationFile).getResistances());
    }

    @Test
    void calculateResistancesTwoResistances() {
        SequenceFile sequenceFile = new SequenceFile();
        sequenceFile.addSequence("MKYLM");
        sequenceFile.addSequence("MMYLM");
        sequenceFile.addSequence("MLMLM");
        HashMap<String, Double> expected = new HashMap<>();
        expected.put("D1", 12.5);
        expected.put("D2", 20.0);
        expected.put("D3", 1.0);
        assertEquals(expected, new FullLengthSequenceAnalysis(reference, sequenceFile, mutationFile).getResistances());
    }
}