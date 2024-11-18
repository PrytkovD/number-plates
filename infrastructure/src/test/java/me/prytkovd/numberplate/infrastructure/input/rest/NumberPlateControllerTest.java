package me.prytkovd.numberplate.infrastructure.input.rest;

import me.prytkovd.numberplate.application.input.AllocatingNumberPlates;
import me.prytkovd.numberplate.domain.model.NumberPlate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NumberPlateController.class)
public class NumberPlateControllerTest {
    @MockBean
    private AllocatingNumberPlates allocatingNumberPlates;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void allocateNextAvailableNumberPlate_Should_Respond_OK_With_TextPlain_When_Available() throws Exception {
        NumberPlate expectedPlate = new NumberPlate("А000АА 116 RUS");

        when(allocatingNumberPlates.allocateNextAvailable())
            .thenReturn(Optional.of(expectedPlate));

        mockMvc
            .perform(get("/next"))
            .andExpectAll(
                status().isOk(),
                content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE),
                content().string(expectedPlate.code())
            );
    }

    @Test
    public void allocateNextAvailableNumberPlate_Should_Respond_NotFound_When_Not_Available() throws Exception {
        when(allocatingNumberPlates.allocateNextAvailable())
            .thenReturn(Optional.empty());

        mockMvc
            .perform(get("/next"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void allocateRandomAvailableNumberPlate_Should_Respond_OK_With_TextPlain_When_Available() throws Exception {
        NumberPlate expectedPlate = new NumberPlate("А000АА 116 RUS");

        when(allocatingNumberPlates.allocateRandomAvailable())
            .thenReturn(Optional.of(expectedPlate));

        mockMvc
            .perform(get("/random"))
            .andExpectAll(
                status().isOk(),
                content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE),
                content().string(expectedPlate.code())
            );
    }

    @Test
    public void allocateRandomAvailableNumberPlate_Should_Respond_NotFound_When_Not_Available() throws Exception {
        when(allocatingNumberPlates.allocateRandomAvailable())
            .thenReturn(Optional.empty());

        mockMvc
            .perform(get("/random"))
            .andExpect(status().isNotFound());
    }
}
