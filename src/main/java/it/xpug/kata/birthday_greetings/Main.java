package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.hexagone.BirthdayService;
import it.xpug.kata.birthday_greetings.hexagone.EmployeeRepository;
import it.xpug.kata.birthday_greetings.hexagone.NotificationProvider;
import it.xpug.kata.birthday_greetings.hexagone.XDate;
import it.xpug.kata.birthday_greetings.infrastructure.EmailServiceAdapter;
import it.xpug.kata.birthday_greetings.infrastructure.FlatFileEmployeeAdapter;
import it.xpug.kata.birthday_greetings.infrastructure.MailProperties;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	private static final Logger logger = Logger.getLogger("Main");

	// Classes du domaine métier
	// Classes de configuration
	public static void main(String[] args) {
		logger.log(Level.INFO, "Run...");
		EmployeeRepository employeeRepository = new FlatFileEmployeeAdapter("employee_data.txt");
		MailProperties mailProperties = new MailProperties("sender@here.com", "localhost", 25);
		NotificationProvider notificationProvider = new EmailServiceAdapter(mailProperties);

		BirthdayService service = new BirthdayService(employeeRepository, notificationProvider);

		service.sendGreetings(new XDate());
	}

}
