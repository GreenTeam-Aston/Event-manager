package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.api.BuildCoordinatesYandexRequest;
import aston.greenteam.eventmanager.api.BuildMapYandexRequest;
import aston.greenteam.eventmanager.dtos.*;
import aston.greenteam.eventmanager.exceptions.ObjectNotFoundException;
import aston.greenteam.eventmanager.json.Root;
import aston.greenteam.eventmanager.dtos.MessageDTO;
import aston.greenteam.eventmanager.dtos.WeatherDTO;
import aston.greenteam.eventmanager.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.sender.email}")
    private String senderAddress;
    private final JavaMailSender javaMailSender;
    public void sendNotice(EventDTO eventDTO, ContactDTO contactDTO, String msg) {
        AddressEventDTO addressEventDTO = new AddressEventDTO(eventDTO.getLinkEventSite());
        String email = contactDTO.getValue();
        MimeMessage ms = javaMailSender.createMimeMessage();
        Path path = generateMap(addressEventDTO);
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(ms, true);
            helper.setFrom(senderAddress);
            helper.setTo(email);
            helper.setSubject(eventDTO.getTitle());
            StringBuilder multiPartMsg = new StringBuilder();
            multiPartMsg.append(msg).append("\n")
                    .append(addressEventDTO.getStreetType()).append(" ")
                    .append(addressEventDTO.getStreetName()).append(" ")
                    .append(addressEventDTO.getHomeNumber());
            helper.setText(multiPartMsg.toString());
            File file = new File(path.toString());
            helper.addAttachment("Meeting place", file);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        javaMailSender.send(ms);

    @Override
    public void sendMessage(WeatherDTO weatherDTO, Long userId) {

    }

    @Override
    public void testSend() {
        String email = "steve128@yandex.ru";
        String msg = "testMessage";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderAddress);
        message.setTo(email);
        message.setSubject(msg);
        message.setText(msg);
        javaMailSender.send(message);
    }

    private Path generateMap(AddressEventDTO addressEventDTO) {
        String uri = BuildCoordinatesYandexRequest.getRequest(addressEventDTO);
        RestTemplate restTemplate = new RestTemplate();
        Root root = restTemplate.getForObject(uri, Root.class);

        if (root == null) throw new ObjectNotFoundException("Address not found.");
        String pos = root.response.geoObjectCollection.featureMember.get(0).geoObject.point.pos;
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO(pos);
        String uriMap = BuildMapYandexRequest.getRequest(coordinatesDTO, 16, 450);
        byte[] imageBytes = restTemplate.getForObject(uriMap, byte[].class);

        if (imageBytes == null) throw new RuntimeException("Error generating the map");
        Path pathMap;
        try {
            pathMap = Files.write(Paths.get("src/main/resources/img/image.jpg"), imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pathMap;
    }


}
