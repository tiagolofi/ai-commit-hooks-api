package com.github.tiagolofi.filtros;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class FiltroToken implements ContainerRequestFilter {
    
    @Override
    public void filter(ContainerRequestContext requestContext) {

    }

}
