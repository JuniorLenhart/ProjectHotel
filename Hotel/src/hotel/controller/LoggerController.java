package hotel.controller;

import org.apache.log4j.Logger;

public class LoggerController {

    public static void log(Class pClass, Exception pException) {
        Logger logger = Logger.getLogger(pClass);
        logger.error(pException);
    }
}
