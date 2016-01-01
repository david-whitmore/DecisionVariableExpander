package org.dw.decision_variable_expander;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.inject.Provider;

/**
 * Used by Guice to create Unmarshaller instances as necessary. See also the
 * configuration in the Module class.
 */
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
            throw new ConfigurationException("Error creating an " + Unmarshaller.class, e);
        }
    }
}
