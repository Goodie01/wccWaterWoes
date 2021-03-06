package org.goodiemania.wcc.waterWoes.application;

import java.io.InputStream;
import java.util.List;
import org.goodiemania.wcc.waterWoes.application.downloader.Downloader;
import org.goodiemania.wcc.waterWoes.application.text.extractor.TextExtractor;
import org.goodiemania.wcc.waterWoes.application.site.DriverSupplier;
import org.goodiemania.wcc.waterWoes.application.site.Scrapper;
import org.goodiemania.wcc.waterWoes.application.text.processor.TextProcessor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Test");
        //final String startingUrl = "https://wellington.govt.nz/your-council/meetings/committees/council/2020/12/16";
        final String startingUrl = "https://wellington.govt.nz/your-council/meetings/committees/council/2022/09/28";

        Scrapper scrapper = new Scrapper(startingUrl, new DriverSupplier());
        Downloader downloader = new Downloader();
        TextExtractor textExtractor = new TextExtractor();
        TextProcessor textProcessor = new TextProcessor();


        String s = "https://wellington.govt.nz/-/media/your-council/meetings/council/2020/december/2020-12-16-minutes-council.pdf";
        InputStream inputStream = downloader.downloadFile(s).orElseThrow();

        String extractedText = textExtractor.extract(inputStream).orElseThrow();
        List<String> strings = textProcessor.extractLines(extractedText);


        /*scrapper.start()
            .parallelStream()
            .flatMap((String url) -> downloader.downloadFile(url).stream())
            .flatMap(inputStream -> textExtractor.extract(inputStream).stream())
            .forEach(s -> {
                System.out.println("Finished up, found string with following length: " + s.length());
            });*/
    }
}
