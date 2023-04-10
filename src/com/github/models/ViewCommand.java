package com.github.models;

public class ViewCommand {

    private ViewCommandVariants viewCommandVariants;

    public ViewCommandVariants getVariant() {
        return this.viewCommandVariants;
    }

    public void setVariant(ViewCommandVariants variant) {
        this.viewCommandVariants = variant;
    }

    public enum ViewCommandVariants {
        NONE,
        CONTINUE,
        CHANGE_VIEW_GAME,
        CHANGE_VIEW_OPTIONS,
        CHANGE_VIEW_SCORES
    }
}
