package com.example.sales_crud.services

import com.example.sales_crud.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.security.SecureRandom


@Service
class UserService(
    @Autowired
    private val userResposity: UserRepository,
    private final val CHARACTERS: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
) {


    fun generateCodeUser():String{
        val random: SecureRandom = SecureRandom()
        val code = StringBuilder(8)
        for (i in 0..7) {
            val index: Int = random.nextInt(CHARACTERS.length)
            code.append(CHARACTERS[index])
        }
        return code.toString()
    }



}