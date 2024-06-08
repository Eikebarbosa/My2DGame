/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import login.modelo.Question;

/**
 *
 * @author schix
 */
public class QuestionDAO {

    public static Question insert(Question question) {
        Connection connection = new ConnectionFactory().obterConexao();

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Pergunta (textoPergunta, respostaCorreta) VALUES (?, ?)")) {
            statement.setString(2, question.toRawText());
            statement.setString(3, question.getCorrectOption());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir pergunta no Banco de Dados.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    question = new Question(generatedKeys.getInt(1), question.getHeader(), question.getOptions(),
                            question.getCorrectAnswer());
                } else {
                    throw new SQLException("Falha ao inserir pergunta no Banco de Dados.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return question;
    }

    public static void delete(int id) {
        Connection connection = new ConnectionFactory().obterConexao();

        try (PreparedStatement statement = connection
                .prepareStatement("DELETE FROM Pergunta WHERE idPergunta = ?")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean existsByRawText(String key) {
        Connection connection = new ConnectionFactory().obterConexao();

        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM Pergunta WHERE textoPergunta = ?")) {
            statement.setString(1, key);

            ResultSet result = statement.executeQuery();

            return result != null && result.next();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static List<Question> getQuestions() {
        Connection connection = new ConnectionFactory().obterConexao();
        List<Question> questions = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Pergunta");

            while (result.next()) {
                questions.add(new Question(result.getInt("idPergunta"), result.getString("textoPergunta"),
                        result.getString("respostaCorreta")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return questions;
    }
}
