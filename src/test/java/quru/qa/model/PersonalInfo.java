package quru.qa.model;

import java.util.List;

public class PersonalInfo {

    public String name;
    public String fullName;
    public int age;
    public List<String> cars;
    public Address address;


    public static class Address {
        public String city;
        public String street;
    }

}