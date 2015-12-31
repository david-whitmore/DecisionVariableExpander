package org.dw.decision_variable_expander;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.google.inject.AbstractModule;

class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(JAXBContext.class).toProvider(JaxbContextProvider.class);
        bind(Unmarshaller.class).toProvider(UnmarshallerProvider.class);
    }
}
