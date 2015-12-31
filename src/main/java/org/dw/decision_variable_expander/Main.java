package org.dw.decision_variable_expander;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;

import com.csvreader.CsvWriter;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Main entrypoint that reads config from standard input, and writes the
 * corresponding decision-table in CSV format to standard output. Uses Guice
 * behind the scenes.
 */
public class Main {
    private final ConfigReader configReader;
    private final CsvWriterFactory writerFactory;
    private final Charset charset;

    @Inject
    Main(ConfigReader configReader, CsvWriterFactory writerFactory, Charset charset) {
        this.configReader = configReader;
        this.writerFactory = writerFactory;
        this.charset = charset;
    }

    private void outputAsCsv() throws JAXBException, IOException {
        DecisionTable decisionTable = readConfig();
        CsvWriter csvWriter = null;

        try {
            csvWriter = createCsvWriter();
            decisionTable.outputAsCsv(csvWriter);
        } finally {
            if (csvWriter != null) {
                csvWriter.close();
            }
        }
    }

    private CsvWriter createCsvWriter() {
        OutputStream outStream = System.out;
        char delimiter = ',';

        return writerFactory.createInstance(outStream, delimiter, charset);
    }

    private DecisionTable readConfig() throws JAXBException {
        InputStream inStream = System.in;
        DecisionTable decisionTable = configReader.read(inStream);
        return decisionTable;
    }

    public static void main(String[] args) throws JAXBException, IOException {
        Injector injector = Guice.createInjector(new Module());
        Main main = injector.getInstance(Main.class);

        main.outputAsCsv();
    }
}
