package com.algaworks.algamoney.api.config.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.algaworks.algamoney.api.security.UserSystem;

@SuppressWarnings("deprecation")
public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
	UserSystem UserSystem = (UserSystem) authentication.getPrincipal();
		
	Map<String, Object> addInfo = new HashMap<>();
	addInfo.put("name", UserSystem.getSystemUser().getName());
	
	((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
	return accessToken;
	}

}
