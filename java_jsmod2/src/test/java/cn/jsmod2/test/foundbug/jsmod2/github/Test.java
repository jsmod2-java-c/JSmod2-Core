package cn.jsmod2.test.foundbug.jsmod2.github;

import org.kohsuke.github.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Test {

    @org.junit.Test
    public void connect() throws Exception {
        String path = System.getProperty("user.home");
        File file = new File(path,".github");
        Properties properties = new Properties();
        properties.setProperty("oauth","");
        properties.setProperty("login","jsmod2");
        properties.setProperty("password","85027859yhn*");
        properties.setProperty("endpoint","https://github.com/");
        FileOutputStream stream = new FileOutputStream(file);
        properties.store(stream,"");
        stream.flush();
        GitHub gitHub = GitHub.connectUsingPassword("jsmod2","85027859yhn*");
        GHOrganization organization = gitHub.getOrganization("jsmod2-java-c");
        GHRepository repository = organization.getRepositories().get("jsmod2");
        List<GHCommit> commits =repository.listCommits().asList();
        Collections.sort(commits,(c1,c2)->{
            try{
                return (int)(c2.getCommitDate().getTime()-c1.getCommitDate().getTime());
            }catch (IOException e){
                e.printStackTrace();
            }
            return 0;
        });
        //https://github.com/jsmod2-java-c/jsmod2/graphs/contributors
        System.out.println(commits.get(0).getSHA1());

        repository.listContributors().forEach(x->{
            System.out.println(x.getLogin());
        });


    }
}
