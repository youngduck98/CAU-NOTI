package org.example.caunoti.domain.object.Site;

import lombok.Builder;
import lombok.Getter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class Link {
    String protocol;
    String subDomain;
    String domain;
    String route;
    Map<String, String> queryParameter;

    // 기본 생성자
    public Link() {
        queryParameter = new LinkedHashMap<>();
    }

    // URL을 받아 각 구성 요소로 분해하는 생성자
    public Link(String url) throws URISyntaxException {
        this();
        URI uri = new URI(url);
        this.protocol = uri.getScheme();

        this.domain = uri.getHost();

        this.route = uri.getPath();

        String query = uri.getQuery();
        if (query != null && !query.isEmpty()) {
            String[] params = query.split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    queryParameter.put(keyValue[0], keyValue[1]);
                }
                else{
                    queryParameter.put(keyValue[0], "");
                }
            }
        }
    }

    // Link 객체를 도메인 주소로 변환하는 함수
    public String toURL() {
        StringBuilder url = new StringBuilder();
        url.append(this.protocol).append("://");

        if (this.subDomain != null && !this.subDomain.isEmpty()) {
            url.append(this.subDomain).append(".");
        }

        url.append(this.domain);

        if (this.route != null && !this.route.isEmpty()) {
            url.append(this.route);
        }

        if (this.queryParameter != null && !this.queryParameter.isEmpty()) {
            url.append("?");
            this.queryParameter.forEach((key, value) -> {
                url.append(key).append("=").append(value).append("&");
            });
            url.deleteCharAt(url.length() - 1); // 마지막 '&' 제거
        }

        return url.toString();
    }
}
