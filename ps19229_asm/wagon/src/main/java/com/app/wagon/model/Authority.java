package com.app.wagon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// AUTHORITIES
@Data
@Entity
@Table(name = "AUTHORITIES")
@Getter
@Setter
@ToString
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "username")
    @ToString.Exclude
    User user;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    @ToString.Exclude
    Role role;

}
