/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

/**
 *
 * @author keyex
 */
public class Player extends Entity {
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
       
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle(0, 0, 48, 48);
        solidArea.x = 16;
        solidArea.y = 60;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 8;
        solidArea.height = 21;
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/images/doctorup1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/images/doctorup2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/images/doctordown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/images/doctordown2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/images/doctorLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/images/doctorLeft2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/images/doctorright1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/images/doctorright2.png"));
            
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyH.upPressed == true || keyH.downPressed == true || 
                keyH.leftPressed == true || keyH.rightPressed == true){
            
            if(keyH.upPressed == true){
            direction = "up";            
        }
        else if(keyH.downPressed == true){
            direction = "down";            
        }
        else if(keyH.leftPressed == true){
            direction = "left";            
        }
        else if(keyH.rightPressed == true){
            direction = "right";           
        }
            
         // CHECK TILE COLLISION
         collisionOn = false;
         gp.cChecker.checkTile(this);
         
         //Check object collision
         int objIndex = gp.cChecker.checkObject(this, true);
         pickUpObject(objIndex);
        
         //CHECK NCP COLLISION
         int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
         interactNPC(npcIndex);
         
         //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false){
                
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
         
        spriteCounter++;
        if(spriteCounter > 10){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        }
        
        
    }
    
    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;
            
            switch(objectName){
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Você encontrou uma chave");
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("Você abriu a porta");
                    }
                    else{
                        gp.ui.showMessage("Você precisa de uma chave");
                    }
                    System.out.println("Key:" +hasKey);
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed += 2;
                    gp.obj[i] = null;
                    break; 
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }
    }
    
    public void interactNPC(int i){
        if(i != 999){
            System.out.println("You are hitting the npc");
        }
    }
    
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        
            switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                
                break;
           
        }
        g2.drawImage( image, screenX, screenY, 42, 84, null);
        
    }
}
