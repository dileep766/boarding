package com.ctl.activiti.login;



import java.util.Enumeration;
import java.util.Hashtable;
import javax.naming.*;
import javax.naming.ldap.*;
import javax.naming.directory.*;
 
public class memberof	{
	public static void main (String[] args)	{
	
		Hashtable env = new Hashtable();
		  String adminName = "uid=vrouteop,ou=People,dc=mnet,dc=qintra,dc=com";
	      String adminPassword = "Coolgear14";
	      String ldapURL = "ldaps://ldap.qintra.com:1636";
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		//set security credentials, note using simple cleartext authentication
		env.put(Context.SECURITY_AUTHENTICATION,"simple");
		env.put(Context.SECURITY_PRINCIPAL,adminName);
		env.put(Context.SECURITY_CREDENTIALS,adminPassword);
				
		//connect to my domain controller
		env.put(Context.PROVIDER_URL,ldapURL);
                //specify attributes to be returned in binary format
		env.put("java.naming.ldap.attributes.binary","tokenGroups");

		
		try {
 
			//Create the initial directory context
			LdapContext ctx = new InitialLdapContext(env,null);
		
			String userdn = "cn=users,dc=qintra,dc=com";
			SearchControls searchCtrls = new SearchControls();
			searchCtrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attributes = {"member","memberof"};
			searchCtrls.setReturningAttributes(attributes);
 
                        //Change the NameOfGroup for the group name you would like to retrieve the members of.
			String filter = "(&(objectCategory=group)(name=ctli_ebonding_dev_team))";
 
                       //use the context we created above and the filter to return all members of a group.
			 SearchControls cons = new SearchControls();
			    cons.setReturningAttributes(new String[] {"cn"});
			    cons.setSearchScope(SearchControls.ONELEVEL_SCOPE);

			    NamingEnumeration<SearchResult> answer = ctx.search("cn=users,dc=mnet,dc=com", "(objectcategory=group)", cons);
			    System.out.println("AD GROUPS:");
			    while(answer.hasMore()) {
			        SearchResult result = (SearchResult) answer.next();
			        Attributes atts = result.getAttributes();
			        Attribute att = atts.get("cn");
			        String groupName = (String)att.get();

			        //how to search for groups nested in this group
			    }
			
			ctx.close();
 
		} 
		
		catch (Exception e) {
			System.out.println("Problem searching directory: " + e);
           	}
	}
        	
	private LdapContext ldapAuthenticate(String password, String userdn) throws Exception
	{
		
		Hashtable<String, String> env = new Hashtable<String,String>();
	env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		//set security credentials, note using simple cleartext authentication
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, userdn);
		env.put(Context.SECURITY_CREDENTIALS, password);
		//connect to my domain controller
		//env.put(Context.PROVIDER_URL, activeServerLdapURL);
		//Create the initial directory context
		LdapContext ctx = null;
		try {
			ctx = new InitialLdapContext(env,null);
		} catch (AuthenticationException e) {
                        //You will get an exception here if the username/password is incorrect
                        //handle it yourself!
		} catch (Exception e) {
                       //something went wrong
                       ///handle in some way
		}
		return ctx;
	}     
       public static final String binarySidToStringSid( byte[] SID ) {
    	
    	String strSID = ""; 

                       //convert the SID into string format

                       long version;
                       long authority;
                       long count;
                       long rid;

                       strSID = "S";
                       version = SID[0];
                       strSID = strSID + "-" + Long.toString(version);
                       authority = SID[4];

                       for (int i = 0;i<4;i++) {
                               authority <<= 8;
                               authority += SID[4+i] & 0xFF;
                       }

                       strSID = strSID + "-" + Long.toString(authority);
                       count = SID[2];
                       count <<= 8;
                       count += SID[1] & 0xFF;

                       for (int j=0;j<count;j++) {

                               rid = SID[11 + (j*4)] & 0xFF;

                               for (int k=1;k<4;k++) {

                                      rid <<= 8;

                                      rid += SID[11-k + (j*4)] & 0xFF;

                               }

                               strSID = strSID + "-" + Long.toString(rid);

                       }
                       
                       return strSID;
    	
    }



}
