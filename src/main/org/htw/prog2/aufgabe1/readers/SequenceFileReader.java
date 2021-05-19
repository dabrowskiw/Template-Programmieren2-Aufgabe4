package org.htw.prog2.aufgabe1.readers;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.htw.prog2.aufgabe1.files.HIVFile;
import org.htw.prog2.aufgabe1.files.SequenceFile;

import java.io.IOException;
import java.util.HashSet;

public interface SequenceFileReader extends HIVFileReader {
    public SequenceFile readFile(String filename) throws IOException, FileFormatException;
}
