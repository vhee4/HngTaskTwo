package com.HngTaskTwo.HngTaskTwo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
public class UpdateRequestDto {
        @JsonProperty("user_id")
        private Long userId;
        private String name;
        private String email;

    }
