package main.crud.controller

import main.crud.model.Skill
import main.crud.repository.SkillRepository
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
class SkillController(
    @Autowired
    private val skillRepository: SkillRepository
) {

    @GetMapping("/skills")
    fun getAll(): List<Skill>? {
        return skillRepository.findAll()
    }

    @GetMapping("/skills/{id}")
    fun getSkill(
        @PathVariable id: Long
    ): Skill {
        return skillRepository.getById(id)
    }

    @PostMapping("/skills")
    fun create(
        @RequestBody
        @Valid
        skill: Skill
    ): Skill {
        return skillRepository.save(skill)
    }

    @PutMapping("/skills")
    fun update(
        @RequestBody
        @Valid
        skill: Skill
    ): Skill {
        return skillRepository.save(skill)
    }

    @DeleteMapping("/skills/{id}")
    fun delete(
        @PathVariable id: Long
    ) {
        return skillRepository.deleteById(id)
    }
}