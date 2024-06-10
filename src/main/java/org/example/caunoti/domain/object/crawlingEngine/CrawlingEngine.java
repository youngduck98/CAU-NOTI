package org.example.caunoti.domain.object.crawlingEngine;

import org.example.caunoti.domain.object.Announcement.Announcement;
import org.example.caunoti.domain.object.Site.Site;

import org.example.caunoti.domain.object.Announcement.Announcement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CrawlingEngine {
    String http = "http";
    String https = "https";
    int maxTryAtOneAnnouncement = 3;
    int maxSeeAfterFindOmitNumber = 3;
    //해당 사이트의 lastPostNum +1번 uid 부터 하나씩 올라가며 숫자가 없을 때 까지 크롤링한다.
    List<Announcement> crawlFrom(Site site) throws IOException, URISyntaxException;
}
