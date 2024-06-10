/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author keyex
 */
public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    // debug
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // PLAY STATE
        if (gp.gameState == gp.playState) {

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            // debug
            if (code == KeyEvent.VK_T) {
                if (checkDrawTime == false) {
                    checkDrawTime = true;
                } else if (checkDrawTime == true) {
                    checkDrawTime = false;
                }
            }
            if (code == KeyEvent.VK_P) {
                System.out.println("(" + ((int) (gp.player.worldX / gp.tileSize)) + ", "
                        + ((int) (gp.player.worldY / gp.tileSize)) + ")");
            }
        }
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }
        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.dialogueEntity.speak();
            }
        }
        // GAME OVER STATE
        else if (gp.gameState == gp.gameOverState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
                gp.retry();
            }
        }
        // VICTORY STATE
        else if (gp.gameState == gp.victoryState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.answersState;
                gp.quizScreen.showAnswers = true;
                gp.quizScreen.isActive = true;
            }
        }
        // QUIZ STATE
        else if (gp.gameState == gp.quizState) {
            switch (code) {
                case KeyEvent.VK_1, KeyEvent.VK_NUMPAD1, KeyEvent.VK_A -> {
                    gp.quizScreen.select(0);
                    gp.quizScreen.nextQuestion();
                }
                case KeyEvent.VK_2, KeyEvent.VK_NUMPAD2, KeyEvent.VK_B -> {
                    gp.quizScreen.select(1);
                    gp.quizScreen.nextQuestion();
                }
                case KeyEvent.VK_3, KeyEvent.VK_NUMPAD3, KeyEvent.VK_C -> {
                    gp.quizScreen.select(2);
                    gp.quizScreen.nextQuestion();
                }
                case KeyEvent.VK_4, KeyEvent.VK_NUMPAD4, KeyEvent.VK_D -> {
                    gp.quizScreen.select(3);
                    gp.quizScreen.nextQuestion();
                }
                case KeyEvent.VK_5, KeyEvent.VK_NUMPAD5, KeyEvent.VK_E -> {
                    gp.quizScreen.select(4);
                    gp.quizScreen.nextQuestion();
                }
            }
        }
        // ANSWERS STATE
        else if (gp.gameState == gp.answersState) {
            if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
                gp.quizScreen.nextQuestion();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

    }
}
