package br.com.alura.jdbc;

import br.com.alura.jdbc.dao.CategoriaDao;
import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemDeCategorias {

    public static void main(String[] args) throws SQLException {

        try (Connection connection = new ConnectionFactory().criaConexao()) {
            CategoriaDao categoriaDao = new CategoriaDao(connection);
            List<Categoria> listaDeCategorias = categoriaDao.listarComProdutos();
            listaDeCategorias.stream().forEach(ct -> {
                System.out.println(ct.getNome());
                for (Produto produto : ct.getProdutos()) {
                    System.out.println(ct.getNome() + " - " + produto.getNome());

                }
            });
        }
    }
}
