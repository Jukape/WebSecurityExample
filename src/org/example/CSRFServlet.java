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
public class CSRFServlet extends HttpServlet {
    public void service(HttpServletRequest req,
                        HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String csrfMode = "insecure";
        String retrievalMethod = "";

        // Retrieve the mode we are operating in
        if (req.getParameter("csrf_mode") != null)
            csrfMode = req.getParameter("csrf_mode");

        if (req.getParameter("method") != null)
            retrievalMethod = req.getParameter("method");


        String title = "<html><head><title>%s!</title></head><body>";

        ArrayList<String> htmlOutput = new ArrayList<>();

        if (retrievalMethod.isEmpty()) {
            switch (csrfMode) {
                case "insecure":
                default:
                    htmlOutput.add(String.format(title, "Insecure XSS"));
                    htmlOutput.add("<p>This is an insecure page!</p>");
                    htmlOutput.add("<img src='csrf?method=get&csrf_mode=insecure'/>");

                    break;
                case "secure":
                    htmlOutput.add(String.format(title, "Secure XSS"));
                    htmlOutput.add("<p>This is a page secured against CSRF using referer checking");
                    htmlOutput.add("<img src='csrf?method=get&csrf_mode=secure'/>");
                    break;
            }
        } else {
            switch (csrfMode) {
                case "insecure":
                default:
                    htmlOutput.add("<p>Hacked!</p>");
                    break;
                case "secure":
                    String referrer = req.getHeader("referer");
                    if (referrer.startsWith("http://localhost:8080"))
                        htmlOutput.add("<p>Secure</p>");
                    else
                        htmlOutput.add("<p>Hacked!</p>");
                    break;
            }
        }

        htmlOutput.add("</body></html>");

        for (String outString : htmlOutput) {
            out.println(outString);
        }
    }
}
