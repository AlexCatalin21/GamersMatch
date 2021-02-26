package com.gamingmatcher.demo.Matches.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "matches")
public class Match {
    @Id
    private Long Id;
    @NotNull
    private Long user1_id;
    @NotNull
    private Long user2_id;
}
