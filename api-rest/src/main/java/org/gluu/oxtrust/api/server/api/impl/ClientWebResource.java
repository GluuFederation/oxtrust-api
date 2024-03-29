package org.gluu.oxtrust.api.server.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.gluu.oxtrust.api.server.annotations.MaxAge;
import org.gluu.oxtrust.api.server.util.ApiConstants;
import org.gluu.oxtrust.model.OxAuthClient;
import org.gluu.oxtrust.service.ClientService;
import org.gluu.oxtrust.service.ScopeService;
import org.gluu.oxtrust.service.filter.ProtectedApi;
import org.gluu.util.StringHelper;
import org.jboss.resteasy.links.AddLinks;
import org.jboss.resteasy.links.LinkResource;
import org.oxauth.persistence.model.Scope;
import org.slf4j.Logger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Path(ApiConstants.BASE_API_URL + ApiConstants.CLIENTS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ClientWebResource extends BaseWebResource {

	@Inject
	private Logger logger;

	@Inject
	private ClientService clientService;

	@Inject
	private ScopeService scopeService;

	public ClientWebResource() {
	}

	@GET
	@Operation(summary = "Retrieve all openid connect clients", description = "Retrieve all openid connect clients")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = OxAuthClient[].class)), description = "Success"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { READ_ACCESS })
	@MaxAge(value = 30)
    @AddLinks
    @LinkResource(value = OxAuthClient.class)
	public Response getOpenIdClients(@Context UriInfo uriInfo) {
		try {
			log(logger, " Retrieving all openid connect clients");
			List<OxAuthClient> clientList = clientService.getAllClients();
			return Response.ok(clientList).build();
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(ApiConstants.INUM_PARAM_PATH + ApiConstants.SCOPES)
	@Operation(summary = "Get assigned OIDC client scopes", description = "Get OIDC scopes assign to OIDC client")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Scope[].class)), description = "Success"),
			@ApiResponse(responseCode = "500", description = "Server error"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	@ProtectedApi(scopes = { READ_ACCESS })
	public Response getClientScope(@PathParam(ApiConstants.INUM) @NotNull String inum) {
		log(logger, "Get client scopes");
		try {
			Objects.requireNonNull(inum);
			OxAuthClient client = clientService.getClientByInum(inum);
			if (client != null) {
				List<String> scopesDn = client.getOxAuthScopes();
				List<Scope> scopes = new ArrayList<Scope>();
				if (scopesDn != null) {
					for (String scopeDn : scopesDn) {
						scopes.add(scopeService.getScopeByDn(scopeDn));
					}
					return Response.ok(scopes).build();
				} else {
					return Response.ok(scopes).build();
				}
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(ApiConstants.INUM_PARAM_PATH)
	@Operation(summary = "Get OIDC client", description = "Get a specific OIDC client")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = OxAuthClient.class)), description = "Success"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { READ_ACCESS })
	@AddLinks
    @LinkResource
	public Response getClientByInum(@PathParam(ApiConstants.INUM) @NotNull String inum) {
		log(logger, "Get client " + inum);
		try {
			Objects.requireNonNull(inum);
			OxAuthClient client = clientService.getClientByInum(inum);
			if (client != null) {
				return Response.ok(client).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(ApiConstants.SEARCH)
	@Operation(summary = "Search OIDC clients", description = "Search OIDC clients")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = OxAuthClient[].class)), description = "Success"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { READ_ACCESS })
	public Response searchClients(@QueryParam(ApiConstants.SEARCH_PATTERN) @NotNull String pattern,
			@DefaultValue("1") @QueryParam(ApiConstants.SIZE) int size) {
		log(logger, "Search client with pattern= " + pattern + " and size " + size);
		try {
			List<OxAuthClient> clients = clientService.searchClients(pattern, size);
			return Response.ok(clients).build();
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Operation(summary = "Add OIDC client", description = "Add an openidconnect client")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = OxAuthClient.class)), description = "Success"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { WRITE_ACCESS })
	@LinkResource
	public Response createClient(OxAuthClient client) {
		log(logger, "Add new client ");
		try {
			Objects.requireNonNull(client, "Attempt to create null client");
			String inum = client.getInum();
			if (StringHelper.isEmpty(inum)) {
				inum = clientService.generateInumForNewClient();
			}
			client.setInum(inum);
			client.setDn(clientService.getDnForClient(inum));
			client.setDeletable(client.isDeletable());
			clientService.addClient(client);
			return Response.status(Response.Status.CREATED).entity(clientService.getClientByInum(inum)).build();
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Operation(summary = "Update OIDC client", description = "Update openidconnect client")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = OxAuthClient.class)), description = "Success"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { WRITE_ACCESS })
	public Response updateClient(OxAuthClient client) {
		try {
			Objects.requireNonNull(client, "Attempt to update null client");
			String inum = client.getInum();
			log(logger, "Update client " + inum);
			OxAuthClient existingClient = clientService.getClientByInum(inum);
			if (existingClient != null) {
				client.setInum(existingClient.getInum());
				client.setBaseDn(clientService.getDnForClient(inum));
				client.setDeletable(client.isDeletable());
				clientService.updateClient(client);
				return Response.ok(clientService.getClientByInum(existingClient.getInum())).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(ApiConstants.INUM_PARAM_PATH + ApiConstants.SCOPES + ApiConstants.SCOPE_INUM_PARAM_PATH)
	@Operation(summary = "Add OIDC client scopes", description = "Add scopes to OIDC client")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Scope[].class)), description = "Success"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { WRITE_ACCESS })
	public Response addScopeToClient(@PathParam(ApiConstants.INUM) @NotNull String inum,
			@PathParam(ApiConstants.SCOPE_INUM) @NotNull String sinum) {
		log(logger, "add new scope to client");
		try {
			OxAuthClient client = clientService.getClientByInum(inum);
			Scope scope = scopeService.getScopeByInum(sinum);
			Objects.requireNonNull(client);
			Objects.requireNonNull(scope);
			if (client != null && scope != null) {
				List<String> scopes = new ArrayList<String>(client.getOxAuthScopes());
				String scopeBaseDn = scopeService.getDnForScope(scope.getInum());
				scopes.remove(scopeBaseDn);
				scopes.add(scopeBaseDn);
				client.setOxAuthScopes(scopes);
				clientService.updateClient(client);
				return Response.ok(scopes).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path(ApiConstants.INUM_PARAM_PATH + ApiConstants.SCOPES + ApiConstants.SCOPE_INUM_PARAM_PATH)
	@Operation(summary = "Remove OIDC client scope", description = "Remove an existing scope from client")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Scope[].class)), description = "Success"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { WRITE_ACCESS })
	public Response removeScopeToClient(@PathParam(ApiConstants.INUM) @NotNull String inum,
			@PathParam(ApiConstants.SCOPE_INUM) @NotNull String sinum) {
		log(logger, "remove scope to client");
		try {
			OxAuthClient client = clientService.getClientByInum(inum);
			Scope scope = scopeService.getScopeByInum(sinum);
			Objects.requireNonNull(client);
			Objects.requireNonNull(scope);
			if (client != null && scope != null) {
				List<String> scopes = new ArrayList<String>(client.getOxAuthScopes());
				scopes.remove(scopeService.getDnForScope(scope.getInum()));
				client.setOxAuthScopes(scopes);
				clientService.updateClient(client);
				return Response.ok(scopes).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path(ApiConstants.INUM_PARAM_PATH)
	@Operation(summary = "Delete OIDC client ", description = "Delete an openidconnect client")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = OxAuthClient[].class)), description = "Success"),
			@ApiResponse(responseCode = "500", description = "Server error") })
	@ProtectedApi(scopes = { WRITE_ACCESS })
	public Response deleteClient(@PathParam(ApiConstants.INUM) @NotNull String inum) {
		log(logger, "Delete client " + inum);
		try {
			Objects.requireNonNull(inum);
			OxAuthClient client = clientService.getClientByInum(inum);
			if (client != null) {
				clientService.removeClient(client);
				return Response.ok().build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			log(logger, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@ProtectedApi(scopes = { WRITE_ACCESS })
	public Response deleteClients() {
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

	@DELETE
	@Path(ApiConstants.INUM_PARAM_PATH + ApiConstants.SCOPES)
	@ProtectedApi(scopes = { WRITE_ACCESS })
	public Response deleteClientScopes(@PathParam(ApiConstants.INUM) @NotNull String inum) {
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
}
