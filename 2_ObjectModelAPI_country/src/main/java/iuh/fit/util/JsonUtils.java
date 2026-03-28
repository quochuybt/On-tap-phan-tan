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
    public static List<String> jsonToList(JsonArray jsonArray) {
        List<String> list = new ArrayList<>();
        IntStream.range(0,jsonArray.size())
                .forEach(x -> {
                    list.add(jsonArray.getString(x));
                });
        return list;
    }
    public static Country fromJson(String fileName) {
        Country country = null;

        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonObject countryJsonObject = reader.readObject();
            int id = countryJsonObject.getInt("id");
            int area = countryJsonObject.getInt("area");
            String capital = countryJsonObject.getString("capital");
            String cca2 = countryJsonObject.getString("cca2");
            String cioc = countryJsonObject.getString("cioc");
            String demonym = countryJsonObject.getString("demonym");
            boolean landLocked = countryJsonObject.getBoolean("landLocked");
            String region = countryJsonObject.getString("region");
            String subregion = countryJsonObject.getString("subregion");

            List<String> altSpellings = jsonToList(countryJsonObject.getJsonArray("altSpellings"));
            List<String> borders = jsonToList(countryJsonObject.getJsonArray("borders"));
            List<String> callingCode = jsonToList(countryJsonObject.getJsonArray("callingCode"));
            List<String> currency = jsonToList(countryJsonObject.getJsonArray("currency"));
            List<Double> latlng = new ArrayList<>();
            JsonArray latlngJsonArray = countryJsonObject.getJsonArray("latlng");
            IntStream.range(0,latlngJsonArray.size())
                    .forEach(x->latlng.add(latlngJsonArray.getJsonNumber(x).doubleValue()));

            JsonObject nameJsonObject = countryJsonObject.getJsonObject("name");
            String common = nameJsonObject.getString("common");
            String official = nameJsonObject.getString("official");
            Name name = new Name(common,official);

            Map<String, Translation>  translations = new LinkedHashMap<>();
            JsonObject tranlationsJsonObject = countryJsonObject.getJsonObject("translations");
            tranlationsJsonObject.keySet().forEach(key -> {
                JsonObject tranlationJsonObject = tranlationsJsonObject.get(key).asJsonObject();
                Translation translation = new Translation(
                        tranlationJsonObject.getString("common"),
                        tranlationJsonObject.getString("official")
                );
                translations.put(key,translation);
            });
//            JsonObject fra = tranlationsJsonObject.getJsonObject("fra");
//            String commonFra = fra.getString("common");
//            String officialFra = fra.getString("official");
//            Translation translation1 = new Translation(commonFra,officialFra);
//            JsonObject ita = tranlationsJsonObject.getJsonObject("ita");
//            String commonIta = ita.getString("common");
//            String officialIta = ita.getString("official");
//            Translation translation2 = new Translation(commonIta,officialIta);
//            translations.put("fra",translation1);
//            translations.put("ita",translation2);
            country = new Country(id,area,capital,cca2,cioc,demonym,landLocked,region,subregion,
                    altSpellings,borders,callingCode,currency,latlng,name,translations);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return country;
    }
}
