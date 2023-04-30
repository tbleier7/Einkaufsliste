package com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence")
@ComponentScan(basePackages = "com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence")
@EntityScan(basePackages = "com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence")
public class PersistenceConfig {
}
