/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;

/**
 *
 * @author keyex
 */
public class Entity {
    
    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0,0,40,40);
    public Rectangle attackArea = new Rectangle(0, 0, 70, 70);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    boolean attacking = false;
    public int invincibleCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public int type;//0 = player, 1 = npc, 2 = monster
    boolean hpBarOn = false;
    int hpBarCounter = 0;
    
    //character status
    public int maxLife;
    public int life;
    
    public Entity(GamePanel gp){
        this.attackArea = new Rectangle(0, 0, 0, 0);
        this.gp = gp;
    }
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                
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
                
                //monster hp bar
                if(type == 2 && life != maxLife){
                    hpBarOn = true;
                    double oneScale = (double)gp.tileSize/maxLife;
                    double hpBarValue = oneScale*life;
                    
                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect(screenX-1, screenY-16, gp.tileSize + 2, 12);
                    
                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);
                    
                    hpBarCounter++;
                    
                    if(hpBarCounter > 6000){
                        hpBarCounter = 0;
                        hpBarOn = false;
                    }
                    
                }
                
                
                
                break;
           
        }
                
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        
    }
    public void setAction(){
        
    }
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        
        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                 direction = "up";
                 break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
}
    public void update(){
        setAction();
        
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        
        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                //we can give damage
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }
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
        if(invincible == true){
            invincibleCounter++;
                if(invincibleCounter > 40){
                    invincible = false;
                    invincibleCounter = 0;
            }
        }
            }

 

    
}

