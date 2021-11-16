package main.crud.controller

import main.crud.model.User
import main.crud.repository.UserRepository
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
class UserController(
    @Autowired
    private val userRepository: UserRepository
) {

    @GetMapping("/users")
    fun getAll(): List<User>? {
        return userRepository.findAll()
    }

    @GetMapping("/skills/{id}")
    fun getSkill(
        @PathVariable id: Long
    ): User {
        return userRepository.getById(id)
    }

    @PostMapping("/users")
    fun create(
        @RequestBody
        @Valid
        user: User
    ): User {
        return userRepository.save(user)
    }

    @PutMapping("/users")
    fun update(
        @RequestBody
        @Valid
        user: User
    ): User {
        return userRepository.save(user)
    }

    @DeleteMapping("/users/{id}")
    fun delete(
        @PathVariable id: Long
    ) {
        return userRepository.deleteById(id)
    }
}