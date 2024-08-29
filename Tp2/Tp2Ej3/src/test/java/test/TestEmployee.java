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
    public void employeesTypes() {
        assertEquals(SalariedEmployee.class, employees[0].getClass());
        assertEquals(HourlyEmployee.class, employees[1].getClass());
        assertEquals(CommissionEmployee.class, employees[2].getClass());
        assertEquals(BasePlusCommissionEmployee.class, employees[3].getClass());
    }

    @Test
    public void testAdditional() {
        assertEquals(900.0, employees[0].salary(LocalDate.of(2024, 1, 1)));
        assertEquals(670.0, employees[1].salary(LocalDate.of(2024, 1, 1)));
        assertEquals(600.0, employees[2].salary(LocalDate.of(2024, 1, 1)));
        assertEquals(500.0, employees[3].salary(LocalDate.of(2024, 1, 1)));

        assertEquals(800.0, employees[0].salary(LocalDate.of(2024, 2, 1)));
        assertEquals(770.0, employees[1].salary(LocalDate.of(2024, 2, 1)));
        assertEquals(600.0, employees[2].salary(LocalDate.of(2024, 2, 1)));
        assertEquals(500.0, employees[3].salary(LocalDate.of(2024, 2, 1)));

        assertEquals(800.0, employees[0].salary(LocalDate.of(2024, 3, 1)));
        assertEquals(670.0, employees[1].salary(LocalDate.of(2024, 3, 1)));
        assertEquals(700.0, employees[2].salary(LocalDate.of(2024, 3, 1)));
        assertEquals(500.0, employees[3].salary(LocalDate.of(2024, 3, 1)));

        assertEquals(800.0, employees[0].salary(LocalDate.of(2024, 4, 1)));
        assertEquals(670.0, employees[1].salary(LocalDate.of(2024, 4, 1)));
        assertEquals(600.0, employees[2].salary(LocalDate.of(2024, 4, 1)));
        assertEquals(600.0, employees[3].salary(LocalDate.of(2024, 4, 1)));
    }
}
