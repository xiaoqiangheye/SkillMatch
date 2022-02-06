package skillmatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Job {
    private String name;
    private Company p;
    private List<Skill> requires;


    public static List<Skill> DEFAULT_SKILLS;
    public static List<Job> DEFAULT_JOBS;


    public Job(String name, List<Skill> skills, Company c) {
        this.name = name;
        if(skills != null) {
            this.requires = new ArrayList<>(skills);
        } else {
            this.requires = new ArrayList<>();
        }
        this.p = c;
    }

    public Job(Job j) {
        this.name = j.name;
        this.p = j.p;
        this.requires = new ArrayList<>(j.requires);
    }

    public void setCompany(Company p) {
        this.p = p;
    }

    public void addSkillRequire(Skill s) {
        requires.add(s);
    }

    public boolean satisfy(Skill s) {
        if(requires.contains(s)){
            return true;
        }

        for(Skill skill: requires) {
            if(skill.getName().contains(s.getName()) || s.getName().contains(skill.getName())) {
                return true;
            }
        }

        return false;
    }

    public static Skill randomizedSkill() {
        Random r = new Random();
        return new Skill(Job.DEFAULT_SKILLS.get(r.nextInt(DEFAULT_SKILLS.size())));
    }

    public static Job randomizedJob() {
        Random r = new Random();
        return new Job(Job.DEFAULT_JOBS.get(r.nextInt(DEFAULT_JOBS.size())));
    }
}
