package ro.softica.iWantPS5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IWantPs5Application {

	public static void main(String[] args) {
		SpringApplication.run(IWantPs5Application.class, args);

		var endlessWorker = new EndlessWorker();
		endlessWorker.start();
	}

}
