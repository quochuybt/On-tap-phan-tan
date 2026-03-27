package iuh.fit.util;

import iuh.fit.model.Country;
import iuh.fit.model.Name;
import iuh.fit.model.Translation;
import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.IntStream;

public class JsonUtils {
    private static JsonArrayBuilder ListToJson(List<String> list) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        list.forEach(x -> arrayBuilder.add(x));
        return arrayBuilder;
    }
    private static List<String> JsonToList(JsonArray jsonArray) {
        List<String> res = new ArrayList<>();
        IntStream.range(0, jsonArray.size())
                .forEach(x -> res.add(jsonArray.getString(x)));
        return res;
    }
    public static Country fromJson(String fileName) {
        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {

            JsonObject countryJsonObject = reader.readObject();
            int id = countryJsonObject.getInt("id");
            int area  = countryJsonObject.getInt("area");
            String capital  = countryJsonObject.getString("capital");
            String cca2 =  countryJsonObject.getString("cca2");
            String cioc =   countryJsonObject.getString("cioc");
            String demonym =  countryJsonObject.getString("demonym");
            Boolean landLocked  = countryJsonObject.getBoolean("landLocked");
            String region = countryJsonObject.getString("region");
            String subregion  = countryJsonObject.getString("subregion");

            List<String> altSpellings = JsonToList(countryJsonObject.getJsonArray("altSpellings"));
            List<String> borders =  JsonToList(countryJsonObject.getJsonArray("borders"));
            List<String> callingCode =   JsonToList(countryJsonObject.getJsonArray("callingCode"));
            List<String> currency =    JsonToList(countryJsonObject.getJsonArray("currency"));
            List<Double> latlng = new ArrayList<>();
            JsonArray latlngJsonArray = countryJsonObject.getJsonArray("latlng");
            IntStream.range(0, latlngJsonArray.size())
                    .forEach(x -> latlng.add(latlngJsonArray.getJsonNumber(x).doubleValue()));

            JsonObject nameJsonObject = countryJsonObject.getJsonObject("name");
            Name name = new Name(
                    nameJsonObject.getString("common"),
                    nameJsonObject.getString("official")
            );
            Map<String, Translation> translations = new LinkedHashMap<>();
            JsonObject translationsJsonObject = countryJsonObject.getJsonObject("translations");
            translationsJsonObject.keySet().forEach(key -> {
                JsonObject translationObject = translationsJsonObject.get(key).asJsonObject();
                Translation translation = new Translation(
                        translationObject.getString("common"),
                        translationObject.getString("official")
                );
                translations.put(key, translation);
            });

            return new Country(id, area, capital,cca2,cioc,demonym,
                    landLocked,region,subregion,altSpellings,borders,callingCode,
                    currency,latlng,name,translations);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void writeToFile(Country country, String fileName) {
        Map<String, Object> connfig = new HashMap<>();
        connfig.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(connfig);

        try (JsonWriter jsonWriter = jsonWriterFactory.createWriter(new FileWriter(fileName))) {

            JsonObjectBuilder countryJsonObject = Json.createObjectBuilder()
                    .add("id", country.getId())
                    .add("area", country.getArea())
                    .add("capital", country.getCapital())
                    .add("cca2", country.getCapital())
                    .add("cioc", country.getCapital())
                    .add("demonym", country.getCapital())
                    .add("landLocked", country.getCapital())
                    .add("region", country.getCapital())
                    .add("subregion", country.getCapital());

            JsonArrayBuilder altSpellingsJsonArray = ListToJson(country.getAltSpellings());
            JsonArrayBuilder bordersJsonArray = ListToJson(country.getBorders());
            JsonArrayBuilder callingCodeJsonArray = ListToJson(country.getCallingCode());
            JsonArrayBuilder currencyJsonArray = ListToJson(country.getCurrency());
            JsonArrayBuilder latlngJsonArray = Json.createArrayBuilder();
            country.getLatlng().forEach(x -> latlngJsonArray.add(x));

            JsonObjectBuilder nameJsonObject = Json.createObjectBuilder()
                    .add("common",country.getName().getCommon())
                    .add("official",country.getName().getOfficial());

            JsonObjectBuilder translationsJsonObject = Json.createObjectBuilder();
            country.getTranslations().forEach((k,v)->{
                JsonObjectBuilder translationJsonObject = Json.createObjectBuilder()
                        .add("common",v.getCommon())
                        .add("official",v.getOfficial());
                translationsJsonObject.add(k,translationJsonObject);
            });
            countryJsonObject.add("altSpellings",altSpellingsJsonArray);
            countryJsonObject.add("borders",bordersJsonArray);
            countryJsonObject.add("callingCode",callingCodeJsonArray);
            countryJsonObject.add("currency",currencyJsonArray);
            countryJsonObject.add("latlng",latlngJsonArray);
            countryJsonObject.add("name",nameJsonObject);
            countryJsonObject.add("translations",translationsJsonObject);

            jsonWriter.writeObject(countryJsonObject.build());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
