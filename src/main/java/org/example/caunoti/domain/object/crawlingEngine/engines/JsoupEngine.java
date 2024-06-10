package org.example.caunoti.domain.object.crawlingEngine.engines;

import org.example.caunoti.domain.object.Announcement.Announcement;
import org.example.caunoti.domain.object.Site.Link;
import org.example.caunoti.domain.object.Site.Site;
import org.example.caunoti.domain.object.crawlingEngine.CrawlingEngine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/*
    대표 학부: 소프트웨어 학부
    https://cse.cau.ac.kr/sub05/sub0501.php?nmode=view&code=oktomato_bbs05&uid=2942&search=&keyword=&temp1=&offset=1
 */
public class JsoupEngine implements CrawlingEngine{

    String nowFormatString;

    public void addOneToQuery(Link link, String key){
        int beforePostNum =
                Integer.parseInt(link.getQueryParameter().get(key));
        link.getQueryParameter()
                .put(key, Integer.toString(beforePostNum+1));
    }

    public void setQueryValue(Link link, String key, String value){
        link.getQueryParameter().put(key, value);
    }

    public Announcement crawlAnnouncementFrom(
            Link link, DateTimeFormatter dateTimeFormatter, String PostNumQueryKey) throws IOException{
        String tryUrl = link.toURL();
        Document doc = Jsoup.connect(tryUrl).get();
        Element titleElement = doc.select("div.header > h3").first();
        Element dateElement = doc.select("div.header span:nth-child(1)").first();
        Element authorElement = doc.select("div.header > div > span:nth-child(3)").first();

        if(titleElement == null)
            throw new IOException();

        String title = titleElement.text();
        LocalDate postAt = LocalDate.parse(dateElement.text(), dateTimeFormatter);
        String writer = authorElement.text();
        int postNum = Integer.parseInt(link.getQueryParameter().get(PostNumQueryKey));


        return Announcement.builder()
                        .postAt(postAt)
                        .title(title)
                        .writer(writer)
                        .postNumber(postNum)
                        .build();
    }

    public int checkAboveAnnouncement(Link link, String postNumQueryKey){
        int count = 0;
        addOneToQuery(link, postNumQueryKey);

        while(count < maxSeeAfterFindOmitNumber) {
            try {
                Document doc = Jsoup.connect(link.toURL()).get();
                return Integer.parseInt(link
                        .getQueryParameter().get(postNumQueryKey));
            }
            catch (IOException e){
                addOneToQuery(link, postNumQueryKey);
                count++;
            }
        }
        return -1;
    }

    @Override
    public List<Announcement> crawlFrom(Site site) throws IOException, URISyntaxException {
        List<Announcement> announcements = new ArrayList<>();

        Link baselink = site.getLink();
        addOneToQuery(baselink, site.getPostNumQueryKey());

        boolean endCondition = false;
        int countSameAnnouncementFail = 0;

        while(!endCondition) {
            try {
                Announcement announcement = crawlAnnouncementFrom(baselink,
                                site.getDateTimeFormat(), site.getPostNumQueryKey());
                announcements.add(announcement);
                site.setLastPostNum(announcement.getPostNumber());
                addOneToQuery(baselink, site.getPostNumQueryKey());
            } catch (IOException e) {
                countSameAnnouncementFail++;
                if(countSameAnnouncementFail > maxTryAtOneAnnouncement) {
                    int checkPostNum = checkAboveAnnouncement(baselink,
                            site.getPostNumQueryKey());
                    if(checkPostNum == -1) {//end condition
                        endCondition = true;
                        continue;
                    }
                    countSameAnnouncementFail = 0;
                    setQueryValue(baselink, site.getPostNumQueryKey(),
                            Integer.toString(checkPostNum));
                }
            }
        }

        return announcements;
    }
}