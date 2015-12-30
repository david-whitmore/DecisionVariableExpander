package org.dw.decision_variable_expander;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newTreeSet;

import java.io.IOException;
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

    public void outputAsCsv(CsvWriter writer) throws IOException {
        List<Set<String>> sets = newArrayList();

        for (DecisionVariable var : variables) {
            Set<String> values = newTreeSet(var.getValues());

            sets.add(values);
        }

        Set<List<String>> rows = Sets.cartesianProduct(sets);

        writeColumnHeadings(writer);
        writeRows(writer, rows);
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
}
