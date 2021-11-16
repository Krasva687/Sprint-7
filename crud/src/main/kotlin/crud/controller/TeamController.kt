package main.crud.controller

import main.crud.model.Team
import main.crud.repository.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class TeamController(
    @Autowired
    private val teamRepository: TeamRepository
) {

    @GetMapping("/teams")
    fun getAll(): List<Team>? {
        return teamRepository.findAll()
    }

    @GetMapping("/teams/{id}")
    fun getSkill(
        @PathVariable id: Long
    ): Team {
        return teamRepository.getById(id)
    }

    @PostMapping("/teams")
    fun create(
        @RequestBody
        @Valid
        team: Team
    ): Team {
        return teamRepository.save(team)
    }

    @PutMapping("/teams")
    fun update(
        @RequestBody
        @Valid
        team: Team
    ): Team {
        return teamRepository.save(team)
    }

    @DeleteMapping("/teams/{id}")
    fun delete(
        @PathVariable id: Long
    ) {
        return teamRepository.deleteById(id)
    }
}