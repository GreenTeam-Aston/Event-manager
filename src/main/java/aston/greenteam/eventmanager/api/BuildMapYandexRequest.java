package aston.greenteam.eventmanager.api;

import aston.greenteam.eventmanager.dtos.CoordinatesDTO;

public class BuildMapYandexRequest {
    private final String map = "https://static-maps.yandex.ru/1.x/?ll=37.620070,55.753630&size=450,450&z=13&l=map&pt=37.620070,55.753630,pmwtm1~37.64,55.76363,pmwtm99&apikey=28b50357-99fb-418f-b9c0-e783";

    private String request;

    public static String getRequest(CoordinatesDTO coordinatesDTO, int zoom, int size) {
        return String.format("https://static-maps.yandex.ru/1.x/?ll=%1$s,%2$s&size=%4$d,%4$d&z=%3$d&l=map&pt=%1$s,%2$s,pm2rdl&apikey=28b50357-99fb-418f-b9c0-e783",
                coordinatesDTO.getLongitude(), coordinatesDTO.getLatitude(), zoom, size);
    }
}
