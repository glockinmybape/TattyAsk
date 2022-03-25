package org.glockinmybape.tattyask.types;

import org.bukkit.entity.Player;

public class Question {
    private final Player a;
    private final String q;
    private boolean answered = false;
    private final int id;
    private String answer = null;

    public Question(Player asker, String question, int i) {
        this.a = asker;
        this.q = question;
        this.id = i;
    }

    public String getQuestion() {
        return this.q;
    }

    public boolean isAnswered() {
        return this.answered;
    }

    public String getAnswer() {
        return this.answer;
    }

    public Player getAsker() {
        return this.a;
    }

    public void setAnswered() {
        this.answered = true;
    }

    public int getId() {
        return this.id;
    }

    public void setAnswer(String s) {
        this.answer = s;
    }
}
