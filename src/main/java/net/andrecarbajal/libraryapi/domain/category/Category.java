package net.andrecarbajal.libraryapi.domain.category;

public enum Category {
    FICTION("fiction"),
    HORROR("horror"),
    ROMANCE("romance"),
    MYSTERY("mystery"),
    FANTASY("fantasy"),
    BIOGRAPHY("biography"),
    HISTORY("history"),
    SELF_HELP("self-help"),
    EDUCATION("education"),
    COOKING("cooking"),
    ART("art"),
    RELIGION("religion"),
    PHILOSOPHY("philosophy"),
    PSYCHOLOGY("psychology");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}