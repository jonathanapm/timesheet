package br.com.timesheet.auth

import br.com.timesheet.persistence.entities.Employee
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationTokenService {

    @Value("\${jwt.expiration}")
    private lateinit var expiration: String

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generatedToken(authentication: Authentication): String {
        val user = authentication.principal as Employee
        val expirationDate = Date(Date().time + expiration.toLong())

        return Jwts.builder()
            .setIssuer("TimesheetApp") //quem gerou o token
            .setSubject(user.id.toString()) //quem é o dono do token
            .setIssuedAt(Date()) // Data de geracao do token
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }
}