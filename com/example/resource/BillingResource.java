package com.example.resource;

import com.example.dao.BillingDAO;
import com.example.model.Billing;
import com.example.exception.UserNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/billings")
public class BillingResource {

    private static final Logger LOGGER = Logger.getLogger(BillingResource.class.getName());
    private BillingDAO billingDAO = new BillingDAO();

    //get all billings
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBillings() {
        try {
            List<Billing> billings = billingDAO.getAllBillings();
            LOGGER.log(Level.INFO, "Fetched all billing records");
            return Response.ok().entity(billings).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch billing records", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch billing records. Please try again later.").build();
        }
    }
// get the billing by ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingById(@PathParam("id") int id) {
        try {
            Billing billing = billingDAO.getBilling(id);
            if (billing != null) {
                LOGGER.log(Level.INFO, "Retrieved billing {0} successfully", id);
                return Response.ok().entity(billing).build();
            } else {
                throw new UserNotFoundException("Billing " + id + " not found.");
            }
        } catch (UserNotFoundException ex) {
            LOGGER.log(Level.WARNING, "Billing {0} not found", id);
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch billing " + id, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to fetch billing " + id + ". Please try again later.").build();
        }
    }
//add a new billing
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        try {
            billingDAO.addBilling(billing);
            LOGGER.log(Level.INFO, "Added billing successfully");
            return Response.status(Response.Status.CREATED).entity("Billing added successfully.").build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to add billing record", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to add billing record. Please try again later.").build();
        }
    }
//edit a billing
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("id") int billingId, Billing updatedBilling) {
        try {
            Billing existingBilling = billingDAO.getBilling(billingId);
            if (existingBilling != null) {
                updatedBilling.setInvoiceNo(billingId);
                billingDAO.updateBilling(updatedBilling);
                LOGGER.log(Level.INFO, "Updated billing {0} successfully", billingId);
                return Response.ok().entity("Billing " + billingId + " successfully updated").build();
            } else {
                throw new UserNotFoundException("Billing " + billingId + " not found.");
            }
        } catch (UserNotFoundException ex) {
            LOGGER.log(Level.WARNING, "Billing {0} not found", billingId);
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to update billing " + billingId, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to update billing " + billingId + ". Please try again later.").build();
        }
    }
//delete a billing
    @DELETE
    @Path("/{id}")
    public Response deleteBilling(@PathParam("id") int id) {
        try {
            billingDAO.deleteBills(id);
            LOGGER.log(Level.INFO, "Deleted billing {0} successfully", id);
            return Response.ok().entity("Billing with ID " + id + " successfully deleted.").build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to delete billing " + id, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete billing " + id + ". Error: " + ex.getMessage()).build();
        }
    }
}
