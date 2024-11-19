package com.example.sales_crud.services

import com.example.sales_crud.dto.user.CreateUserDto
import com.example.sales_crud.error.exception.BadRequestException
import com.example.sales_crud.error.exception.NotFoundException
import com.example.sales_crud.model.Role
import com.example.sales_crud.model.User
import com.example.sales_crud.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.util.Optional
import java.util.UUID


@Service
class UserService(
    @Autowired
    private val userResposity: UserRepository,
    private final val CHARACTERS: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",
    private val roleService: RoleService
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

    fun findUser(username: String):Optional<User> = this.userResposity.findUserByName(username);

    fun createUser(createUserDto: CreateUserDto): User{
        val findUser = this.userResposity.findUserByName(createUserDto.username);
        if(findUser.isPresent)
            throw BadRequestException("User already exists");
        val findRole = this.roleService.findIdRol(createUserDto.role);

        val userCreate = User(
            name = createUserDto.username,
            password = BCryptPasswordEncoder().encode(createUserDto.password),
            role = findRole
        );

        return this.userResposity.save(userCreate);
    }

    fun findAllUsers(search:String?,page: Int, size: Int,username:String): Page<User> {
        val pageable = PageRequest.of(page,size);
        return if(search.isNullOrBlank()){
            this.userResposity.findAllByNameNotIn(listOf("admin",username),pageable);
        }else{
            this.userResposity.searchFindUser(listOf("admin",username),search,pageable);
        }
    }

    fun findUserId(id: UUID): User{
        val findUser = this.userResposity.findUserById(id);
        println(id)
        if(findUser.isEmpty)
            throw NotFoundException("Usuario no encontrado");

        return findUser.get();
    }
}