package me.prytkovd.numberplate.infrastructure.input.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import me.prytkovd.numberplate.application.input.AllocatingNumberPlates;
import me.prytkovd.numberplate.domain.model.NumberPlate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "Number Plates", description = "Operations related to allocating number plates")
@RestController
@AllArgsConstructor
public class NumberPlateController {
    private final AllocatingNumberPlates allocatingNumberPlates;

    @Operation(
        summary = "Allocate the next available number plate",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully allocated the next available number plate",
                content = @Content(examples = @ExampleObject("А000АА116"))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "No available number plates found",
                content = @Content(schema = @Schema())
            ),
        }
    )
    @GetMapping(value = "/next", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> allocateNextAvailableNumberPlate() {
        Optional<String> code = allocatingNumberPlates
            .allocateNextAvailable()
            .map(NumberPlate::code);
        return ResponseEntity.of(code);
    }

    @Operation(
        summary = "Allocate a random available number plate",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully allocated a random available number plate",
                content = @Content(examples = @ExampleObject("А000АА116"))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "No available number plates found",
                content = @Content(schema = @Schema())
            ),
        }
    )
    @GetMapping(value = "/random", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> allocateRandomAvailableNumberPlate() {
        Optional<String> code = allocatingNumberPlates
            .allocateRandomAvailable()
            .map(NumberPlate::code);
        return ResponseEntity.of(code);
    }
}