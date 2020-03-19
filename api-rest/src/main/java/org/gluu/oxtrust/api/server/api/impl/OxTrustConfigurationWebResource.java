package org.gluu.oxtrust.api.server.api.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gluu.config.oxtrust.AppConfiguration;
import org.gluu.oxtrust.api.server.util.ApiConstants;
import org.gluu.oxtrust.api.server.util.Constants;
import org.gluu.oxtrust.service.JsonConfigurationService;
import org.gluu.oxtrust.service.filter.ProtectedApi;
import org.slf4j.Logger;

import com.google.common.base.Preconditions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Path(ApiConstants.CONFIGURATION + ApiConstants.OXTRUST)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OxTrustConfigurationWebResource extends BaseWebResource {

	@Inject
	private Logger logger;
	@Inject
	private JsonConfigurationService jsonConfigurationService;

	private AppConfiguration oxTrustappConfiguration;

	@GET
	@Operation(summary = "Retrieve oxtrust configuration", description = "Retrieve oxtrust configuration")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AppConfiguration.class)), description = Constants.RESULT_SUCCESS),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { READ_ACCESS })
	public Response retrieveOxtrustConfiguration() {
		try {
			log(logger, "Retrieving oxtrust configuration");
			this.oxTrustappConfiguration = jsonConfigurationService.getOxTrustappConfiguration();
			return Response.ok(oxTrustappConfiguration).build();
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Operation(summary = "Update oxtrust configuration", description = "Update oxtrust configuration")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AppConfiguration.class)), description = Constants.RESULT_SUCCESS),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { WRITE_ACCESS })
	public Response updateOxtrustConfiguration(AppConfiguration appConfiguration) {
		try {
			log(logger, "Processing oxtrust json update request");
			Preconditions.checkNotNull(appConfiguration, "Attempt to update null oxtrust json settings");
			jsonConfigurationService.saveOxTrustappConfiguration(appConfiguration);
			return Response.ok(Constants.RESULT_SUCCESS).build();
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
