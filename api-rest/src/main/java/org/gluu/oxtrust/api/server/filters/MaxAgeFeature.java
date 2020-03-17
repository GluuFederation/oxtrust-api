package org.gluu.oxtrust.api.server.filters;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.gluu.oxtrust.api.server.annotations.MaxAge;

@Provider
public class MaxAgeFeature implements DynamicFeature {

	public void configure(ResourceInfo ri, FeatureContext ctx) {
		MaxAge max = ri.getResourceMethod().getAnnotation(MaxAge.class);
		if (max == null)
			return;
		CacheControlFilter filter = new CacheControlFilter(max.value());
		ctx.register(filter);
	}
}