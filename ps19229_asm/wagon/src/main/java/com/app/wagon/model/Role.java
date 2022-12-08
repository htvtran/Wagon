package com.app.wagon.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "Roles")
@Getter
@Setter
@ToString
public class Role {
    @Id
    String id;

    String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    List<Authority> authorities;
}
