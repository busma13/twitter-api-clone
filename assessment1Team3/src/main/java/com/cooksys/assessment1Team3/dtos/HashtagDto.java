package com.cooksys.assessment1Team3.dtos;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HashtagDto {

	private Long id;

	private String label;

	private Timestamp firstUsed;

	private Timestamp lastUsed;

}
