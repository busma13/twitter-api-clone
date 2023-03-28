package com.cooksys.assessment1Team3.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class TweetResponseDto {
    private LocalDateTime posted;
    private String content;
}
