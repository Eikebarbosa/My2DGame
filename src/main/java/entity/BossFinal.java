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
            up1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_Left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_right_2.png"));
            
        
        }catch(IOException e){
            e.printStackTrace();
        }
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
