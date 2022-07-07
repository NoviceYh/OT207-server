package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {

}
