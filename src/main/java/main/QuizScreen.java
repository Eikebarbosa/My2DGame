/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import login.db.QuestionDAO;

public class QuizScreen {
    GamePanel gp;
    List<String[]> questions = new ArrayList<>();
    List<List<String[]>> options = new ArrayList<>();
    List<Integer> correctAnswers = new ArrayList<>();
    public int[] selectedAnswers;

    int currentQuestion = 0;
    public boolean showAnswers = false;
    public boolean isActive = false;

    public QuizScreen(GamePanel gp) {
        this.gp = gp;
        for (Map.Entry<String, String> entry : QuestionDAO.getQuestions().entrySet()) {
            String[] split = entry.getKey().split("\\r?\\n");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].trim();
            }

            char lastAnswerLetter = Character.toUpperCase(split[split.length - 1].charAt(0));
            int countAnswers = switch (lastAnswerLetter) {
                case 'E' -> 5;
                default -> 4;
            };

            String question = split[0];
            for (int i = 1; i < split.length - countAnswers; i++) {
                question += "\n" + split[i];
            }
            this.questions.add(wrap(question, 100));

            char answer = Character.toUpperCase(entry.getValue().trim().charAt(0));
            switch (answer) {
                case 'A' -> this.correctAnswers.add(0);
                case 'B' -> this.correctAnswers.add(1);
                case 'C' -> this.correctAnswers.add(2);
                case 'D' -> this.correctAnswers.add(3);
                case 'E' -> this.correctAnswers.add(4);
                default -> throw new RuntimeException(
                        "A questão #" + split[0].substring(0, 10)
                                + "... não tem uma resposta que comece com as letras A, B, C, D ou E.");
            }

            List<String[]> questionOptions = new ArrayList<>();
            for (int i = split.length - countAnswers; i < split.length; i++) {
                questionOptions.add(wrap(split[i], 100));
            }
            options.add(questionOptions);
        }
        selectedAnswers = new int[questions.size()];
    }

    public void draw(Graphics2D g2) {
        if (isActive) {
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(50, 50, gp.screenWidth - 100, gp.screenHeight - 100, 35, 35);
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(50, 50, gp.screenWidth - 100, gp.screenHeight - 100, 25, 25);

            g2.setFont(new Font("Arial", Font.BOLD, 35));

            String missionLabel = "DERROTE GEHRMAN, O PRIMEIRO VÍRUS!";
            String questionLabel = "Questão " + currentQuestion + "/" + questions.size();

            g2.setColor(Color.BLACK);
            g2.drawString(missionLabel, 235 + 2, 100 + 2);
            g2.drawString(questionLabel, 830 + 2, 790 + 2);

            g2.setColor(Color.WHITE);
            g2.drawString(missionLabel, 235 + 1, 100 + 1);
            g2.drawString(questionLabel, 830 + 1, 790 + 1);

            g2.setColor(Color.BLACK);
            g2.drawString(missionLabel, 235, 100);
            g2.drawString(questionLabel, 830, 790);

            g2.setFont(new Font("Arial", Font.BOLD, 18));
            // Draw question
            String[] lines = questions.get(currentQuestion);
            for (int i = 0; i < lines.length; i++) {
                int lineSpacing = (i * 35);
                g2.drawString(lines[i], 100, 150 + lineSpacing);
            }

            // Draw options
            List<String[]> questionOptions = options.get(currentQuestion);
            int lineCount = 0;
            for (int i = 0; i < questionOptions.size(); i++) {
                if (showAnswers) {
                    if (correctAnswers.get(currentQuestion) == i) {
                        g2.setColor(Color.GREEN);
                    } else if (selectedAnswers[currentQuestion] == i) {
                        g2.setColor(Color.RED);
                    } else {
                        g2.setColor(Color.BLACK);
                    }
                }

                String[] optionLines = questionOptions.get(i);
                for (int j = 0; j < optionLines.length; j++) {
                    int questionSpacing = lines.length * 35;
                    int optionsSpacing = lineCount * 30 + i * 35;
                    int lineSpacing = j * 35;
                    g2.drawString(optionLines[j], 100, 190 + questionSpacing + optionsSpacing + lineSpacing);
                }
                lineCount += optionLines.length;
            }
        }
    }

    public void select(int a) {
        selectedAnswers[currentQuestion] = a;
    }

    public int countCorrect() {
        System.out.println(correctAnswers);
        int count = 0;
        for (int i = 0; i < selectedAnswers.length; i++) {
            if (correctAnswers.get(i) == selectedAnswers[i]) {
                count++;
            }
        }
        return count;
    }

    public void nextQuestion() {
        currentQuestion++;
        if (currentQuestion >= questions.size()) {
            currentQuestion = 0;
            isActive = false;
            if (gp.gameState == gp.answersState) {
                showAnswers = false;
                gp.gameState = gp.playState;
                gp.retry();
            } else {
                gp.gameState = gp.playState;
            }
        }
    }

    public static String[] wrap(String string, int lineLength) {
        List<String> b = new ArrayList<>();
        for (String line : string.split(Pattern.quote("\n"))) {
            b.addAll(wrapLine(line, lineLength));
        }
        return b.toArray(String[]::new);
    }

    private static List<String> wrapLine(String line, int lineLength) {
        if (line.length() == 0)
            return new ArrayList<>();
        if (line.length() <= lineLength)
            return List.of(line);
        String[] words = line.split(" ");
        List<String> allLines = new ArrayList<>();
        StringBuilder trimmedLine = new StringBuilder();
        for (String word : words) {
            if (trimmedLine.length() + 1 + word.length() <= lineLength) {
                trimmedLine.append(word).append(" ");
            } else {
                allLines.add(trimmedLine.toString());
                trimmedLine = new StringBuilder();
                trimmedLine.append(word).append(" ");
            }
        }
        if (trimmedLine.length() > 0) {
            allLines.add(trimmedLine.toString());
        }
        return allLines;
    }

}
