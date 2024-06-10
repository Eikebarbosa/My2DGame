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
 * @author keyex
 */
public class SlimeMonster extends Entity {
    public SlimeMonster(GamePanel gp, int x, int y, int group) {
        super(gp, x, y);
        this.group = group;
        type = 2;
        name = "Bactéria (lvl 1)";
        direction = "down";
        speed = 2;
        maxLife = 1;
        life = maxLife;

        spriteScale = 2.5f;
        solidArea.x = 10;
        solidArea.y = 40;
        solidArea.width = 65;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        sprite = resolveImage();
    }

    public void getImage() {
        try {
            walkingSprites[0] = ImageIO.read(getClass().getResourceAsStream("/images/slime1.png"));
            walkingSprites[1] = ImageIO.read(getClass().getResourceAsStream("/images/slime2.png"));
            walkingSprites[2] = ImageIO.read(getClass().getResourceAsStream("/images/slime1.png"));
            walkingSprites[3] = ImageIO.read(getClass().getResourceAsStream("/images/slime2.png"));
            walkingSprites[4] = ImageIO.read(getClass().getResourceAsStream("/images/slime1.png"));
            walkingSprites[5] = ImageIO.read(getClass().getResourceAsStream("/images/slime2.png"));
            walkingSprites[6] = ImageIO.read(getClass().getResourceAsStream("/images/slime1.png"));
            walkingSprites[7] = ImageIO.read(getClass().getResourceAsStream("/images/slime2.png"));
            onFreezeSprite = Entity.redImage(walkingSprites[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // escolhe um numero de 1 a 100

            if (i <= 25) {
                setDirection("up");
            }
            if (i > 25 && i <= 50) {
                setDirection("down");
            }
            if (i > 50 && i <= 75) {
                setDirection("left");
            }
            if (i > 75 && i <= 100) {
                setDirection("right");
            }

            actionLockCounter = 0;
        }
    }

    @Override
    public void damage(int damage) {
        super.damage(damage);
        freezed = true;
        freezedCounter = 60;
    }
}
