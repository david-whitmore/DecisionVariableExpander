package org.dw.decision_variable_expander;

import java.nio.charset.Charset;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.google.inject.AbstractModule;

/** Guice configuration for the application. */
class Module extends AbstractModule {
    private static final String DEFAULT_CHARSET = "UTF-8";

    @Override
    protected void configure() {
        bind(Charset.class).toInstance(Charset.forName(DEFAULT_CHARSET));
        bind(JAXBContext.class).toProvider(JaxbContextProvider.class);
        bind(Unmarshaller.class).toProvider(UnmarshallerProvider.class);
    }
}
