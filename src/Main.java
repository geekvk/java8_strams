import org.w3c.dom.ls.LSOutput;

import javax.xml.namespace.QName;
import java.util.*;
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

//        group
        Map<Gender, List<Person>> genderListMap =  personList.stream()
                .collect(Collectors.groupingBy(Person::getGender));
        genderListMap.forEach((gender, person) -> {
            System.out.println(gender);
            person.forEach(System.out::println);
        });
        Optional<String> olderstFemale =  personList.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        olderstFemale.ifPresent(name -> System.out.println(name));

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