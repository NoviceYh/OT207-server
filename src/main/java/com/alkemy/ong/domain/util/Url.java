package com.alkemy.ong.domain.util;


import com.alkemy.ong.dto.PageDTO;
import com.alkemy.ong.exception.NotFoundException;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Url {

    public static final String MEMBERS_URI = "/members";
    public static final String ACTIVITIES_URI = "/activities";
    public static final String CATEGORY_URI = "/categories";
    public static final String CONTACTS_URI = "/contacts";
    public static final String ORGANIZATION_URI = "/organization";
    public static final String SLIDES_URI = "/slides";
    public static final String USER_URI = "/user";
    public static final String TESTIMONIALS_URI = "/testimonials";
    public static final String NEWS_URI = "/news";
    public static final String COMMENTS_URI = "/comments";
    public static final String PAGE_URI = "/page?page=";
    public static final String URL= "http://localhost:8080";
    public static final int MAX_PAGE = 10;

    public static PageDTO pagination (PageDTO pageDTO, Page pageEntity, Integer page, List entityPage2Dto,@NotEmpty  String url ){
        if (pageEntity.isEmpty()) {
            throw new NotFoundException("The list is empty");
        }
        if (pageEntity.hasNext()) {
            pageDTO.setNext(url + (page + 1));
        }
        if (!pageEntity.isFirst()) {
            pageDTO.setPrevious(url + (page - 1));
        }
        pageDTO.setCurrent(Integer.toString(page));
        pageDTO.setT(entityPage2Dto);
        return pageDTO;
    }


}
