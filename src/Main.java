import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = getPersonList();
        // Filter
//        List<Person> female = new ArrayList<>();
//        for (Person person : personList){
//            if(person.getGender().equals(Gender.FEMALE)){
//                female.add(person);
//            }
//        }
//        female.forEach(System.out::println);
        List<Person> females = personList.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
//        females.forEach(System.out::println);
//            personList.forEach(System.out::println);
        List<Person> sortedPerson =  personList.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());

        List<Person> reversedSort =  personList.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
                .collect(Collectors.toList());
        reversedSort.forEach(System.out::println);

        boolean allMatch = personList.stream()
                .allMatch(person -> person.getAge() > 15);
        System.out.println(allMatch);

        boolean nonMatch = personList.stream()
                .noneMatch(person -> person.getName().equals("Anton"));
        System.out.println(nonMatch);

        Optional<Person> max =  personList.stream()
                .max(Comparator.comparing(Person::getAge));
        System.out.println(max);

        Optional<Person> min =  personList.stream()
                .min(Comparator.comparing(Person::getAge));
        System.out.println(min);

    }
    public static List<Person> getPersonList(){
        return List.of(
                new Person(23, "Anton", Gender.MALE),
                new Person(24, "u", Gender.FEMALE),
                new Person(21, "eer", Gender.FEMALE),
                new Person(12, "er", Gender.MALE)
        );
    }
}