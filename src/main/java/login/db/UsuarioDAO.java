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

    public boolean existe(Usuario u) throws Exception {
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

    public void inserir(String nomeAluno, String senhaAluno) throws Exception{
        // 1: Definir o comando SQL
        String sql = "INSERT INTO Aluno(nomeAluno, senhaAluno) VALUES (?, ?)";
        // 2: Abrir uma conexão
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection c = factory.obtemConexao()) {
            // 3: Pré compilar o comando
            PreparedStatement ps = c.prepareStatement(sql);
            
            // 4: Preencher os dados faltantes
            ps.setString(1, nomeAluno);
            ps.setString(2, senhaAluno);
            
            // 5: Executar o comando
            ps.execute();
        }
    }

    public void inserir(Usuario novoUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void inserirUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
