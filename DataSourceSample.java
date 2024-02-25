import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.OracleConnection;

public class DataSourceSample {
  public static void main(String args[]) throws SQLException {
    Properties info = new Properties();
    info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, "system");
    info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, "1234");
    info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");

    OracleDataSource ods = new OracleDataSource();
    ods.setURL("jdbc:oracle:thin:@localhost:1521/xe");
    ods.setConnectionProperties(info);

    try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
      sqlConnect(connection);
    }
  }

  public static void sqlConnect(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      try (ResultSet resultSet = statement.executeQuery("select * from emp")) {
        System.out.println("FIRST_NAME" + "  " + "LAST_NAME");
        System.out.println("---------------------");
        while (resultSet.next()){
          for (int i = 1;i<9; i++) {
            System.out.print(resultSet.getString(i)+" ");
          }
          System.out.println();
        }
      }
    }
  }
}