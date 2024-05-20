/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
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
    public boolean gameFinished = false;
    public String message = "";
    int messageCounter = 0;
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
        
        if(gp.gameState == gp.playState){
            
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }
    public void drawPauseScreen(){
            String text = "Paused";
            int x = getXforCenteredText(text);
            
            
            int y = gp.screenHeight/2;
            g2.drawString(text, x, y);
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
