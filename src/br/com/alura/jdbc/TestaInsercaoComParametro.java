package br.com.alura.jdbc;

import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.criaConexao()) {

            connection.setAutoCommit(false);

            try (PreparedStatement stm =
                         connection.prepareStatement("INSERT INTO PRODUTO_LOJA_VIRTUAL (ID, NOME, DESCRICAO) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ) {
                adicionarvariavel(6, "SmartTV", "45 polegadas", stm);
                adicionarvariavel(7, "Radio", "Radio de bateria", stm);

                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK EXECUTADO");
                connection.rollback();
            }
        }
    }

    private static void adicionarvariavel(Integer id, String nome, String descricao, PreparedStatement stm) throws SQLException {

        stm.setInt(1, id);
        stm.setString(2, nome);
        stm.setString(3, descricao);


        if (nome.equals("Radio")) {
            throw new RuntimeException("Não foi possível adicionar o produto");
        }

        stm.execute();

        try (ResultSet rst = stm.getGeneratedKeys()) {
            while (rst.next()) {
//            Integer id = rst.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
        }

    }

}
