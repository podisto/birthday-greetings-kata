package it.xpug.kata.birthday_greetings.infrastructure;

import it.xpug.kata.birthday_greetings.hexagone.Employee;
import it.xpug.kata.birthday_greetings.hexagone.EmployeeRepository;

import java.util.List;

/**
 * Created by podisto on 16/04/2022.
 */
public class JpaEmployeeAdapter implements EmployeeRepository {

    @Override
    public List<Employee> all() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
