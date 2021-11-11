package net.romusoft.rsapp.model;

import java.util.Date;

/**
 * class to store access token data
 * 
 * @author Emmanuel Romulus
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

	/***************************************************************************************************************/

	/**
	 * the access token
	 * 
	 * @return the access token
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * the access token
	 * 
	 * @param access_token the access token
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * the id token that has the user information
	 * 
	 * @return the id token that has the user information
	 */
	public String getId_token() {
		return id_token;
	}

	/**
	 * the id token that has the user information
	 * 
	 * @param id_token the id token that has the user information
	 */
	public void setId_token(String id_token) {
		this.id_token = id_token;
	}

	/**
	 * the token type - access, refresh token
	 * 
	 * @return the token type
	 */
	public String getToken_type() {
		return token_type;
	}

	/**
	 * the token type
	 * 
	 * @param token_type the token type
	 */
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	/**
	 * resource
	 * 
	 * @return resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * resource
	 * 
	 * @param resource resource
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	/**
	 * scope - open id, etc.
	 * 
	 * @return scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * scope
	 * 
	 * @param scope scope
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * user profile info
	 * 
	 * @return user profile info
	 */
	public String getProfile_inf() {
		return profile_inf;
	}

	/**
	 * user profile info
	 * 
	 * @param profile_inf user profile info
	 */
	public void setProfile_inf(String profile_inf) {
		this.profile_inf = profile_inf;
	}

	/**
	 * milliseconds for token expiration
	 * 
	 * @return milliseconds for token expiration
	 */
	public int getId_token_expires_in() {
		return id_token_expires_in;
	}

	/**
	 * milliseconds for token expiration
	 * 
	 * @param id_token_expires_in milliseconds for token expiration
	 */
	public void setId_token_expires_in(int id_token_expires_in) {
		this.id_token_expires_in = id_token_expires_in;
	}

	/**
	 * milliseconds for token expiration
	 * 
	 * @return milliseconds for token expiration
	 */
	public int getExpires_in() {
		return expires_in;
	}

	/**
	 * milliseconds for token expiration
	 * 
	 * @param expires_in milliseconds for token expiration
	 */
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	/**
	 * date before which token may expire
	 * 
	 * @return date before which token may expire
	 */
	public Date getNot_before() {
		return not_before;
	}

	/**
	 * date before which token may expire
	 * 
	 * @param not_before date before which token may expire
	 */
	public void setNot_before(Date not_before) {
		this.not_before = not_before;
	}

	/**
	 * Date token expires on
	 * 
	 * @return Date token expires on
	 */
	public Date getExpires_on() {
		return expires_on;
	}

	/**
	 * Date token expires on
	 * 
	 * @param expires_on Date token expires on
	 */
	public void setExpires_on(Date expires_on) {
		this.expires_on = expires_on;
	}

}
