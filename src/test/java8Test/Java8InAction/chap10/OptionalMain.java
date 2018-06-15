package test.java8Test.Java8InAction.chap10;

import java.util.*;

import static java.util.stream.Collectors.toSet;

public class OptionalMain {

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                     .flatMap(Car::getInsurance)
                     .map(Insurance::getName)
                     .orElse("Unknown");
    }

    public Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                      .map(Person::getCar)
                      .map(optCar -> optCar.flatMap(Car::getInsurance))
                      .map(optInsurance -> optInsurance.map(Insurance::getName))
                      .filter(Optional::isPresent)
                      .map(Optional::get)
//                      .flatMap(Optional::stream)
                      .collect(toSet());
    }
}
