package it.xpug.kata.birthday_greetings.infrastructure;

import it.xpug.kata.birthday_greetings.hexagone.Employee;
import it.xpug.kata.birthday_greetings.hexagone.EmployeeRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by podisto on 15/04/2022.
 */
public class FlatFileEmployeeAdapter implements EmployeeRepository {

    private static final Logger logger = Logger.getLogger("FlatFileEmployeeRepository");
    public static final String COMMA_SEPARATED = ", ";

    private final String fileName;

    public FlatFileEmployeeAdapter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Employee> all() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))){
            String str = "";
            str = in.readLine(); // skip header
            while ((str = in.readLine()) != null) {
                String[] employeeData = str.split(COMMA_SEPARATED);
                Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
                employees.add(employee);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Une erreur sest produite lors de la lecture {}", e.getMessage());
        }
        return employees;
    }

}
