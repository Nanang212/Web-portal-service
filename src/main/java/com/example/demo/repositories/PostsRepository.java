package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Posts;

public interface PostsRepository extends JpaRepository <Posts, Integer> {

}
