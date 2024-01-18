package com.example.demo.models.dto.request;

import com.example.demo.models.ForumType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForumsRequest {
    private String name;
    private String description;
    private String icon;
    private ForumType type;
    private Integer subForumId;
}
