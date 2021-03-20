package com.rsapp.mvvm.model;

import java.util.Date;

/**
 * class to store access token data
 * 
 * @author eromu_000
 *
 */
public class RsTokenInfo {
	private String access_token;
	private String id_token;
	private String token_type;

	private String resource;
	private String scope;
	private String profile_inf;

	private int id_token_expires_in;
	private int expires_in;
	private Date not_before;
	private Date expires_on;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getId_token() {
		return id_token;
	}

	public void setId_token(String id_token) {
		this.id_token = id_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getProfile_inf() {
		return profile_inf;
	}

	public void setProfile_inf(String profile_inf) {
		this.profile_inf = profile_inf;
	}

	public int getId_token_expires_in() {
		return id_token_expires_in;
	}

	public void setId_token_expires_in(int id_token_expires_in) {
		this.id_token_expires_in = id_token_expires_in;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public Date getNot_before() {
		return not_before;
	}

	public void setNot_before(Date not_before) {
		this.not_before = not_before;
	}

	public Date getExpires_on() {
		return expires_on;
	}

	public void setExpires_on(Date expires_on) {
		this.expires_on = expires_on;
	}

}
