package org.example;

import java.util.Objects;
import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    private int age;
    private String address;

    public Person(PersonBuilder personBuilder) {
        this.name = personBuilder.name;
        this.surname = personBuilder.surname;
        this.age = personBuilder.age;
        this.address = personBuilder.address;
    }

    public boolean hasAge() {
        return age >= 0;
    }

    public boolean hasAddress() {
        return address != null;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        if (hasAge()) {
            return OptionalInt.of(age);
        }
        return OptionalInt.empty();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        if (hasAge()) {
            age++;
        }
    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder child = new PersonBuilder();
        child.setAge(0);
        child.setSurname(this.surname);
        child.setAddress(this.address);
        return child;
    }

    private String oldFormat(int age){
        int ageLastNumber = age % 10;
        String old = "";

        if (ageLastNumber == 1)
            old = "год";
        if(ageLastNumber == 0 || ageLastNumber >= 5 && ageLastNumber <= 9)
            old = "лет";
        if(ageLastNumber >= 2 && ageLastNumber <= 4)
            old = "года";
        if ((age % 100 >= 11) && (age % 100 <= 14))
            old = "лет";
        return old;
    }

    @Override
    public String toString() {
        if (hasAge() && hasAddress()) {
            return name + " " + surname + ", возраст " + age + " " + oldFormat(age)
                    + ", место проживания " + address;
        }
        if (hasAge()) {
            return name + " " + surname + ", возраст " + age +
                    " " + oldFormat(age);
        }
        if (hasAddress()) {
            return name + " " + surname +
                    ", место проживания " + address;
        }
        return name + " " + surname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getAge(), getAddress());
    }

}