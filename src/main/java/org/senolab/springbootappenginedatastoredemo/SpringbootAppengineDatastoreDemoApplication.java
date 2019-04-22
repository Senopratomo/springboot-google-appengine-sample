package org.senolab.springbootappenginedatastoredemo;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootAppengineDatastoreDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAppengineDatastoreDemoApplication.class, args);
	}

	@Bean
	public Datastore cloudDatastoreService() {
		return DatastoreOptions.getDefaultInstance().getService();
	}

	/**
	 * When running your app locally for test (by running 'mvn appengine:run':
	 * 1) comment out the above cloudDatastoreService() method
	 * 2) Uncomment this cloudDatastoreService() method
	 * 3) replace 'your-project' with your Google App Engine project id
	@Bean
	public Datastore cloudDatastoreService() {
		return DatastoreOptions.newBuilder().setProjectId("your-project").build().getService();
	}**/


}
