package org.tuxdevelop.spring.batch.lightmin.client.classic.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.tuxdevelop.spring.batch.lightmin.client.api.LightminClientApplication;
import org.tuxdevelop.spring.batch.lightmin.client.classic.configuration.LightminClientClassicConfigurationProperties;
import org.tuxdevelop.spring.batch.lightmin.client.configuration.LightminClientProperties;
import org.tuxdevelop.spring.batch.lightmin.client.service.LightminServerLocatorService;

import java.util.Collections;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LightminClientRegistrationServiceTest {

    @Mock
    private LightminClientProperties lightminClientProperties;
    @Mock
    private LightminClientClassicConfigurationProperties lightminClientClassicConfigurationProperties;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private JobRegistry jobRegistry;
    @Mock
    private LightminServerLocatorService lightminServerLocatorService;

    @InjectMocks
    private LightminClientRegistratorService lightminClientRegistratorService;

    @Test
    public void testRegister() {
        final LightminClientApplication lightminClientApplication = new LightminClientApplication();
        lightminClientApplication.setId("12345");
        final ResponseEntity<LightminClientApplication> responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(lightminClientApplication);
        when(this.lightminServerLocatorService.getRemoteUrls()).thenReturn(Collections.singletonList("http://localhost:8080"));
        when(this.jobRegistry.getJobNames()).thenReturn(new LinkedList<>());
        when(this.restTemplate.postForEntity(anyString(), any(HttpEntity.class), any(Class.class))).thenReturn(responseEntity);

        final Boolean result = this.lightminClientRegistratorService.register();
        assertThat(result).isTrue();
    }

    @Test
    public void testRegisterResponseError() {
        final LightminClientApplication lightminClientApplication = new LightminClientApplication();
        lightminClientApplication.setId("12345");
        final ResponseEntity<LightminClientApplication> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lightminClientApplication);
        when(this.lightminServerLocatorService.getRemoteUrls()).thenReturn(Collections.singletonList("http://localhost:8080"));
        when(this.jobRegistry.getJobNames()).thenReturn(new LinkedList<>());
        when(this.restTemplate.postForEntity(anyString(), any(HttpEntity.class), any(Class.class))).thenReturn(responseEntity);

        final Boolean result = this.lightminClientRegistratorService.register();
        assertThat(result).isFalse();
    }

    @Test
    public void testDeregister() {
        try {
            when(this.lightminServerLocatorService.getRemoteUrls()).thenReturn(Collections.singletonList("http://localhost:8080"));
            this.lightminClientRegistratorService.deregister(null);
        } catch (final Exception e) {
            fail(e.getMessage());
        }

    }

    @BeforeEach
    public void init() {
        when(this.lightminClientClassicConfigurationProperties.getServer())
                .thenReturn(new LightminClientClassicConfigurationProperties.LightminClientServerProperties());
    }

}
