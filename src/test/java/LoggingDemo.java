import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.DriverFactory;

import java.io.IOException;


public class LoggingDemo extends DriverFactory {

    public LoggingDemo() throws IOException {
    }

    public static void main(String[] args) {
            log.trace("Trace Message Printed");
            log.debug("Debug Message Printed");
            log.info("Info Message Printed");
            log.error("Error Message Printed");
            log.fatal("Fatal Message Printed");
        }
    }


