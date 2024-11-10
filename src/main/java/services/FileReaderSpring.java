package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Ticket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileReaderSpring {

    @Value("classpath:ticketData.json")
    private Resource ticketsResource;

    public FileReaderSpring() {
    }


    public List<Ticket> loadTickets() {
        try (InputStream inputStream = ticketsResource.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, new TypeReference<List<Ticket>>(){});
        } catch (IOException e) {
            throw new RuntimeException("Failed to load tickets from resource", e);
        }
    }


}
