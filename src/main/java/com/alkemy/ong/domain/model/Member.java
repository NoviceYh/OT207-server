package com.alkemy.ong.domain.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SQLDelete(sql = "UPDATE member SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Column(name = "name")
    @NotBlank(message = "Name cannot be empty")
    @NotEmpty(message = "Name cannot be null")
    @Pattern(regexp = "[a-zA-Z\\s]*", message = "Name cannot contain numbers or characters other than letters")
    private String name;

    @Nullable
    @Column(name = "facebook_url")
    private String facebookUrl;

    @Nullable
    @Column(name = "instagram_url")
    private String instragramUrl;

    @Nullable
    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @NotNull(message = "Image cannot be null")
    @Column(name = "image")
    private String image;

    @Nullable
    @Column(name = "description")
    private String description;

    private Boolean deleted = Boolean.FALSE;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;
}
