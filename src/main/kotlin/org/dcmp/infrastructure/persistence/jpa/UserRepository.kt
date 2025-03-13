package org.dcmp.infrastructure.persistence.jpa


import org.dcmp.domain.entity.Role
import org.dcmp.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional


@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
    fun findByEmail(email: String): Optional<User>
    fun findByAuthority(authority: Role): MutableList<User>
    fun existsByEmail(email: String): Boolean
}
