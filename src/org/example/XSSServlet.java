package org.example;

import com.sun.xml.internal.ws.policy.PolicyException;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Servlet")
public class XSSServlet extends HttpServlet {
    public void service(HttpServletRequest req,
                        HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String xssMode = "insecure";
        String xssCode = "<script>alert('HACKED: this page is vulnerable to xss')</script>";

        // Retrieve the mode we are operating in
        if (req.getParameter("xss_mode") != null)
            xssMode = req.getParameter("xss_mode");

        //retrieve the xss_code parameter if it has been sent
        if (req.getParameter("xss_code") != null)
            xssCode = req.getParameter("xss_code");

        String title = "<html><head><title>%s!</title></head><body>";

        ArrayList<String> htmlOutput = new ArrayList<>();
        switch (xssMode) {
            case "insecure":
            default:
                htmlOutput.add(String.format(title, "Insecure XSS"));
                htmlOutput.add("<p>This is an insecure page!</p><p>Use the request parameter 'xss_code' to inject HTML/ javascript into this page</p>");
                htmlOutput.add(String.format("<div>%s</div>", xssCode));
                break;
            case "secure":
                htmlOutput.add(String.format(title, "Secure XSS"));
                htmlOutput.add("<p>This is a page secured against XSS using <a href='https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project' target='_blank'>AntiSamy</a></p>");
                htmlOutput.add("<p>The content of the 'xss_code' parameter is parsed using AntiSammy.scan('VALUE_TO_SCAN')");
                htmlOutput.add(String.format("<div>%s</div>", UserInputSanitizer.sanitize(xssCode)));
                break;
        }

        htmlOutput.add("</body></html>");

        for (String outString : htmlOutput) {
            out.println(outString);
        }
    }

    public static class UserInputSanitizer {

        private static Policy policy;
        private static AntiSamy antiSamy;

        private static AntiSamy getAntiSamy() throws PolicyException, org.owasp.validator.html.PolicyException {
            if (antiSamy == null) {
                policy = getPolicy("evocatus-default");
                antiSamy = new AntiSamy();
            }
            return antiSamy;

        }

        public static String sanitize(String input) {
            CleanResults cr;
            try {
                cr = getAntiSamy().scan(input, policy);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return cr.getCleanHTML();
        }

        private static Policy getPolicy(String name) throws PolicyException, org.owasp.validator.html.PolicyException {
            Policy policy =
                    Policy.getInstance(XSSServlet.class.getResourceAsStream("../../../../META-INF/antisamy/" + name + ".xml"));
            return policy;
        }

    }
}
