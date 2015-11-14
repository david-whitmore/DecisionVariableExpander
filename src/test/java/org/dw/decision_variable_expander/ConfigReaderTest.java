package org.dw.decision_variable_expander;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConfigReaderTest {
    private final ConfigReader sut = new ConfigReader();

    @Test
    public void parseConfig_noDecisionVariablesAndNoOutputTemplate() throws UnsupportedEncodingException, IOException,
            JAXBException {
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><config></config>";
        Config config = readFromString(input);

        assertThat(config.getOutputTemplate(), is(""));
        assertThat(config.getVariables(), is(empty()));
    }

    private Config readFromString(String input) throws UnsupportedEncodingException, IOException, JAXBException {
        try (ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("utf-8"))) {
            return sut.read(in);
        }
    }
}
