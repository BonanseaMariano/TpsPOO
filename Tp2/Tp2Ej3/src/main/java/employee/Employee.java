package employee;
// Fig. 10.4: Employee.java
// Employee abstract superclass.

import java.time.LocalDate;

public abstract class Employee {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private LocalDate birthDate;
    private static double additional;

    // three-argument constructor
    public Employee(String first, String last, String ssn, LocalDate birthDate) {
        this.firstName = first;
        this.lastName = last;
        this.socialSecurityNumber = ssn;
        this.birthDate = birthDate;
    } // end three-argument Employee constructor

    //set birthdate
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    //return birthdate
    public LocalDate getBirthDate() {
        return birthDate;
    }

    //set additional
    public static void setAdditional(double add) {
        additional = add;
    }

    //return additional
    public static double getAdditional() {
        return additional;
    }

    // set first name
    public void setFirstName(String first) {
        firstName = first;
    } // end method setFirstName

    // return first name
    public String getFirstName() {
        return firstName;
    } // end method getFirstName

    // set last name
    public void setLastName(String last) {
        lastName = last;
    } // end method setLastName

    // return last name
    public String getLastName() {
        return lastName;
    } // end method getLastName

    // set social security number
    public void setSocialSecurityNumber(String ssn) {
        socialSecurityNumber = ssn; // should validate
    } // end method setSocialSecurityNumber

    // return social security number
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    } // end method getSocialSecurityNumber

    // return String representation of Employee object
    public String toString() {
        return String.format("%s %s\nsocial security number: %s, birth date: %s/%s/%s", getFirstName(), getLastName(), getSocialSecurityNumber(), getBirthDate().getDayOfMonth(), getBirthDate().getMonthValue(), getBirthDate().getYear());
    } // end method toString

    // abstract method overridden by subclasses
    public abstract double earnings(); // no implementation here

    /**
     * Calculates the salary of an employee for the given date (with or without additional).
     *
     * @param date the date for which the salary is calculated
     * @return the calculated salary
     */
    public double salary(LocalDate date) {
        double salary = earnings();
        if (date.getMonth().equals(birthDate.getMonth())) {
            salary += additional;
        }
        return salary;
    }
} // end abstract class Employee


/**************************************************************************
 * (C) Copyright 1992-2007 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
