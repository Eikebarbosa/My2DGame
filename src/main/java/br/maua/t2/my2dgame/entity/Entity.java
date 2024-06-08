/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import br.maua.t2.my2dgame.main.GamePanel;

/**
 *
 * @author keyex
 */
public class Entity {

    public GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public int signumDirectionX;
    public int signumDirectionY;
    public String direction;
    public int group = -1;

    public BufferedImage sprite;
    public BufferedImage[] walkingSprites = new BufferedImage[8], attackSprites = new BufferedImage[8];
    public BufferedImage onFreezeSprite;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public float spriteScale = 1f;
    public int spriteX;
    public int spriteY;
    public int spriteOffsetX;
    public int spriteOffsetY;

    public String name;
    public Rectangle solidArea = new Rectangle(0, 0, 0, 0);
    public Rectangle attackArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean freezed;
    public int freezedCounter;
    public boolean invincible = false;
    protected boolean entityHandleSpriteDrawing = true;
    public int attackDelayCounter;
    public int invincibleCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public int type;// 0 = player, 1 = npc, 2 = monster

    // character status
    public int maxLife;
    public int life;

    public Entity(GamePanel gp, int x, int y) {
        this.attackArea = new Rectangle(0, 0, 0, 0);
        this.gp = gp;
        this.worldX = x * gp.tileSize;
        this.worldY = y * gp.tileSize;
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            int spriteVisualWidth = (int) (walkingSprites[0].getWidth() * spriteScale);

            // name label
            if (name != null) {
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                int lenght = (int) g2.getFontMetrics().getStringBounds(name, g2).getWidth();
                int x = (spriteVisualWidth / 2 - lenght / 2) + spriteX;

                g2.setColor(Color.black);
                g2.drawString(name, (int) (screenX + spriteX * spriteScale) + x + 2,
                        (int) (screenY + spriteY * spriteScale) - 22);
                g2.setColor(Color.white);
                g2.drawString(name, (int) (screenX + spriteX * spriteScale) + x,
                        (int) (screenY + spriteY * spriteScale) - 22);
            }

            // hp bar
            if (type != 0 && life != maxLife) {
                double oneScale = spriteVisualWidth / maxLife;
                int hpBarValue = (int) (oneScale * life);

                int x = (int) (screenX + spriteY * spriteScale);
                int y = (int) (screenY + spriteY * spriteScale);

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(x, y - 19, spriteVisualWidth - 2, 14);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(x - 1, y - 18, hpBarValue - 4, 12);
            }

            if (entityHandleSpriteDrawing) {
                if (freezed) {
                    sprite = onFreezeSprite;
                } else {
                    sprite = resolveImage();
                }
                g2.setColor(Color.white);
                g2.drawImage(sprite, (int) (screenX + (spriteX + spriteOffsetX) * spriteScale),
                        (int) (screenY + (spriteY + spriteOffsetY) * spriteScale),
                        (int) (sprite.getWidth() * spriteScale),
                        (int) (sprite.getHeight() * spriteScale), null);
            }

            /*
             * // DEBUG: Attack HitBox drawing
             * g2.setColor(new Color(0, 0, 255, 100));
             * 
             * g2.fillRect(
             * screenX + solidArea.x + solidArea.width / 2 + (solidArea.width / 2 +
             * attackArea.width / 2)
             * signumDirectionX
             * + attackArea.width / -2,
             * screenY + solidArea.y + solidArea.height / 2 + (solidArea.height / 2 +
             * attackArea.height /
             * 2) * signumDirectionY
             * + attackArea.height / -2,
             * attackArea.width,
             * attackArea.height);
             * 
             * 
             * // DEBUG: HitBox drawing
             * g2.setColor(new Color(255, 0, 0, 100));
             * g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width,
             * solidArea.height);
             */

        }

    }

    public void setAction() {

    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
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

    public void update() {
        setAction();

        if (freezedCounter > 0) {
            freezedCounter--;
            if (freezedCounter == 0) {
                freezed = false;
            }
        }

        if (attackDelayCounter > 0) {
            if (attackDelayCounter > 30) {
                spriteNum = 2;
            } else {
                spriteNum = 1;
            }
            attackDelayCounter--;
        }

        if (attackDelayCounter == 0) {

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);

        // IF COLLISION IS FALSE, MONSTER CAN MOVE
        if (collisionOn == false) {
            if (!freezed && attackDelayCounter == 0) {
                switch (direction) {
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
        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void damage(int damage) {
        if (invincible == false) {
            this.life -= damage;
        }
    }

    public static BufferedImage redImage(BufferedImage bImage) {
        BufferedImage brighter = new BufferedImage(bImage.getWidth(), bImage.getHeight(), bImage.getType());
        for (int x = 0; x < bImage.getWidth(); x++) {
            for (int y = 0; y < bImage.getHeight(); y++) {
                Color col = new Color(bImage.getRGB(x, y), true);
                col = new Color(Math.min(col.getRed() + 50, 255), col.getGreen(), col.getBlue(), col.getAlpha());
                brighter.setRGB(x, y, col.getRGB());
            }
        }
        return brighter;
    }

    public BufferedImage resolveImage() {
        int dir = switch (direction) {
            case "up" -> 0;
            case "down" -> 2;
            case "left" -> 4;
            case "right" -> 6;
            default -> 0;
        };
        int spr = dir + spriteNum - 1;
        var attackSpr = attackSprites[spr];
        return attackDelayCounter > 0 && attackSpr != null ? attackSpr : walkingSprites[spr];
    }

    public void setDirection(String direction) {
        this.direction = direction;
        signumDirectionX = 0;
        signumDirectionY = 0;
        switch (direction) {
            case "up" -> {
                signumDirectionY = -1;
            }
            case "down" -> {
                signumDirectionY = 1;
            }
            case "left" -> {
                signumDirectionX = -1;
            }
            case "right" -> {
                signumDirectionX = 1;
            }
        }
    }

}
