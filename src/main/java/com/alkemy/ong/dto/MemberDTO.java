package com.alkemy.ong.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    @JsonProperty("id")
    private Long id;

    @NotNull(message = "Name cannot be null")
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("image")
    private String image;

    @Nullable
    @JsonProperty("facebook_url")
    private String facebookUrl;

    @Nullable
    @JsonProperty("instagram_url")
    private String instagramUrl;

    @Nullable
    @JsonProperty("linkedin_url")
    private String linkedinUrl;

    @Nullable
    @JsonProperty("description")
    private String description;


}
