import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection criaConexao() throws SQLException {
        return DriverManager
                .getConnection
                        ("jdbc:oracle:thin:@localhost:1521:XE",
                                "qa_homo", "qa_homo");
    }
}
