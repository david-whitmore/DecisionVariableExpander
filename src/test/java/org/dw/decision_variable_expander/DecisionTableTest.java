package org.dw.decision_variable_expander;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.csvreader.CsvWriter;

@RunWith(MockitoJUnitRunner.class)
public class DecisionTableTest {
    private DecisionTable sut;

    @Mock
    private CsvWriter writer;

    @Before
    public void setUp() {
        sut = new DecisionTable();
    }

    @Test
    public void outputAsCsv_noDecisionVariables() throws IOException {
        char delimiter = ',';
        Writer out = new StringWriter();

        writer = createWriter(out, delimiter);
        sut.outputAsCsv(writer);

        assertThat(out.toString(), is(""));
    }

    @Test
    public void outputAsCsv_oneDecisionVariableWithNoValues() throws IOException {
        DecisionVariable var = createDecisionVariable("decision variable 1");
        char delimiter = ',';
        Writer out = new StringWriter();

        writer = createWriter(out, delimiter);
        sut.setVariables(newArrayList(var));
        sut.outputAsCsv(writer);

        assertThat(out.toString(), is("decision variable 1" + System.lineSeparator()));
    }

    @Test
    public void outputAsCsv_oneDecisionVariableWithOneValue() throws IOException {
        DecisionVariable var = createDecisionVariable("decision variable 1", "true");
        char delimiter = ',';
        Writer out = new StringWriter();

        writer = createWriter(out, delimiter);
        sut.setVariables(newArrayList(var));
        sut.outputAsCsv(writer);

        String expected = "decision variable 1" + System.lineSeparator() + "true" + System.lineSeparator();

        assertThat(out.toString(), is(expected));
    }

    @Test
    public void outputAsCsv_oneDecisionVariableWithManyValues() throws IOException {
        DecisionVariable var = createDecisionVariable("decision variable 1", "true", "false");
        char delimiter = ',';
        Writer out = new StringWriter();

        writer = createWriter(out, delimiter);
        sut.setVariables(newArrayList(var));
        sut.outputAsCsv(writer);

        String expected = "decision variable 1" + System.lineSeparator() + "false" + System.lineSeparator() + "true"
                + System.lineSeparator();

        assertThat(out.toString(), is(expected));
    }

    @Test
    public void outputAsCsv_manyDecisionVariablesAndCommaDelimiter() throws IOException {
        DecisionVariable var1 = createDecisionVariable("decision variable 1", "true", "false");
        DecisionVariable var2 = createDecisionVariable("decision variable 2", "3", "1", "2");
        char delimiter = ',';
        Writer out = new StringWriter();

        writer = createWriter(out, delimiter);
        sut.setVariables(newArrayList(var1, var2));
        sut.outputAsCsv(writer);

        String expected = "decision variable 1,decision variable 2" + System.lineSeparator() + "false,1"
                + System.lineSeparator() + "false,2" + System.lineSeparator() + "false,3" + System.lineSeparator()
                + "true,1" + System.lineSeparator() + "true,2" + System.lineSeparator() + "true,3"
                + System.lineSeparator();

        assertThat(out.toString(), is(expected));
    }

    private DecisionVariable createDecisionVariable(String id, String... values) {
        DecisionVariable var = new DecisionVariable();

        var.setId(id);
        var.setValues(Arrays.asList(values));
        return var;
    }

    private CsvWriter createWriter(Writer out, char delimiter) {
        return new CsvWriter(out, delimiter);
    }
}
