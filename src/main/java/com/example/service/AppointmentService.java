package com.example.service;

import com.example.model.Appointment;
import com.example.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class AppointmentService {


    public String createAppointment(Appointment appointment) throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentReference docRef = firestore.collection("appointments").document();
		appointment.setAppointmentID(docRef.getId());

		ApiFuture<WriteResult> apiFuture = docRef.set(appointment);
		return apiFuture.get().getUpdateTime().toString();
	}

    public List<Appointment> getAppointments() throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("appointments").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        List<Appointment> appointmentList = list.stream().map((doc) -> doc.toObject(Appointment.class)).collect(Collectors.toList());
        return appointmentList;
    }

    public Appointment getAppointmentByName(String name) throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("appointments")
                .whereGreaterThanOrEqualTo("name", name)
                .whereLessThanOrEqualTo("name", name+'z').get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();

        List<Appointment> appointmentList = list.stream().map((doc) -> doc.toObject(Appointment.class)).collect(Collectors.toList());

        return (Appointment) appointmentList;
    }
    public List<Appointment> getAppointmentByDate(String date) throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("appointments")
                .whereGreaterThanOrEqualTo("date", date)
                .whereLessThanOrEqualTo("date", date+'z').get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();

        List<Appointment> appointmentList = list.stream().map((doc) -> doc.toObject(Appointment.class)).collect(Collectors.toList());


        return appointmentList;
    }



    public String deleteAppointment(String id) throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("appointments").document(id);
        ApiFuture<WriteResult> apiFuture = docRef.delete();
        
        return "Deleted " + id;
    }

    public List<Appointment> getAppointmentByPatientId(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("appointments")
                .whereGreaterThanOrEqualTo("patientId", id)
                .whereLessThanOrEqualTo("patientId", id+'z').get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();

        List<Appointment> appointmentList = list.stream().map((doc) -> doc.toObject(Appointment.class)).collect(Collectors.toList());
        return appointmentList;
    }

}
