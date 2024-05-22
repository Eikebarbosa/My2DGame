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
        speed = 1;
        
        getImage();
        setDialogue();
    }
    public void getImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_Left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png"));
            
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setDialogue(){
        
        //Os dialogos do NPC
        dialogues[0] = "AH!!";
        dialogues[1] = "Me desculpe. Eu não te vi ai, Doutora.";
        dialogues[2] = "Você veio ajudar?";
        dialogues[3] = "QUE ÓTIMO!! Quanto mais ajuda, melhor. \nVocê precisa entrar lá e salvar o hospital";
        
        
        
        
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

    
    
