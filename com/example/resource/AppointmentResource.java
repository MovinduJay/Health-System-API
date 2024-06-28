package com.example.resource;

import com.example.dao.AppointmentDAO;
import com.example.model.Appointment;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/appointments")
public class AppointmentResource {

    private static final Logger LOGGER = Logger.getLogger(AppointmentResource.class.getName());
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    // Get all appointments
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentDAO.getAllAppointments();
            LOGGER.log(Level.INFO, "Fetched all appointments.");
            return Response.ok().entity(appointments).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch appointments", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch appointments. Please try again later.").build();
        }
    }

    // Get appointment by ID
    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
            if (appointment != null) {
                LOGGER.log(Level.INFO, "Fetched appointment with ID: " + appointmentId + " successfully");
                return Response.ok().entity(appointment).build();
            } else {
                LOGGER.log(Level.INFO, "Appointment with ID: " + appointmentId + " not available");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Appointment with ID: " + appointmentId + " not available").build();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch appointment with ID: " + appointmentId, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch appointment with ID: " + appointmentId + ". Please try again later.").build();
        }
    }

    // Add a new appointment
    @POST
    public Response addAppointment(Appointment appointment) {
        try {
            appointmentDAO.addAppointment(appointment);
            LOGGER.log(Level.INFO, "Added new appointment.");
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to add appointment", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add appointment. Please try again later.").build();
        }
    }

   // Update an existing appointment
@PUT
@Path("/{appointmentId}")
public Response updateAppointment(@PathParam("appointmentId") int appointmentId, Appointment updatedAppointment) {
    try {
        appointmentDAO.updateAppointment(appointmentId, updatedAppointment);
        LOGGER.log(Level.INFO, "Updated appointment with ID: {0}", appointmentId);
        return Response.ok().entity("Appointment with ID " + appointmentId + " updated successfully").build();
    } catch (Exception ex) {
        LOGGER.log(Level.SEVERE, "Failed to update appointment with ID: " + appointmentId, ex);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Failed to update appointment with ID: " + appointmentId + ". Please try again later.").build();
    }
}
    // Delete an appointment by ID
    @DELETE
    @Path("/{appointmentId}")
    public Response deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        try {
            appointmentDAO.deleteAppointment(appointmentId);
            LOGGER.log(Level.INFO, "Deleted appointment : {0} successfully", appointmentId);
            return Response.ok().entity("Appointment " + appointmentId + " successfully deleted.").build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to delete appointment : " + appointmentId, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete appointment : " + appointmentId + ". Please try again later.").build();
        }
    }
}
