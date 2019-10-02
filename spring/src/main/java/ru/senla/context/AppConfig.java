package ru.senla.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"ru.senla.daoImpl", "ru.senla.service.impl", "ru.senla.context", "ru.senla.webconfig"})
public class AppConfig {

}
