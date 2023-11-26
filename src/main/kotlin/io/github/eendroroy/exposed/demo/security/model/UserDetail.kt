package io.github.eendroroy.exposed.demo.security.model

import io.github.eendroroy.exposed.demo.persistence.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.security.Principal

class UserDetail(val user: User) : UserDetails, Principal {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.roles.mapTo(ArrayList()) { SimpleGrantedAuthority(it.role) }
    }

    override fun getPassword(): String {
        return ""
    }

    override fun getUsername(): String {
        return user.userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getName(): String {
        return user.userName
    }
}
