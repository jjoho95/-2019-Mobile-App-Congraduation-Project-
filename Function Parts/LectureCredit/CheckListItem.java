package com.example.congraduation;

public class CheckListItem {
    private String text;
    private Boolean checked;

    public Boolean getChecked() {
        return checked;
    }

    public String getText() {
        return text;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public void setText(String text) {
        this.text = text;
    }
}
