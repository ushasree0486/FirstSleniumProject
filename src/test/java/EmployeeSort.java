import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeSort {
    String name;

    public static void main(String[] args) {
        List<Employee> employeeList = customisedSortDemo();
          for (Employee e : employeeList)
            System.out.println(e.getName());

        /*List<String> nameList=defaultNaturalSortingOrder();
        for(String name:nameList)
            System.out.println(name);
    */}

    private static List<String> defaultNaturalSortingOrder() {
        List<String> nameList = new ArrayList<>();
        nameList.add("Jack");
        nameList.add("Dean");
        nameList.add("Robert");
        nameList.add("Mark");
        Collections.sort(nameList);
        return nameList;
    }

    private static List<Employee> customisedSortDemo() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Kundan"));
        employeeList.add(new Employee("Adann"));
        employeeList.add(new Employee("Jyothi"));
        employeeList.add(new Employee("Ruchi"));
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {

                int k = e1.getName().compareTo(e2.getName());
                System.out.println("k : " + k);
                return k;
            }

        });
        return employeeList;
    }
}

class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}