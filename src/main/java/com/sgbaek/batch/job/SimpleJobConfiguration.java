package com.sgbaek.batch.job;

import com.sgbaek.batch.entity.User;
import com.sgbaek.batch.repository.MyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SimpleJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    @Autowired
    MyJpaRepository myJpaRepository;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("simplejob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>>>>SimpleJob1");
                    User user = new User();
                    user.setName("sgbaek");
                    user.setAge(36);
                    user.setEmail("sgsgbaek@gmail.com");
                    myJpaRepository.save(user);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
