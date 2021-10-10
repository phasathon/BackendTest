/**
 * 
 */
package com.backend.test.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.backend.test.entities.ErrorMappingJSON;
import com.backend.test.entities.ErrorMappingJSON.ErrorDetail;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorMapping {

	public static String getErrorMapping(String ErrorCode) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		ErrorMappingJSON errorMappingJSON = objectMapper.readValue(new File("config/errorMapping.json"), ErrorMappingJSON.class);
		
		List<ErrorDetail> errorFilter = errorMappingJSON.getErrorList().stream()
				.filter(t -> t.getCode().equals(ErrorCode))
				.collect(Collectors.toList());
		return String.format("%s [%s]", errorFilter.get(0).getDspMessage(),errorFilter.get(0).getCodeMapping());
	}

}
