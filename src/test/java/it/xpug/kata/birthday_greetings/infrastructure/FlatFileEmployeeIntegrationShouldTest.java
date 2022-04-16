package it.xpug.kata.birthday_greetings.infrastructure;

import it.xpug.kata.birthday_greetings.hexagone.Employee;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by podisto on 16/04/2022.
 */
public class FlatFileEmployeeIntegrationShouldTest {

    FlatFileEmployeeAdapter sut = new FlatFileEmployeeAdapter("employee_data.txt");

    @Test
    public void read_all_amployees_from_file() throws Exception {
        List<Employee> employees = sut.all();

        assertThat(employees.size()).isEqualTo(3);
        assertThat(employees).contains(new Employee("Omar", "Dione", "1975/04/15", "dione.omar@foobar.com"));
    }

}