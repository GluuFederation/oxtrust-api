package org.gluu.oxtrust.api.server.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "remoteIdp")
public class TrustedIDPApi implements Serializable {

	public TrustedIDPApi(String inum, String name, String host, String signingCertificates,
			List<SingleSignOnServices> supportedSingleSignOnServices,
			SingleSignOnServices selectedSingleSignOnService) {
		super();
		//this.inum = inum;
		this.name = name;
		this.host = host;
		this.signingCertificates = signingCertificates;
		this.supportedSingleSignOnServices = supportedSingleSignOnServices;
		this.selectedSingleSignOnService = selectedSingleSignOnService;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5914998360095334159L;
	//private String inum;
	private String name;
	private String host;
	private String signingCertificates;
	private List<SingleSignOnServices> supportedSingleSignOnServices;
	private SingleSignOnServices selectedSingleSignOnService;

	public TrustedIDPApi() {
	}

	/*
	 * public String getInum() { return inum; }
	 * 
	 * public void setInum(String inum) { this.inum = inum; }
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSigningCertificates() {
		return signingCertificates;
	}

	public void setSigningCertificates(String signingCertificates) {
		this.signingCertificates = signingCertificates;
	}

	public List<SingleSignOnServices> getSupportedSingleSignOnServices() {
		return supportedSingleSignOnServices;
	}

	public void setSupportedSingleSignOnServices(List<SingleSignOnServices> supportedSingleSignOnServices) {
		this.supportedSingleSignOnServices = supportedSingleSignOnServices;
	}

	public SingleSignOnServices getSelectedSingleSignOnService() {
		return selectedSingleSignOnService;
	}

	public void setSelectedSingleSignOnService(SingleSignOnServices selectedSingleSignOnService) {
		this.selectedSingleSignOnService = selectedSingleSignOnService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		//result = prime * result + ((inum == null) ? 0 : inum.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((selectedSingleSignOnService == null) ? 0 : selectedSingleSignOnService.hashCode());
		result = prime * result + ((signingCertificates == null) ? 0 : signingCertificates.hashCode());
		result = prime * result
				+ ((supportedSingleSignOnServices == null) ? 0 : supportedSingleSignOnServices.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrustedIDPApi other = (TrustedIDPApi) obj;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		/*
		 * if (inum == null) { if (other.inum != null) return false; } else if
		 * (!inum.equals(other.inum)) return false;
		 */
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (selectedSingleSignOnService == null) {
			if (other.selectedSingleSignOnService != null)
				return false;
		} else if (!selectedSingleSignOnService.equals(other.selectedSingleSignOnService))
			return false;
		if (signingCertificates == null) {
			if (other.signingCertificates != null)
				return false;
		} else if (!signingCertificates.equals(other.signingCertificates))
			return false;
		if (supportedSingleSignOnServices == null) {
			if (other.supportedSingleSignOnServices != null)
				return false;
		} else if (!supportedSingleSignOnServices.equals(other.supportedSingleSignOnServices))
			return false;
		return true;
	}


}
