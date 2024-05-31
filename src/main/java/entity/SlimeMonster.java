/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

/**
 *
 * @author keyex
 */
public class SlimeMonster extends Entity {
    public SlimeMonster(GamePanel gp) {
        super(gp);
        
        type = 2;
        direction = "down";
        speed = 2;
        maxLife = 2;
        life = maxLife;
        
        solidArea.x = 5;
        solidArea.y = 20;
        solidArea.width = 45;
        solidArea.height = 35;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        
        getImage();
    }
    public void getImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/images/slime1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/images/slime2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/images/slime1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/images/slime2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/images/slime1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/images/slime2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/images/slime1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/images/slime2.png"));
            
        
        }catch(IOException e){
            e.printStackTrace();
        }
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
    
        g2.drawImage(image, screenX, screenY, 100, 100, null);
    }

    public void setAction(){
        
        actionLockCounter ++;
        
        if(actionLockCounter == 120){
        Random random = new Random();
        int i = random.nextInt(100) + 1; //escolhe um numero de 1 a 100
        
        if(i <= 25){
            direction = "up";
        }
        if(i > 25 && i <= 50){
            direction = "down";
        }
        if(i > 50 && i <= 75){
            direction = "left";
        }
        if(i > 75 && i <= 100){
            direction = "right";
        }
         
        actionLockCounter = 0;
        }
    }
    public void speak(){
        super.speak();
    }
}
