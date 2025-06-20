import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SpaceGame extends JFrame implements KeyListener {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 750;
    private int score = 0;

    private final JPanel gamePanel;
    private final JLabel scoreLabel;
    private final Timer timer;
    private boolean isGameOver;
    private final java.util.List<Obstacle> obstacles;
    private final java.util.List<Star> stars;
    private final java.util.List<Projectile> projectiles;

    private boolean playerLeft;
    private boolean playerRight;
    private boolean playerUp;
    private boolean playerDown;
    private boolean playerShielded;
    private boolean playerFiring;

    private final Image shipImage;
    private final Image healthbar;
    private final Image shieldbar;

    public Player player;

    public java.util.List<ThrusterParticles> thrusterParticles;
    public java.util.List<Powerup> powerUps;

    public static final Random random = new Random();

    public int powerUpTimer;

    private final BufferedImage healthSheet;
    private final BufferedImage shieldSheet;
    private final BufferedImage laserSheet;
    private final BufferedImage obstacleSheet;
    private final BufferedImage obstacleHitSheet;

    private final Map<String, Clip> sounds = new HashMap<>();

    int fireRate = 10;
    int fireTimer = 0;

    int scoreTimer = 0;

    int challengeDistance = 500;
    boolean inChallenge = false;

    Clip clip;

    int tutorialTimer = 500;

//    private String[] audioNames = {"background",
//            "destroy_asteroid1",
//            "destroy_asteroid2",
//            "destroy_asteroid3",
//            "health_up",
//            "destroy_asteroid3",
//            "destroy_asteroid2",
//            "destroy_asteroid3"};

    public SpaceGame() {
        setTitle("Space Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        stars = new ArrayList<>();
        generateStars(500);

        thrusterParticles = new ArrayList<>();
        powerUps = new ArrayList<>();
        projectiles = new ArrayList<>();

        player = new Player(new Vector2((double) WIDTH / 2, HEIGHT - 50), new Vector2(16 * 3, 16 * 3), new Vector2(0, -25));
        player.health = 100;
        player.shield = 100;
        player.laserDamage = 15;

        try {
            shipImage = ImageIO.read(new File("ship.png"));
            healthbar = ImageIO.read(new File("healthbar.png"));
            shieldbar = ImageIO.read(new File("shieldbar.png"));
            healthSheet = ImageIO.read(new File("health.png"));
            shieldSheet = ImageIO.read(new File("shield.png"));
            laserSheet = ImageIO.read(new File("laser.png"));
            obstacleSheet = ImageIO.read(new File("obstacle_spritesheet.png"));
            obstacleHitSheet = ImageIO.read(new File("obstacle_spritesheet_hit.png"));

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("shoot_laser.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setFramePosition(0);
            clip.start();

//            Put sounds into a map for easy use
//            audioInputStream = AudioSystem.getAudioInputStream(new File("background.wav"));
//            Clip audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("background", audioClip);
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File("destroy_asteroid1.wav"));
//            audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("destroy_asteroid1", audioClip);
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File("destroy_asteroid2.wav").getAbsoluteFile());
//            audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("destroy_asteroid2", audioClip);
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File("destroy_asteroid3.wav").getAbsoluteFile());
//            audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("destroy_asteroid3", audioClip);
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File("health_up.wav").getAbsoluteFile());
//            audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("health_up", audioClip);
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File("hit_asteroid.wav").getAbsoluteFile());
//            audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("hit_asteroid", audioClip);
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File("hurt.wav").getAbsoluteFile());
//            audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("hurt", audioClip);
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File("hurt_shielded.wav").getAbsoluteFile());
//            audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("hurt_shielded", audioClip);
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File("laser_up.wav").getAbsoluteFile());
//            audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("laser_up", audioClip);
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File("player_explode.wav").getAbsoluteFile());
//            audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("player_explode", audioClip);
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File("player_lose.wav").getAbsoluteFile());
//            audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("player_lose", audioClip);
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File("shield_up.wav").getAbsoluteFile());
//            audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("shield_up", audioClip);
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File("shoot_laser.wav").getAbsoluteFile());
//            audioClip = AudioSystem.getClip();
//            audioClip.open(audioInputStream);
//            sounds.put("shoot_laser", audioClip);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(10, 10, 100, 20);
        gamePanel.add(scoreLabel);

        add(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(this);

        isGameOver = false;
        obstacles = new java.util.ArrayList<>();

        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isGameOver) {
                    update();
                    gamePanel.repaint();
                }
            }
        });
        timer.start();
        powerUpTimer = random.nextInt(forSeconds(1)) + forSeconds(1);
    }

    private void generateStars(int numStars) {
        for (int i = 0; i < numStars; i++) {
            int size = random.nextInt(3) + 1;
            stars.add(new Star(new Vector2(size, size), new Vector2(random.nextInt(WIDTH), random.nextInt(HEIGHT + 50) - 50), new Vector2(0, random.nextDouble(0.05) + 0.02), generateRandomColor(), false));
        }
    }

    private void generateStars(int numStars, int yOffset) {
        for (int i = 0; i < numStars; i++) {
            int size = random.nextInt(3) + 1;
            stars.add(new Star(new Vector2(size, size), new Vector2(random.nextInt(WIDTH), yOffset), new Vector2(0, random.nextDouble(0.05) + 0.02), generateRandomColor(), false));
        }
    }

    private void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for (Star star : stars) {
            g.setColor(star.getColor());
            g.fillRect((int) star.getPos().x, (int) star.getPos().y, (int) star.getSize().x, (int) star.getSize().y);
        }

        for (ThrusterParticles t : thrusterParticles) {
            g.setColor(t.color);
            g.fillRect((int) t.getPos().x, (int) t.getPos().y, (int) t.getSize().x, (int) t.getSize().y);
        }

        for (Powerup p : powerUps) {
            p.spriteIndex++;
            if (p.spriteIndex > 17) {
                p.spriteIndex = 0;
            }
            if (p.type == PowerUps.HEALTH) {
                if (healthSheet != null) {
                    g.drawImage(healthSheet.getSubimage(p.spriteIndex * 16, 0, 16, 16), (int) p.position.x, (int) p.position.y, 32, 32, null);
                }
            } else if (p.type == PowerUps.SHIELD) {
                if (shieldSheet != null) {
                    g.drawImage(shieldSheet.getSubimage(p.spriteIndex * 16, 0, 16, 16), (int) p.position.x, (int) p.position.y, 32, 32, null);
                }
            } else if (p.type == PowerUps.LASER) {
                if (shieldSheet != null) {
                    g.drawImage(laserSheet.getSubimage(p.spriteIndex * 16, 0, 16, 16), (int) p.position.x, (int) p.position.y, 32, 32, null);
                }
            }
        }

        g.drawImage(shipImage, (int) player.position.x, (int) player.position.y, (int) player.size.x, (int) player.size.y, null);

        for (Projectile p : projectiles) {
            g.setColor(Color.GREEN);
            g.fillRect((int) p.getPos().x, (int) p.getPos().y, (int) p.getSize().x, (int) p.getSize().y);
        }

        for (Obstacle o : obstacles) {
            if (o.hit) {
                g.drawImage(obstacleHitSheet.getSubimage(o.spritesheetIndex * 16, 0, 16, 16), (int) o.position.x, (int) o.position.y, (int) o.size.x, (int) o.size.y, null);
            } else {
                g.drawImage(obstacleSheet.getSubimage(o.spritesheetIndex * 16, 0, 16, 16), (int) o.position.x, (int) o.position.y, (int) o.size.x, (int) o.size.y, null);
            }

        }

        if (playerShielded) {
            g.setColor(new Color(0, 185, 255, 175));
            g.fillRect((int) (player.position.x) - 10, (int) (player.position.y) - 10, (int) player.size.x + 20, (int) player.size.y + 20);
        }

        if (isGameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Game Over!", WIDTH / 2 - 80, HEIGHT / 2);
            g.drawString("(R to restart)", WIDTH / 2 - 81, HEIGHT / 2 + 25);
        }

        if (tutorialTimer > 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Arrow keys to move", WIDTH / 2 - 80, HEIGHT / 2 - 25);
            g.drawString("Z to fire", WIDTH / 2 - 80, HEIGHT / 2);
            g.drawString("X to shield", WIDTH / 2 - 80, HEIGHT / 2 + 25);
        }

        g.drawImage(healthbar, 10, HEIGHT - 70, 110, 30, null);
        g.drawImage(shieldbar, 10, HEIGHT - 110, 110, 30, null);

        g.setColor(Color.RED);
        g.fillRect(15, HEIGHT - 70 + 5, player.health, 20);

        g.setColor(new Color(0, 185, 255));
        g.fillRect(15, HEIGHT - 110 + 5, player.shield, 20);

        g.setColor(Color.CYAN);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 50, 50);

        if (!inChallenge) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("⚠ Major asteroid field in " + challengeDistance + " meters", WIDTH - 450, 50);
        }
    }

    private void update() {
        if (!isGameOver) {

            if (scoreTimer == 0) {
                score++;
                scoreTimer = 5;
            }
            scoreTimer--;

            challengeDistance--;
            if (challengeDistance <= 0) {
                inChallenge = true;
            }

            if (player.shield == 0) {
                playerShielded = false;
            }

            if (tutorialTimer > 0) {
                tutorialTimer--;
            }

            powerUpTimer--;
            if (powerUpTimer == 0) {
                int powerUpIndex = random.nextInt(PowerUps.values().length);
                Powerup powerup = new Powerup(PowerUps.values()[powerUpIndex], 0);
                powerup.velocity = new Vector2(0, 5);
                powerup.position = new Vector2(random.nextInt(WIDTH), 10);
                powerup.setSize(new Vector2(3));
                powerUps.add(powerup);
            }

            for (int i = 0; i < powerUps.size(); i++) {
                Powerup u = powerUps.get(i);
                u.setPos(new Vector2(u.getPos().x + u.getVelocity().x, u.getPos().y + u.getVelocity().y));
                if (u.getPos().y > HEIGHT) {
                    powerUpTimer = random.nextInt(forSeconds(1)) + forSeconds(1);
                    powerUps.remove(u);
                }
            }

            for (int i = 0; i < stars.size(); i++) {
                Star s = stars.get(i);
                s.setPos(new Vector2(s.getPos().x + s.getVelocity().x, s.getPos().y + s.getVelocity().y));
                if (inChallenge && !s.sped) {
//                    s.size.y += 1;
                    s.size.y *= 2;
//                    s.size.x /= 1.5;
//                    s.velocity.y += 1;
                    s.velocity.y *= 3;
                    s.sped = true;
                }
                if (s.getPos().y > HEIGHT - s.size.y) {
                    stars.remove(s);
                    generateStars(1, -50);
                    i--;
                }
            }

//            if (Math.random() > 0.1) generateStars(1);

            // Generate new obstacles
            boolean spawn = false;
            double chance = Math.random();
            if (inChallenge) {
                if (chance < 0.2) spawn = true;
            } else {
                if (chance < 0.1) spawn = true;
            }
            if (spawn) {
                int values;
                if (inChallenge) {
                    values = random.nextInt(100) + 20;
                } else {
                    values = random.nextInt(40) + 15;
                }
                Obstacle obstacle = new Obstacle((int) (values * 0.75));
                obstacle.size = new Vector2(values + 10);
//                obstacle.velocity = new Vector2(random.nextDouble(1) - 0.5, 5 + ((double) 135 / values));
                if (inChallenge) {
                    obstacle.velocity = new Vector2(random.nextDouble(1) - 0.5, 5 + ((double) 120 / values));
                } else {
                    obstacle.velocity = new Vector2(random.nextDouble(1) - 0.5, 1 + ((double) 55 / values));
                }
                obstacle.position = new Vector2(random.nextInt(WIDTH),  -60);
                obstacle.spritesheetIndex = random.nextInt(4);
                obstacles.add(obstacle);
            }

            for (int i = 0; i < obstacles.size(); i++) {
                Obstacle o = obstacles.get(i);
                if (Math.random() < 0.05) {
                    o.spritesheetIndex = random.nextInt(4);
                }
                o.hit = false;
                o.position.x += o.velocity.x;
                o.position.y += o.velocity.y;
                if (o.position.y > HEIGHT - o.size.y) {
                    obstacles.remove(o);
                    i--;
                }
            }

            if (playerLeft) player.velocity.x -= player.ACCELERATION;
            if (playerRight) player.velocity.x += player.ACCELERATION;
            if (playerUp) player.velocity.y -= player.ACCELERATION;
            if (playerDown) player.velocity.y += player.ACCELERATION;
            player.velocity.x *= 0.9;
            player.velocity.y *= 0.9;
            player.position = new Vector2(player.position.x + player.velocity.x, player.position.y + player.velocity.y);
            if (player.position.x < 0) {
                player.position.x = 0;
                player.velocity.x = 0;
            }
            if (player.position.x > WIDTH - player.size.x) {
                player.position.x = WIDTH - player.size.x;
                player.velocity.x = 0;
            }
            if (player.position.y > HEIGHT - player.size.y * 1.5) {
                player.position.y = HEIGHT - player.size.y * 1.5;
                player.velocity.y = 0;
            }
            if (player.position.y < (double) HEIGHT / 2 - player.size.y) {
                player.position.y = (double) HEIGHT / 2 - player.size.y;
                player.velocity.y = 0;
            }

            for (int i = 0; i < thrusterParticles.size(); i++) {
                ThrusterParticles t = thrusterParticles.get(i);
                thrusterParticles.remove(t);
                i--;
            }

            ThrusterParticles particle = new ThrusterParticles();
            particle.size = new Vector2(random.nextInt(3) + 3);
            particle.position = new Vector2(player.position.x + player.size.x / 2 - particle.size.x / 2, player.position.y + player.size.y - 30);
            particle.size.y = particle.size.y + random.nextInt(20) + 90;
            particle.color = generateThrusterColor();
            particle.velocity = new Vector2(random.nextDouble(2) - 1, random.nextDouble(5) + 15);
            thrusterParticles.add(particle);

            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = projectiles.get(i);
                p.position.x += p.velocity.x;
                p.position.y += p.velocity.y;
                if (p.position.y < 0 - p.size.y / 2) {
                    projectiles.remove(p);
                    i--;
                }
            }

            // Check collision between player and obstacle
            Rectangle playerRect = new Rectangle((int) player.position.x + 5, (int) player.position.y + 5, (int) player.size.x - 10, (int) player.size.y - 10);
            for (Obstacle o : obstacles) {
                Rectangle obstacleRect = new Rectangle((int) o.position.x + 5, (int) o.position.y + 5, (int) o.size.x - 10, (int) o.size.y - 10);
                if (playerRect.intersects(obstacleRect)) {
                    if (playerShielded) {
                        if (player.position.y < o.position.y) {
                            player.changeShield(-o.health / 6);
                        } else {
                            player.changeShield(-o.health / 3);
                        }
                        playSound("hurt_shielded");
                    } else {
                        if (player.position.y < o.position.y) {
                            player.changeHealth(-o.health / 2);
                        } else {
                            player.changeHealth(-o.health);
                        }
                        playSound("hurt");
                        if (player.health <= 0) {
                            isGameOver = true;
                            playSound("player_explode");
                            playSound("player_lose");
                        }
                    }
                    obstacles.remove(o);
                    break;
                }
            }

            // Check collision between player and powerup
            playerRect = new Rectangle((int) player.position.x - 5, (int) player.position.y - 5, (int) player.size.x + 10, (int) player.size.y + 10);
            for (Powerup p : powerUps) {
                Rectangle powerUpRect = new Rectangle((int) p.position.x - 5, (int) p.position.y - 5, (int) p.size.x + 10, (int) p.size.y + 10);
                if (playerRect.intersects(powerUpRect)) {
                    if (p.type == PowerUps.HEALTH) {
                        player.changeHealth(25);
                        playSound("health_up");
                    } else if (p.type == PowerUps.SHIELD) {
                        player.changeShield(25);
                        playSound("shield_up");
                    } else if (p.type == PowerUps.LASER) {
                        player.changeLaser(10);
                        fireRate *= 0.95;
                        playSound("laser_up");
                    }
                    powerUpTimer = random.nextInt(forSeconds(10)) + forSeconds(10);
                    powerUps.remove(p);
                    break;
                }
            }

            // Check collision between projectile and obstacle
            for (int i = 0; i < obstacles.size(); i++) {
                Obstacle o = obstacles.get(i);
                Rectangle obstacleRect = new Rectangle((int) o.position.x - 5, (int) o.position.y - 5, (int) o.size.x + 10, (int) o.size.y + 10);
                for (int j = 0; j < projectiles.size(); j++) {
                    Projectile p = projectiles.get(j);
                    Rectangle projectileRect = new Rectangle((int) (p.position.x - p.size.x / 2), (int) (p.position.y - p.size.y / 2), (int) p.size.x, (int) p.size.y);
                    if (projectileRect.intersects(obstacleRect)) {
                        o.changeHealth(-p.damage);
                        o.hit = true;
                        projectiles.remove(p);
                        score += 10;
                        playSound("hit_asteroid");
                        if (o.health <= 0) {
                            obstacles.remove(o);
                            score += 100;
                            int soundIndex = random.nextInt(3) + 1;
                            switch (soundIndex) {
                                case 1 -> playSound("destroy_asteroid1");
                                case 2 -> playSound("destroy_asteroid2");
                                case 3 -> playSound("destroy_asteroid3");
                            }
                        }
                        break;
                    }
                }
            }


            if (fireTimer > 0) {
                fireTimer--;
            }

            if (playerFiring && fireTimer == 0 && !playerShielded) {
                playSound("background");
                Projectile projectile = new Projectile(player.laserDamage);
                projectile.velocity = new Vector2(random.nextDouble(0.1) - 0.05, -30);
                projectile.size = new Vector2(player.laserDamage * 0.25, player.laserDamage * 3);
                projectile.position = new Vector2(player.position.x + player.size.x / 2 - projectile.size.x / 2, player.position.y - player.laserDamage * 1.5);
                fireTimer = fireRate;
                projectiles.add(projectile);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT && player.position.x > 0) {
            playerLeft = true;
        } else if (keyCode == KeyEvent.VK_RIGHT && player.position.x < WIDTH - player.size.x) {
            playerRight = true;
        } else if (keyCode == KeyEvent.VK_UP && player.position.y > (double) HEIGHT / 2) {
            playerUp = true;
        } else if (keyCode == KeyEvent.VK_DOWN && player.position.y < HEIGHT - player.size.y) {
            playerDown = true;
        } else if (keyCode == KeyEvent.VK_X && player.shield > 0) {
            playerShielded = true;
        } else if (keyCode == KeyEvent.VK_Z && !playerShielded) {
            playerFiring = true;
        } else if (keyCode == KeyEvent.VK_R && isGameOver) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new SpaceGame().setVisible(true);
                }
            });
        }
    }

    public static Color generateRandomColor() {
        int r = random.nextInt(156) + 100; // Red component (0-255)
        int g = random.nextInt(20) + 190;
//        int g = random.nextInt(106) + 150;
//        int g = 200;
        int b = random.nextInt(156) + 100; // Red component (0-255)
        if (r > b) {
            r = 255;
        } else {
            b = 255;
        }
        return new Color(r, g, b);
    }

    public static Color generateThrusterColor() {
//        int b = random.nextInt(16) + 240;
        return new Color(random.nextInt(50) + 50, random.nextInt(50) + 150, 255);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT -> playerLeft = false;
            case KeyEvent.VK_RIGHT -> playerRight = false;
            case KeyEvent.VK_UP -> playerUp = false;
            case KeyEvent.VK_DOWN -> playerDown = false;
            case KeyEvent.VK_X -> playerShielded = false;
            case KeyEvent.VK_Z -> playerFiring = false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SpaceGame().setVisible(true);
            }
        });
    }

    public int forSeconds(int input) {
        return input * timer.getDelay();
    }

    public void playSound(String clipName) {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }

//        Pull sound clip from map by name
//        if (sounds.get(clipName) != null) {
//            Clip sound = sounds.get(clipName);
//            sound.setFramePosition(0);
//            sound.start();
//        }
    }
}