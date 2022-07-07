package com.alkemy.ong.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@SQLDelete(sql = "UPDATE news SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")

@Entity
@Table(name="news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be empty")
    @Column(name = "name", length = 40)
    private String name;

    @NotNull(message = "Content cannot be empty")
    @Column(name = "content")
    private String content;

    @NotNull(message = "Image URL is required")
    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private Boolean deleted = Boolean.FALSE;
}
