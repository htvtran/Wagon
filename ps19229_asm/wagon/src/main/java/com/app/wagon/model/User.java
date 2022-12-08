package com.app.wagon.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "Users")
@Getter
@Setter
@ToString
public class User implements Serializable {

    @Id
    private String username;

    private String email;

    private String passwords;

    private String fullname;

    private Boolean gender;

    private String address;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Authority> authorities;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Order> orders;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm:ss")
    LocalDateTime createdDate;

}
