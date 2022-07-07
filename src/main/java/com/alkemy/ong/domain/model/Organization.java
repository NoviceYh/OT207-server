package com.alkemy.ong.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "organizations")
@Getter
@Setter

@SQLDelete(sql = "UPDATE organizations SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")

public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Image cannot be empty")
    private String image;

    private String address;

    @Digits(integer = 10, fraction = 0)
    @Size(min = 9, max = 10)
    @NotEmpty(message = "Phone cannot be empty")
    private String phone;

    @Column(nullable = false)
    @NotEmpty(message = "email cannot be empty")
    @Email
    private String email;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty(message = "welcome text cannot be empty")
    private String welcomeText;

    @Column(columnDefinition = "text")
    private String aboutUsText;


    @Nullable
    @Column(name = "facebook_url")
    private String facebookUrl;

    @Nullable
    @Column(name = "instagram_url")
    private String instagramUrl;

    @Nullable
    @Column(name = "linkedin_url")
    private String linkedinUrl;



    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private Boolean deleted = Boolean.FALSE;
}
