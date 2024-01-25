package br.com.appfastfood;


import br.com.appfastfoodpagamentos.config.CucumberSpringConfiguration;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.*;


@Suite
@SelectClasspathResource("features")
@ConfigurationParameters({
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "br.com.appfastfood.stepdefinitions"),
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html")
})
public class CucumberRunnerTest {
}