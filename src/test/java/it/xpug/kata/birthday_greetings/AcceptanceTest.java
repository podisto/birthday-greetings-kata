package it.xpug.kata.birthday_greetings;

import static org.junit.Assert.*;

import it.xpug.kata.birthday_greetings.hexagone.BirthdayService;
import it.xpug.kata.birthday_greetings.hexagone.EmployeeRepository;
import it.xpug.kata.birthday_greetings.hexagone.NotificationProvider;
import it.xpug.kata.birthday_greetings.hexagone.XDate;
import it.xpug.kata.birthday_greetings.infrastructure.EmailServiceAdapter;
import it.xpug.kata.birthday_greetings.infrastructure.FlatFileEmployeeAdapter;
import it.xpug.kata.birthday_greetings.infrastructure.MailProperties;
import org.junit.*;

import com.dumbster.smtp.*;


public class AcceptanceTest {

	private static final int NONSTANDARD_PORT = 9999;
	private BirthdayService birthdayService;
	private SimpleSmtpServer mailServer;

	@Before
	public void setUp() throws Exception {
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
		EmployeeRepository employeeRepository = new FlatFileEmployeeAdapter("employee_data.txt");
		MailProperties properties = new MailProperties("sender@here.com", "localhost", NONSTANDARD_PORT);
		NotificationProvider notificationProvider = new EmailServiceAdapter(properties);
		birthdayService = new BirthdayService(employeeRepository, notificationProvider);
	}

	@After
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

		birthdayService.sendGreetings(new XDate("2008/10/08"));

		assertEquals("message not sent?", 1, mailServer.getReceivedEmailSize());
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertEquals("Happy Birthday, dear John!", message.getBody());
		assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
		String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
		assertEquals("john.doe@foobar.com", recipients[0].toString());
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		birthdayService.sendGreetings(new XDate("2008/01/01"));

		assertEquals("what? messages?", 0, mailServer.getReceivedEmailSize());
	}
}
