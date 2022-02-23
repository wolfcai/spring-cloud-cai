package com.cai.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 配置授权服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private TokenStore tokenStore;

	@Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
	private ApprovalStore approvalStore;

	@Autowired
	ClientDetailsService clientDetailsService;

	/**
	 * 用来配置客户端的详细信息
	 * @param clients
	 * @throws Exception
	 */
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //添加客户端信息
        //使用内存存储OAuth客户端信息
        clients.inMemory()
                // client_id
                .withClient("client")
                // client_secret
                .secret((new BCryptPasswordEncoder().encode("123")))
                // 该client允许的授权类型，不同的类型，则获得token的方式不一样。
//                .authorizedGrantTypes("authorization_code","implicit","refresh_token")
				.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
				.resourceIds("resourceId")
                //回调uri，在authorization_code与implicit授权方式时，用以接收服务器的返回信息
                .redirectUris("https://www.baidu.com")
                // 允许的授权范围
                .scopes("app","test");
    }

	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authorizationCodeServices(authorizationCodeServices())
				.tokenServices(tokenServices()).authenticationManager(authenticationManager);
    }


	/**
	 * 用来配置令牌端点的安全约束，也就是这个端点谁能访问，谁不能访问。
	 * checkTokenAccess 是指一个 Token 校验的端点，
	 * 这个端点我们设置为可以直接访问（在后面，当资源服务器收到 Token
	 * 之后，需要去校验 Token 的合法性，就会访问这个端点）
	 * @param security
	 * @throws Exception
	 */
	@Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("permitAll()")
				.allowFormAuthenticationForClients();
    }

	/**
	 * token保存策略
	 * @return
	 */
	@Bean
	public TokenStore tokenStore() {
		//token保存在内存中（也可以保存在数据库、Redis中）。
		//如果保存在中间件（数据库、Redis），那么资源服务器与认证服务器可以不在同一个工程中。
		//注意：如果不保存access_token，则没法通过access_token取得用户信息
		return new InMemoryTokenStore();
	}

	/**
	 *  这个 Bean 主要用来配置 Token 的一些基本信息，
	 *  例如 Token 是否支持刷新、Token 的存储位置、Token 的有效期以及刷新
	 *  Token 的有效期等等。Token 有效期这个好理解，刷新 Token 的有效期我说一下，
	 *  当 Token 快要过期的时候，我们需要获取一个新的 Token，在获取新的 Token 时候，
	 *  需要有一个凭证信息，这个凭证信息不是旧的 Token，而是另外一个 refresh_token，
	 *  这个 refresh_token 也是有有效期的
	 * @return
	 */
	@Bean
	AuthorizationServerTokenServices tokenServices() {
		DefaultTokenServices services = new DefaultTokenServices();
		services.setClientDetailsService(clientDetailsService);
		services.setSupportRefreshToken(true);
		services.setTokenStore(tokenStore);
		services.setAccessTokenValiditySeconds(60 * 60 * 2);
		services.setRefreshTokenValiditySeconds(60 * 60 * 24 * 3);
		return services;
	}

	/**
	 * 授权信息保存策略
	 * @return
	 */
	@Bean
	public ApprovalStore approvalStore() {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}

	/**
	 * 用来配置授权码的存储
	 * @return
	 */
	@Bean
	AuthorizationCodeServices authorizationCodeServices() {
		return new InMemoryAuthorizationCodeServices();
	}
}
