package com.almabase.diff.comparator;

import com.almabase.diff.mapping.FieldNameCompare;
import com.almabase.diff.mapping.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Comparator {


	public List<String> diffGenerator(Mapper mappingList, JSONObject source, JSONObject destination) throws JSONException {

		//check each value in mapping list
		//compare it with source and destination and generate diff
		List<String> dataPatch=new ArrayList<>();

		if(mappingList.getSourceId() !=source.get("id") || mappingList.getDestinationId() != destination.get("id")) throw new DataMisMatchError();

		for(FieldNameCompare value:mappingList.getFieldNameCompareList()){
			source.get(value.getSourceFieldName());
			//if destination is empty
			if(ObjectUtils.isEmpty(destination.get(value.getDestinationFieldName()))){
				//you have to put the data in the destination file
				dataPatch.add("Add the "+value.getSourceFieldName() +" in destination file with the value "+ source.get(value.getSourceFieldName()));
			}
			//if field names are equal
			else if (value.getDestinationFieldName().equals(value.getSourceFieldName())) {

				//but value is different
				if (!(destination.get(value.getDestinationFieldName()).equals(source.get(value.getSourceFieldName())))) {
					dataPatch.add("Change the data in " + value.getDestinationFieldName() + " to " + source.get(value.getSourceFieldName()));
				}
			}
			//if fieldNames are different just use the destination fieldName and value
			else{
				dataPatch.add("Use the data in destination:field name is " +value.getDestinationFieldName() +" data is " +destination.get(value.getDestinationFieldName()));
			}


		}





		return dataPatch;
	}

	public List<String> getKeys(JSONObject data){
		Iterator dataIterator=data.keys();
		List<String> dataKeys=new ArrayList<>();
		while(dataIterator.hasNext()) dataKeys.add(dataIterator.next().toString());
		//removing id, to avoid duplicate check;
		dataKeys.remove("id");
		return  dataKeys;

	}



}
