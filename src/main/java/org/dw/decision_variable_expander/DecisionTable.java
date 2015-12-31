package org.dw.decision_variable_expander;

import static com.google.common.collect.Lists.newArrayList;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.csvreader.CsvWriter;
import com.google.common.collect.Lists;

/**
 * Acts as config for outputting a decision table. The main method is the
 * 'outputAsCsv()' method, which effectively calculates the cartesian product of
 * the decision variable values, and outputs it as a corresponding CSV file.
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
        writeColumnHeadings(writer);
        writeRows(writer, calculateRows());
    }

    private List<List<String>> calculateRows() {
        List<List<String>> decisionVarValues = newArrayList();

        for (DecisionVariable var : variables) {
            List<String> values = newArrayList(var.getValues());

            decisionVarValues.add(values);
        }

        return Lists.cartesianProduct(decisionVarValues);
    }

    private void writeColumnHeadings(CsvWriter writer) throws IOException {
        List<String> headings = newArrayList();

        for (DecisionVariable var : variables) {
            headings.add(var.getId());
        }

        String[] headingsAsArray = (String[]) headings.toArray(new String[headings.size()]);

        writer.writeRecord(headingsAsArray);
    }

    private void writeRows(CsvWriter writer, List<List<String>> rows) throws IOException {
        for (List<String> row : rows) {
            writeRow(writer, row);
        }
    }

    private void writeRow(CsvWriter writer, List<String> row) throws IOException {
        String[] rowAsArray = (String[]) row.toArray(new String[row.size()]);

        writer.writeRecord(rowAsArray);
    }
}
