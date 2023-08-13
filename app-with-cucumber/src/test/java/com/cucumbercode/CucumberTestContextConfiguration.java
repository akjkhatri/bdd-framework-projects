package com.cucumbercode;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = {AppWithCucumberApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberTestContextConfiguration {

}