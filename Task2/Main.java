package StreamAPI.Task2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Stream<Person> streamYoungPerson = persons.stream();
        Stream<Person> streamRecruits = persons.stream();
        Stream<Person> streamWorkingSpecialists = persons.stream();

        long countYoungPerson = streamYoungPerson
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(countYoungPerson);

        List<String> listRecruits =  streamRecruits
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        int count = 0;
        for (String recruit: listRecruits) {
            count++;
            System.out.println(count + " " + recruit);
        }

        List<Person> sortedListWorkingSpecialists = streamWorkingSpecialists
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getSex() == Sex.WOMAN? person.getAge() <= 60: person.getAge() <= 65)
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());
        count = 0;
        for (Person person: sortedListWorkingSpecialists) {
            count++;
            System.out.println(count + " " + person);
        }
    }
}
