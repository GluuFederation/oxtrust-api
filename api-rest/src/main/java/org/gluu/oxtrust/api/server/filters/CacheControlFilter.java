package org.gluu.oxtrust.api.server.filters;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;

@ApplicationScoped
public class CacheControlFilter implements ContainerResponseFilter {

	private int maxAge;

	public CacheControlFilter(int maxAge) {
		this.maxAge = maxAge;
	}

	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
		if (request.getMethod().equals("GET")) {
			CacheControl cacheControl = new CacheControl();
			cacheControl.setMaxAge(this.maxAge);
			response.getHeaders().add("Cache-Control", cacheControl);
		}
	}

}
