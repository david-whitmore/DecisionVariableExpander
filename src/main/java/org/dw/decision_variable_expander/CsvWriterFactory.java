package org.dw.decision_variable_expander;

import java.io.OutputStream;
import java.nio.charset.Charset;

import com.csvreader.CsvWriter;

class CsvWriterFactory {
    CsvWriter createInstance(OutputStream outStream, char delimiter, Charset charset) {
        return new CsvWriter(outStream, delimiter, charset);
    }
}
