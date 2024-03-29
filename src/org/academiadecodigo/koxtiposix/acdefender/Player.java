package org.academiadecodigo.koxtiposix.acdefender;

import org.academiadecodigo.koxtiposix.acdefender.audio.Audio;
import org.academiadecodigo.koxtiposix.acdefender.weapons.Weapon;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ConcurrentModificationException;

public class Player {

    private final Picture playerChar;
    private final Weapon weapon;
    private int health;
    private final int x = 0;
    private String teletransportAudioFile = "/resources/audio/dbz-teleport.wav";


    public Player(CollisionDetector detector) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        playerChar = new Picture(180, Utils.PLAYER_Y_POS - 60, "resources/Main player.png");

        weapon = new Weapon(detector);
        health = 3;
    }

    public void draw() {
        playerChar.draw();
    }
    private int x1 = 0;
    private int x2 = 0;

    public void moveUp() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        x2 ++;
        if (x2 == 1 && x1 != 1) {
            playerChar.translate(0 , -Utils.JUMP_SIZE);

            x1 ++;
        }
        x2 = 0;
        System.out.println(x2);
        System.out.println("--" + x1);
        new Audio(teletransportAudioFile).play(true);
    }

    public void moveDown() {

        x2--;
        if (x2 == -1 && x1 != -1) {
            playerChar.translate(0, Utils.JUMP_SIZE);

            x1--;

        }
        x2 = 0;

        new Audio(teletransportAudioFile).play(true);
    }

    public void shoot() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        weapon.shoot(playerChar.getY());

    }


    public void moveBullet() {
        try {
            weapon.moveBullet();
        } catch (ConcurrentModificationException e) {
            e.getMessage();
        }

    }

    public void takeKey() {
        health--;
    }

    public int health() {
        return health;
    }

    public void eraseBullets() {
        weapon.eraseBullets();
    }

}
