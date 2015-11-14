package org.dw.decision_variable_expander;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

/**
 * Defines the output of the tool: a list of decision-variables (with their
 * associated values) and a template that defines the output.
 */
class Config {
    private List<DecisionVariable> variables = newArrayList();
    private String outputTemplate = "";

    public List<DecisionVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<DecisionVariable> variables) {
        this.variables = variables;
    }

    public String getOutputTemplate() {
        return outputTemplate;
    }

    public void setOutputTemplate(String outputTemplate) {
        this.outputTemplate = outputTemplate;
    }
}
