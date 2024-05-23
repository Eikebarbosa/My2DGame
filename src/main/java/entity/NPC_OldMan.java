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
 * @author Mateus
 */
public class NPC_OldMan extends Entity {
    
    public NPC_OldMan(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 0;
        
        getImage();
        setDialogue();
    }
    public void getImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_Left_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
            
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setDialogue(){
        
        //Os dialogos do NPC
        dialogues[0] ="Ah! Você veio ajudar? \n Que ótimo! Quanto mais ajuda, melhor. \nVocê precisa entrar lá e salvar o hospital";
        
        
        
        
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

    
    

