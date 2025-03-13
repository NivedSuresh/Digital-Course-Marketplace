package org.dcmp.domain.entity



import jakarta.persistence.*
import lombok.NoArgsConstructor


@Entity
@Table(name = "users")
@NoArgsConstructor
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val username: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val hashedPassword: String,

    @Enumerated(EnumType.STRING)
    val authority: Role,

    @OneToMany(mappedBy = "creator", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    val courses: MutableList<Course> = mutableListOf(),

    )


