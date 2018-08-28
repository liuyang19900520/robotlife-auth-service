package com.liuyang19900520.robotlife.auth;

import com.liuyang19900520.robotlife.auth.annotation.ExcludeFromComponentScan;
import com.liuyang19900520.robotlife.auth.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrixDashboard
@RibbonClient(name = "robotlife-user", configuration = RibbonConfig.class)
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class)})
public class RobotlifeAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobotlifeAuthServiceApplication.class, args);
	}



}
