package com.codecool.series.repository;

import com.codecool.series.entity.Episode;
import com.codecool.series.entity.Genre;
import com.codecool.series.entity.Season;
import com.codecool.series.entity.Series;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class AllRepositoryTest {

    @Autowired
    private SeriesRepository seriesRepository;

    @Test
    public void saveOneSeries() {
        Series theWire = Series.builder()
                .name("The wire")
                .genre(Genre.CRIME)
                .genre(Genre.DRAMA)
                .genre(Genre.THRILLER)
                .description("Baltimore drug scene, seen through the eyes of drug dealers and law enforcement.")
                .build();

        seriesRepository.save(theWire);
        List<Series> series = seriesRepository.findAll();

        assertThat(series)
                .hasSize(1)
                .containsOnly(theWire);
    }

    @Test
    public void transientIsWorking() {
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

        Season first = Season.builder()
                .creationYear(2002L)
                .numberOfSeason(1)
                .episode(onePerOne)
                .episode(onePerTwo)
                .build();

        Series wire = Series.builder()
                .name("The Wire")
                .genre(Genre.CRIME)
                .genre(Genre.DRAMA)
                .genre(Genre.THRILLER)
                .season(first)
                .description("Baltimore drug scene, seen through the eyes of drug dealers and law enforcement.")
                .build();

        onePerOne.setSeason(first);
        onePerTwo.setSeason(first);
        first.setSeries(wire);

        seriesRepository.save(wire);

        int numberOfEpisodes = first.calculateNumberOfEpisodes();
        assertThat(numberOfEpisodes).isEqualTo(2);

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void seriesNameIsNotNull() {
        Series theWire = Series.builder()
                .genre(Genre.DRAMA)
                .description("This is a description")
                .build();

        seriesRepository.save(theWire);
    }



}