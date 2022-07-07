package com.alkemy.ong.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE testimonials SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Table(name = "testimonials")
@NoArgsConstructor
public class Testimonial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Column(name = "name")
    private String name;

    @Nullable
    @Column(name = "image")
    private String image;

    @NotNull
    @Column(name = "content")
    private String content;

    private Boolean deleted = Boolean.FALSE;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
