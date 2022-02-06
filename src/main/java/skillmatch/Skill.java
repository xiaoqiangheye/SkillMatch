package skillmatch;


import java.util.Objects;

public class Skill implements Comparable<Skill>{
    private final String name;
    private int level;
    public Skill(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public Skill(Skill s) {
        this.name = s.name;
        this.level = s.level;
    }

    public String getName() {
        return name;
    }


    @Override
    public int compareTo(Skill o) {
        if(!o.name.equals(name)) {
            return 0;
        } else {
            if(level > o.level) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    @Override
    public boolean equals(Object s) {
        if(s instanceof Skill){
            Skill skill = (Skill) s;
            return skill.name.equals(name);
        }
        return false;
    }

}
