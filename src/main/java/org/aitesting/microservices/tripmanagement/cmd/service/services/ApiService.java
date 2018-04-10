package org.aitesting.microservices.tripmanagement.cmd.service.services;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netflix.discovery.DiscoveryClient;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.Services;
import org.aitesting.microservices.tripmanagement.common.models.TripInvoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public abstract class ApiService<T, K> {
    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    protected Services service;
    protected int port;
    protected Class<T> type;


    @Value("${aist.use_ssl}")
    protected boolean useSsl;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    public ApiService() {
        port = -1;
    }

    public T getOne(String path) {
        restTemplate = restTemplate();
        String uri = buildUri(service.name(), path);
        logger.info("Sending a GET request to " + uri);
        ResponseEntity<T> exchange = this.restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<T>() {});
        return exchange.getBody();
    }

    public List<T> getMany(String path) {
        restTemplate = restTemplate();
        String uri = buildUri(service.name(), path);
        logger.info("Sending a GET request to " + uri);
        ResponseEntity<List<T>> exchange = this.restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<T>>() {});
        return exchange.getBody();
    }

    public T create(String path, K obj) {
        restTemplate = restTemplate();
        String uri = buildUri(service.name(), path);
        logger.info("Sending a POST request to " + uri);
        logger.info("obj to send: " + obj);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<K> entity = new HttpEntity<>(obj, headers);
        logger.info("entity to send: " + entity);
        ResponseEntity<String> exchange = this.restTemplate.exchange(
            uri,
            HttpMethod.POST,
            entity,
            String.class);
        logger.info("Got a reply back");

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(exchange.getBody(), type);
    }

    public void update(String path, K obj) {
        String uri = buildUri(service.name(), path);
        logger.info("Sending a PUT request to " + uri);
        this.restTemplate.put(uri, obj);
    }

    public void delete(String path) {
        restTemplate = restTemplate();
        String uri = buildUri(service.name(), path);
        logger.info("Sending a PUT request to " + uri);
        this.restTemplate.delete(uri);
    }

    private String buildUri(String appName, String path) {
        // this method does not use URI since the app_name is usually not a valid URI Hostname
        String url;
        if (useSsl) {
            url = "https://";
        } else {
            url = "http://";
        }
        url += appName.toLowerCase();
        if (port > 0) {
            url += ":" + port;
        }
        if (path.charAt(0) != '/') {
            path = "/" + path;
        }
        url += path;
        return url;
    }
}
