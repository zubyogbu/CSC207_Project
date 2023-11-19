package main.java.use_case.notes;

import main.java.entity.Notes;

import java.util.HashMap;

public interface NotesDataAccessInterface {

    public void addNotes(Notes notes, String courseId);
    public boolean noteExists(String courseId, String notesTitle);
    public void updateContent(String courseId, String notesTitle, String notesContent);
    public Notes getNotes(String courseId, String notesTitle);
}
