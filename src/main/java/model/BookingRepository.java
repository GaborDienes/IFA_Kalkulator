package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BookingRepository {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
    private static BookingRepository instance;

    public static BookingRepository getInstance() {
        if (instance == null) {
            instance = new BookingRepository();
        }
        return instance;
    }

    private List<Nap> napok;



    private BookingRepository() {
        try {
            File initialFile = new File("Foglalasok.json");
            InputStream targetStream = new FileInputStream(initialFile);
            loadNapok(targetStream);
        } catch (IOException e) {
            throw new AssertionError("Failed to load resource Foglalasok.json", e); // Can't happen
        }
    }

    private void loadNapok(InputStream is) throws IOException {
        napok = OBJECT_MAPPER.readValue(is, new TypeReference<List<Nap>>() {
        });
    }

    public List<Nap> getAll() {
        return napok;
    }

}
