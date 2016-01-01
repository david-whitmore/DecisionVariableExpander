package org.dw.decision_variable_expander;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.google.inject.Provider;

/**
 * Used by Guice to create JAXBContexts as necessary. See also the configuration
 * in the Module class.
 */
class JaxbContextProvider implements Provider<JAXBContext> {
    @Override
    public JAXBContext get() {
        try {
            return JAXBContext.newInstance(DecisionTable.class);
        } catch (JAXBException e) {
            throw new ProviderException("Error creating an " + JAXBContext.class, e);
        }
    }
}
