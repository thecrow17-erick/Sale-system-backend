package com.example.sales_crud.services

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import java.security.Key
import java.util.*


class JwtService(
    @Value("11AV353CR37AUGU32121441241252142411232145efggrew34341fesdewrew")
    private val SECRET_KEY:String
) {
    fun getToken(user: UserDetails): String {
        return getToken(HashMap(), user);
    }
    private fun getToken(extraClaims: Map<String, Any?>, user: UserDetails): String {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 24 * 30))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun getKey(): Key {
        val keyBytes = Decoders.BASE64.decode(this.SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun getCodeFromToken(token: String): String{
        return getClaims(token,Claims::getSubject);
    }
    private fun getAllClaimsFromToken(token: String):Claims{
        return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun <T> getClaims(token:String, claimsResolver: (Claims)->T): T{
        val claims: Claims = getAllClaimsFromToken(token);
        return claimsResolver(claims);
    }

    private fun getExpiration(token: String): Date {
        return getClaims(token) { obj: Claims -> obj.expiration }
    }

    private fun isTokenExpired(token: String): Boolean {
        return getExpiration(token).before(Date())
    }
    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username: String = getCodeFromToken(token)
        return (username == userDetails.username && !isTokenExpired(token))
    }
}