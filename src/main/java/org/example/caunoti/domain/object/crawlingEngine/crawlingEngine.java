package org.example.caunoti.domain.object.crawlingEngine;

import org.example.caunoti.domain.object.Site.Site;
import org.example.caunoti.domain.object.Annotation.Annotation;

import java.io.IOException;
import java.util.List;

public interface crawlingEngine {
    List<Annotation> crawlFrom(Site site, String baseUrl, Integer postNum) throws IOException;
}
