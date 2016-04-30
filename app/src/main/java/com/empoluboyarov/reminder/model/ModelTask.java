package com.empoluboyarov.reminder.model;

/**
 * Created by Evgeniy on 30.04.2016.
 */
public class ModelTask implements Item {

    private String title;
    private long date;

    public ModelTask() {
    }

    public ModelTask(String title, long date) {
        this.date = date;
        this.title = title;
    }

    @Override
    public boolean isTask() {
        return true;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
