package com.vizaco.onlinecontrol.controller.authorization;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class FacebookController {

	private RestOperations facebookRestTemplate;

	@RequestMapping("/registration/facebook")
	public String photos(Model model) throws Exception {

		AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
		details.setId("facebook");
		details.setClientId("779752082073743");
		details.setClientSecret("fb18b46825cec0cdbf1095047de60163");
		details.setAccessTokenUri("https://graph.facebook.com/oauth/access_token");
		details.setUserAuthorizationUri("https://www.facebook.com/dialog/oauth");
		details.setTokenName("oauth_token");
		details.setAuthenticationScheme(AuthenticationScheme.query);
		details.setClientAuthenticationScheme(AuthenticationScheme.form);
		details.setPreEstablishedRedirectUri("http://localhost:10201/callback/facebook/registration");

		DefaultAccessTokenRequest accessTokenRequest = new DefaultAccessTokenRequest();
		accessTokenRequest.set("state", "registration");
		accessTokenRequest.set("scope", "email,user_location");
		accessTokenRequest.setCurrentUri("http://localhost:10201/onlinecontrol/callback/facebook/registration");
		accessTokenRequest.setPreservedState("registration");

		DefaultOAuth2ClientContext context = new DefaultOAuth2ClientContext(accessTokenRequest);
		context.setPreservedState("registration", "state");
//		DefaultOAuth2ClientContext context = new DefaultOAuth2ClientContext();
//		context.setAccessToken(new DefaultOAuth2AccessToken("https://graph.facebook.com/oauth/access_token"));

		OAuth2RestTemplate template = new OAuth2RestTemplate(details, context);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,	MediaType.valueOf("text/javascript")));
		template.setMessageConverters(Arrays.<HttpMessageConverter<?>> asList(converter));

		this.facebookRestTemplate = template;

		ObjectNode result = facebookRestTemplate.getForObject("https://graph.facebook.com/me", ObjectNode.class);
		ArrayNode data = (ArrayNode) result.get("data");
		ArrayList<String> friends = new ArrayList<String>();
		for (JsonNode dataNode : data) {
			friends.add(dataNode.get("name").getTextValue());
		}
		model.addAttribute("friends", friends);
		return "facebook";
	}

	public void setFacebookRestTemplate(RestOperations facebookRestTemplate) {
		this.facebookRestTemplate = facebookRestTemplate;
	}

}
