/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame.entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import br.maua.t2.my2dgame.main.GamePanel;
import br.maua.t2.my2dgame.persistencia.db.UsuarioDAO;

/**
 *
 * @author keyex
 */
public class BossFinal extends Entity {
    public boolean quizAfterDialog;
    boolean onQuiz;
    boolean cutsceneAfterDialog;
    boolean onCutscene;
    boolean bossDefeated;
    boolean onFinish;

    public BossFinal(GamePanel gp, int x, int y) {
        super(gp, x, y);
        gp.boss = this;
        maxLife = 25;
        life = maxLife;
        name = "GEHRMAN";
        direction = "down";
        speed = 0;
        type = 1;
        spriteScale = 4;
        solidArea.width = 150;
        solidArea.height = 210;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        setDialogue();
        sprite = resolveImage();
    }

    public void getImage() {
        try {
            walkingSprites[0] = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            walkingSprites[1] = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            walkingSprites[2] = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            walkingSprites[3] = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            walkingSprites[4] = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            walkingSprites[5] = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            walkingSprites[6] = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            walkingSprites[7] = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDialogue() {

        // Os dialogos do NPC
        dialogues[0] = "Você ousa me desafiar? \nVOCÊ...\nNÃO...\nÉ...\nPAREO!!";
        dialogues[1] = "NÃOOOOOOOOOOOOOOOOOOO";
        dialogues[2] = "BAHAHAHHAHAH!!!\nFALTA-TE CONHECIMENTO!";

    }

    public void setAction() {
        actionLockCounter++;

        if (quizAfterDialog && gp.gameState == gp.playState) {
            quizAfterDialog = false;
            onQuiz = true;
            gp.quizScreen.isActive = true;
            gp.gameState = gp.quizState;
        }

        if (onQuiz && !gp.quizScreen.isActive) {
            onQuiz = false;
            cutsceneAfterDialog = true;
            gp.gameState = gp.dialogueState;

            int correctAnswers = gp.quizScreen.countCorrect();

            if (gp.recorde < correctAnswers) {
                gp.recorde = correctAnswers;
                var thread = new Thread(() -> {
                    try {
                        UsuarioDAO.setRecorde(gp.user.getCodigo(), correctAnswers);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }

            if (correctAnswers >= (int) (gp.quizScreen.selectedAnswers.length * 0.60)) {
                dialogueIndex = 1;
                bossDefeated = true;
            } else {
                dialogueIndex = 2;
            }

            speak();
        }

        if (cutsceneAfterDialog && gp.gameState == gp.playState) {
            actionLockCounter = 0;
            cutsceneAfterDialog = false;
            onCutscene = true;
            gp.gameState = gp.cutSceneState;
        }

        if (onCutscene) {
            if (bossDefeated) {
                if (actionLockCounter == 120) {
                    life = 1;
                    onFinish = true;
                    gp.playSE(5);
                } else if (actionLockCounter == 160) {
                    actionLockCounter = 0;
                    gp.ui.showMessage("Finalize o chefão!", -1);
                    invincible = false;
                    gp.gameState = gp.playState;
                    onCutscene = false;
                    gp.setDefaultSpawnPoint();
                }
            } else if (actionLockCounter == 120) {
                gp.player.life = 0;
                gp.playSE(5);
                gp.gameState = gp.gameOverState;
                onCutscene = false;
                gp.setDefaultSpawnPoint();
            }
        }

        if (onFinish && life <= 0) {
            gp.gameState = gp.victoryState;
            gp.ui.resetMessage();
            onFinish = false;
        }

        if (actionLockCounter == 120 && !onCutscene) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // escolhe um numero de 1 a 100

            if (i <= 25) {
                direction = "down";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "down";
            }
            if (i > 75 && i <= 100) {
                direction = "down";
            }

            actionLockCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }
}
