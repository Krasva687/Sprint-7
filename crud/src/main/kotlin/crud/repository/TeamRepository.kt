package main.crud.repository

import main.crud.model.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeamRepository: JpaRepository<Team, Long> {
}