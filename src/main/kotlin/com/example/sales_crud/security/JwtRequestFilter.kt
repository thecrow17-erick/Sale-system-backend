package com.example.sales_crud.security

import com.example.sales_crud.services.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
@RequiredArgsConstructor
class JwtRequestFilter(
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService
):OncePerRequestFilter() {
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val token:String = this.getTokenFromRequest(req);
        if(token == ""){
            chain.doFilter(req,res);
            return;
        }
        val username: String = jwtService.getCodeFromToken(token);
        if(username != null && SecurityContextHolder.getContext().authentication == null){
            val userDetail:UserDetails = userDetailsService.loadUserByUsername(username);
            if(jwtService.isTokenValid(token, userDetail)){
                val authentication:UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    userDetail.authorities
                );
                SecurityContextHolder.getContext().authentication = authentication;
            }
        }
        req.setAttribute("user_id", username);
        chain.doFilter(req,res);
    }

    private fun getTokenFromRequest(req: HttpServletRequest):String{
        val token = req.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(token) && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return "";
    }
}