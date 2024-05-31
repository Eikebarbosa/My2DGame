/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

/**
 *
 * @author keyex
 */
public class BossFinal extends Entity{  
    public BossFinal(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 0;
        solidArea.x = 20;//hitbox 
        //solidArea.y = ;
        solidArea.width = 70;
        solidArea.height = 70;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
        setDialogue();
    }
    public void getImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/images/boss.png"));
            
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void Resize(){
        
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch(direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
    
        g2.drawImage(image, screenX, screenY, 150, 210, null);
    }
    public void setDialogue(){
        
        //Os dialogos do NPC
        dialogues[0] ="Você ousa me desafiar? \nVOCÊ\nNÃO\nÉ\nPAREO";
        
        
        
        
    }
    public void setAction(){
        
        actionLockCounter ++;
        
        if(actionLockCounter == 120){
        Random random = new Random();
        int i = random.nextInt(100) + 1; //escolhe um numero de 1 a 100
        
        if(i <= 25){
            direction = "down";
        }
        if(i > 25 && i <= 50){
            direction = "down";
        }
        if(i > 50 && i <= 75){
            direction = "down";
        }
        if(i > 75 && i <= 100){
            direction = "down";
        }
         
        actionLockCounter = 0;
        }
    }
    public void speak(){
        super.speak();
    }
}
