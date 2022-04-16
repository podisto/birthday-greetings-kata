package it.xpug.kata.birthday_greetings.hexagone;

public class BirthdayService {

	private final EmployeeRepository employeeRepository;
	private final NotificationProvider notificationProvider;

	public BirthdayService(EmployeeRepository employeeRepository, NotificationProvider notificationProvider) {
		this.employeeRepository = employeeRepository;
		this.notificationProvider = notificationProvider;
	}

	public void sendGreetings(XDate xDate) {
		employeeRepository.all()
				.stream()
				.filter(e -> e.isBirthday(xDate))
				.forEach(this::send);
	}

	private void send(Employee employee) {
		String body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
		String subject = "Happy Birthday!";
		notificationProvider.send(employee.getEmail(), subject, body);
	}

}
