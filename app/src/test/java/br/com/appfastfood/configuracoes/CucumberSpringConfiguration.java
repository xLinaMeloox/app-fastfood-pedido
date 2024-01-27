package br.com.appfastfood.configuracoes;

import br.com.appfastfood.AppFastfoodApplicationTests;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= AppFastfoodApplicationTests.class)
public class CucumberSpringConfiguration {
}