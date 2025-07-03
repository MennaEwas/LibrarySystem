package com.code81.library_system.entities;

public enum BookCategory {
    FICTION("Fiction"),
    NON_FICTION("Non-Fiction"),
    SCIENCE("Science"),
    HISTORY("History"),
    BIOGRAPHY("Biography"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    FANTASY("Fantasy"),
    THRILLER("Thriller"),
    CHILDREN("Children"),
    TEXTBOOK("Textbook"),
    REFERENCE("Reference"),
    POETRY("Poetry"),
    DRAMA("Drama"),
    PHILOSOPHY("Philosophy");
    
    private final String displayName;
    
    BookCategory(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}