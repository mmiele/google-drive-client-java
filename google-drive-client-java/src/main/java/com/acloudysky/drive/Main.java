/** 
 * LEGAL: Use and Disclaimer. 
 * This software belongs to the owner of the http://www.acloudysky.com site and supports the
 * examples described there. 
 * Unless required by applicable law or agreed to in writing, this software is distributed on 
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. 
 * Please, use the software accordingly and provide the proper acknowledgement to the author.
 * @author milexm@gmail.com  
 **/
package com.acloudysky.drive;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;


/***
 * Contains the main entry for the console application. 
 * @author Michael
 *
 */
public class Main {

	 
	/***
	 * Main entry point which displays the start greetings.
	 * Most importantly, it delegates to the SimpleUI class the display of the selection menu 
	 * and the processing of the user's input. 
	 * @see SimpleUI#SimpleUI()
	 * @param args; 
	 *  args[0] = "Michael"
	 *  args[1] = "Google Drive Console Application"
	 * 
	 */
	public static void main(String[] args) {
		
		String name = null, topic = null;
		
		// Read input parameters.
		try {
				name = args[0];
				topic = args[1];
		}
		catch (Exception e) {
			System.out.println("IO error trying to read application input! Assigning default values.");
			// Assign default values if none are passed.
			if (args.length==0) {
				name = "Michael";
				topic = "Google Drive Console Application";
			}
			else {
				System.out.println("IO error trying to read application input!");
				System.exit(1); 
			}
		}
		
		String startGreetings = String.format("Hello %s let's start %s", name, topic);
		System.out.println(startGreetings);	
		
		
		
		// Get authorized service client using default scope.
		Drive driveServiceClient = 
				OAuthUtilities.getAuthorizedServiceClient(DriveScopes.DRIVE);
		
		if (driveServiceClient != null) {
			/*
			 *  Instantiate the DefaultSettings class.
			 *	We assume that in the user home directory a parent directory exists called ".store".
			 * 	It contains a child directory called "drive_sample", which in turn contains a file 
			 *	"sample_settings.json". This file contains the applications default settings.
			 */
			DefaultSettings defaults = new DefaultSettings(".store", "drive_sample", "sample_settings.json");
			
			// Read current application default values.
			defaults.readSettings();
			
			// Initialize operations classes.
			FileOperations.initFileOperations(driveServiceClient);
			
			// Initialize simple UI and display menu.
			SimpleUI sui = new SimpleUI();
			
					
			// Process user's input.
			sui.processUserInput();
		}
		else 
			String.format("Error %s", "service object is null.");
	}
	
}

