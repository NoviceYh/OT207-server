package com.alkemy.ong.domain.service.impl;

import com.alkemy.ong.dto.TestimonialDTO;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.domain.model.Testimonial;
import com.alkemy.ong.domain.repository.TestimonialRepository;
import com.alkemy.ong.domain.service.ITestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TestimonialServiceImpl implements ITestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    private TestimonialMapper mapper;

    @Override
    @Transactional
    public TestimonialDTO save(TestimonialDTO dto) {
        Testimonial testimonial = mapper.testimonialDto2Entity(dto);
        return mapper.testimonialEntity2Dto(testimonialRepository.save(testimonial));
    }

    @Override
    @Transactional
    public TestimonialDTO update(Long id, TestimonialDTO dto) {
        Optional<Testimonial> entity = this.testimonialRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Testimonial id does not exist");
        }
        this.mapper.entityTestimonialRefreshValues(entity.get(), dto);
        Testimonial result = this.testimonialRepository.save(entity.get());
        return this.mapper.testimonialEntity2Dto(result);
    }

    @Override
    @Transactional
    public void deleteTestimonial(Long id) throws NotFoundException {
        try {
            testimonialRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Testimonial id does not exist");
        }
    }
}
