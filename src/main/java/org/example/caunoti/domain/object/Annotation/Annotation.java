package org.example.caunoti.domain.object.Annotation;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class Annotation {
    int id;
    int postNumber;
    String title;
    LocalDate postAt;
    String writer;
}
