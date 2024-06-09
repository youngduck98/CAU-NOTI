package org.example.caunoti.domain.object.crawlingEngine.engines;

import org.example.caunoti.domain.object.Announcement.Announcement;
import org.example.caunoti.domain.object.Site.Site;
import org.example.caunoti.domain.object.crawlingEngine.CrawlingEngine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
    대표 학부: 소프트웨어 학부
    https://cse.cau.ac.kr/sub05/sub0501.php?nmode=view&code=oktomato_bbs05&uid=2942&search=&keyword=&temp1=&offset=1
 */
public class JsoupEngine implements CrawlingEngine{

    public Announcement crawlAnnouncementFrom(String tryAddress) throws IOException{
        Document doc = Jsoup.connect(tryAddress).get();
        Element titleElement = doc.select("div.header > h3").first();
        Element dateElement = doc.select("div.header span:nth-child(1)").first();
        Element authorElement = doc.select("div.header > div > span:nth-child(3)").first();

    }
    @Override
    public List<Announcement> crawlFrom(Site site, Integer postNum) throws IOException {
        int nowPostNum = postNum;
        List<Announcement> announcements = new ArrayList<>();

        boolean endCondition = false;
        int endCount = 0;
        while(!endCondition) {
            String tryAddress = site.getLink().replace("uid=", "uid=" + nowPostNum);
            try {
                Announcement announcement = crawlAnnouncementFrom(tryAddress);
                announcements.add(announcement);
            } catch (IOException e) {
                endCount++;
                if(endCount > 3)
                    endCondition = true;
                continue;
            }
            endCount = 0;
            nowPostNum++;
        }

        return announcements;
    }
}
