package org.dw.decision_variable_expander;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

class ConfigReader {
    Config read(InputStream in) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Config.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (Config) unmarshaller.unmarshal(in);
    }
}
