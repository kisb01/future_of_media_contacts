package hu.futureofmedia.task.contactsapi;

import hu.futureofmedia.task.contactsapi.dto.ContactPersonDto;
import hu.futureofmedia.task.contactsapi.entities.Company;
import hu.futureofmedia.task.contactsapi.entities.ContactPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactPersonIntegrationTests {

    private static final TestRestTemplate restTemplate = new TestRestTemplate();
    @LocalServerPort
    private Integer port;
    private String BASE_URL;

    @BeforeEach
    public void init() {
        BASE_URL = "http://localhost:" + port + "/contactperson";
    }

    @Test
    public void getContactPerson_returnsFullList() {
        List<ContactPerson> contactPeople = List.of(restTemplate.getForObject(BASE_URL, ContactPerson[].class));
        assertEquals(0, contactPeople.size());
    }

    @Test
    public void addNewContact_ShouldReturnContactName() {
        Company company = new Company(null, "Company #1");
        ContactPersonDto contactPerson = new ContactPersonDto("Janos", "Biro", "janos@biro.hu", 1l);
        ContactPersonDto result = restTemplate.postForObject(BASE_URL, contactPerson, ContactPersonDto.class);
        assertEquals(contactPerson.getFirstName(), result.getFirstName());
    }
}
