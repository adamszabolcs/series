package com.codecool.series.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Serie {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;


    @Enumerated(EnumType.STRING)
    @Singular("genre")
    private List<Genre> genre;

}
