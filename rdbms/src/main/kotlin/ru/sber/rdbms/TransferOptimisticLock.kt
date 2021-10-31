package ru.sber.rdbms

import java.lang.Exception
import java.sql.DriverManager
import java.sql.SQLException

class TransferOptimisticLock {
    val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/db",
        "postgres",
        "123"
    )
    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {
        connection.use {
            val autoCommit = connection.autoCommit
            connection.autoCommit = false
            try {
                var amount1 = 0
                var id1 = 0
                var version1 = 0
                var amount2 = 0
                var id2 = 0
                var version2 = 0
                val st1 = connection.prepareStatement("select * from account1 where id = $accountId1")
                st1.use { statement ->
                    val resultSet = statement.executeQuery()
                    resultSet.use {
                        it.next()
                        id1 = it.getInt("id")
                        amount1 = it.getInt("amount")
                        version1 = it.getInt("version")
                        if(it.next())throw Exception("more than 1 row in result")
                    }
                }
                val st2 = connection.prepareStatement("select * from account1 where id = $accountId2")
                st2.use { statement ->
                    val resultSet2 = statement.executeQuery()
                    resultSet2.use {
                        it.next()
                        id2 = it.getInt("id")
                        amount2 = it.getInt("amount")
                        version2 = it.getInt("version")
                        if(it.next())throw Exception("more than 1 row in result")
                    }
                }
                if(amount1 - amount > 0) {
                    val st3 =
                        connection.prepareStatement("update account1 set amount = amount - $amount, version = version + 1 where id = $id1 and version = $version1")
                    st3.use { statement ->
                        statement.executeUpdate()
                    }
                    val st4 =
                        connection.prepareStatement("update account1 set amount = amount + $amount, version = version + 1 where id = $id2 and version = $version2")
                    st4.use { statement ->
                        statement.executeUpdate()
                    }
                    connection.commit()
                }else throw Exception("Amount can't be lower then 0")
            } catch (exception: SQLException) {
                println(exception.message)
                connection.rollback()
            } finally {
                connection.autoCommit = autoCommit
            }
        }
    }
}
