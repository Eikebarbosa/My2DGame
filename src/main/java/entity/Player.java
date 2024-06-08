/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_SpawnPoint;

/**
 *
 * @author keyex
 */
public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int keys = 0;

    public Player(GamePanel gp, KeyHandler keyH, int x, int y) {
        super(gp, x, y);
        name = gp.user.getLogin();
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea.x = 3;
        solidArea.y = 3;
        spriteScale = 3;
        solidArea.width = 42;
        solidArea.height = 100;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = (int) (gp.tileSize * 1.33);
        attackArea.height = (int) (gp.tileSize * 1.33);

        entityHandleSpriteDrawing = false;
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        sprite = resolveImage();
    }

    public void setDefaultValues() {
        speed = 4;
        direction = "down";
        maxLife = 6;
        life = maxLife;
    }

    public void setDefaultPositions() {
        worldX = gp.tileSize * 45;
        worldY = gp.tileSize * 47;
        direction = "down";
    }

    public void restoreLife() {
        life = maxLife;
        invincible = false;

    }

    public void getPlayerImage() {
        try {
            walkingSprites[0] = ImageIO.read(getClass().getResourceAsStream("/images/doctorup1.png"));
            walkingSprites[1] = ImageIO.read(getClass().getResourceAsStream("/images/doctorup2.png"));
            walkingSprites[2] = ImageIO.read(getClass().getResourceAsStream("/images/doctordown1.png"));
            walkingSprites[3] = ImageIO.read(getClass().getResourceAsStream("/images/doctordown2.png"));
            walkingSprites[4] = ImageIO.read(getClass().getResourceAsStream("/images/doctorLeft1.png"));
            walkingSprites[5] = ImageIO.read(getClass().getResourceAsStream("/images/doctorLeft2.png"));
            walkingSprites[6] = ImageIO.read(getClass().getResourceAsStream("/images/doctorright1.png"));
            walkingSprites[7] = ImageIO.read(getClass().getResourceAsStream("/images/doctorright2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // caso der errado, coloque todos esses junto com os demais acima
    public void getPlayerAttackImage() {

        try {
            attackSprites[0] = ImageIO.read(getClass().getResourceAsStream("/images/doctorAttackUp1.png"));
            attackSprites[1] = ImageIO.read(getClass().getResourceAsStream("/images/doctorAttackUp2.png"));
            attackSprites[2] = ImageIO.read(getClass().getResourceAsStream("/images/doctorAttackDown1.png"));
            attackSprites[3] = ImageIO.read(getClass().getResourceAsStream("/images/doctorAttackDown2.png"));
            attackSprites[4] = ImageIO.read(getClass().getResourceAsStream("/images/doctorAttackLeft1.png"));
            attackSprites[5] = ImageIO.read(getClass().getResourceAsStream("/images/doctorAttackLeft2.png"));
            attackSprites[6] = ImageIO.read(getClass().getResourceAsStream("/images/doctorAttackRight1.png"));
            attackSprites[7] = ImageIO.read(getClass().getResourceAsStream("/images/doctorAttackRight2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        attacking();

        if (keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {

            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            if (attackDelayCounter == 0) {
                signumDirectionX = 0;
                signumDirectionY = 0;
                if (keyH.upPressed) {
                    direction = "up";
                    signumDirectionY = -1;
                }
                if (keyH.downPressed) {
                    direction = "down";
                    signumDirectionY = 1;
                }
                if (keyH.leftPressed) {
                    direction = "left";
                    signumDirectionX = -1;
                }
                if (keyH.rightPressed) {
                    direction = "right";
                    signumDirectionX = 1;
                }
            }

            var previousSolidArea = new Rectangle(solidArea);
            solidArea.width = 60;
            solidArea.height = 60;
            solidArea.x += (previousSolidArea.width - solidArea.width) / 2;
            solidArea.y += (previousSolidArea.height - solidArea.height) / 2;

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check object collision
            int objIndex = gp.cChecker.checkObject(this);
            pickUpObject(objIndex);

            solidArea = previousSolidArea;

            // CHECK MONSTER COLLISION
            if (invincible == false) {
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                contactMonster(monsterIndex);
            }

            // CHECK NPC COLLISION
            gp.cChecker.checkEntity(this, gp.npc);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && attackDelayCounter == 0) {
                worldY += signumDirectionY * speed;
                worldX += signumDirectionX * speed;
            }
        }

        // THIS NEED TO BE OUTSIDE OF KEY IF STATEMENT!
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (life <= 0) {
            gp.gameState = gp.gameOverState;
        }
    }

    public void attacking() {
        if (gp.keyH.enterPressed) {
            if (attackDelayCounter == 0) {
                int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
                if (!interactNPC(npcIndex)) {
                    attackDelayCounter = 40;
                    spriteNum = 1;
                    if (direction.equals("left")) {
                        spriteOffsetX = -18;
                    }
                }
            }
            gp.keyH.enterPressed = false;
        }
        if (attackDelayCounter == 30) {
            spriteNum = 2;
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up", "down" -> {
                    signumDirectionX = 0;
                }
                case "right", "left" -> {
                    signumDirectionY = 0;
                }
            }

            worldX += solidArea.x + solidArea.width / 2
                    + (solidArea.width / 2 + attackArea.width / 2) * signumDirectionX
                    + attackArea.width / -2;
            worldY += solidArea.y + solidArea.height / 2
                    + (solidArea.height / 2 + attackArea.height / 2) * signumDirectionY
                    + attackArea.height / -2;

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            if (gp.boss.onFinish) {
                int result = gp.cChecker.checkEntity(this, List.of(gp.boss));
                damageBoss(result);
            }

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (attackDelayCounter > 0) {
            spriteCounter = 0;
            attackDelayCounter--;
        }
        if (attackDelayCounter == 0 || freezed) {
            spriteOffsetX = 0;
        }

    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj.get(i).name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    keys++;
                    gp.obj.remove(i);
                    gp.ui.showMessage("Você encontrou uma chave", 1);
                    break;
                case "Heart":
                    gp.playSE(1);
                    life += 2;
                    gp.obj.remove(i);
                    gp.ui.showMessage("Você recebeu 1 coração!", 1);
                    break;
                case "Door":
                    if (keys > 0) {
                        gp.playSE(3);
                        int group = ((OBJ_Door) gp.obj.get(i)).group;
                        for (int j = 0; j < gp.obj.size(); j++) {
                            if (gp.obj.get(j) instanceof OBJ_Door door && door.group == group) {
                                gp.obj.remove(j);
                                j--;
                            }
                        }
                        keys--;
                        gp.ui.showMessage("Você abriu a porta", 1);
                    } else {
                        gp.ui.showMessage("Você precisa de uma chave", 1);
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed += 2;
                    gp.obj.remove(i);
                    break;
                case "SpawnPoint":
                    gp.playSE(2);
                    gp.ui.showMessage("Spawn Point salvo!", 1);
                    gp.spawnPoint = (OBJ_SpawnPoint) gp.obj.get(i);
                    gp.obj.remove(i);
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }
    }

    public boolean interactNPC(int i) {
        if (i != 999) {
            if (gp.npc.get(i) instanceof BossFinal boss) {
                if (boss.onFinish) {
                    return true;
                }
                boss.quizAfterDialog = true;
            }
            gp.gameState = gp.dialogueState;
            gp.npc.get(i).speak();
            return true;
        }
        return false;

    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false) {
                this.damage(1);
                gp.playSE(6);
                invincible = true;
                gp.monster.get(i).attackDelayCounter = 60;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            Entity monster = gp.monster.get(i);
            if (monster.invincible == false) {
                gp.playSE(5);
                monster.damage(1);
                monster.invincible = true;
                if (monster.life <= 0) {
                    int group = monster.group;
                    gp.monster.remove(i);
                    for (Entity e : gp.monster) {
                        if (e.group == group) {

                            java.util.Random random = new java.util.Random();
                            int drop = random.nextInt(100) + 1;
                            if (drop <= 20) {
                                var heart = new OBJ_Heart(gp, 0, 0);
                                heart.worldX = monster.worldX + solidArea.x;
                                heart.worldY = monster.worldY + solidArea.y;
                                gp.obj.add(heart);
                            }

                            return;
                        }
                    }
                    var key = new OBJ_Key(gp, 0, 0);
                    key.worldX = monster.worldX + solidArea.x;
                    key.worldY = monster.worldY + solidArea.y;
                    gp.obj.add(key);

                }
            }
        }
    }

    public void damageBoss(int result) {
        if (result != 999 && gp.boss.invincible == false) {
            gp.playSE(5);
            gp.boss.damage(1);
        }
    }

    public void draw(Graphics2D g2) {

        sprite = resolveImage();

        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(sprite, (int) (screenX + (spriteX + spriteOffsetX) * spriteScale),
                (int) (screenY + (spriteY + spriteOffsetY) * spriteScale),
                (int) (sprite.getWidth() * spriteScale),
                (int) (sprite.getHeight() * spriteScale), null);

        // RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        super.draw(g2);
        // DEBUG
        // g2.setFont(new Font("Arial", Font.PLAIN, 26));
        // g2.setColor(Color.white);
        // g2.drawString("Invincible:"+invincibleCounter, 10, 400);
    }
}
