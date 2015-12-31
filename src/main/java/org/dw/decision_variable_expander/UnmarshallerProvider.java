package org.dw.decision_variable_expander;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.inject.Provider;

class UnmarshallerProvider implements Provider<Unmarshaller> {
    private final JAXBContext context;

    @Inject
    UnmarshallerProvider(JAXBContext context) {
        this.context = context;
    }

    @Override
    public Unmarshaller get() {
        try {
            return context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException("Error providing an Unmarshaller", e);
        }
    }
}
