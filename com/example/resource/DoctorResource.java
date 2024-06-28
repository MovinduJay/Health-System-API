
package com.example.resource;

import com.example.dao.DoctorDAO;
import com.example.model.Doctor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/doctors")

public class DoctorResource {
    
    private static final Logger LOGGER = Logger.getLogger(DoctorResource.class.getName());
    private DoctorDAO doctorDAO = new DoctorDAO();
    
    //get all doctors
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors() {
          try {
            List<Doctor> doctors = DoctorDAO.getAllDoctors();
            LOGGER.log(Level.INFO, "Retrieved all doctors.");
            return Response.ok().entity(doctors).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to get doctors records", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to get doctors records. Please try again later.").build();
        }
    }
//get doctor by ID
    @GET
    @Path("/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("Id") int id) {
        try {
            Doctor doctor = doctorDAO.getDoctorById(id);
            if (doctor != null) {
                return Response.ok(doctor).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found").build();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error getting doctor by ID", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error getting doctor").build();
        }
    }
//add a new doctor
    @POST
    public Response addDoctor(Doctor doctor) {
        try {
            doctorDAO.addDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity("Doctor added successfully.").build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error adding doctor record", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding doctor record").build();
        }
    }
// Update a doctor by ID
@PUT
@Path("/{doctorId}")
@Consumes(MediaType.APPLICATION_JSON)
public Response updateDoctor(@PathParam("doctorId") int doctorId, Doctor updatedDoctor) {
    try {
        Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);
        if (existingDoctor != null) {
            updatedDoctor.setID(doctorId);
            doctorDAO.updateDoctor(updatedDoctor);
            LOGGER.log(Level.INFO, "Doctor with ID {0} updated successfully", doctorId);
            return Response.ok().entity("Doctor with ID " + doctorId + " updated successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor record not found").build();
        }
    } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Error updating doctor record", e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating doctor record").build();
    }
}


    //delete doctor by ID
    @DELETE
    @Path("/{Id}")
    public Response deleteDoctor(@PathParam("Id") int id) {
        try {
            doctorDAO.deleteDoctor(id);
            LOGGER.log(Level.INFO, "Deleted doctor {0} successfully", id);
            return Response.ok().entity("Doctor " + id + " successfully deleted.").build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting doctor", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting doctor").build();
        }
    }
}

