package main.crud.repository

import main.crud.model.Skill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SkillRepository: JpaRepository<Skill, Long> {
}