package com.example.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.model.Appointment;
import com.example.model.Patient;
import com.example.model.Visitation;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class VisitationService {
	
	// Create
	public String addVisitation(Visitation visit) throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentReference docRef = firestore.collection("visit").document();
		visit.setMedicalCertificateID(docRef.getId());

		ApiFuture<WriteResult> apiFuture = docRef.set(visit);
		return apiFuture.get().getUpdateTime().toString();
	}
	
	
	// Get All
	public List<Visitation> getAllVisits() throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();

		ApiFuture<QuerySnapshot> apiFuture = firestore.collection("visit").get();
		List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();

		List<Visitation> visitList = list.stream().map((doc) -> doc.toObject(Visitation.class)).collect(Collectors.toList());

		return visitList;
	}
	
	
	// Get by ID
	public Visitation getVisitbyId(String medicalCertificateID) throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentReference docRef = firestore.collection("visit").document(medicalCertificateID);

		ApiFuture<DocumentSnapshot> apiFuture = docRef.get();
		DocumentSnapshot document = apiFuture.get();
		Visitation visit;

		if (document.exists()) {
			visit = document.toObject(Visitation.class);
			return visit;
		}
		return null;
	}
	
	
	
	//Edit
	public String editVisit(Visitation visit) throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentReference docRef = firestore.collection("visit").document(visit.getMedicalCertificateID());
		
		ApiFuture<WriteResult> apiFuture = docRef.set(visit);
	
		return apiFuture.get().getUpdateTime().toString();
	}
	
	  public Visitation getAppointmentById(String id) throws ExecutionException, InterruptedException {
		  Firestore firestore = FirestoreClient.getFirestore();
			DocumentReference docRef = firestore.collection("appointments").document(id);

			ApiFuture<DocumentSnapshot> apiFuture = docRef.get();
			DocumentSnapshot document = apiFuture.get();
			Visitation visit;

			if (document.exists()) {
				visit = document.toObject(Visitation.class);
				return visit;
			}
			return null;
	    }

}
