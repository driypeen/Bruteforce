package com.driypeen.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    private String name;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_has_privilege",
            joinColumns = @JoinColumn(
                    name = "role", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege", referencedColumnName = "privilege_id"))
    private Set<Privilege> privileges;
}
