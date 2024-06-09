/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame.entity;

import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import br.maua.t2.my2dgame.main.GamePanel;

/**
 *
 * @author Mateus
 */
public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp, int x, int y) {
        super(gp, x, y);

        direction = "down";
        speed = 0;
        spriteX = 6;
        spriteY = -7;
        spriteScale = 3;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        setDialogue();
        sprite = resolveImage();
    }

    public void getImage() {
        try {
            walkingSprites[0] = ImageIO.read(getClass().getResourceAsStream("/npc/gotinha1.png"));
            walkingSprites[1] = ImageIO.read(getClass().getResourceAsStream("/npc/gotinha1.png"));
            walkingSprites[2] = ImageIO.read(getClass().getResourceAsStream("/npc/gotinha1.png"));
            walkingSprites[3] = ImageIO.read(getClass().getResourceAsStream("/npc/gotinha1.png"));
            walkingSprites[4] = ImageIO.read(getClass().getResourceAsStream("/npc/gotinha1.png"));
            walkingSprites[5] = ImageIO.read(getClass().getResourceAsStream("/npc/gotinha1.png"));
            walkingSprites[6] = ImageIO.read(getClass().getResourceAsStream("/npc/gotinha1.png"));
            walkingSprites[7] = ImageIO.read(getClass().getResourceAsStream("/npc/gotinha1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDialogue() {

        // Os dialogos do NPC
        dialogues[0] = "Ah, DOUTORA! VOCÊ FINALMENTE CHEGOU!! \nMaravilha! Quanto mais ajuda, melhor. \nVocê precisa entrar lá e salvar o hospital! \n\nPressione ENTER novamente.       ====>";
        dialogues[1] = "Mas cuidado, doutora! \nVocê irá se deparar com diversos inimigos, \nnão tenha medo \n\nPressione ENTER novamente.        ====>";
        dialogues[2] = "Para abrir a próxima porta, você deverá \nmatar todos inimigos presentes na sala  \n\nPressione ENTER novamente.        ====>";
        dialogues[3] = "A sua seringa, que sempre está com você, deve ajudar\n BOA SORTE!. \n\nPressione ENTER novamente.           ====>";
        dialogues[4] = "Para responder o quiz: (abcde) ou (12345)";
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // escolhe um numero de 1 a 100

            if (i <= 25) {
                direction = "down";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "down";
            }
            if (i > 75 && i <= 100) {
                direction = "down";
            }

            actionLockCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }
}
