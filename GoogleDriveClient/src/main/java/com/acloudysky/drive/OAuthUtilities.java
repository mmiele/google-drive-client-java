package com.acloudysky.drive;

import com.acloudysky.oauth.AuthorizeServiceClient;

import com.google.api.services.drive.Drive;

/**
 * This class contains utility methods to handle authorization tasks.
 * @author milexm@gmail.com
 *
 */
public class OAuthUtilities {
	
	/***
	 * Authorizes the application to use the Drive API. 
	 * <p><b>Note</b>. The method assumes that you already have created a directory to 
	 * store the file with the client secrets. The directory is .store/drive_sample. 
	 * The file containing the secrets is client_secrets.json.
	 * @param serviceScope - The scope for which to obtain the authorization.
	 * The values for the scope can be found at 
	 * <a href="https://developers.google.com/resources/api-libraries/documentation/drive/v2/java/latest/com/google/api/services/drive/DriveScopes.html" target="_blank">Class DriveScopes</a>.
	 * @return The object that represents the authorized service.
	 */
	public static Drive getAuthorizedServiceClient(String serviceScope) {
		
		Drive driveServiceClient = null;
				
		try {
				// Instantiate the AuthorizeServiceClient class.
				AuthorizeServiceClient serviceClient = 
				new AuthorizeServiceClient(".store", "drive_sample", "client_secrets.json");
		
				// Get the authorized service client so the application can use the service  API.
				driveServiceClient = (Drive) serviceClient.getAuthorizedServiceClient("drive", serviceScope);		
		}
		catch (Exception e) {
			String.format("Error %s during service authorization.", e.toString());
		}
		return driveServiceClient;
		
	}

}
