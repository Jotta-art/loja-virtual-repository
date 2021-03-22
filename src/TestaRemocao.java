import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.criaConexao();

        PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO_LOJA_VIRTUAL WHERE ID > ?");

        stm.setInt(1, 2);

        stm.execute();

        Integer linhasModificadas = stm.getUpdateCount();

        System.out.println("Quantidade de linhas que foram modificadas " + linhasModificadas);

        stm.close();
        connection.close();
    }
}
