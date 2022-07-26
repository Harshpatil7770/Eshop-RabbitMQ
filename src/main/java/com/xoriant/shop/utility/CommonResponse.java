package com.xoriant.shop.utility;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {

	private T response;
	
	private int statusCode;

	private HttpStatus message;

}
