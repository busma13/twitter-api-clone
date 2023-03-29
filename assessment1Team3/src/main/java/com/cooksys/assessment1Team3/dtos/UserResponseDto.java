package com.cooksys.assessment1Team3.dtos;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {
	private String username;
    private ProfileDto profile;
    private Timestamp joined;
}
