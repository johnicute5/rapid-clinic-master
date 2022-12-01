package com.example.service;


import com.example.model.Patient;
import com.example.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class UserService {
	

    public List<User> getAllUsers() throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("users").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();

        List<User> userList = list.stream().map((doc) -> doc.toObject(User.class)).collect(Collectors.toList());

        return userList;
    }
    
    public UserDetails getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // getUsername() - Returns the username/email used to authenticate the user.
        System.out.println("Username: " + userDetails.getUsername());

        // getAuthorities() - Returns the authorities granted to the user.
        System.out.println("User has authorities: " + userDetails.getAuthorities());
        return userDetails;
    }

	public User getUserbyId(String id) throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentReference docRef = firestore.collection("users").document(id);

		ApiFuture<DocumentSnapshot> apiFuture = docRef.get();
		DocumentSnapshot document = apiFuture.get();
		User user;

		if (document.exists()) {
			user = document.toObject(User.class);
			return user;
		}
		return null;
	}
    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("users")
                .whereGreaterThanOrEqualTo("email", email)
                .whereLessThanOrEqualTo("email", email+'z').get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();

        User user = list.stream().map((doc) -> doc.toObject(User.class)).findFirst().get();
        return user;
    }
    
    public User getUserByUsername(String username) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("users")
                .whereGreaterThanOrEqualTo("username", username)
                .whereLessThanOrEqualTo("username", username+'z').get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();

        User user = list.stream().map((doc) -> doc.toObject(User.class)).findFirst().get();
        return user;
    }
    
    
	public User getPatientByName(String firstName) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("patient")
                .whereGreaterThanOrEqualTo("firstName", firstName)
                .whereLessThanOrEqualTo("firstName", firstName+'z').get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        Patient patient = list.stream().map((doc) -> doc.toObject(Patient.class)).findFirst().get();
        return patient;
    }
    public User getUserByResetPasswordToken(String token) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("users")
                .whereGreaterThanOrEqualTo("passwordResetToken", token)
                .whereLessThanOrEqualTo("passwordResetToken", token+'z').get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();

        User user = list.stream().map((doc) -> doc.toObject(User.class)).findFirst().get();
        return user;
    }

    public String addUser(User user) throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("users").document();
        user.setId(docRef.getId());

        ApiFuture<WriteResult> apiFuture = docRef.set(user);
        return apiFuture.get().getUpdateTime().toString();
    }

    public String updateUser(User user) throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("users").document(user.getId());

        ApiFuture<WriteResult> apiFuture = docRef.set(user);
        
        return apiFuture.get().getUpdateTime().toString();
    }

    public void updateResetPasswordToken(String token, String email) throws ExecutionException, InterruptedException {
        User user = getUserByEmail(email);
        if (user != null) {
            user.setPasswordResetToken(token);
            updateUser(user);
        } else {
            System.out.println("Error encountered");
        }
    }

    public void updatePassword(User user, String newPassword) throws ExecutionException, InterruptedException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setPasswordResetToken(null);
        user.setFirstLogin(false);
        updateUser(user);
    }
    
    // Not yet done
	public String uploadImage(MultipartFile file) {
		
		return "Uploaded";
	}
	
	public String downloadImage(String fileName) {
		
		return "Downloaded";
	}
}
