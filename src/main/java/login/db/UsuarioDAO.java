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
    
    public boolean existe(Usuario u) throws Exception{
        // 1. especificar o comando SQL (Select)
        String sql = "SELECT * FROM Aluno WHERE nomeAluno = ? AND senhaAluno = ?";
        // 2. estabelecer uma conexão com o banco
        Connection conexao = new ConnectionFactory(). obterConexao();
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
}
