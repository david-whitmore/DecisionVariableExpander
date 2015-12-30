package org.dw.decision_variable_expander;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.csvreader.CsvWriter;
import com.google.common.collect.Sets;

/**
 * Defines the output of the tool: a list of decision-variables (with their
 * associated values) and a template that defines the output.
 */
@XmlRootElement(name = "config")
class DecisionTable {
    private List<DecisionVariable> variables = newArrayList();

    @XmlElement(name = "variable")
    public List<DecisionVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<DecisionVariable> variables) {
        this.variables = variables;
    }

    public void outputAsCsv(OutputStream out, char delimiter) throws IOException {
        List<Set<String>> sets = newArrayList();

        for (DecisionVariable var : variables) {
            Set<String> values = newHashSet(var.getValues());

            sets.add(values);
        }

        Set<List<String>> rows = Sets.cartesianProduct(sets);
        CsvWriter writer = new CsvWriter(out, delimiter, Charset.forName("utf-8"));

        try {
            writeColumnHeadings(writer);
            writeRows(writer, rows);
        } finally {
            writer.close();
        }
    }

    private void writeColumnHeadings(CsvWriter writer) throws IOException {
        List<String> headings = newArrayList();

        for (DecisionVariable var : variables) {
            headings.add(var.getId());
        }

        String[] headingsAsArray = (String[]) headings.toArray(new String[headings.size()]);

        writer.writeRecord(headingsAsArray);
    }

    private void writeRows(CsvWriter writer, Set<List<String>> rows) throws IOException {
        for (List<String> row : rows) {
            writeRow(writer, row);
        }
    }

    private void writeRow(CsvWriter writer, List<String> row) throws IOException {
        String[] rowAsArray = (String[]) row.toArray(new String[row.size()]);

        writer.writeRecord(rowAsArray);
    }

    public static void main(String[] args) throws IOException {
        DecisionTable table = new DecisionTable();
        DecisionVariable var1 = new DecisionVariable();

        var1.setId("isEmpty");
        var1.setValues(newArrayList("true", "false"));

        DecisionVariable var2 = new DecisionVariable();

        var2.setId("charset");
        var2.setValues(newArrayList("ascii", "utf-8", "latin-1"));

        table.getVariables().add(var1);
        table.getVariables().add(var2);

        table.outputAsCsv(System.out, ',');
    }
}
