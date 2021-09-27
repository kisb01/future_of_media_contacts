package hu.futureofmedia.task.contactsapi.controller;

import hu.futureofmedia.task.contactsapi.converter.StringToLong;
import hu.futureofmedia.task.contactsapi.dto.ContactPersonDto;
import hu.futureofmedia.task.contactsapi.entities.ContactPerson;
import hu.futureofmedia.task.contactsapi.services.ContactPersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactperson")
public class ContactPersonController {

    private final ContactPersonService contactPersonService;

    public ContactPersonController(ContactPersonService contactPersonService) {
        this.contactPersonService = contactPersonService;
    }

    @GetMapping
    public List<ContactPerson> findAll() {
        return contactPersonService.findAll();
    }

    @GetMapping("/{id}")
    public ContactPerson getById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new RuntimeException("Must enter a valid number");
        }
        return contactPersonService.findById(StringToLong.convert(id));
    }

    @PostMapping
    public ContactPersonDto add(@RequestBody ContactPersonDto contactPersonDto) {
        contactPersonDto.setId(null);
        return contactPersonService.saveContactPersonDto(contactPersonDto);
    }

    @PutMapping("/{id}")
    public ContactPersonDto update(@RequestBody ContactPersonDto contactPersonDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new RuntimeException("Must enter a valid number");
        }
        contactPersonDto.setId(StringToLong.convert(id));
        return contactPersonService.update(contactPersonDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new RuntimeException("Must enter a valid number");
        }
        contactPersonService.deleteContactPersonById(StringToLong.convert(id));
    }
}
