package com.footballhub.footballhubapp.common.utils;

import org.springframework.web.client.RestTemplate;

public class FetchDataset {
    private final RestTemplate restTemplate = new RestTemplate();
    private String url = null;

    public FetchDataset(String league, String year) {
        this.url = String.format("https://fixturedownload.com/feed/json/%s-%s", league, year);
    }

    public String fetchEPLDataset() {
        return restTemplate.getForObject(url, String.class);
    }

    public String fetchCLDataset() {
        return restTemplate.getForObject(url, String.class);
    }
}
