package com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest
@ContextConfiguration(
        classes = {PersistenceConfig.class,
                JpaMapperConfig.class})
//Everything related to testcontainers is configured in application properties
public @interface PersistenceAdapterTest {
}
