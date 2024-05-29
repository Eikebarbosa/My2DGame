/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class QuizScreen {
    GamePanel gp;
    String[] questions = {
        "Qual é a capital da França?",
        "Quem escreveu 'Dom Quixote'?",
        "Qual é a fórmula química da água?"
    };
    String[][] options = {
        {"1. Paris", "2. Londres", "3. Roma", "4. Berlim"},
        {"1. William Shakespeare", "2. Miguel de Cervantes", "3. Dante Alighieri", "4. Homero"},
        {"1. H2O", "2. CO2", "3. NaCl", "4. O2"}
    };
    int currentQuestion = 0;
    public boolean isActive = false;

    public QuizScreen(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        if (isActive) {
            g2.setColor(Color.WHITE);
            g2.fillRect(50, 50, gp.screenWidth - 100, gp.screenHeight - 100);

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 20));

            // Draw question
            g2.drawString(questions[currentQuestion], 100, 100);

            // Draw options
            for (int i = 0; i < options[currentQuestion].length; i++) {
                g2.drawString(options[currentQuestion][i], 100, 150 + (i * 40));
            }
        }
    }

    public void nextQuestion() {
        currentQuestion++;
        if (currentQuestion >= questions.length) {
            currentQuestion = 0;
            isActive = false;
            gp.gameState = gp.playState;
        }
    }
}

