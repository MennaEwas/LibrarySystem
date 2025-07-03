package com.code81.library_system.entities;

public enum UserRole {
    ADMINISTRATOR("admin"),
    LIBRARIAN("librarian"),
    STAFF("staff");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;

    }

    public String getDisplayName() {
        return displayName;
    }
}
