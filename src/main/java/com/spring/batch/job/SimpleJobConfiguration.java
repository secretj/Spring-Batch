package com.spring.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j	// log 사용을 위한 lombok 어노테이션
@RequiredArgsConstructor //생성자 DI를 위한 lombok 어노테이션
@Configuration
public class SimpleJobConfiguration {
	private final JobBuilderFactory jobBuilderFactory;	// 생성자 DI 받음
	private final StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음
	
	@Bean
	public Job simpleJob() {
		return jobBuilderFactory.get("simpleJob")	//simpleJob이란 이름으로 batch Job생성
				.start(simpleStep1())
				.build();
	}
	
	@Bean
	public Step simpleStep1() {
		 return stepBuilderFactory.get("simpleStep1")
				 /***
             	 * Step안에서 수행될 기능들을 명시
             	 * tasklet은 Step안에서 단일로 수행될 커스텀한 기능들을 선언할 때 사용
             	 * 여기서는 Batch가 수행되면 하기에 log를 출력하도록 한다.
             	 */
	                .tasklet((contribution, chunkContext) -> {
	                    log.info(">>>>> This is Step1");
	                    return RepeatStatus.FINISHED;
	                })
	                .build();
	}

}
