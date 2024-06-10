package org.example.caunoti.domain.object.crawlingEngine;

import org.example.caunoti.domain.object.Announcement.Announcement;
import org.example.caunoti.domain.object.Site.Site;

import org.example.caunoti.domain.object.Announcement.Announcement;

import java.io.IOException;
import java.util.List;

public interface CrawlingEngine {
    String http = "http";
    String https = "https";
    int maxTry = 3;
    //해당 사이트에서 postMum 부터 하나씩 올라가며 숫자가 없을 때 까지 크롤링한다.
    List<Announcement> crawlFrom(Site site, Integer postNum) throws IOException;
}
