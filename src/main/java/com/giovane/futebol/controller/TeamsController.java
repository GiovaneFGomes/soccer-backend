package com.giovane.futebol.controller;

import com.giovane.futebol.model.Teams;
import com.giovane.futebol.service.TeamsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/soccer")
public class TeamsController {

    private final TeamsService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/team", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Teams save(@RequestBody @Valid Teams teams) {
        return service.save(teams);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/team/{id}")
    public void update(@RequestBody @Valid Teams teams, @PathVariable("id") Integer id) {
        service.updateTeam(teams, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/team/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.deleteId(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/teams")
    public List<Teams> findAllTeams() {
        return service.select();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/team/{id}")
    public Optional<Teams> searchTeamById(@PathVariable("id") Integer id) {
        return service.select2(id);
    }

}
