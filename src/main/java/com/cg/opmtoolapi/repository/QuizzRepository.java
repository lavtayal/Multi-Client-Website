package com.cg.opmtoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.opmtoolapi.domain.Quizz;

@Repository
public interface QuizzRepository extends JpaRepository<Quizz,Long>{

}
