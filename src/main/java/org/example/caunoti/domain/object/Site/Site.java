package org.example.caunoti.domain.object.Site;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class Site{
    int id;
    Link link;
    String koName;
    String enName;
    DateTimeFormatter dateTimeFormat;
    String postNumQueryKey;

    @Setter
    int lastPostNum;
    boolean crawlAble;

    public Link getLink() throws URISyntaxException {
        return new Link(link.toURL());
    }
}