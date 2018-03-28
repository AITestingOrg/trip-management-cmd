package org.aitesting.microservices.tripmanagement.cmd.service.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.aitesting.microservices.tripmanagement.cmd.domain.models.Services;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public abstract class ApiService<T, K> {
    protected Services service;

    @Value("${aist.use_ssl}")
    protected boolean useSsl;

    @Autowired
    private RestTemplate restTemplate;

    public ApiService() {}

    public T getOne(String path) throws URISyntaxException {
        ResponseEntity<T> exchange = this.restTemplate.exchange(
            buildUri(service.name(), path),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<T>() {});
        return exchange.getBody();
    }

    public List<T> getMany(String path) throws URISyntaxException {
        ResponseEntity<List<T>> exchange = this.restTemplate.exchange(
            buildUri(service.name(), path),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<T>>() {});
        return exchange.getBody();
    }

    public T create(String path, K obj) throws URISyntaxException {
        ResponseEntity<T> exchange = this.restTemplate.exchange(
            buildUri(service.name(), path).toString(),
            HttpMethod.POST,
            null,
            new ParameterizedTypeReference() {},
            obj);
        return exchange.getBody();
    }

    public void update(String path, K obj) throws URISyntaxException {
        this.restTemplate.put(buildUri(service.name(), path), obj);
    }

    public void delete(String path) throws URISyntaxException {
        this.restTemplate.delete(buildUri(service.name(), path));
    }

    private URI buildUri(String appName, String path) throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        if (useSsl) {
            builder.setScheme("https");
        } else {
            builder.setScheme("http");
        }
        builder.setHost(appName);
        builder.setPath(path);
        return builder.build();
    }
}
