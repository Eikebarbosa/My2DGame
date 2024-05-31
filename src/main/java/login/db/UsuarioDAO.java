/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.db;

import login.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author schix
 */
public class UsuarioDAO {
    public static boolean existe(Usuario u) throws Exception {
        // 1. Especificar o comando SQL (Select)
        String sql = "SELECT * FROM Aluno WHERE nomeAluno = ? AND senhaAluno = ?";

        // 2. Estabelecer uma conexão com o banco
        Connection conexao = new ConnectionFactory().obterConexao();

        // 3. Preparar o comando
        PreparedStatement ps = conexao.prepareStatement(sql);

        // 4. Substituir os eventuais placeholders
        ps.setString(1, u.getLogin());
        ps.setString(2, u.getSenha());

        // 5. Executar
        ResultSet rs = ps.executeQuery();

        // 6. Lidar com o resultado
        boolean achou = rs.next();

        // 7. Fechar a conexão
        rs.close();
        ps.close();
        conexao.close();

        // 8. Responder se existe ou não
        return achou;
    }

    public static int getRecorde(int codigo) throws Exception {
        String sql = "SELECT * FROM Aluno WHERE idAluno = ?";

        Connection conexao = new ConnectionFactory().obterConexao();
        PreparedStatement ps = conexao.prepareStatement(sql);

        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();

        int recorde = 0;
        if (rs.next()) {
            recorde = rs.getInt("recorde");
        }

        rs.close();
        ps.close();
        conexao.close();

        return recorde;
    }

    public static void setRecorde(int codigo, int recorde) throws Exception {
        String sql = "UPDATE Aluno SET recorde = ? WHERE idAluno = ?";

        Connection conexao = new ConnectionFactory().obterConexao();
        PreparedStatement ps = conexao.prepareStatement(sql);

        ps.setInt(1, recorde);
        ps.setInt(2, codigo);
        ps.executeUpdate();

        ps.close();
        conexao.close();
    }

    public static void inserir(Usuario u) throws Exception {
        Connection conexao = new ConnectionFactory().obterConexao();
        try (PreparedStatement statement = conexao
                .prepareStatement("INSERT INTO Aluno (idAluno, nomeAluno, senhaAluno, recorde) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, u.getCodigo());
            statement.setString(2, u.getLogin());
            statement.setString(3, u.getSenha());
            statement.setInt(4, u.getRecorde());

            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        conexao.close();
    }
}