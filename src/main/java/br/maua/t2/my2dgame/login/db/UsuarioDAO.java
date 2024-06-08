/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame.login.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.maua.t2.my2dgame.login.modelo.Usuario;

/**
 *
 * @author schix
 */
public class UsuarioDAO {
    public static Usuario authenticate(String login, String senha) {
        String sql = "SELECT * FROM Aluno WHERE nomeAluno = ? AND senhaAluno = ?";
        Usuario usuario = null;

        try (Connection conexao = new ConnectionFactory().obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);) {
            ps.setString(1, login);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("idAluno"),
                        rs.getString("nomeAluno"),
                        rs.getString("senhaAluno"),
                        rs.getInt("recorde"));
            }

            rs.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return usuario;
    }

    public static Usuario getByLogin(String login) {
        String sql = "SELECT * FROM Aluno WHERE nomeAluno = ?";
        Usuario usuario = null;

        try (Connection conexao = new ConnectionFactory().obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);) {
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("idAluno"),
                        rs.getString("nomeAluno"),
                        rs.getString("senhaAluno"),
                        rs.getInt("recorde"));
            }

            rs.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return usuario;
    }

    public static List<Usuario> getAll() {
        List<Usuario> all = new ArrayList<>();
        try (Connection conexao = new ConnectionFactory().obterConexao();
                PreparedStatement ps = conexao.prepareStatement("SELECT * FROM Aluno");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                all.add(new Usuario(
                        rs.getInt("idAluno"),
                        rs.getString("nomeAluno"),
                        rs.getString("senhaAluno"),
                        rs.getInt("recorde")));
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return all;
    }

    public static int getRecorde(int codigo) {
        String sql = "SELECT * FROM Aluno WHERE idAluno = ?";
        int recorde = 0;

        try (Connection conexao = new ConnectionFactory().obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                recorde = rs.getInt("recorde");
            }

            rs.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return recorde;
    }

    public static void setRecorde(int codigo, int recorde) {
        String sql = "UPDATE Aluno SET recorde = ? WHERE idAluno = ?";
        try (Connection conexao = new ConnectionFactory().obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, recorde);
            ps.setInt(2, codigo);
            ps.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    public static void inserir(Usuario user) {
        var sql = "INSERT INTO Aluno (nomeAluno, senhaAluno, recorde) VALUES (?, ?, ?)";
        try (Connection conexao = new ConnectionFactory().obterConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getSenha());
            ps.setInt(4, user.getRecorde());
            ps.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}