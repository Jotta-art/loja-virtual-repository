import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    public DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
        comboPooledDataSource.setUser("qa_homo");
        comboPooledDataSource.setPassword("qa_homo");

        comboPooledDataSource.setMaxPoolSize(15);

        this.dataSource = comboPooledDataSource;
    }

    public Connection criaConexao() throws SQLException {
        return this.dataSource.getConnection();
    }
}
