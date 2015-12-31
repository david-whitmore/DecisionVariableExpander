package org.dw.decision_variable_expander;

import java.io.InputStream;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

class ConfigReader {
    private final Unmarshaller unmarshaller;

    @Inject
    ConfigReader(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    DecisionTable read(InputStream in) throws JAXBException {
        return (DecisionTable) unmarshaller.unmarshal(in);
    }
}
