package com.example.reactive.reactiveserver.config;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 10/30/2019.
 */
@Component
public class CorsFilter implements WebFilter
{
	@Override
	public Mono<Void> filter( final ServerWebExchange serverWebExchange, final WebFilterChain webFilterChain )
	{
		serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
		serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
		serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
		if (serverWebExchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
			serverWebExchange.getResponse().getHeaders().add("Access-Control-Max-Age", "1728000");
			serverWebExchange.getResponse().setStatusCode( HttpStatus.NO_CONTENT);
			return Mono.empty();
		} else {
			serverWebExchange.getResponse().getHeaders().add("Access-Control-Expose-Headers", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
			return webFilterChain.filter(serverWebExchange);
		}
	}
}
