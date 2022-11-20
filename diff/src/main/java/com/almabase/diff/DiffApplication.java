package com.almabase.diff;

import com.almabase.diff.comparator.Comparator;
import com.almabase.diff.mapping.FieldNameCompare;
import com.almabase.diff.mapping.Mapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DiffApplication {

	public static void main(String[] args) throws JSONException {

		SpringApplication.run(DiffApplication.class, args);
		Mapper mapper=new Mapper();
		mapper.setSourceId("1");
		mapper.setDestinationId("a");

		List<FieldNameCompare> fieldNameCompareList=new ArrayList<>();
		fieldNameCompareList.add(new FieldNameCompare("name","display_name"));
		mapper.setFieldNameCompareList(fieldNameCompareList);

		JSONObject source=new JSONObject();
		source.put("id","1");
		source.put("name","John");

		JSONObject destination=new JSONObject();
		destination.put("id","a");
		destination.put("display_name","John");

		Comparator comparator= new Comparator();
		System.out.println(comparator.diffGenerator(mapper,source,destination));



	}

}
