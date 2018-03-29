package org.aitesting.microservices.tripmanagement.cmd.service.services;

import java.util.List;

import org.aitesting.microservices.tripmanagement.cmd.domain.models.Services;
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

    public ApiService() {
    }

    public T getOne(String path) {
        ResponseEntity<T> exchange = this.restTemplate.exchange(
            buildUri(service.name(), path),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<T>() {});
        return exchange.getBody();
    }

    public List<T> getMany(String path) {
        ResponseEntity<List<T>> exchange = this.restTemplate.exchange(
            buildUri(service.name(), path),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<T>>() {});
        return exchange.getBody();
    }

    public T create(String path, K obj) {
        ResponseEntity<T> exchange = this.restTemplate.exchange(
            buildUri(service.name(), path),
            HttpMethod.POST,
            null,
            new ParameterizedTypeReference<T>() {},
            obj);
        return exchange.getBody();
    }

    public void update(String path, K obj) {
        this.restTemplate.put(buildUri(service.name(), path), obj);
    }

    public void delete(String path) {
        this.restTemplate.delete(buildUri(service.name(), path));
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
        if (path.charAt(0) != '/') {
            path = "/" + path;
        }
        url += path;
        return url;
    }
}
