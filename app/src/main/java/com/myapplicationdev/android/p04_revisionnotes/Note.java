package com.myapplicationdev.android.p04_revisionnotes;

public class Note {
    private int id;
    private String Note;
    private int star;

    public Note(int id, String note, int star) {
        this.id = id;
        Note = note;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return Note;
    }

    public int getStar() {
        return star;
    }
}
