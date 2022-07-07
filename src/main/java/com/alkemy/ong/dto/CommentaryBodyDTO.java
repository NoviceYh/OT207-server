package com.alkemy.ong.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentaryBodyDTO {
    
    @NotBlank(message = "Commentary cannot be null")
    private String body;
}
