/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import br.maua.t2.my2dgame.object.OBJ_Heart;
import br.maua.t2.my2dgame.object.SuperObject;

/**
 *
 * @author Mateus
 */
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        // create hud object
        SuperObject heart = new OBJ_Heart(gp, 0, 0);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void showMessage(String text, int time) {
        message = text;
        messageCounter = time * 60;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.BLACK);

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawMessage(message);
            drawPlayerLife();
            drawPlayerHighcore();
        }
        // CUTSCENE
        if (gp.gameState == gp.cutSceneState) {
            drawMessage(message);
        }
        /// PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
            drawPlayerHighcore();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen("Fim de Jogo", "aperte ENTER para tentar novamente");
        }
        // VICTORY STATE
        if (gp.gameState == gp.victoryState) {
            drawGameOverScreen("Vitória!", "aperte ENTER para ver o gabarito");
        }
    }

    public void drawPlayerLife() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // draw max life
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, 50, 50, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        // draw current life
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, 50, 50, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, 50, 50, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawPlayerHighcore() {
        var string = "Recorde: " + gp.recorde;

        g2.setFont(arial_40);
        // shadow
        g2.setColor(Color.black);
        int x = gp.screenWidth - gp.tileSize * 4;
        int y = gp.screenHeight - gp.tileSize * 11;
        g2.drawString(string, x, y);
        // main
        g2.setColor(Color.white);
        g2.drawString(string, x - 4, y - 4);
    }

    public void drawPauseScreen() {
        String text = "Paused";
        int x = getXforCenteredText(text, g2);

        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {

        // WINDOW
        int x = gp.tileSize + 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {

        // ULTIMO NUMERO MUDA A OPACIDADE DO DIALOGO
        Color c = new Color(0, 0, 0, 205);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int getXforCenteredText(String text, Graphics2D g2) {
        int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - lenght / 2;
        return x;
    }

    public void resetMessage() {
        messageCounter = 0;
    }

    public void drawMessage(String text) {
        if (messageCounter > 0) {
            messageCounter--;
        } else if (messageCounter == 0) {
            return;
        }
        int x;
        int y;
        g2.setFont(gp.getFont().deriveFont(Font.BOLD, 40f));

        // shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text, g2);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // message
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

    }

    public void drawGameOverScreen(String bigText, String smallText) {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        g2.setFont(gp.getFont().deriveFont(Font.BOLD, 110f));

        // shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(bigText, g2);
        y = gp.tileSize * 4;
        g2.drawString(bigText, x, y);
        // main
        g2.setColor(Color.white);
        g2.drawString(bigText, x - 4, y - 4);

        // recorde
        var recordeString = "Recorde: " + gp.recorde;
        g2.setFont(g2.getFont().deriveFont(40f));
        x = getXforCenteredText(recordeString, g2);
        y += gp.tileSize * 1;
        g2.drawString(recordeString, x, y);

        // retry
        g2.setFont(g2.getFont().deriveFont(50f));
        x = getXforCenteredText(smallText, g2);
        y += gp.tileSize * 3;
        g2.drawString(smallText, x, y);

    }

    // FINAL DO JOGO A SEGUIR(apagado pelo cara do video)
    /*
     * if(gameFinished == true){
     * 
     * g2.setFont(arial_40);
     * g2.setColor(Color.WHITE);
     * 
     * String text;
     * int textLenght;
     * int x;
     * int y;
     * 
     * text = "Você achou o tesouro";
     * textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
     * 
     * x = gp.screenWidth/2 - textLenght/2;
     * y = gp.screenHeight/2 - (gp.tileSize*3);
     * g2.drawString(text, x, y);
     * 
     * g2.setFont(arial_80B);
     * g2.setColor(Color.yellow);
     * 
     * text = "Parabéns!";
     * textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
     * 
     * x = gp.screenWidth/2 - textLenght/2;
     * y = gp.screenHeight/2 + (gp.tileSize*2);
     * g2.drawString(text, x, y);
     * 
     * gp.gameThread = null;
     * }
     * else{
     * g2.setFont(arial_40);
     * g2.setColor(Color.WHITE);
     * g2.drawString("Chaves = "+ gp.player.hasKey, 50, 50);
     * //MESSAGE
     * if(messageOn == true){
     * g2.setFont(g2.getFont().deriveFont(30F));
     * g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
     * 
     * messageCounter++;
     * 
     * if(messageCounter > 120){
     * messageCounter = 0;
     * messageOn = false;
     * }
     * }
     * }
     */

}
