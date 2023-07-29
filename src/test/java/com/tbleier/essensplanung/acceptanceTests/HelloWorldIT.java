package com.tbleier.essensplanung.acceptanceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloWorldIT {

    @Test
    public void I_am_executed_at_verify() {
        //Arrange

        //Act

        //Assert
        Assertions.assertTrue(true, "Should be always true :)");
    }
}
