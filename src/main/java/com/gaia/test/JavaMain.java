package com.gaia.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaMain {
	public static final int NUMBER1 = 111;
	
	
	private static class Holder {
		public static final JavaMain INSTANCE = new JavaMain ();
	}
	
	enum Something {
		INSTANCE;
		public final Boolean NUMBER2 = Boolean.valueOf(true);
		
		public String getName() { return "Something";}
		
		public void printNumbers() {
			System.out.println("Number1:" + NUMBER1 + "\nNumber2: " + NUMBER2);
		}
	}
	
	public JavaMain() {}
	
	
	public static JavaMain getInstance() { return Holder.INSTANCE; }
	
	public static void joiningTest() {
        List<String> list = Arrays.asList("Ram","Shyam","Shiv","Mahesh");
        
        String result =  list.stream().sorted().collect(Collectors.joining());
        System.out.println(result);
        
        result=  list.stream().collect(Collectors.joining(","));
        System.out.println(result);
        
        result=  list.stream().collect(Collectors.joining("-","[","]"));
        System.out.println(result);        
    }	
	
	@Test
	public void jsonTest () throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper ();
		
		String b = "{\"took\":7,\"timed_out\":false,\"_shards\":{\"total\":10,\"successful\":10,\"skipped\":0,\"failed\":0},\"hits\":{\"total\":1,\"max_score\":3.0,\"hits\":[{\"_index\":\"at_atclaim_ext-2018.02.01\",\"_type\":\"AT_ATCLAIM_EXT\",\"_id\":\"69\",\"_score\":3.0,\"_source\":{\"ATCLAIM_NO\":69,\"ATCLAIM_CNTS\":\"기타 목\"}}]}}";

		JsonNode jsonNode = objectMapper.readTree(b);
		JsonNode node = jsonNode.get("hits").get("hits").get(0);
		node = node.get("_source");
		
		
		SourceVO vo = objectMapper.convertValue(node, SourceVO.class);
		
		System.out.println(vo);
		
	}
	
	@Test
	public void printNumbersTest () {
		
	}
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Something.INSTANCE.printNumbers();
		/*
		Stream.iterate("*", UnaryOperator.identity()).limit(5).collect(Collectors.joining(""));
		
		System.out.println(Something.INSTANCE.NUMBER2);
		
		
		joiningTest();
		*/
	}
}
