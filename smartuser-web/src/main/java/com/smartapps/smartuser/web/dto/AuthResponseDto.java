package com.smartapps.smartuser.web.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AuthResponse")
public class AuthResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String jwtToken;
}
