package com.gamingmatcher.demo.auth.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String username;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
}
