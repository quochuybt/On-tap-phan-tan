package iuh.fit;

import iuh.fit.model.Country;
import iuh.fit.util.JsonUtils;

public class Main {
    public static void main(String[] args) {
        Country  country = JsonUtils.fromJson("json/country.json");
        System.out.println(country);
//        JsonUtils.writeToFile(country, "json/country2.json");
    }
}
