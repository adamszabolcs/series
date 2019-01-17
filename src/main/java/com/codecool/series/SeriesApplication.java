package com.codecool.series;

import com.codecool.series.entity.Genre;
import com.codecool.series.entity.Serie;
import com.codecool.series.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SeriesApplication {

    @Autowired
    private SerieRepository serieRepository;

    public static void main(String[] args) {
        SpringApplication.run(SeriesApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Serie wire = Serie.builder()
                    .name("The Wire")
                    .genre(Genre.CRIME)
                    .genre(Genre.DRAMA)
                    .genre(Genre.THRILLER)
                    .description("Baltimore drug scene, seen through the eyes of drug dealers and law enforcement.")
                    .build();

            serieRepository.save(wire);

        };
    }

}

