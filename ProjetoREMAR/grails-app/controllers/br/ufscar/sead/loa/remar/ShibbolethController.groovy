package br.ufscar.sead.loa.remar

class ShibbolethController {

    def connect() {

    	/* Shibboleth attributes available:
            "Shib-brEduPerson-brEduAffiliationType", -- userType : [student, teacher, employee, ...];
            "Shib-eduPerson-eduPersonAffiliation",   -- userType custom identifier
            "Shib-eduPerson-eduPersonPrincipalName", -- UNIQUE name identifier for user within the federation; Used as username;
            "Shib-inetOrgPerson-cn",			     -- user complete name
            "Shib-inetOrgPerson-mail",				 -- user email
            "Shib-inetOrgPerson-sn",				 -- user surname
            "Shib-Application-ID",
            "Shib-Session-ID",
            "Shib-Identity-Provider",				 -- Authorizing IDP identifier
            "Shib-Authentication-Instant",           -- Authentication timestamp
            "Shib-Authentication-Method",            -- Auth method
            "Shib-AuthnContext-Class",
            "Shib-Session-Index",					 -- unique session identifier
        */

        def u = User.findByLogin(request.getAttribute("Shib-eduPerson-eduPersonPrincipalName"))

        if (u) {
        	session.user = u;
        	render view: "success", model: [user: u]
        } else{
        	u = new User(
        		username: request.getAttribute("Shib-eduPerson-eduPersonPrincipalName"),
                password: request.getAttribute("Shib-Session-ID"),
                email: request.getAttribute("Shib-inetOrgPerson-mail"),
                firstName: request.getAttribute("Shib-inetOrgPerson-cn"),
                lastName: request.getAttribute("Shib-inetOrgPerson-sn"),
                firsAccess: true,
                enabled: true
            )

            if (u.save(flush: true)) {
            	session.user = u;
            	render view: "success", model: [user: u]
            } else {
            	respond status: 500
            }
        }

    }
}
