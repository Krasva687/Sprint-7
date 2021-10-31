package ru.sber.rdbms

import java.sql.DriverManager
import java.sql.ResultSet

fun main() {
    val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/db",
        "postgres",
        "123"
    )
    connection.use { conn ->
        val prepareStatement = conn.prepareStatement("select * from account1 \n where id = 1")
        prepareStatement.use { statement ->
            val resultSet = statement.executeQuery()
            resultSet.use {
                println("Has result: ${it.next()}")
                val result = it.getInt(1)
                println("Execution result: $result")
            }
        }
    }
}

