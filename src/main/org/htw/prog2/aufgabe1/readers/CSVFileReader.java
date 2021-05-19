package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.MutationFile;
import org.htw.prog2.aufgabe1.files.Mutation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CSVFileReader implements MutationFileReader {

    public MutationFile readFile(String filename) throws IOException, FileFormatException {
        MutationFile res = new MutationFile();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            // Ignore empty lines
            if(line.strip().length() == 0) {
                continue;
            }
            // Ignore lines starting with #
            if(line.charAt(0) == '#')
                continue;
            if(line.startsWith("\"Mutation Patterns\"")) {
                for(String drug : parseDrugs(line)) {
                    res.addDrug(drug);
                }
                firstLine = false;
                continue;
            }
            // If this happens, then the first line was not a definition line.
            if(firstLine) {
                throw new FileFormatException("The first line of the mutation patterns CSV file must be a definition line.");
            }
            String[] data = line.split(";");
            if(2 + res.getDrugs().size() != data.length) {
                throw new FileFormatException("Each line must have the same number of columns.");
            }
            if(data.length < 3)
                continue;
            else {
                String pattern = data[0];
                HashMap<String, Double> sus = parseSusceptibilities(data, res.getDrugs());
                res.addMutation(new Mutation(pattern, sus));
            }
        }
        return res;
    }

    /**
     * Parst die Definitionszeile.
     * @param line Definitionszeile aus der CSV-Datei
     * @return Liste der Medikamentennamen aus der Definitionszeile
     */
    public static List<String> parseDrugs(String line) throws FileFormatException {
        LinkedList<String> res = new LinkedList<>();
        String[] data = line.split(";");
        if(!data[0].equals("\"Mutation Patterns\"") || !data[1].equals("\"Number of Sequences\"")) {
            throw new FileFormatException("First elements of definition line should be \"Mutation Patterns\" and " +
                    "\"Number of Sequences\"");
        }
        for(int i=2; i<data.length; i++) {
            String drug = data[i];
            if(!drug.endsWith(" foldn\"")) {
                throw new FileFormatException("All drug definitions in first line must end with \"foldn\"\".");
            }
            // Der Medikamentenname steht vor dem Leerzeichen und hinter dem Anführungszeichen -
            // raussplitten und speichern.
            res.add(drug.split(" ")[0].split("\"")[1]);
        }
        return res;
    }

    public static HashMap<String, Double> parseSusceptibilities(String[] data, List<String> drugs) {
        HashMap<String, Double> res = new HashMap<>();
        for(int i = 2; i< data.length; i++) {
            String drug = drugs.get(i-2);
            try {
                res.put(drug, Double.parseDouble(data[i]));
            } catch(NumberFormatException e) {
                res.put(drug, -1d);
            }
        }
        return res;
    }

    public boolean canReadFile(String filename) {
        // Any text file could theoretically be a CSV file. Extend this in case further formats come in.
        return true;
    }
}
