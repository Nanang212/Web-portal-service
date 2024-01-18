package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Forums;

@Repository
public interface ForumsRepository extends JpaRepository <Forums, Integer> {

}
