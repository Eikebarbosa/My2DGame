/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_Heart;
import object.SuperObject;


/**
 *
 * @author Mateus
 */
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        
        //create hud object
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        
    }
    
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(arial_40);
        g2.setColor(Color.BLACK);
        
        //PLAY STATE
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        ///PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
           drawPlayerLife();
           drawDialogueScreen(); 
        }
        //GAME OVER STATE
        if(gp.gameState == gp.gameOverState){
           drawGameOverScreen();
        }
        
    }
    public void drawPlayerLife(){
        
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        
        //draw max life
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        
        //draw current life
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
    public void drawPauseScreen(){
            String text = "Paused";
            int x = getXforCenteredText(text);
            
            
            int y = gp.screenHeight/2;
            g2.drawString(text, x, y);
        }
    public void drawDialogueScreen(){
        
        //WINDOW
        int x = gp.tileSize + 2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize * 4;
        
        drawSubWindow(x, y, width, height);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        x += gp.tileSize;
        y += gp.tileSize;
        
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
            
        
    }
    public void drawSubWindow(int x, int y, int width, int height){
        
        //ULTIMO NUMERO MUDA A OPACIDADE DO DIALOGO
        Color c = new Color(0,0,0,205);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
        
        
    }
    public int getXforCenteredText(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return x;
    }
    
    public void drawGameOverScreen(){
        g2.setColor(new Color(0, 0, 0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        int x;
        int y;
        String text;
        g2.setFont(gp.getFont().deriveFont(Font.BOLD, 110f));
        
        text = "Fim de jogo";
        //shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        //main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);
        
        //retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "aperte ENTER para tentar novamente";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        
    }
        
        
        //FINAL DO JOGO A SEGUIR(apagado pelo cara do video)
        /*if(gameFinished == true){
            
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            
            String text;
            int textLenght;
            int x;
            int y;
            
            text = "Você achou o tesouro";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            
            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);
            
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            
            text = "Parabéns!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            
            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);
            
            gp.gameThread = null;
        }
        else{
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawString("Chaves = "+ gp.player.hasKey, 50, 50);
            //MESSAGE
            if(messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;

                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                    }
                }  
            }*/
        
    
}
