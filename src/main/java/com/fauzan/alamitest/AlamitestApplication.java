package com.fauzan.alamitest;

import com.fauzan.alamitest.bean.Nasabah;
import com.fauzan.alamitest.utils.CustomLogger;
import com.fauzan.alamitest.utils.FileUtil;
import com.fauzan.alamitest.worker.WorkerAverageBalance;
import com.fauzan.alamitest.worker.WorkerBenefit;
import com.fauzan.alamitest.worker.WorkerCommission;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootApplication
public class AlamitestApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlamitestApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext context){
		return args -> {
			try {
				FileUtil fileUtil = new FileUtil("input/Before Eod.csv", "output/After Eod.csv");
				fileUtil.read();

				List<Future<?>> futures = new ArrayList<Future<?>>();

				ExecutorService poolAverage = Executors.newFixedThreadPool(5);
				ExecutorService poolBenefit = Executors.newFixedThreadPool(5);
				ExecutorService poolCommission = Executors.newFixedThreadPool(8);

				for (Nasabah nasabah : fileUtil.getNasabahList()){
					Future<?> futureAverage = poolAverage.submit(new WorkerAverageBalance(nasabah));
					Future<?> futureBenefit = poolBenefit.submit(new WorkerBenefit(nasabah));
					futures.add(futureAverage);
					futures.add(futureBenefit);

					if (nasabah.getId() >= 1 && nasabah.getId() <= 10){
						Future<?> futureCommission = poolCommission.submit(new WorkerCommission(nasabah, 10));
						futures.add(futureCommission);
					}
				}

				for(Future<?> future : futures)
					future.get();

				poolAverage.shutdown();
				poolBenefit.shutdown();
				poolCommission.shutdown();
				fileUtil.write();
			} catch (Exception ex){
				new CustomLogger(AlamitestApplication.class).error(ex);
			}
		};
	}

}
