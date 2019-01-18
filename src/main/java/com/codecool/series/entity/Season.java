package com.codecool.series.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Season {

    @Id
    @GeneratedValue
    private Long id;

    @Transient
    private Integer numberOfEpisodes;

    private Long creationYear;

    @ManyToOne
    private Series series;

    private Integer numberOfSeason;

    @Singular
    @OneToMany(mappedBy = "season", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private Set<Episode> episodes;

    public Integer calculateNumberOfEpisodes() {
        return episodes.size();
    }

}
