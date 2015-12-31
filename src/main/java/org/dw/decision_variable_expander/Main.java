package org.dw.decision_variable_expander;

import java.io.IOException;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;

import com.csvreader.CsvWriter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class Main {
    private final Provider<ConfigReader> readerFactory;
    private final Provider<CsvWriter> writerFactory;

    @Inject
    public Main(Provider<ConfigReader> readerFactory, Provider<CsvWriter> writerFactory) {
        this.readerFactory = readerFactory;
        this.writerFactory = writerFactory;
    }

    private void outputAsCsv() throws JAXBException, IOException {
        DecisionTable decisionTable = readerFactory.get().read(System.in);

        decisionTable.outputAsCsv(writerFactory.get());
    }

    public static void main(String[] args) throws JAXBException, IOException {
        Injector injector = Guice.createInjector(new Module());
        Main main = injector.getInstance(Main.class);

        main.outputAsCsv();
    }
}
