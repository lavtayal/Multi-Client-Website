package com.cg.opmtoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.opmtoolapi.domain.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String>{

}
