package com.codecool.series.repository;

import com.codecool.series.entity.Genre;
import com.codecool.series.entity.Serie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class AllRepositoryTest {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveOneSerie() {
        Serie theWire = Serie.builder()
                .name("The wire")
                .genre(Genre.CRIME)
                .genre(Genre.DRAMA)
                .genre(Genre.THRILLER)
                .description("Baltimore drug scene, seen through the eyes of drug dealers and law enforcement.")
                .build();

        serieRepository.save(theWire);
        List<Serie> series = serieRepository.findAll();

        assertThat(series)
                .hasSize(1)
                .containsOnly(theWire);
    }

}