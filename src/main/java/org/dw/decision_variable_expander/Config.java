package org.dw.decision_variable_expander;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Defines the output of the tool: a list of decision-variables (with their
 * associated values) and a template that defines the output.
 */
@XmlRootElement
class Config {
    private List<DecisionVariable> variables = newArrayList();

    @XmlElement(name = "variable")
    public List<DecisionVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<DecisionVariable> variables) {
        this.variables = variables;
    }
}
