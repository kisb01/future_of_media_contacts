package hu.futureofmedia.task.contactsapi;

import hu.futureofmedia.task.contactsapi.entities.ContactPerson;
import hu.futureofmedia.task.contactsapi.services.ContactPersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactPerson.class)
public class ContactPersonTest {

    @MockBean
    ContactPersonService contactPersonService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAll_ShouldReturnAll() throws Exception {
        ContactPerson contactPerson = new ContactPerson();

        when(contactPersonService.findAll()).thenReturn(List.of(contactPerson));

        mockMvc.perform(get("/contactperson")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
    }
}
