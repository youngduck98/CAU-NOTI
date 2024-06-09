package org.example.caunoti.domain.object.Announcement;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class Announcement {
    int id;
    int postNumber;
    String title;
    LocalDate postAt;
    String writer;
}
