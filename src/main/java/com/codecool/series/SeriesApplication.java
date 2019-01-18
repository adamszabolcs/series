package com.codecool.series;

import com.codecool.series.entity.Episode;
import com.codecool.series.entity.Genre;
import com.codecool.series.entity.Season;
import com.codecool.series.entity.Series;
import com.codecool.series.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@SpringBootApplication
public class SeriesApplication {

    @Autowired
    private SeriesRepository seriesRepository;

    public static void main(String[] args) {
        SpringApplication.run(SeriesApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {

            Episode onePerOne = Episode.builder()
                    .title("The target")
                    .releaseDate(LocalDate.of(2002, 6, 2))
                    .orderOfEpisode(1)
                    .build();

            Episode onePerTwo = Episode.builder()
                    .title("The Detail")
                    .releaseDate(LocalDate.of(2002, 6, 9))
                    .orderOfEpisode(2)
                    .build();

            Episode twoPerOne = Episode.builder()
                    .title("Ebb Tide")
                    .releaseDate(LocalDate.of(2003, 6, 1))
                    .orderOfEpisode(1)
                    .build();

            Episode twoPerTwo = Episode.builder()
                    .title("Collateral Damage")
                    .releaseDate(LocalDate.of(2003, 6, 8))
                    .orderOfEpisode(2)
                    .build();

            Season first = Season.builder()
                    .creationYear(2002L)
                    .numberOfEpisodes(13)
                    .numberOfSeason(1)
                    .episode(onePerOne)
                    .episode(onePerTwo)
                    .build();

            Season second = Season.builder()
                    .creationYear(2003L)
                    .numberOfEpisodes(12)
                    .numberOfSeason(2)
                    .episode(twoPerOne)
                    .episode(twoPerTwo)
                    .build();

            Series wire = Series.builder()
                    .name("The Wire")
                    .genre(Genre.CRIME)
                    .genre(Genre.DRAMA)
                    .genre(Genre.THRILLER)
                    .season(first)
                    .season(second)
                    .description("Baltimore drug scene, seen through the eyes of drug dealers and law enforcement.")
                    .build();

            onePerOne.setSeason(first);
            onePerTwo.setSeason(first);
            twoPerOne.setSeason(second);
            twoPerTwo.setSeason(second);
            first.setSeries(wire);
            second.setSeries(wire);

            seriesRepository.save(wire);

        };
    }

}

