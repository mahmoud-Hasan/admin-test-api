package com.santechture.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;


/* 
 * Login response body class to hold token & user details as a JSON response
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

	private String token;
	
	private String username;
	
	private Integer adminId;

}
