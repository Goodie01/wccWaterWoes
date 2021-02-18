package org.goodiemania.wcc.waterWoes.application.text.extractor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class TextExtractor {
    private PDFTextStripper pdfTextStripper;

    public TextExtractor() {
        try {
            pdfTextStripper = new PDFTextStripper();
        } catch (IOException e) {
            throw new IllegalStateException("Man, fuck I don't know", e);
        }
    }

    public Optional<String> extract(final InputStream inputStream) {
        try {
            PDDocument doc = PDDocument.load(inputStream);


/*            for (int i = 0; i < doc.getNumberOfPages(); ++i) {
                PDPage page = doc.getPage(i);

                PDResources res = page.getResources();
                for (COSName fontName : res.getFontNames()) {
                    PDFont font = res.getFont(fontName);
                    // do stuff with the font
                }
            }*/

            return Optional.of(pdfTextStripper.getText(doc));
        } catch (IOException e) {

            throw new IllegalStateException(e);
            //return Optional.empty();
        }
    }
}
