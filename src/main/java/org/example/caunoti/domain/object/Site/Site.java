package org.example.caunoti.domain.object.Site;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class Site{
    int id;
    Link link;
    String koName;
    String enName;
    String dateTimeFormat;

    int lastPostNum;
    boolean crawlAble;

    public Link getLink() throws URISyntaxException {
        return new Link(link.toURL());
    }
}