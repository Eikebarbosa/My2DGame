/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import br.maua.t2.my2dgame.entity.BossFinal;
import br.maua.t2.my2dgame.entity.Entity;
import br.maua.t2.my2dgame.entity.Player;
import br.maua.t2.my2dgame.login.modelo.Usuario;
import br.maua.t2.my2dgame.object.OBJ_SpawnPoint;
import br.maua.t2.my2dgame.object.SuperObject;
import br.maua.t2.my2dgame.tile.TileManager;

/**
 *
 * @author keyex
 */
public class GamePanel extends JPanel implements Runnable {
    // Configurações de tela
    final int originalTileSize = 24; // 16x16 tile
    // 16 pixels, da para aumentar
    // em certas telas, 16 é bem pouco
    final int scale = 3; // Adjust the scale to maintain the correct aspect ratio

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16; // horizontal
    public final int maxScreenRow = 12; // vertical
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    public Usuario user;
    public int recorde;
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    Thread gameThread;// Mantém o jogo rodando até forçar a parada
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public QuizScreen quizScreen = new QuizScreen(this);

    // entity and object
    public Player player;
    public BossFinal boss;
    public List<SuperObject> obj = new ArrayList<>();
    public List<Entity> npc = new ArrayList<>();
    public List<Entity> monster = new ArrayList<>();
    public OBJ_SpawnPoint spawnPoint;

    // game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int gameOverState = 4;
    public final int quizState = 5;
    public final int victoryState = 6;
    public final int answersState = 7;
    public final int cutSceneState = 8;

    public GamePanel(Usuario user) {
        this.user = user;
        this.recorde = user.getRecorde();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        setDefaultSpawnPoint();
        // playMusic(0);
    }

    public void setDefaultSpawnPoint() {
        this.spawnPoint = new OBJ_SpawnPoint(this, 45, 47);
    }

    public void setupGame() {
        obj.clear();
        npc.clear();
        monster.clear();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        gameState = playState;
        this.player = new Player(this, keyH, spawnPoint.worldX / tileSize, spawnPoint.worldY / tileSize);
    }

    public void retry() {
        player.setDefaultPositions();
        player.restoreLife();
        setupGame();
        ui.resetMessage();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        // criar um gameLoop
        while (gameThread != null) {
            // System.out.println("The game loop is running!");

            // 1. Atualizar as informações como uma posição de personagem
            update();

            // 2. Desenhar a tela com os updates de informção
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void update() {

        if (gameState == playState || gameState == cutSceneState) {
            // player
            player.update();
            // npc
            for (int i = 0; i < npc.size(); i++) {
                if (npc.get(i) != null) {
                    npc.get(i).update();
                }
            }
            for (int i = 0; i < monster.size(); i++) {
                if (monster.get(i) != null) {
                    monster.get(i).update();
                }
            }
        }
        if (gameState == pauseState) {
            // nada
        } else if (gameState == quizState) {
            // Handle quiz updates if needed
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // debug
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        // TILE
        tileM.draw(g2);

        // OBJECT
        for (int i = 0; i < obj.size(); i++) {
            if (obj.get(i) != null) {
                obj.get(i).draw(g2, this);
            }

        }
        // NPC
        for (int i = 0; i < npc.size(); i++) {
            if (npc.get(i) != null) {
                npc.get(i).draw(g2);
            }
        }
        // MONSTER
        for (int i = 0; i < monster.size(); i++) {
            if (monster.get(i) != null) {
                monster.get(i).draw(g2);
            }
        }

        // Player
        player.draw(g2);

        // UI
        ui.draw(g2);

        // quiz
        quizScreen.draw(g2);

        // debug
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
            g2.dispose();
        }
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }

}
