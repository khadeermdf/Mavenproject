package khadeerDemoJson.data;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Multipleuser {

	
	public List<HashMap<String, String>> getJsonData() throws IOException {
		
		//Read Json to String
		String JsonContent =FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\khadeerDemoJson\\data\\JsonData.json")
				,StandardCharsets.UTF_8);
			
	     //covert String to HashMap
		ObjectMapper Mapper = new ObjectMapper();
		
		//mapper is object
			List<HashMap<String, String>>data =Mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		//  List<HashMap<String, String>>data =Mapper.readValue(JsonContent, new TypeReference<List<HasMap<String, String>>>(){});	
			return data;
				
	}
	}
