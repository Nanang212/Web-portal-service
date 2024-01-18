package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.repositories.ForumsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ForumsService {
    private ForumsRepository forumsRepository;


}
