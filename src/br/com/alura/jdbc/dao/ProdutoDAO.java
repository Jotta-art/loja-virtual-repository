package br.com.alura.jdbc.dao;

import br.com.alura.jdbc.modelo.Produto;

import java.sql.*;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvarProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO_LOJA_VIRTUAL (ID, NOME, DESCRICAO) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, produto.getId());
            pstm.setString(2, produto.getNome());
            pstm.setString(3, produto.getDescricao());

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    produto.setId(rst.getInt(1));
                }
            }
        }
    }
}
