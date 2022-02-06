package skillmatch;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private List<Skill> skills;

    public Person(String name) {
        this.name = name;
        skills = new ArrayList<>();
    }

    public void addSkill(Skill s) {
        skills.add(s);
    }
}
