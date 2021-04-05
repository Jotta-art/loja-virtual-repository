import br.com.alura.jdbc.ConnectionFactory;
import br.com.alura.jdbc.modelo.Produto;

import java.sql.*;

public class TestaInsercaoComProduto {

    public static void main(String[] args) throws SQLException {

        Produto comoda = new Produto(3, "Cômoda", "Cômoda vertical");

        try (Connection connection = new ConnectionFactory().criaConexao()) {

            String sql = "INSERT INTO PRODUTO (ID, NOME, DESCRICAO) VALUES (?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setInt(1, comoda.getId());
                pstm.setString(2, comoda.getNome());
                pstm.setString(3, comoda.getDescricao());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        comoda.setId(rst.getInt(1));
                    }
                }
            }
        }
        System.out.println(comoda);
    }
}
