package com.kamesh.pojo.serializeDeserialize;

import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamesh.pojo.Pet;

public class PetSerializeDeserialize {

	public Pet petDeserializeToPojo(String jsonValue) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMappper = new ObjectMapper();
		Pet petPojo = objectMappper.readValue(jsonValue, Pet.class);
		return petPojo;
		}
	
	}

