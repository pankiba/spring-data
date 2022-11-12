package com.pankiba.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.manytomany.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
