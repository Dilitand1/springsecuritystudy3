package ru.litvinov.springsecuritystudy2.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.litvinov.springsecuritystudy2.model.Developer;

import javax.swing.event.DocumentEvent;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {

    private List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L,"Ivan","Ivanov"),
            new Developer(2L,"Dima","Dimov"),
            new Developer(3L,"Privet","Privetov")
    ).collect(Collectors.toList());

    @RequestMapping
    public List<Developer> getAll(){
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    public Developer getById(@PathVariable Long id) {
        return DEVELOPERS.stream().filter(developer -> developer.getId().equals(id)).findFirst().orElse(null);
    }

}
