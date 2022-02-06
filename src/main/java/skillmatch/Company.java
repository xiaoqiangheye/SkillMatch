package skillmatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class Company {
    private String name;

    public static List<Company> DEFAULT_COMPANY;

    public Company(String name){
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if(o instanceof Company){
            Company c = (Company) o;
            return c.name.equals(name);
        }
        return false;
    }

    public static Company randonmizedCompany() {
        Random r = new Random();
        return DEFAULT_COMPANY.get(r.nextInt(DEFAULT_COMPANY.size()));
    }

}
