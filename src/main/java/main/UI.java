/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


/**
 *
 * @author Mateus
 */
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }
    
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        
        //PLAY STATE
        if(gp.gameState == gp.playState){
            
        }
        ///PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
           drawDialogueScreen(); 
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
