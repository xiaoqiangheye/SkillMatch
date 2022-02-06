package skillmatch;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SparkServer {

    public static void main(String[] args) {
        //format: /sampleSkillMatch?skills=
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();

        Job.DEFAULT_SKILLS = new ArrayList<>();
        File text = new File("src/main/java/skillmatch/DefaultSkill.txt");
        try{
            Scanner s = new Scanner(text);
            while (s.hasNextLine()) {
                String next = s.nextLine();
                Random r = new Random();
                Job.DEFAULT_SKILLS.add(new Skill(next, r.nextInt(5) + 1));
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(Job.DEFAULT_SKILLS.size());

        File companies = new File("src/main/java/skillmatch/constituents.csv");
        Company.DEFAULT_COMPANY = new ArrayList<>();
        try {
            Scanner s2 = new Scanner(companies);
            while(s2.hasNextLine()) {
                String s = s2.nextLine();
                String[] ss = s.split(",");
                Company c = new Company(ss[1]);
                Company.DEFAULT_COMPANY.add(c);
            }
            s2.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        File jobfile = new File("src/main/java/skillmatch/techjob.txt");
        try {
            Scanner s3 = new Scanner(jobfile);
            Job.DEFAULT_JOBS = new ArrayList<>();
            while(s3.hasNextLine()) {
                Job.DEFAULT_JOBS.add(new Job(s3.nextLine(), null, null));
            }
            s3.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        List<Job> jobs = new ArrayList<>();
        for(int i = 0; i < 500; i++) {
            Job j = Job.randomizedJob();
            j.addSkillRequire(Job.randomizedSkill());
            j.addSkillRequire(Job.randomizedSkill());
            j.setCompany(Company.randonmizedCompany());
            jobs.add(j);
        }

        System.out.println(jobs.size());

        //get path with start and end and return the shortest path as json
        Spark.get("/skillmatch", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String s = request.queryParams("skills");
                String[] skills = s.split(",");
                List<Job> jobsMatched = new ArrayList<>();
                for(String skill: skills) {
                    for(Job j : jobs) {
                        if(j.satisfy(new Skill(skill, 1))) {
                            jobsMatched.add(j);
                        }
                    }
                }
                System.out.println(jobsMatched.size());

                Gson gson = new Gson();
                String json = gson.toJson(jobsMatched);
                return json;
            }
        });
    }
}
