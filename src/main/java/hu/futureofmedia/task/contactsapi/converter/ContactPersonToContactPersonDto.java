package hu.futureofmedia.task.contactsapi.converter;

import hu.futureofmedia.task.contactsapi.dto.ContactPersonDto;
import hu.futureofmedia.task.contactsapi.entities.ContactPerson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ContactPersonToContactPersonDto implements Converter<ContactPerson, ContactPersonDto> {

    @Override
    public ContactPersonDto convert(ContactPerson source) {
        if (source == null) return null;
        final ContactPersonDto contactPersonDto = new ContactPersonDto();
        contactPersonDto.setId(source.getId());
        contactPersonDto.setFirstName(source.getFirstName());
        contactPersonDto.setLastName(source.getLastName());
        contactPersonDto.setEmail(source.getEmail());
        contactPersonDto.setPhoneNumber(source.getPhoneNumber());
        if (source.getCompany() != null) {
            contactPersonDto.setCompanyId(source.getCompany().getId());
        }
        contactPersonDto.setComment(source.getComment());
        return contactPersonDto;
    }
}
