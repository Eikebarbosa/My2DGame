/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
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
        getPlayerAttackImage();
    }
    public void setDefaultValues(){
        
        worldX = gp.tileSize * 45;
        worldY = gp.tileSize * 47;
        speed = 4;
        direction = "down";
        
        //player status
        
        maxLife = 6;
        life = maxLife;
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
    
    //caso der errado, coloque todos esses junto com os demais acima
    public void getPlayerAttackImage(){
        
        try {
            attackUp1 = ImageIO.read(getClass().getResourceAsStream("/images/boy_attack_up_1.png"));
            attackUp2 = ImageIO.read(getClass().getResourceAsStream("/images/boy_attack_up_2.png"));
            attackDown1 = ImageIO.read(getClass().getResourceAsStream("/images/boy_attack_down_1.png"));
            attackDown2 = ImageIO.read(getClass().getResourceAsStream("/images/boy_attack_down_2.png"));
            attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/images/boy_attack_Left_1.png"));
            attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/images/boy_attack_Left_2.png"));
            attackRight1 = ImageIO.read(getClass().getResourceAsStream("/images/boy_attack_right_1.png"));
            attackRight2 = ImageIO.read(getClass().getResourceAsStream("/images/boy_attack_right_2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        
        if(attacking == true){
            attacking();
        }
        
        else if (keyH.upPressed == true || keyH.downPressed == true || 
                keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true){
            
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
         
         //CHECK MONSTER COLLISION
         int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
         contactMonster(monsterIndex);
         
         //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false && keyH.enterPressed == false){
                
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
         gp.keyH.enterPressed = false;
            
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
        
        //THIS NEED TO BE OUTSIDE OF KEY IF STATEMENT!
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void attacking(){
        
        spriteCounter++;
        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;
        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
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
        
        if(gp.keyH.enterPressed == true){
            
            if(i != 999){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();   
            }
            else{
                attacking = true;
            }
            
        }
        
       // gp.keyH.enterPressed = false;
    }

                
    
    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false){
                life -= 1;
                invincible = true;
                
                
            }
        }
    }
    
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
            switch(direction){
            case "up":
                if(attacking == false){
                    if(spriteNum == 1){image = up1;}
                    if(spriteNum == 2){image = up2;}
                }
                if(attacking == true){
                    tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1){image = attackUp1;}
                    if(spriteNum == 2){image = attackUp2;}
                }
                break;
                    
            case "down":
                if(attacking == false){
                    if(spriteNum == 1){image = down1;}
                    if(spriteNum == 2){image = down2;}
                }
                if(attacking == true){
                    if(spriteNum == 1){image = attackDown1;}
                    if(spriteNum == 2){image = attackDown2;}
                }
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum == 1){image = left1;}
                    if(spriteNum == 2){image = left2;}
                }
                 if(attacking == true){
                    tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1){image = attackLeft1;}
                    if(spriteNum == 2){image = attackLeft2;}
                 }   
                break;
            case "right":
                if(attacking == false){
                    if(spriteNum == 1){image = right1;}
                    if(spriteNum == 2){image = right2;}
                }
                if(attacking == true){
                    if(spriteNum == 1){image = attackRight1;}
                    if(spriteNum == 2){image = attackRight2;}
                }
                break;
                    
        }
            
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage( image, tempScreenX, tempScreenY, 42, 90, null);
        
        //RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
        //DEBUG
        //g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //g2.setColor(Color.white);
        //g2.drawString("Invincible:"+invincibleCounter, 10, 400);
    }
}
                    
                
           
