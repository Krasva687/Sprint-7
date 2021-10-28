package com.example.demo.persistance

import org.springframework.data.annotation.Id
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue

@Entity
data class Entity(
    @javax.persistence.Id @Id
    @GeneratedValue
    var id: Long? = null,
    @Column
    var name: String?
)