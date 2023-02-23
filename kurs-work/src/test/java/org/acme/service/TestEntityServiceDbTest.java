package org.acme.service;


import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class TestEntityServiceDbTest {

    @Inject
    TestEntityServiceDb testService;

    @Test
    public void testHelloEndpointSubPath() {

        Long aLong = testService.countTestEntities();

        Assertions.assertNotNull(aLong);
    }

}
