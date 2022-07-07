package com.alkemy.ong.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.ong.domain.model.Activity;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
    

}
