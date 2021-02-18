package org.goodiemania.wcc.waterWoes.application.text.processor;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class TextProcessor {
    public List<String> extractLines(final String pdfText) {
        String[] split = StringUtils.split(pdfText, "\r\n");
        return Arrays.asList(split);
    }
}
