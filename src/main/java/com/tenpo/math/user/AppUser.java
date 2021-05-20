package com.tenpo.math.user;

import javax.persistence.*;
import java.util.List;

import com.tenpo.math.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(uniqueConstraints={@UniqueConstraint(columnNames ={"username"})})
public class AppUser extends BaseEntity {

    private String username;

    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Role> roles;

    private boolean active;
}

