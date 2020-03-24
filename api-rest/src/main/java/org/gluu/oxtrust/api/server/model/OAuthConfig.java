package org.gluu.oxtrust.api.server.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3940329523059518172L;
	@JsonProperty("oxAuthSectorIdentifierUrl")
	private String oxAuthSectorIdentifierUrl;
	@JsonProperty("oxAuthClientId")
	private String oxAuthClientId;
	@JsonProperty("oxAuthClientPassword")
	private String oxAuthClientPassword;
	@JsonProperty("oxAuthClientScope")
	private String oxAuthClientScope;
	@JsonProperty("oxAuthIssuer")
	private String oxAuthIssuer;

	@JsonProperty("oxAuthSectorIdentifierUrl")
	public String getOxAuthSectorIdentifierUrl() {
		return oxAuthSectorIdentifierUrl;
	}

	@JsonProperty("oxAuthSectorIdentifierUrl")
	public void setOxAuthSectorIdentifierUrl(String oxAuthSectorIdentifierUrl) {
		this.oxAuthSectorIdentifierUrl = oxAuthSectorIdentifierUrl;
	}

	@JsonProperty("oxAuthClientId")
	public String getOxAuthClientId() {
		return oxAuthClientId;
	}

	@JsonProperty("oxAuthClientId")
	public void setOxAuthClientId(String oxAuthClientId) {
		this.oxAuthClientId = oxAuthClientId;
	}

	@JsonProperty("oxAuthClientPassword")
	public String getOxAuthClientPassword() {
		return oxAuthClientPassword;
	}

	@JsonProperty("oxAuthClientPassword")
	public void setOxAuthClientPassword(String oxAuthClientPassword) {
		this.oxAuthClientPassword = oxAuthClientPassword;
	}

	@JsonProperty("oxAuthClientScope")
	public String getOxAuthClientScope() {
		return oxAuthClientScope;
	}

	@JsonProperty("oxAuthClientScope")
	public void setOxAuthClientScope(String oxAuthClientScope) {
		this.oxAuthClientScope = oxAuthClientScope;
	}

	@JsonProperty("oxAuthIssuer")
	public String getOxAuthIssuer() {
		return oxAuthIssuer;
	}

	@JsonProperty("oxAuthIssuer")
	public void setOxAuthIssuer(String oxAuthIssuer) {
		this.oxAuthIssuer = oxAuthIssuer;
	}

}
