package com.giovane.futebol.controller;

import com.giovane.futebol.dto.TeamRequestDto;
import com.giovane.futebol.dto.TeamResponseDto;
import com.giovane.futebol.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/soccer")
public class TeamsController {

    private final TeamService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/team", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Save a football team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Team was created"),
            @ApiResponse(responseCode = "400", description = "An incorrect request has been sent")
    })
    public TeamRequestDto saveTeam(@RequestBody @Valid TeamRequestDto team) {
        return service.save(team);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/team/{id}")
    @Operation(summary = "Update a football team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Team has been updated"),
            @ApiResponse(responseCode = "400", description = "Team's ID does not exist or it was an incorrect request")
    })
    public void updateTeam(@RequestBody @Valid TeamRequestDto team, @PathVariable("id") Integer id) {
        service.update(team, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/team/{id}")
    @Operation(summary = "Delete a football team by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The football team has been deleted"),
            @ApiResponse(responseCode = "404", description = "There is no team with this ID yet")
    })
    public void deleteTeam(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/teams")
    @Operation(summary = "List all football teams")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. Shows all existing football teams"),
    })
    public List<TeamResponseDto> findAllTeams() {
        return service.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/team/{id}")
    @Operation(summary = "Return a single football team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. Shows a single existing football team"),
            @ApiResponse(responseCode = "400", description = "It was an incorrect request"),
            @ApiResponse(responseCode = "404", description = "Team's ID does not exist")
    })
    public Optional<TeamResponseDto> searchTeamById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

}
