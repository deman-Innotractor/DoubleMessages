package org.example;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.argument.StructuredArguments.v;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@Slf4j
@SpringBootApplication
public class Main implements ApplicationRunner {
    public static void main(String[] args) {


        SpringApplication app = new SpringApplication(Main.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.addListeners(new ApplicationPidFileWriter());
        try {
            app.run(args);
        }
        catch (Exception e){
            log.error("Fatal exception", e);
            System.exit(1);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int i = 0;
        while (true){
            log.info("Test value {}", kv("abc", i));
            if (i++ > 10000){
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}