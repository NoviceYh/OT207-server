package com.alkemy.ong.domain.service;

import com.alkemy.ong.dto.TestimonialDTO;
import org.springframework.transaction.annotation.Transactional;

public interface ITestimonialService {

    @Transactional
    TestimonialDTO save(TestimonialDTO dto);

    @Transactional
    TestimonialDTO update(Long id, TestimonialDTO dto);

    @Transactional
    void deleteTestimonial(Long id);
}
