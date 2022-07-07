package com.alkemy.ong.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {
    private String next;
    private String current;
    private String previous;
    private List<T> t;

}
