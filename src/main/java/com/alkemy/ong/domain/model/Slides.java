package com.alkemy.ong.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter

@Entity
@Table(name = "slides")
public class Slides implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private String text;

    private Integer orderSlides;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizations_id", referencedColumnName = "id")
    private Organization organizations;

}
