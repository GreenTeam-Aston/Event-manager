package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.json.Root;
import aston.greenteam.eventmanager.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/maps")
public class MapController {

    private final EmailService emailService;

    @GetMapping
    private void getMap() {
        final String uri = "https://geocode-maps.yandex.ru/1.x/?apikey=109c4104-f8c5-4a6c-aea7-9061fef664ca&geocode=Москва,+Тверская+улица,+дом+7&format=json";
        RestTemplate restTemplate = new RestTemplate();
        Root root = restTemplate.getForObject(uri, Root.class);
        assert root != null;
        String pos = root.response.geoObjectCollection.featureMember.get(0).geoObject.point.pos;
        System.out.println(pos);
        final String map = "https://static-maps.yandex.ru/1.x/?ll=37.620070,55.753630&size=450,450&z=13&l=map&pt=37.620070,55.753630,pmwtm1~37.64,55.76363,pmwtm99&apikey=28b50357-99fb-418f-b9c0-e783";
        byte[] imageBytes = restTemplate.getForObject(map, byte[].class);
        try {
            assert imageBytes != null;
            Files.write(Paths.get("image.jpg"), imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //emailService.sendNotice();
    }

}
