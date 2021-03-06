package nz.ac.auckland.cer.account.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class Util {

    private Logger log = Logger.getLogger(Util.class.getName());
    
    public String createAccountName(String eppn, String fullName) {
        String accountName = "N/A";
        if (eppn == null || eppn.length() == 0) {
            log.warn("eppn is null or empty");
            if (fullName == null || fullName.length() == 0) {
                log.warn("fullName is null or empty");
            } else {
                accountName = fullName.trim().replaceAll(" +", " ").replaceAll(" ",  ".").toLowerCase();
            }
        } else {
            if (eppn.toLowerCase().contains("@auckland.ac.nz")) {
                String[] tokens = eppn.split("@");
                if (tokens == null || tokens.length == 0) {
                    log.warn("Strange eppn: " + eppn);
                } else {
                    accountName = tokens[0];                    
                }
            } else {
                if (fullName == null || fullName.length() == 0) {
                    log.warn("fullName is null or empty");
                } else {
                    accountName = fullName.trim().replaceAll(" +", " ").replaceAll(" ",  ".").toLowerCase();
                }
            }
        }
        return accountName;
    }

    public String createAuditLogMessage(
            HttpServletRequest req,
            String message) {

        StringBuffer msg = new StringBuffer();
        msg.append("method=").append(req.getMethod()).append(" ").append("path=").append(req.getPathInfo());
        if (req.getQueryString() != null) {
            msg.append("?").append(req.getQueryString());
        }
        msg.append(" msg='").append(message).append("'");
        return msg.toString();
    }
}
