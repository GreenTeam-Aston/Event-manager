package aston.greenteam.eventmanager.api;


import aston.greenteam.eventmanager.dtos.AddressEventDTO;

public class BuildCoordinatesYandexRequest {
    private final String uri = "https://geocode-maps.yandex.ru/1.x/?apikey=109c4104-f8c5-4a6c-aea7-9061fef664ca&geocode=Москва,+Тверская+улица,+дом+7&format=json";
    private String request;

    public static String getRequest(AddressEventDTO addressEventDTO) {
        return String.format("https://geocode-maps.yandex.ru/1.x/?apikey=109c4104-f8c5-4a6c-aea7-9061fef664ca&geocode=%s,+%s+%s,+дом+%s&format=json",
                addressEventDTO.getCity(), addressEventDTO.getStreetName(), addressEventDTO.getStreetType(), addressEventDTO.getHomeNumber());
    }

}
