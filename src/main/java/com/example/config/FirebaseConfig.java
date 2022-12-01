package com.example.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseConfig {
	
	@SuppressWarnings("deprecation")
	@PostConstruct
	public void firebaseConnection() throws IOException {
		
		File configFile = ResourceUtils.getFile("classpath:config/firebaseConfig.json");
		
		FileInputStream serviceAccount =
				new FileInputStream(configFile);
		
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();
		
		FirebaseApp.initializeApp(options);
		
	}

}
