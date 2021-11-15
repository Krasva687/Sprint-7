package ru.sber.rdbms

import java.lang.Exception
import java.sql.DriverManager
import java.sql.SQLException

class TransferPessimisticLock {
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
                val st1 = connection.prepareStatement("select * from account1 where id = $accountId1")
                st1.use { statement ->
                    val resultSet = statement.executeQuery()
                    resultSet.use {
                        it.next()
                        amount1 = it.getInt("amount")
                        if(it.next())throw Exception("More then 1 result row")
                    }
                }
                val st2 = connection.prepareStatement("select * from account1 where id in ($accountId1,$accountId2) for update")
                st2.use{statement ->
                    statement.executeQuery()
                }
                if(amount1 - 100 > 0) {
                    val st3 =
                        connection.prepareStatement("update account1 set amount = amount - $amount where id = $accountId1 ")
                    st3.use { statement ->
                        statement.executeUpdate()
                    }
                    val st4 =
                        connection.prepareStatement("update account1 set amount = amount + $amount where id = $accountId2 ")
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
