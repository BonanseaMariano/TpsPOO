package test;

import org.junit.jupiter.api.BeforeAll;
import employee.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmployee {
    // create subclass objects
    static SalariedEmployee salariedEmployee = new SalariedEmployee("John", "Smith", "111-11-1111", LocalDate.of(1990, 1, 1), 800.00);
    static HourlyEmployee hourlyEmployee = new HourlyEmployee("Karen", "Price", "222-22-2222", LocalDate.of(1990, 2, 2), 16.75, 40);
    static CommissionEmployee commissionEmployee = new CommissionEmployee("Sue", "Jones", "333-33-3333", LocalDate.of(1990, 3, 3), 10000, .06);
    static BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", LocalDate.of(1990, 4, 4), 5000, .04, 300);
    static Employee employees[];


    @BeforeAll
    public static void setUp() {
        // create four-element Employee array
        employees = new Employee[4];

        // initialize array with Employees
        employees[0] = salariedEmployee;
        employees[1] = hourlyEmployee;
        employees[2] = commissionEmployee;
        employees[3] = basePlusCommissionEmployee;

        // set additional
        Employee.setAdditional(100.0);
    }

    @Test
    public void employeesProcesedIndividually() {
        System.out.println("\tEmployees processed individually:\n");

        System.out.printf("%s\n%s: $%,.2f\n\n", salariedEmployee, "earned", salariedEmployee.earnings());
        System.out.printf("%s\n%s: $%,.2f\n\n", hourlyEmployee, "earned", hourlyEmployee.earnings());
        System.out.printf("%s\n%s: $%,.2f\n\n", commissionEmployee, "earned", commissionEmployee.earnings());
        System.out.printf("%s\n%s: $%,.2f\n\n", basePlusCommissionEmployee, "earned", basePlusCommissionEmployee.earnings());
    }

    @Test
    public void employeesProcessedPolymorphically() {
        System.out.println("\tEmployees processed polymorphically:\n");

        // generically process each element in array employees
        for (Employee currentEmployee : employees) {
            System.out.println(currentEmployee); // invokes toString

            // determine whether element is a BasePlusCommissionEmployee
            if (currentEmployee instanceof BasePlusCommissionEmployee) {
                // downcast Employee reference to
                // BasePlusCommissionEmployee reference
                BasePlusCommissionEmployee employee =
                        (BasePlusCommissionEmployee) currentEmployee;

                double oldBaseSalary = employee.getBaseSalary();
                employee.setBaseSalary(1.10 * oldBaseSalary);
                System.out.printf(
                        "new base salary with 10%% increase is: $%,.2f\n",
                        employee.getBaseSalary());
            } // end if

            System.out.printf(
                    "earned $%,.2f\n\n", currentEmployee.earnings());
        } // end for

        // get type name of each object in employees array
        for (int j = 0; j < employees.length; j++)
            System.out.printf("Employee %d is a %s\n", j,
                    employees[j].getClass().getName());

        System.out.println();
    }

    @Test
    public void additionals() {
        System.out.println("\tEmployess salaries with additional for 01/01/2024:\n");
        for (Employee e : employees) {
            System.out.println(e.toString());
            System.out.printf("earned $%,.2f\n", e.earnings());
            System.out.printf("additional $%,.2f\n\n", e.salary(LocalDate.of(2024, 1, 1)));
        }
    }

    @Test
    public void employeesTypes() {
        assertEquals(SalariedEmployee.class, employees[0].getClass());
        assertEquals(HourlyEmployee.class, employees[1].getClass());
        assertEquals(CommissionEmployee.class, employees[2].getClass());
        assertEquals(BasePlusCommissionEmployee.class, employees[3].getClass());
    }

    @Test
    public void testAdditional() {
        assertEquals(employees[0].earnings() + Employee.getAdditional(), employees[0].salary(LocalDate.of(2024, 1, 1)));
        assertEquals(employees[1].earnings(), employees[1].salary(LocalDate.of(2024, 1, 1)));
        assertEquals(employees[2].earnings(), employees[2].salary(LocalDate.of(2024, 1, 1)));
        assertEquals(employees[3].earnings(), employees[3].salary(LocalDate.of(2024, 1, 1)));

        assertEquals(employees[0].earnings(), employees[0].salary(LocalDate.of(2024, 2, 1)));
        assertEquals(employees[1].earnings() + Employee.getAdditional(), employees[1].salary(LocalDate.of(2024, 2, 1)));
        assertEquals(employees[2].earnings(), employees[2].salary(LocalDate.of(2024, 2, 1)));
        assertEquals(employees[3].earnings(), employees[3].salary(LocalDate.of(2024, 2, 1)));

        assertEquals(employees[0].earnings(), employees[0].salary(LocalDate.of(2024, 3, 1)));
        assertEquals(employees[1].earnings(), employees[1].salary(LocalDate.of(2024, 3, 1)));
        assertEquals(employees[2].earnings() + Employee.getAdditional(), employees[2].salary(LocalDate.of(2024, 3, 1)));
        assertEquals(employees[3].earnings(), employees[3].salary(LocalDate.of(2024, 3, 1)));

        assertEquals(employees[0].earnings(), employees[0].salary(LocalDate.of(2024, 4, 1)));
        assertEquals(employees[1].earnings(), employees[1].salary(LocalDate.of(2024, 4, 1)));
        assertEquals(employees[2].earnings(), employees[2].salary(LocalDate.of(2024, 4, 1)));
        assertEquals(employees[3].earnings() + Employee.getAdditional(), employees[3].salary(LocalDate.of(2024, 4, 1)));
    }
}
