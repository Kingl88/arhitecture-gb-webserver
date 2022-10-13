package ru.gb.patterns.composite;

public class Main {
    public static void main(String[] args) {
        Employee john = new Developer(12000, "John Doe");
        Employee jane = new Developer(15000, "Jane Doe");

        Organization organization = new Organization();
        organization.addEmployee(john);
        organization.addEmployee(jane);
        System.out.println("Net salaries: " + organization.getNetSalaries());
    }
}
