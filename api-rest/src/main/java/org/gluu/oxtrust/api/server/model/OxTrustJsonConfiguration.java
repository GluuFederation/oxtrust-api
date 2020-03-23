package org.gluu.oxtrust.api.server.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OxTrustJsonConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3011359447811602714L;
	@JsonProperty("baseDN")
	@JsonIgnore
	private String baseDN;
	@JsonProperty("orgSupportEmail")
	private String orgSupportEmail;
	@JsonProperty("applicationUrl")
	private String applicationUrl;
	@JsonProperty("baseEndpoint")
	private String baseEndpoint;
	@JsonProperty("personObjectClassTypes")
	private List<String> personObjectClassTypes = null;
	@JsonProperty("personCustomObjectClass")
	private String personCustomObjectClass;
	@JsonProperty("personObjectClassDisplayNames")
	private List<String> personObjectClassDisplayNames = null;
	@JsonProperty("contactObjectClassTypes")
	private List<Object> contactObjectClassTypes = null;
	@JsonProperty("contactObjectClassDisplayNames")
	private List<Object> contactObjectClassDisplayNames = null;
	@JsonProperty("photoRepositoryRootDir")
	private String photoRepositoryRootDir;
	@JsonProperty("photoRepositoryThumbWidth")
	private Integer photoRepositoryThumbWidth;
	@JsonProperty("photoRepositoryThumbHeight")
	private Integer photoRepositoryThumbHeight;
	@JsonProperty("photoRepositoryCountLeveles")
	private Integer photoRepositoryCountLeveles;
	@JsonProperty("photoRepositoryCountFoldersPerLevel")
	private Integer photoRepositoryCountFoldersPerLevel;
	@JsonProperty("ldifStore")
	private String ldifStore;
	@JsonProperty("updateStatus")
	private Boolean updateStatus;
	@JsonProperty("svnConfigurationStoreRoot")
	private String svnConfigurationStoreRoot;
	@JsonProperty("svnConfigurationStorePassword")
	private String svnConfigurationStorePassword;
	@JsonProperty("keystorePath")
	private String keystorePath;
	@JsonProperty("keystorePassword")
	private String keystorePassword;
	@JsonProperty("allowPersonModification")
	private Boolean allowPersonModification;

	@JsonProperty("spMetadataPath")
	private String spMetadataPath;
	@JsonProperty("logoLocation")
	private String logoLocation;
	@JsonProperty("gluuSpAttributes")
	private List<Object> gluuSpAttributes = null;
	@JsonProperty("configGeneration")
	private Boolean configGeneration;

	@JsonProperty("gluuSpCert")
	private String gluuSpCert;
	@JsonProperty("caCertsLocation")
	private String caCertsLocation;
	@JsonProperty("caCertsPassphrase")
	private String caCertsPassphrase;
	@JsonProperty("tempCertDir")
	private String tempCertDir;
	@JsonProperty("certDir")
	private String certDir;
	@JsonProperty("servicesRestartTrigger")
	private String servicesRestartTrigger;
	@JsonProperty("persistSVN")
	private Boolean persistSVN;
	@JsonProperty("oxAuthSectorIdentifierUrl")
	private String oxAuthSectorIdentifierUrl;
	@JsonProperty("oxAuthClientId")
	private String oxAuthClientId;
	@JsonProperty("oxAuthClientPassword")
	private String oxAuthClientPassword;
	@JsonProperty("oxAuthClientScope")
	private String oxAuthClientScope;
	@JsonProperty("loginRedirectUrl")
	private String loginRedirectUrl;
	@JsonProperty("logoutRedirectUrl")
	private String logoutRedirectUrl;
	@JsonProperty("clientAssociationAttribute")
	private String clientAssociationAttribute;
	@JsonProperty("oxAuthIssuer")
	private String oxAuthIssuer;
	@JsonProperty("ignoreValidation")
	private Boolean ignoreValidation;
	@JsonProperty("umaIssuer")
	private String umaIssuer;

	@JsonProperty("cssLocation")
	private String cssLocation;
	@JsonProperty("jsLocation")
	private String jsLocation;
	@JsonProperty("enableUpdateNotification")
	private Boolean enableUpdateNotification;

	@JsonProperty("oxIncommonFlag")
	private Boolean oxIncommonFlag;
	@JsonProperty("clientWhiteList")
	private List<String> clientWhiteList = null;
	@JsonProperty("clientBlackList")
	private List<String> clientBlackList = null;
	@JsonProperty("loggingLevel")
	private String loggingLevel;
	@JsonProperty("organizationName")
	private String organizationName;

	@JsonProperty("disableJdkLogger")
	private Boolean disableJdkLogger;
	@JsonProperty("passwordResetRequestExpirationTime")
	private Integer passwordResetRequestExpirationTime;
	@JsonProperty("cleanServiceInterval")
	private Integer cleanServiceInterval;
	@JsonProperty("enforceEmailUniqueness")
	private Boolean enforceEmailUniqueness;
	@JsonProperty("useLocalCache")
	private Boolean useLocalCache;
	@JsonProperty("ScimProperties")
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("baseDN")
	public String getBaseDN() {
		return baseDN;
	}

	@JsonProperty("baseDN")
	public void setBaseDN(String baseDN) {
		this.baseDN = baseDN;
	}

	@JsonProperty("orgSupportEmail")
	public String getOrgSupportEmail() {
		return orgSupportEmail;
	}

	@JsonProperty("orgSupportEmail")
	public void setOrgSupportEmail(String orgSupportEmail) {
		this.orgSupportEmail = orgSupportEmail;
	}

	@JsonProperty("applicationUrl")
	public String getApplicationUrl() {
		return applicationUrl;
	}

	@JsonProperty("applicationUrl")
	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	@JsonProperty("baseEndpoint")
	public String getBaseEndpoint() {
		return baseEndpoint;
	}

	@JsonProperty("baseEndpoint")
	public void setBaseEndpoint(String baseEndpoint) {
		this.baseEndpoint = baseEndpoint;
	}

	@JsonProperty("personObjectClassTypes")
	public List<String> getPersonObjectClassTypes() {
		return personObjectClassTypes;
	}

	@JsonProperty("personObjectClassTypes")
	public void setPersonObjectClassTypes(List<String> personObjectClassTypes) {
		this.personObjectClassTypes = personObjectClassTypes;
	}

	@JsonProperty("personCustomObjectClass")
	public String getPersonCustomObjectClass() {
		return personCustomObjectClass;
	}

	@JsonProperty("personCustomObjectClass")
	public void setPersonCustomObjectClass(String personCustomObjectClass) {
		this.personCustomObjectClass = personCustomObjectClass;
	}

	@JsonProperty("personObjectClassDisplayNames")
	public List<String> getPersonObjectClassDisplayNames() {
		return personObjectClassDisplayNames;
	}

	@JsonProperty("personObjectClassDisplayNames")
	public void setPersonObjectClassDisplayNames(List<String> personObjectClassDisplayNames) {
		this.personObjectClassDisplayNames = personObjectClassDisplayNames;
	}

	@JsonProperty("contactObjectClassTypes")
	public List<Object> getContactObjectClassTypes() {
		return contactObjectClassTypes;
	}

	@JsonProperty("contactObjectClassTypes")
	public void setContactObjectClassTypes(List<Object> contactObjectClassTypes) {
		this.contactObjectClassTypes = contactObjectClassTypes;
	}

	@JsonProperty("contactObjectClassDisplayNames")
	public List<Object> getContactObjectClassDisplayNames() {
		return contactObjectClassDisplayNames;
	}

	@JsonProperty("contactObjectClassDisplayNames")
	public void setContactObjectClassDisplayNames(List<Object> contactObjectClassDisplayNames) {
		this.contactObjectClassDisplayNames = contactObjectClassDisplayNames;
	}

	@JsonProperty("photoRepositoryRootDir")
	public String getPhotoRepositoryRootDir() {
		return photoRepositoryRootDir;
	}

	@JsonProperty("photoRepositoryRootDir")
	public void setPhotoRepositoryRootDir(String photoRepositoryRootDir) {
		this.photoRepositoryRootDir = photoRepositoryRootDir;
	}

	@JsonProperty("photoRepositoryThumbWidth")
	public Integer getPhotoRepositoryThumbWidth() {
		return photoRepositoryThumbWidth;
	}

	@JsonProperty("photoRepositoryThumbWidth")
	public void setPhotoRepositoryThumbWidth(Integer photoRepositoryThumbWidth) {
		this.photoRepositoryThumbWidth = photoRepositoryThumbWidth;
	}

	@JsonProperty("photoRepositoryThumbHeight")
	public Integer getPhotoRepositoryThumbHeight() {
		return photoRepositoryThumbHeight;
	}

	@JsonProperty("photoRepositoryThumbHeight")
	public void setPhotoRepositoryThumbHeight(Integer photoRepositoryThumbHeight) {
		this.photoRepositoryThumbHeight = photoRepositoryThumbHeight;
	}

	@JsonProperty("photoRepositoryCountLeveles")
	public Integer getPhotoRepositoryCountLeveles() {
		return photoRepositoryCountLeveles;
	}

	@JsonProperty("photoRepositoryCountLeveles")
	public void setPhotoRepositoryCountLeveles(Integer photoRepositoryCountLeveles) {
		this.photoRepositoryCountLeveles = photoRepositoryCountLeveles;
	}

	@JsonProperty("photoRepositoryCountFoldersPerLevel")
	public Integer getPhotoRepositoryCountFoldersPerLevel() {
		return photoRepositoryCountFoldersPerLevel;
	}

	@JsonProperty("photoRepositoryCountFoldersPerLevel")
	public void setPhotoRepositoryCountFoldersPerLevel(Integer photoRepositoryCountFoldersPerLevel) {
		this.photoRepositoryCountFoldersPerLevel = photoRepositoryCountFoldersPerLevel;
	}

	@JsonProperty("ldifStore")
	public String getLdifStore() {
		return ldifStore;
	}

	@JsonProperty("ldifStore")
	public void setLdifStore(String ldifStore) {
		this.ldifStore = ldifStore;
	}

	@JsonProperty("updateStatus")
	public Boolean getUpdateStatus() {
		return updateStatus;
	}

	@JsonProperty("updateStatus")
	public void setUpdateStatus(Boolean updateStatus) {
		this.updateStatus = updateStatus;
	}

	@JsonProperty("svnConfigurationStoreRoot")
	public String getSvnConfigurationStoreRoot() {
		return svnConfigurationStoreRoot;
	}

	@JsonProperty("svnConfigurationStoreRoot")
	public void setSvnConfigurationStoreRoot(String svnConfigurationStoreRoot) {
		this.svnConfigurationStoreRoot = svnConfigurationStoreRoot;
	}

	@JsonProperty("svnConfigurationStorePassword")
	public String getSvnConfigurationStorePassword() {
		return svnConfigurationStorePassword;
	}

	@JsonProperty("svnConfigurationStorePassword")
	public void setSvnConfigurationStorePassword(String svnConfigurationStorePassword) {
		this.svnConfigurationStorePassword = svnConfigurationStorePassword;
	}

	@JsonProperty("keystorePath")
	public String getKeystorePath() {
		return keystorePath;
	}

	@JsonProperty("keystorePath")
	public void setKeystorePath(String keystorePath) {
		this.keystorePath = keystorePath;
	}

	@JsonProperty("keystorePassword")
	public String getKeystorePassword() {
		return keystorePassword;
	}

	@JsonProperty("keystorePassword")
	public void setKeystorePassword(String keystorePassword) {
		this.keystorePassword = keystorePassword;
	}

	@JsonProperty("allowPersonModification")
	public Boolean getAllowPersonModification() {
		return allowPersonModification;
	}

	@JsonProperty("allowPersonModification")
	public void setAllowPersonModification(Boolean allowPersonModification) {
		this.allowPersonModification = allowPersonModification;
	}

	@JsonProperty("spMetadataPath")
	public String getSpMetadataPath() {
		return spMetadataPath;
	}

	@JsonProperty("spMetadataPath")
	public void setSpMetadataPath(String spMetadataPath) {
		this.spMetadataPath = spMetadataPath;
	}

	@JsonProperty("logoLocation")
	public String getLogoLocation() {
		return logoLocation;
	}

	@JsonProperty("logoLocation")
	public void setLogoLocation(String logoLocation) {
		this.logoLocation = logoLocation;
	}

	@JsonProperty("gluuSpAttributes")
	public List<Object> getGluuSpAttributes() {
		return gluuSpAttributes;
	}

	@JsonProperty("gluuSpAttributes")
	public void setGluuSpAttributes(List<Object> gluuSpAttributes) {
		this.gluuSpAttributes = gluuSpAttributes;
	}

	@JsonProperty("configGeneration")
	public Boolean getConfigGeneration() {
		return configGeneration;
	}

	@JsonProperty("configGeneration")
	public void setConfigGeneration(Boolean configGeneration) {
		this.configGeneration = configGeneration;
	}

	@JsonProperty("gluuSpCert")
	public String getGluuSpCert() {
		return gluuSpCert;
	}

	@JsonProperty("gluuSpCert")
	public void setGluuSpCert(String gluuSpCert) {
		this.gluuSpCert = gluuSpCert;
	}

	@JsonProperty("caCertsLocation")
	public String getCaCertsLocation() {
		return caCertsLocation;
	}

	@JsonProperty("caCertsLocation")
	public void setCaCertsLocation(String caCertsLocation) {
		this.caCertsLocation = caCertsLocation;
	}

	@JsonProperty("caCertsPassphrase")
	public String getCaCertsPassphrase() {
		return caCertsPassphrase;
	}

	@JsonProperty("caCertsPassphrase")
	public void setCaCertsPassphrase(String caCertsPassphrase) {
		this.caCertsPassphrase = caCertsPassphrase;
	}

	@JsonProperty("tempCertDir")
	public String getTempCertDir() {
		return tempCertDir;
	}

	@JsonProperty("tempCertDir")
	public void setTempCertDir(String tempCertDir) {
		this.tempCertDir = tempCertDir;
	}

	@JsonProperty("certDir")
	public String getCertDir() {
		return certDir;
	}

	@JsonProperty("certDir")
	public void setCertDir(String certDir) {
		this.certDir = certDir;
	}

	@JsonProperty("servicesRestartTrigger")
	public String getServicesRestartTrigger() {
		return servicesRestartTrigger;
	}

	@JsonProperty("servicesRestartTrigger")
	public void setServicesRestartTrigger(String servicesRestartTrigger) {
		this.servicesRestartTrigger = servicesRestartTrigger;
	}

	@JsonProperty("persistSVN")
	public Boolean getPersistSVN() {
		return persistSVN;
	}

	@JsonProperty("persistSVN")
	public void setPersistSVN(Boolean persistSVN) {
		this.persistSVN = persistSVN;
	}

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

	@JsonProperty("loginRedirectUrl")
	public String getLoginRedirectUrl() {
		return loginRedirectUrl;
	}

	@JsonProperty("loginRedirectUrl")
	public void setLoginRedirectUrl(String loginRedirectUrl) {
		this.loginRedirectUrl = loginRedirectUrl;
	}

	@JsonProperty("logoutRedirectUrl")
	public String getLogoutRedirectUrl() {
		return logoutRedirectUrl;
	}

	@JsonProperty("logoutRedirectUrl")
	public void setLogoutRedirectUrl(String logoutRedirectUrl) {
		this.logoutRedirectUrl = logoutRedirectUrl;
	}

	@JsonProperty("clientAssociationAttribute")
	public String getClientAssociationAttribute() {
		return clientAssociationAttribute;
	}

	@JsonProperty("clientAssociationAttribute")
	public void setClientAssociationAttribute(String clientAssociationAttribute) {
		this.clientAssociationAttribute = clientAssociationAttribute;
	}

	@JsonProperty("oxAuthIssuer")
	public String getOxAuthIssuer() {
		return oxAuthIssuer;
	}

	@JsonProperty("oxAuthIssuer")
	public void setOxAuthIssuer(String oxAuthIssuer) {
		this.oxAuthIssuer = oxAuthIssuer;
	}

	@JsonProperty("ignoreValidation")
	public Boolean getIgnoreValidation() {
		return ignoreValidation;
	}

	@JsonProperty("ignoreValidation")
	public void setIgnoreValidation(Boolean ignoreValidation) {
		this.ignoreValidation = ignoreValidation;
	}

	@JsonProperty("umaIssuer")
	public String getUmaIssuer() {
		return umaIssuer;
	}

	@JsonProperty("umaIssuer")
	public void setUmaIssuer(String umaIssuer) {
		this.umaIssuer = umaIssuer;
	}

	@JsonProperty("cssLocation")
	public String getCssLocation() {
		return cssLocation;
	}

	@JsonProperty("cssLocation")
	public void setCssLocation(String cssLocation) {
		this.cssLocation = cssLocation;
	}

	@JsonProperty("jsLocation")
	public String getJsLocation() {
		return jsLocation;
	}

	@JsonProperty("jsLocation")
	public void setJsLocation(String jsLocation) {
		this.jsLocation = jsLocation;
	}

	@JsonProperty("enableUpdateNotification")
	public Boolean getEnableUpdateNotification() {
		return enableUpdateNotification;
	}

	@JsonProperty("enableUpdateNotification")
	public void setEnableUpdateNotification(Boolean enableUpdateNotification) {
		this.enableUpdateNotification = enableUpdateNotification;
	}

	@JsonProperty("oxIncommonFlag")
	public Boolean getOxIncommonFlag() {
		return oxIncommonFlag;
	}

	@JsonProperty("oxIncommonFlag")
	public void setOxIncommonFlag(Boolean oxIncommonFlag) {
		this.oxIncommonFlag = oxIncommonFlag;
	}

	@JsonProperty("clientWhiteList")
	public List<String> getClientWhiteList() {
		return clientWhiteList;
	}

	@JsonProperty("clientWhiteList")
	public void setClientWhiteList(List<String> clientWhiteList) {
		this.clientWhiteList = clientWhiteList;
	}

	@JsonProperty("clientBlackList")
	public List<String> getClientBlackList() {
		return clientBlackList;
	}

	@JsonProperty("clientBlackList")
	public void setClientBlackList(List<String> clientBlackList) {
		this.clientBlackList = clientBlackList;
	}

	@JsonProperty("loggingLevel")
	public String getLoggingLevel() {
		return loggingLevel;
	}

	@JsonProperty("loggingLevel")
	public void setLoggingLevel(String loggingLevel) {
		this.loggingLevel = loggingLevel;
	}

	@JsonProperty("organizationName")
	public String getOrganizationName() {
		return organizationName;
	}

	@JsonProperty("organizationName")
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@JsonProperty("disableJdkLogger")
	public Boolean getDisableJdkLogger() {
		return disableJdkLogger;
	}

	@JsonProperty("disableJdkLogger")
	public void setDisableJdkLogger(Boolean disableJdkLogger) {
		this.disableJdkLogger = disableJdkLogger;
	}

	@JsonProperty("passwordResetRequestExpirationTime")
	public Integer getPasswordResetRequestExpirationTime() {
		return passwordResetRequestExpirationTime;
	}

	@JsonProperty("passwordResetRequestExpirationTime")
	public void setPasswordResetRequestExpirationTime(Integer passwordResetRequestExpirationTime) {
		this.passwordResetRequestExpirationTime = passwordResetRequestExpirationTime;
	}

	@JsonProperty("cleanServiceInterval")
	public Integer getCleanServiceInterval() {
		return cleanServiceInterval;
	}

	@JsonProperty("cleanServiceInterval")
	public void setCleanServiceInterval(Integer cleanServiceInterval) {
		this.cleanServiceInterval = cleanServiceInterval;
	}

	@JsonProperty("enforceEmailUniqueness")
	public Boolean getEnforceEmailUniqueness() {
		return enforceEmailUniqueness;
	}

	@JsonProperty("enforceEmailUniqueness")
	public void setEnforceEmailUniqueness(Boolean enforceEmailUniqueness) {
		this.enforceEmailUniqueness = enforceEmailUniqueness;
	}

	@JsonProperty("useLocalCache")
	public Boolean getUseLocalCache() {
		return useLocalCache;
	}

	@JsonProperty("useLocalCache")
	public void setUseLocalCache(Boolean useLocalCache) {
		this.useLocalCache = useLocalCache;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
