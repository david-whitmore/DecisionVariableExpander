package org.dw.decision_variable_expander;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConfigReaderTest {
    private static final String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    private ConfigReader sut;

    @Before
    public void setUp() throws JAXBException {
        Unmarshaller unmarshaller = (Unmarshaller) JAXBContext.newInstance(ConfigReader.class);
        sut = new ConfigReader(unmarshaller);
    }

    @Test
    public void parseConfig_noDecisionVariables() throws UnsupportedEncodingException, IOException, JAXBException {
        String input = XML_DECLARATION + "<config></config>";
        DecisionTable config = readFromString(input);

        assertThat(config.getVariables(), is(empty()));
    }

    @Test
    public void parseConfig_oneDecisionVariableWithNoValues() throws UnsupportedEncodingException, IOException,
            JAXBException {
        String input = XML_DECLARATION + "<config>" + "<variable><id>isEmpty</id></variable>" + "</config>";
        DecisionTable config = readFromString(input);
        List<DecisionVariable> variables = config.getVariables();

        assertThat(variables.size(), is(1));
        assertThat(variables.get(0).getId(), is("isEmpty"));
    }

    @Test
    public void parseConfig_oneDecisionVariableWithOneValue() throws UnsupportedEncodingException, IOException,
            JAXBException {
        String input = XML_DECLARATION + "<config>" + "<variable><id>isEmpty</id><value>true</value></variable>"
                + "</config>";
        DecisionTable config = readFromString(input);
        List<DecisionVariable> variables = config.getVariables();

        assertThat(variables.size(), is(1));
        assertThat(variables.get(0).getId(), is("isEmpty"));
        assertEquals(newArrayList("true"), variables.get(0).getValues());
    }

    @Test
    public void parseConfig_oneDecisionVariableWithManyValues() throws UnsupportedEncodingException, IOException,
            JAXBException {
        String input = XML_DECLARATION + "<config>"
                + "<variable><id>isEmpty</id><value>true</value><value>false</value></variable>" + "</config>";
        DecisionTable config = readFromString(input);
        List<DecisionVariable> variables = config.getVariables();

        assertThat(variables.size(), is(1));
        assertThat(variables.get(0).getId(), is("isEmpty"));
        assertEquals(newArrayList("true", "false"), variables.get(0).getValues());
    }

    @Test
    public void parseConfig_manyDecisionVariables() throws UnsupportedEncodingException, IOException, JAXBException {
        String input = XML_DECLARATION + "<config>" + "<variable><id>isEmpty</id></variable>"
                + "<variable><id>containsUtf8</id></variable>" + "</config>";
        DecisionTable config = readFromString(input);
        List<DecisionVariable> variables = config.getVariables();

        assertThat(variables.size(), is(2));
        assertThat(variables.get(0).getId(), is("isEmpty"));
        assertThat(variables.get(1).getId(), is("containsUtf8"));
    }

    private DecisionTable readFromString(String input) throws UnsupportedEncodingException, IOException, JAXBException {
        try (ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("utf-8"))) {
            return sut.read(in);
        }
    }
}
