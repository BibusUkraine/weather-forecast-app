package com.bibusukraine.weatherforecastapi.payload.response.client;

import lombok.Data;

@Data
public class CoordinatesClientResponse {

    private String name;
    private double lat;
    private double lon;
    private String country;

}
