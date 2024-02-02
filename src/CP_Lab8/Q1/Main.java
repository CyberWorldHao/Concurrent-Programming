package CP_Lab8.Q1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String [] args){

        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Poseidon", "M", 23));
        employees.add(new Employee("Hera", "F", 18));
        employees.add(new Employee("Apollo", "M", 20));
        employees.add(new Employee("Athena", "F", 35));
        employees.add(new Employee("Demeter", "F", 50));

        System.out.println("Alternative 1 : Collector List");
        List<Employee> employeeAgeMoreThan21AndFemale = employees.stream().filter
                (employee -> employee.getAge()>=21 && employee.getGender().equals("F")).collect(Collectors.toList());
        System.out.println(employeeAgeMoreThan21AndFemale);

        System.out.println("Alternative 2: For each");
        employees.stream().filter(Employee::moreThan21AndFemale).forEach(employee -> System.out.println(employee));

    }
}
