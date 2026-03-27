package iuh.fit.util;

import iuh.fit.model.Address;
import iuh.fit.model.Person;
import iuh.fit.model.PhoneNumber;
import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    public static List<Person> fromJson(String fileName) {
        List<Person> res = new ArrayList<>();

        try(JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonArray peopleJsonArray = reader.readArray();
            peopleJsonArray.forEach(personValue -> {
                JsonObject personJsonObject = personValue.asJsonObject();
                String firstName = personJsonObject.getString("firstName");
                String lastName = personJsonObject.getString("lastName");
                int  age = personJsonObject.getInt("age");

                JsonObject addressJsonObject = personJsonObject.getJsonObject("address");
                Address address = new Address(
                        addressJsonObject.getString("streetAddress"),
                        addressJsonObject.getString("city"),
                        addressJsonObject.getString("state"),
                        addressJsonObject.getInt("postalCode")
                );
                List<PhoneNumber> phoneNumbers = new ArrayList<>();
                JsonArray phoneNumbersJsonArray = personJsonObject.getJsonArray("phoneNumbers");
                phoneNumbersJsonArray.forEach(phoneNumberValue -> {
                   JsonObject phoneNumberJsonObject = phoneNumberValue.asJsonObject();
                   PhoneNumber phoneNumber = new PhoneNumber(
                           phoneNumberJsonObject.getString("type"),
                           phoneNumberJsonObject.getString("number")
                   );
                   phoneNumbers.add(phoneNumber);
                });
                Person person = new Person(firstName, lastName, age, address, phoneNumbers);
                res.add(person);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void writeToFile(List<Person> people, String fileName) {
        //config
        Map<String, Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(config);

        try(JsonWriter jsonWriter = jsonWriterFactory.createWriter(new FileWriter(fileName))) {
            JsonArrayBuilder peopleJsonArray = Json.createArrayBuilder();
            people.forEach(personValue -> {
                JsonObjectBuilder personJsonObject = Json
                        .createObjectBuilder()
                        .add("firstName", personValue.getFirstName())
                        .add("lastName", personValue.getLastName())
                        .add("age", personValue.getAge());
                JsonObjectBuilder addressJsonObject = Json
                        .createObjectBuilder()
                        .add("streetAddress", personValue.getAddress().getStreetAddress())
                        .add("city", personValue.getAddress().getCity())
                        .add("state", personValue.getAddress().getState())
                        .add("postalCode", personValue.getAddress().getPostalCode());
                personJsonObject.add("address", addressJsonObject);
                JsonArrayBuilder phoneNumbersJsonArray = Json.createArrayBuilder();
                List<PhoneNumber> phoneNumbers = personValue.getPhoneNumbers();
                phoneNumbers.forEach(phoneNumber -> {
                    JsonObjectBuilder phoneNumberJsonObject = Json
                            .createObjectBuilder()
                            .add("type", phoneNumber.getType())
                            .add("number", phoneNumber.getNumber());
                    phoneNumbersJsonArray.add(phoneNumberJsonObject);
                });
                personJsonObject.add("phoneNumbers", phoneNumbersJsonArray);
                peopleJsonArray.add(personJsonObject);
            });
            jsonWriter.write(peopleJsonArray.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
