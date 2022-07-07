package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.model.Slides;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlidesRepository extends JpaRepository<Slides, Long> {

    List<Slides> findByOrganizations(Organization organization);

}