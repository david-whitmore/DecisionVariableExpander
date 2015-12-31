package org.dw.decision_variable_expander;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.google.inject.Provider;

class JaxbContextProvider implements Provider<JAXBContext> {
    @Override
    public JAXBContext get() {
        try {
            return JAXBContext.newInstance(DecisionTable.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
