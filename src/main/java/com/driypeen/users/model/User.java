package com.driypeen.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String login;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Role role;
}
