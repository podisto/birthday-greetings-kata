package it.xpug.kata.birthday_greetings.hexagone;

import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.*;

/**
 * Created by podisto on 16/04/2022.
 */
public class BirthdayServiceShouldTest {

    // Chicago School/Classicist/Inside Out vs London School/Mockist/Oustside In
    private final EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    private final NotificationProvider notificationProvider = Mockito.mock(NotificationProvider.class);
    private final BirthdayService sut = new BirthdayService(employeeRepository, notificationProvider);

    @Test
    public void not_send_greetings_when_list_is_empty() {
        when(employeeRepository.all()).thenReturn(emptyList());

        sut.sendGreetings(new XDate());

        verify(notificationProvider, never()).send(anyString(), anyString(), anyString());
    }

    @Test
    public void not_send_greetings_when_it_is_not_employee_birthday() throws ParseException {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "Doe", "1982/10/08", "john.doe@foobar.com"),
                new Employee("Mary", "Ann", "1975/03/11", "mary.ann@foobar.com")
        );
        when(employeeRepository.all()).thenReturn(employees);

        sut.sendGreetings(new XDate());

        verify(notificationProvider, never()).send("john.doe@foobar.com", "Happy Birthday!", "Happy Birthday, dear John!");
        verify(notificationProvider, never()).send("mary.ann@foobar.com", "Happy Birthday!", "Happy Birthday, dear Mary!");
    }

    @Test
    public void send_greetings_when_it_is_employee_birthday() throws ParseException {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "Doe", "1982/10/08", "john.doe@foobar.com"),
                new Employee("Mary", "Ann", "1975/03/11", "mary.ann@foobar.com"),
                new Employee("Omar", "Dione", "1975/04/16", "omar.dione@foobar.com")
        );
        when(employeeRepository.all()).thenReturn(employees);

        sut.sendGreetings(new XDate());

        verify(notificationProvider, times(1)).send("omar.dione@foobar.com", "Happy Birthday!", "Happy Birthday, dear Omar!");
    }

}