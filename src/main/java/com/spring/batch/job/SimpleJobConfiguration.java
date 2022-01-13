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

@Slf4j	// log ����� ���� lombok ������̼�
@RequiredArgsConstructor //������ DI�� ���� lombok ������̼�
@Configuration
public class SimpleJobConfiguration {
	private final JobBuilderFactory jobBuilderFactory;	// ������ DI ����
	private final StepBuilderFactory stepBuilderFactory; // ������ DI ����
	
	@Bean
	public Job simpleJob() {
		return jobBuilderFactory.get("simpleJob")	//simpleJob�̶� �̸����� batch Job����
				.start(simpleStep1())
				.build();
	}
	
	@Bean
	public Step simpleStep1() {
		 return stepBuilderFactory.get("simpleStep1")
				 /***
             	 * Step�ȿ��� ����� ��ɵ��� ���
             	 * tasklet�� Step�ȿ��� ���Ϸ� ����� Ŀ������ ��ɵ��� ������ �� ���
             	 * ���⼭�� Batch�� ����Ǹ� �ϱ⿡ log�� ����ϵ��� �Ѵ�.
             	 */
	                .tasklet((contribution, chunkContext) -> {
	                    log.info(">>>>> This is Step1");
	                    return RepeatStatus.FINISHED;
	                })
	                .build();
	}

}
