import iuh.fit.model.Person;
import iuh.fit.util.JsonUltils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = JsonUltils.fromJson("json/People.json");
        people.forEach(System.out::println);
        JsonUltils.writeToFile(people, "json/People2.json");
    }
}
