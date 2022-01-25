package org.gluu.oxtrust.api.server.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gluu.oxtrust.api.server.model.TrustedIDPApi;
import org.gluu.oxtrust.api.server.model.SingleSignOnServices;
import org.gluu.oxtrust.api.server.util.ApiConstants;
import org.gluu.oxtrust.model.OxTrustedIdp;
import org.gluu.oxtrust.service.TrustedIDPService;
import org.gluu.oxtrust.service.filter.ProtectedApi;
import org.gluu.util.StringHelper;
import org.slf4j.Logger;
import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@Named("TrustedIDPEndPoint")
@Path(ApiConstants.BASE_API_URL + ApiConstants.INBOUNDSAML + ApiConstants.TRUSTEDIDP)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TrustedIDPWebResource  extends BaseWebResource{
	
	@Inject
	private Logger logger;
	

	@Inject
	private TrustedIDPService trustedIDPService;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Operation(summary = "Retrieve all trusted-idps", description = "Retrieve all trusted-idps")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = TrustedIDPApi[].class)), description = "Success"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { READ_ACCESS })
	public Response gluuTrustedIdps() {
		log(logger, "get all trusted-idps ");
		try {
			log(logger, " Retrieving all trusted-idps");
			List<OxTrustedIdp> oxTrustedIdpList = trustedIDPService.getAllTrustedIDP();
			List<TrustedIDPApi> trustedIDPApiList = new ArrayList<TrustedIDPApi>();
			for(OxTrustedIdp oxTrustedIdp : oxTrustedIdpList) {
				trustedIDPApiList.add(copyParameters(oxTrustedIdp));
			}
			
			return Response.ok(trustedIDPApiList).build();
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(ApiConstants.INUM + ApiConstants.INUM_PARAM_PATH)
	@Operation(summary = "Get TrustedIDP by inum", description = "Get a TrustedIDP by inum")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = TrustedIDPApi.class)), description = "Success"),
			@ApiResponse(responseCode = "404", description = "Resource not Found"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { READ_ACCESS })
	public Response gluuTrustedIdp( @PathParam("inum") String inum) {
		log(logger, "get trusted-idp by inum ");
		try {
			log(logger, "get trusted-idps by inum");
			OxTrustedIdp oxTrustedIdp = trustedIDPService.getTrustedIDPByInumCustom(inum);
			TrustedIDPApi trustedIDPApi = null;
			if(oxTrustedIdp == null) {
				return Response.status(Response.Status.NOT_FOUND).entity("{\r\n" + 
						"  \"message\": \"The requested ressource was not found\"\r\n" + 
						"}").build();
			}
			
			trustedIDPApi = copyParameters(oxTrustedIdp);
			return Response.ok(trustedIDPApi).build();
		} catch (Exception e) {
		log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path(ApiConstants.REMOTEIDPHOST)
	@Operation(summary = "Get TrustedIDP by remote idp host", description = "Get a TrustedIDP by remote idp host")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = TrustedIDPApi.class)), description = "Success"),
			@ApiResponse(responseCode = "404", description = "Resource not Found"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { READ_ACCESS })
	public Response gluuTrustedIdpByRemoteIdpHost( @PathParam("remoteIdpHost") String remoteIdpHost) {
		log(logger, "get  trusted-idps by remote idp host ");
		try {
			log(logger, " Retrieving  trusted-idps by remote idp host");
			OxTrustedIdp oxTrustedIdp = trustedIDPService.getTrustedIDPByRemoteIdpHost(remoteIdpHost);
			TrustedIDPApi trustedIDPApi = null;
			if(oxTrustedIdp == null) {
				return Response.status(Response.Status.NOT_FOUND).entity("{\r\n" + 
						"  \"message\": \"The requested ressource was not found\"\r\n" + 
						"}").build();
			}
			
			trustedIDPApi = copyParameters(oxTrustedIdp);
			return Response.ok(trustedIDPApi).build();
		} catch (Exception e) {
		log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Operation(summary = "Add TrustedIDP", description = "Add an TrustedIDP")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = TrustedIDPApi.class)), description = "Success"),
			@ApiResponse(responseCode = "403", description = "Trust Relation already exists"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { WRITE_ACCESS })
	public Response createGluuTrustedIdp(TrustedIDPApi trustedIDPApi) {
		log(logger, "Add new remote idp ");
		try {
			Objects.requireNonNull(trustedIDPApi, "Attempt to create null TrustedIDP");
			OxTrustedIdp existingoxTrustedIdp = trustedIDPService.getTrustedIDPByRemoteIdpHost(trustedIDPApi.getHost());
			if(existingoxTrustedIdp != null) {
				return Response.status(403).entity("{\r\n" + 
						"  \"message\": \"A Trust relation with remote idp host "+trustedIDPApi.getHost()+" already exists.\"\r\n" + 
						"}").build();			
			}
			
			String inum = trustedIDPService.generateInumForTrustedIDP();
			
			//trustedIDPApi.setInum(inum);
			OxTrustedIdp oxTrustedIdp = copyAttributes(trustedIDPApi);
			oxTrustedIdp.setInum(inum);
			oxTrustedIdp.setDn(trustedIDPService.getDnForTrustedIDP(inum));
			trustedIDPService.addTrustedIDP(oxTrustedIdp);
			return Response.status(Response.Status.CREATED).entity("{\r\n" + 
					"  \"message\": \"Created\"\r\n" + 
					"}").build();
		} catch (Exception e) {
			log(logger, e.getLocalizedMessage());
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT
	@Path(ApiConstants.REMOTEIDPHOST)
	@Operation(summary = "Update TrustedIDP", description = "Update a TrustedIDP")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = TrustedIDPApi.class)), description = "Success"),
			@ApiResponse(responseCode = "404", description = "Resource not Found"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { WRITE_ACCESS })
	public Response updateTrustedIdp(@PathParam("remoteIdpHost") String remoteIdpHost, TrustedIDPApi trustedIDPApi)
			throws Exception {
		log(logger,"update TrustedIDP ");
		try {
			Objects.requireNonNull(trustedIDPApi, "Attempt to create null TrustedIDP");
			//get inum for remoteIDP
			OxTrustedIdp existingTrustedIDP = trustedIDPService.getTrustedIDPByRemoteIdpHost(remoteIdpHost);
			if(existingTrustedIDP == null) {
				return Response.status(Response.Status.NOT_FOUND)
						.entity("{\r\n" + 
								"  \"message\": \"The requested ressource was not found\"\r\n" + 
								"}").build();
			
			}
			OxTrustedIdp oxTrustedIdp = copyAttributes(trustedIDPApi);
			oxTrustedIdp.setInum(existingTrustedIDP.getInum());
			oxTrustedIdp.setDn(trustedIDPService.getDnForTrustedIDP(existingTrustedIDP.getInum()));
			trustedIDPService.updateTrustedIDP(oxTrustedIdp);
			
			TrustedIDPApi returnTrustedIDPApi = copyParameters(trustedIDPService.getTrustedIDPByInum(existingTrustedIDP.getInum()));
			return Response.status(Response.Status.NO_CONTENT)
					.entity(returnTrustedIDPApi).build();
		} catch (Exception e) {
			log(logger,e.getLocalizedMessage());
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\r\n" + 
					"  \"message\": \"Internal Server Error, please contact admin\"\r\n" + 
					"}").build();
		}
	}
	
	@DELETE
	@Path(ApiConstants.REMOTEIDPHOST)
	@Operation(summary = "Delete TrustedIDP", description = "Delete a TrustedIDP")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { WRITE_ACCESS })
	public Response deleteTrustedIdps(@PathParam("remoteIdpHost") String remoteIdpHost) throws Exception {
		log(logger,"delete TrustedIDP by host");
		try {
			OxTrustedIdp oxTrustedIdp = trustedIDPService.getTrustedIDPByRemoteIdpHost(remoteIdpHost);
			if(oxTrustedIdp != null)
				trustedIDPService.removeTrustedIDP(oxTrustedIdp);

			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			log(logger,e.getLocalizedMessage());
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	private OxTrustedIdp copyAttributes(TrustedIDPApi trustedIDPApi) throws JsonProcessingException {
		OxTrustedIdp oxTrustedIdp = new OxTrustedIdp();
		oxTrustedIdp.setRemoteIdpHost(trustedIDPApi.getHost());
		oxTrustedIdp.setRemoteIdpName(trustedIDPApi.getName());
		oxTrustedIdp.setSigningCertificates(trustedIDPApi.getSigningCertificates());
		oxTrustedIdp.setSelectedSingleSignOnService(new ObjectMapper().writeValueAsString(trustedIDPApi.getSelectedSingleSignOnService()));
		oxTrustedIdp.setSupportedSingleSignOnServices(new ObjectMapper().writeValueAsString(trustedIDPApi.getSupportedSingleSignOnServices()));
		//oxTrustedIdp.setInum(trustedIDPApi.getInum());
		return oxTrustedIdp;
	}
	
	private TrustedIDPApi copyParameters(OxTrustedIdp oxTrustedIdp) throws JsonProcessingException {
		TrustedIDPApi trustedIDPApi = new TrustedIDPApi();
		trustedIDPApi.setHost(oxTrustedIdp.getRemoteIdpHost());
		trustedIDPApi.setName(oxTrustedIdp.getRemoteIdpName());
		trustedIDPApi.setSigningCertificates(oxTrustedIdp.getSigningCertificates());
		trustedIDPApi.setSelectedSingleSignOnService(new ObjectMapper().readValue(oxTrustedIdp.getSelectedSingleSignOnService(),
				SingleSignOnServices.class));
		ObjectMapper readMapper = new ObjectMapper();
		trustedIDPApi.setSupportedSingleSignOnServices(readMapper.readValue(oxTrustedIdp.getSupportedSingleSignOnServices(),
				readMapper.getTypeFactory().constructCollectionType(List.class,SingleSignOnServices.class)));
		//trustedIDPApi.setInum(oxTrustedIdp.getInum());
		return trustedIDPApi;
	}

}
