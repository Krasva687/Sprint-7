package com.example.demo.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(Controller::class)
class ControllerTest{
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `GET pass Spring should return content successfully with status 200 OK`() {
        val result = mockMvc.perform(MockMvcRequestBuilders.get(("/passSpring")))
        result
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("Pass Spring Boot!"))
    }
}