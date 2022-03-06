package com.guestline.pre_interview.battleships;

/**
 * @author Jovhar Isayev
 */
final class Computer extends Participant implements Player {
    public Computer(String name) {
        super(name);
    }

    @Override
    public void play() {
        System.out.println(this + " is playing");
    }

    @Override
    public String toString() {
        return name;
    }
}
