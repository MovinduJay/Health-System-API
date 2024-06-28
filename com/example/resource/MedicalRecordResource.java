package com.example.resource;

import com.example.dao.MedicalRecordDAO;
import com.example.model.MedicalRecord;
import javax.ws.rs.*;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/medical-records")
public class MedicalRecordResource {

    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordResource.class);
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

    // Get all medical records
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordDAO.getAllMedicalRecords();
    }
    
    // Get a medical record by ID
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> getMedicalRecord(@PathParam("patientId") int id) {
        return medicalRecordDAO.getMedicalRecords(id);
    }
    
    // Add a new medical record
@POST
@Consumes(MediaType.APPLICATION_JSON)
public Response addMedicalRecord(MedicalRecord medicalRecord) {
    try {
        medicalRecordDAO.addMedicalRecord(medicalRecord);
        logger.info("Medical record added: {}", medicalRecord.getMedicalRecordId());
        return Response.status(Response.Status.CREATED).build();
    } catch (Exception ex) {
        logger.error("Failed to add medical record", ex);
        throw new WebApplicationException("Failed to add medical record. Please try again later.", ex);
    }
}

    
    // Update a medical record by ID
@PUT
@Path("/{medicalRecordId}")
@Consumes(MediaType.APPLICATION_JSON)
public Response updateMedicalRecord(@PathParam("medicalRecordId") int medicalRecordId, MedicalRecord updatedMedicalRecord) {
    try {
        medicalRecordDAO.updateMedicalRecord(medicalRecordId, updatedMedicalRecord);
        logger.info("Updated medical record with ID: {}", medicalRecordId);
        return Response.ok().entity("Medical record with ID " + medicalRecordId + " updated successfully").build();
    } catch (Exception ex) {
        logger.error("Failed to update medical record with ID: {}", medicalRecordId, ex);
        throw new WebApplicationException("Failed to update medical record with ID: " + medicalRecordId + ". Please try again later.", ex);
    }
}

// Delete a record by ID
@DELETE
@Path("/{ID}")
public Response deleteMedicalRecord(@PathParam("ID") int id) {
    try {
        medicalRecordDAO.deleteMedicalRecord(id);
        logger.info("Medical record with ID {} deleted successfully", id);
        return Response.ok().entity("Medical record with ID " + id + " deleted successfully").build();
    } catch (Exception ex) {
        logger.error("Error occurred when deleting record with ID {}: {}", id, ex.getMessage());
        throw new WebApplicationException("Failed to delete record with ID " + id + ". Please try again later.", ex);
    }
}

}
