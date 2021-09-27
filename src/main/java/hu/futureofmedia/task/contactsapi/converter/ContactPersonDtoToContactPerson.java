package hu.futureofmedia.task.contactsapi.converter;

import hu.futureofmedia.task.contactsapi.dto.ContactPersonDto;
import hu.futureofmedia.task.contactsapi.entities.Company;
import hu.futureofmedia.task.contactsapi.entities.ContactPerson;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ContactPersonDtoToContactPerson implements Converter<ContactPersonDto, ContactPerson> {

    @Synchronized
    @Nullable
    @Override
    public ContactPerson convert(ContactPersonDto source) {
        if (source == null) return null;
        final ContactPerson contactPerson = new ContactPerson();
        contactPerson.setId(source.getId());
        contactPerson.setFirstName(source.getFirstName());
        contactPerson.setLastName(source.getLastName());
        contactPerson.setEmail(source.getEmail());
        contactPerson.setPhoneNumber(source.getPhoneNumber());
        if (source.getCompanyId() != null) {
            Company company = new Company();
            company.setId(source.getCompanyId());
            contactPerson.setCompany(company);
        }

        contactPerson.setComment(source.getComment());
        return contactPerson;
    }
}
