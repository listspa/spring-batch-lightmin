package org.tuxdevelop.spring.batch.lightmin.repository.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tuxdevelop.spring.batch.lightmin.repository.JobConfigurationRepository;
import org.tuxdevelop.spring.batch.lightmin.repository.MapJobConfigurationRepository;

@Configuration
public class MapJobConfigurationRepositoryConfiguration extends LightminJobConfigurationRepositoryConfigurer {

    //Cannot access Bean directly in configureJobConfigurationRepository due it is called by @PostConstruct in super class
    // https://github.com/spring-projects/spring-framework/issues/27876
    private static final MapJobConfigurationRepository mapJobConfigurationRepository = new MapJobConfigurationRepository();

    @Override
    @Bean
    public JobConfigurationRepository jobConfigurationRepository() {
        return mapJobConfigurationRepository;
    }

    @Override
    protected void configureJobConfigurationRepository() {
        this.setJobConfigurationRepository(mapJobConfigurationRepository);
    }

}
