package ressources;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;
import couchedepersistance.User;


@Provider
public class LoginFilter implements ContainerRequestFilter{
	
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "secured";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if(authHeader!= null && authHeader.size()>0) {
				String authToken = authHeader.get(0);
				System.out.println(authToken);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				System.out.println(authToken);
				String decodedString = new String(Base64.decode(authToken.getBytes()));;
				System.out.println(decodedString);
				StringTokenizer tokenizer = new StringTokenizer(decodedString,":");
				String username = tokenizer.nextToken();
				System.out.println(username);
				String password = tokenizer.nextToken();
				
				
				//if("user".equals(username) && "password".equals(password)) {
				if(userExist(username,password)) {
					return;
				}
			}
		
			Response unauthorizedStatus = Response
												.status(Response.Status.UNAUTHORIZED)
												.entity("user cannot access the resource.")
												.build();
			requestContext.abortWith(unauthorizedStatus);
		
		}
	}
	public boolean userExist(String username, String password) {
		User user = UserRessource.getCorrespondantUser(username, password);
		if (user == null) {
		return false;
		}
		else {
			return true;
		}
	}
}	