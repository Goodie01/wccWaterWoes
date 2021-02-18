package org.goodiemania.wcc.waterWoes.application.downloader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

public class Downloader {
    public Optional<InputStream> downloadFile(final String url) {
        try {
            return Optional.of(new BufferedInputStream(new URL(url).openStream()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
