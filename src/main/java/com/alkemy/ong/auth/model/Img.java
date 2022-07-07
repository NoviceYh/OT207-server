package com.alkemy.ong.auth.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Img {
    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    private String titulo;

    private String imagenPath;

    @Transient
    private String imagenUrl;
}
