package com.alkemy.ong.domain.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@ToString
@SQLDelete(sql = "UPDATE contacts SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 75)
    @NotNull(message = "Name cannot be empty")
    private String name;

    @Column(length = 50)
    private String phone;

    @Column(nullable = false, unique = true)
    @Email(message = "Has to be an email format")
    @NotBlank
    private String email;

    private String message;

    private Boolean deleted = Boolean.FALSE;

}
