package kz.medicare.entity;

import jakarta.persistence.*;
import kz.medicare.security.enums.Role;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false,  length = 100)
    private String email;

    @Column(name = "phone", nullable = true, length = 20)
    private String phone;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @ColumnDefault("0")
    @Column(name = "blocked", nullable = false)
    private Boolean blocked = false;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

}