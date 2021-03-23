package test.java.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Log {
    private final Logger LOGGER = LogManager.getLogger(Log.class.getName());

    public void startTestCase() {
        LOGGER.info("****************************************************************************************");
        LOGGER.info("****************************************************************************************");
        LOGGER.info("$$$$$$$$$$$$$$$$$$$$$        " + "-S---T---A---R---T--" + "        $$$$$$$$$$$$$$$$$$$$$$$$$");
        LOGGER.info("****************************************************************************************");
        LOGGER.info("****************************************************************************************");
    }

    public void endTestCase() {
        LOGGER.info("XXXXXXXXXXXXXXXXXXXXXXX          " + "-E---N---D-" + "                XXXXXXXXXXXXXXXXXXXXXX");
        LOGGER.info("X");
        LOGGER.info("X");
        LOGGER.info("X");
        LOGGER.info("X");
    }
}
