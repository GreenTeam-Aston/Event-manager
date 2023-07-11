package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.ContactDTO;
import aston.greenteam.eventmanager.entities.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public ContactDTO mapContactToDTO(Contact contact) {
        return new ContactDTO(contact.getId(), contact.getValue());
    }
}
