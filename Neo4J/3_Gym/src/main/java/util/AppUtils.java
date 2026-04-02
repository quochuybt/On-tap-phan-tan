package util;

import org.neo4j.driver.*;


public class AppUtils implements AutoCloseable{
    private static final String DB_NAME = "quochuy23664121";
    private static final String USER_NAME = "neo4j";
    private static final String PASSWORD = "Quochuy*1911";
    private static final String URI = "neo4j://127.0.0.1:7687";

    private static Driver driver;

    public static Driver getDriver() {
        if (driver==null) {
            driver = GraphDatabase.driver(URI, AuthTokens.basic(USER_NAME,PASSWORD));
        }
        return driver;
    }

    public static Session getSession() {
        return getDriver().session(SessionConfig.forDatabase(DB_NAME));
    }
    @Override
    public void close() throws Exception {
        driver.close();
    }
}
