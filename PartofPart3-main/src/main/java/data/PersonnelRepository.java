package main.java.data;

import javafx.print.PaperSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Data Repository
 * 
 * @author riteshp
 *
 */
public class PersonnelRepository {

	private static List<Person> PERSON_LIST = new ArrayList<Person>();


	/**
	 * Load item records from the stock.json file
	 */
	static {
		// System.out.println("Loading items");
		BufferedReader reader = null;
		try {
			PERSON_LIST.clear();

			reader = new BufferedReader(new FileReader("C:\\Users\\Hamza Latif\\Pictures\\Screenshots\\PartofPart3-main\\src\\main\\resources\\personnel.json"));
			Object data = JSONValue.parse(reader);
			if (data instanceof JSONArray) {
				JSONArray dataArray = (JSONArray) data;
				for (Object obj : dataArray) {
					if (obj instanceof JSONObject) {
						JSONObject jsonData = (JSONObject) obj;
						Person person = new Person();
						person.setUserName(jsonData.get("user_name").toString());
						person.setPassword(jsonData.get("password").toString());
						//person.setHeadOf((Person) jsonData.getOrDefault("head_of", null));
						PERSON_LIST.add(person);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * Get All persons
	 *
	 * @return
	 */
	public static List<Person> getAllPersons() {
		return PERSON_LIST;
	}

	public static boolean isUserValid(String username, String password) {
		//to implement
		String uName,uPass;
		boolean flag = false;
		for (int i = 0; i < PERSON_LIST.size(); i++) {
			uName= PERSON_LIST.get(i).getUserName();
			uPass = PERSON_LIST.get(i).getPassword();

			if (uName.equals(username) && (uPass.equals(password))) {
				return  true;
			}
			else
				flag = false;
		}
		return  flag;
	}

}