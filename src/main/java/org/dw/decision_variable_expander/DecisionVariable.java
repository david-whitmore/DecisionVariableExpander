package org.dw.decision_variable_expander;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A decision variable has an id (which is used as the column heading in the
 * final decision table), and several values.
 */
@XmlRootElement
class DecisionVariable {
    private String id;
    private List<String> values = newArrayList();

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "value")
    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
