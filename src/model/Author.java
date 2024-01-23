package model;

public class Author {
    String name;
    String firstSurname;
    String bio;

    public Author(String name, String firstSurname, String bio) {
        this.name = name;
        this.firstSurname = firstSurname;
        this.bio = bio;
    }

    public Author(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
