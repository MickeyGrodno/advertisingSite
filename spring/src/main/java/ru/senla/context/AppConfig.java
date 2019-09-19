package ru.senla.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reflection.AnnotationCSVReader;
import reflection.AnnotationCSVWriter;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;

@Configuration
@ComponentScan({"ru.senla.*"})
public class AppConfig {
    @Bean
    public CsvWriter createCsvWriter() {
        return new AnnotationCSVWriter();
    }

    @Bean
    public CsvReader createCsvReader() {
        return new AnnotationCSVReader();
    }
}
