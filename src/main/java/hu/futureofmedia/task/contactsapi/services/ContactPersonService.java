package hu.futureofmedia.task.contactsapi.services;

import hu.futureofmedia.task.contactsapi.converter.ContactPersonDtoToContactPerson;
import hu.futureofmedia.task.contactsapi.converter.ContactPersonToContactPersonDto;
import hu.futureofmedia.task.contactsapi.dto.ContactPersonDto;
import hu.futureofmedia.task.contactsapi.entities.ContactPerson;
import hu.futureofmedia.task.contactsapi.entities.Status;
import hu.futureofmedia.task.contactsapi.repositories.ContactPersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContactPersonService {

    private final ContactPersonRepository contactPersonRepository;
    private final ContactPersonDtoToContactPerson contactPersonDtoToContactPerson;
    private final ContactPersonToContactPersonDto contactPersonToContactPersonDto;

    public ContactPersonService(ContactPersonRepository contactPersonRepository, ContactPersonDtoToContactPerson contactPersonDtoToContactPerson, ContactPersonToContactPersonDto contactPersonToContactPersonDto) {
        this.contactPersonRepository = contactPersonRepository;
        this.contactPersonDtoToContactPerson = contactPersonDtoToContactPerson;
        this.contactPersonToContactPersonDto = contactPersonToContactPersonDto;
    }

    public List<ContactPerson> findAll() {
        return contactPersonRepository.findAll();
    }

    public ContactPerson findById(Long id) {
        Optional<ContactPerson> optionalContactPerson = contactPersonRepository.findById(id);
        if (optionalContactPerson.isPresent()) {
            return optionalContactPerson.get();
        } else {
            throw new RuntimeException("Contact person not found");
        }
    }

    public ContactPersonDto saveContactPersonDto(ContactPersonDto contactPersonDto) {
        ContactPerson contactPerson = contactPersonDtoToContactPerson.convert(contactPersonDto);
        LocalDateTime now = LocalDateTime.now();

        contactPerson.setStatus(Status.ACTIVE);
        contactPerson.setCreationTime(now);
        contactPerson.setLastModified(now);

        ContactPerson savedContactPerson = contactPersonRepository.save(contactPerson);
        return contactPersonToContactPersonDto.convert(savedContactPerson);
    }

    public ContactPersonDto update(ContactPersonDto contactPersonDto) {
        ContactPerson oldContactPerson = findById(contactPersonDto.getId());
        ContactPerson newContactPerson = contactPersonDtoToContactPerson.convert(contactPersonDto);
        newContactPerson.setCompany(oldContactPerson.getCompany());
        newContactPerson.setStatus(oldContactPerson.getStatus());
        newContactPerson.setCreationTime(oldContactPerson.getCreationTime());
        newContactPerson.setLastModified(LocalDateTime.now());
        ContactPerson saved = contactPersonRepository.save(newContactPerson);
        return contactPersonToContactPersonDto.convert(saved);
    }

    public void deleteContactPersonById(Long id) {
        contactPersonRepository.deleteById(id);
    }
}
