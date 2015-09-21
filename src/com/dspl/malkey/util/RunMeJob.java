package com.dspl.malkey.util;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.dspl.malkey.service.FreshedSRV;

public class RunMeJob extends QuartzJobBean {
	
	@Resource(name="freshedSRV")
	FreshedSRV freshedSRV;
	
	public void setRunMeTask(FreshedSRV freshedSRV) {
		this.freshedSRV = freshedSRV;
	}

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("RunMeJob.executed.");
		freshedSRV.runTask();
	}
}