package edu.wgu.jobjournalcapstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JobjournalcapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobjournalcapstoneApplication.class, args);
	}

}
