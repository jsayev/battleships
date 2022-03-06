package com.guestline.pre_interview.battleships;

/**
 * @author Jovhar Isayev
 */
final class User extends Participant implements Player {
    public User(String name) {
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
