package org.example.caunoti.domain.object.Site;

import lombok.Getter;

import java.net.URISyntaxException;

@Getter
public class Site{
    int id;
    Link link;
    String koName;
    String enName;
    boolean crawlAble;

    public Link getLink() throws URISyntaxException {
        return new Link(link.toURL());
    }
}