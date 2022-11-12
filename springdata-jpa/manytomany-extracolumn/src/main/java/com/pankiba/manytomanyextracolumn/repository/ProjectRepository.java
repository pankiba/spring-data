package com.pankiba.manytomanyextracolumn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.manytomanyextracolumn.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
