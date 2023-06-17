import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.logging.Logger;

@SpringBootApplication
public class SDGinUJIApplication {

	private static final Logger log = Logger.getLogger(SDGinUJIApplication.class.getName());

	public static void main(String[] args) {
		// Auto-configura l'aplicació
		new SpringApplicationBuilder(SDGinUJIApplication.class).run(args);
	}

}
