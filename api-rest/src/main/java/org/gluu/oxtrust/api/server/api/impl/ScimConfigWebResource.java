package org.gluu.oxtrust.api.server.api.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gluu.config.oxtrust.AppConfiguration;
import org.gluu.oxtrust.api.server.model.ScimConfig;
import org.gluu.oxtrust.api.server.util.ApiConstants;
import org.gluu.oxtrust.api.server.util.Constants;
import org.gluu.oxtrust.service.JsonConfigurationService;
import org.gluu.oxtrust.service.filter.ProtectedApi;
import org.slf4j.Logger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Path(ApiConstants.CONFIGURATION + ApiConstants.SCIM)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ScimConfigWebResource extends BaseWebResource {

	@Inject
	private Logger logger;
	@Inject
	private JsonConfigurationService jsonConfigurationService;

	@GET
	@Operation(summary = "Retrieve scim configuration", description = "Retrieve scim configuration")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ScimConfig.class)), description = Constants.RESULT_SUCCESS),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { READ_ACCESS })
	public Response retrieveScimConfiguration() {
		try {
			log(logger, "Retrieving SCIM configuration");
			AppConfiguration oxTrustappConfiguration = jsonConfigurationService.getOxTrustappConfiguration();
			ScimConfig scimConfig = new ScimConfig();
			scimConfig.setScimTestMode(oxTrustappConfiguration.isScimTestMode());
			scimConfig.setScimUmaClientId(oxTrustappConfiguration.getScimUmaClientId());
			scimConfig.setScimUmaClientKeyId(oxTrustappConfiguration.getScimUmaClientKeyId());
			scimConfig.setScimUmaClientKeyStoreFile(oxTrustappConfiguration.getScimUmaClientKeyStoreFile());
			scimConfig.setScimUmaClientKeyStorePassword(oxTrustappConfiguration.getScimUmaClientKeyStorePassword());
			scimConfig.setScimUmaResourceId(oxTrustappConfiguration.getScimUmaResourceId());
			scimConfig.setScimUmaScope(oxTrustappConfiguration.getScimUmaScope());
			scimConfig.setScimMaxCount(oxTrustappConfiguration.getScimProperties().getMaxCount());
			return Response.ok(scimConfig).build();
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
