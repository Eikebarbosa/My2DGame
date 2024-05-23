/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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
        
        
        direction = "down";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        
        getImage();
    }
    public void getImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/images/greenslime_down_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/images/greenslime_down_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/images/greenslime_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/images/greenslime_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/images/greenslime_down_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/images/greenslime_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/images/greenslime_down_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/images/greenslime_down_2.png"));
            
        
        }catch(IOException e){
            e.printStackTrace();
        }
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
