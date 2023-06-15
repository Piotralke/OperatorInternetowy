package pl.psk.upc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class UpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpcApplication.class, args);
	}

}
