package org.example.caunoti.domain.object.site;

import org.example.caunoti.domain.object.Site.Link;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URISyntaxException;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class LinkTest {
    @Container
    public static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0.26")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @Test
    public void 링크변환() throws URISyntaxException {
        Link link = new Link("https://cse.cau.ac.kr/sub05/sub0501.php?nmode=view&code=oktomato_bbs05&uid=2942&search=&keyword=&temp1=&offset=1");
        //System.out.println(link.toURL());
        assertThat(link.toURL().equals("https://cse.cau.ac.kr/sub05/sub0501.php?nmode=view&code=oktomato_bbs05&uid=2942&search=&keyword=&temp1=&offset=1")).isTrue();
    }

    @Test
    public void 프로퍼티삽입() throws URISyntaxException{
        Link link = new Link("https://cse.cau.ac.kr/sub05/sub0501.php?nmode=view&code=oktomato_bbs05&uid=&search=&keyword=&temp1=&offset=1");
        link.getQueryParameter().put("uid", "2942");
        link.toURL().equals("https://cse.cau.ac.kr/sub05/sub0501.php?nmode=view&code=oktomato_bbs05&uid=2942&search=&keyword=&temp1=&offset=1");
    }
}
