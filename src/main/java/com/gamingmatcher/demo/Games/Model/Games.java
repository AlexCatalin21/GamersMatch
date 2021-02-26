package com.gamingmatcher.demo.Games.Model;


import com.gamingmatcher.demo.auth.model.AppUser;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "games")
public class Games {
    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer year;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<AppUser> users = new ArrayList<>();


}
