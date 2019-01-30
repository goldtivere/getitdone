/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.register;

import BackGroundManager.MessageModel;
import com.git.core.ApiConnection;
import com.git.core.Transactions;
import com.git.dbcon.AESencrp;
import com.git.dbcon.DateManipulation;
import com.git.dbcon.DbConnectionX;
import com.git.dbcon.LoadPPTfile;
import com.git.getitdone.XMLCreator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

/**
 *
 * @author Gold
 */
@ManagedBean
@SessionScoped
public class Registration implements Serializable {

    private boolean firstPanel;
    private boolean secondPanel;
    private boolean thirdPanel;
    private String logUsername;
    private String logPassword;
    private String fname;
    private String lname;
    private String pnum;
    private String verCode;
    private String pwd;
    private UserDetails dto = new UserDetails();

    @PostConstruct
    public void init() {
        setFirstPanel(true);
        setSecondPanel(false);
        setThirdPanel(false);
    }

    public void tempPhone() {

    }

    public void getIn() {
        setThirdPanel(true);
        setFirstPanel(false);
        setSecondPanel(false);
    }

    public static String userIp() {
        HttpServletRequest request = new HttpServletRequest() {
            @Override
            public String getAuthType() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Cookie[] getCookies() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public long getDateHeader(String name) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getHeader(String name) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Enumeration<String> getHeaders(String name) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Enumeration<String> getHeaderNames() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getIntHeader(String name) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getMethod() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getPathInfo() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getPathTranslated() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getContextPath() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getQueryString() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getRemoteUser() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isUserInRole(String role) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Principal getUserPrincipal() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getRequestedSessionId() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getRequestURI() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public StringBuffer getRequestURL() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getServletPath() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public HttpSession getSession(boolean create) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public HttpSession getSession() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String changeSessionId() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isRequestedSessionIdValid() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isRequestedSessionIdFromCookie() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isRequestedSessionIdFromURL() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isRequestedSessionIdFromUrl() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void login(String username, String password) throws ServletException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void logout() throws ServletException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Collection<Part> getParts() throws IOException, ServletException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Part getPart(String name) throws IOException, ServletException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Object getAttribute(String name) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getCharacterEncoding() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getContentLength() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public long getContentLengthLong() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getContentType() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public ServletInputStream getInputStream() throws IOException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getParameter(String name) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Enumeration<String> getParameterNames() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String[] getParameterValues(String name) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getProtocol() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getScheme() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getServerName() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getServerPort() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public BufferedReader getReader() throws IOException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getRemoteAddr() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getRemoteHost() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setAttribute(String name, Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removeAttribute(String name) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Locale getLocale() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Enumeration<Locale> getLocales() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isSecure() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public RequestDispatcher getRequestDispatcher(String path) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getRealPath(String path) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getRemotePort() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getLocalName() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getLocalAddr() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getLocalPort() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public ServletContext getServletContext() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public AsyncContext startAsync() throws IllegalStateException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isAsyncStarted() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isAsyncSupported() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public AsyncContext getAsyncContext() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public DispatcherType getDispatcherType() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        String ipAddress = "";

        if (request != null) {
            ipAddress = request.getHeader("X-FORWARD-FOR");
            if (ipAddress == null || "".equals(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
        }
        return ipAddress;
    }

    public void userIpPopulate() {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dbConnections.mySqlDBconnection();
            String insert = "insert into tbusertable (userip,dateloggedin,dateloggedtime)"
                    + "values(?,?,?)";
            pstmt = con.prepareStatement(insert);

            pstmt.setString(1, userIp());
            pstmt.setString(2, DateManipulation.dateAlone());
            pstmt.setString(3, DateManipulation.dateAndTime());

            pstmt.executeUpdate();
            System.out.println("done boy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean submit() {
        try {
            Transactions trxn = new Transactions();
            LoadPPTfile loadfile = new LoadPPTfile();
            String gRecap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
            //boolean verify = VerifyRecaptcha.verify(gRecap);

            JSONObject bn = trxn.chargeCaptcha(gRecap, loadfile.siteSecret(), "https://www.google.com/recaptcha/api/siteverify");

            boolean a = bn.getBoolean("success");
            if (a) {

                return true;
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Select Captcha"));
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//takes you back to registration page by making panels true or false
    public String backtoRegister() {
        setFirstPanel(true);
        setSecondPanel(false);
        setThirdPanel(false);
        return "/index.xhtml?faces-redirect=true";

    }

    //login code
    public void login() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            con = dbConnections.mySqlDBconnection();

            String queryProfile = "select * from tbregistration "
                    + "where phonenumber=? and Password=? and isdeleted=?";

            //encrypt the password entered and then compared with what you have in the DB
            String doEncPwd = AESencrp.encrypt(getLogPassword());
            //

            pstmt = con.prepareStatement(queryProfile);
            pstmt.setString(1, getLogUsername());
            pstmt.setString(2, doEncPwd);
            pstmt.setBoolean(3, false);

            //System.out.println(getUsername() + "," + doEncPwd  + "<>" + getPassword() );
            //pstmt.setString(2, "ok");
            rs = pstmt.executeQuery();

            if (rs.next()) {
                 userIpPopulate();
                dto.setId(rs.getInt("id"));
                dto.setFname(rs.getString("firstname"));
                dto.setLname(rs.getString("lastname"));
                dto.setFullname(rs.getString("fullname"));
                dto.setPnum(rs.getString("phonenumber"));

                context.getExternalContext().getSessionMap().put("sessn_nums", getDto());

                NavigationHandler nav = context.getApplication().getNavigationHandler();

                String url_ = "/pages/home/homepage.xhtml?faces-redirect=true";
                nav.handleNavigation(context, null, url_);
                context.renderResponse();
               
            } else {

                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid login details", "Invalid login details"));

                //System.out.println("Failed.");
            }
        } catch (NullPointerException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage().toString(), "System unavailable111, please try again later."));
        } catch (Exception e) {

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage().toString(), "System unavailable, please try again later."));
            e.printStackTrace();

        } finally {

            if (!(con == null)) {
                con.close();
                con = null;
            }
            if (!(pstmt == null)) {
                pstmt.close();
                pstmt = null;
            }

        }

    }

    public void noactivity(ActionEvent evt) {
        getLogout();
    }

    public boolean getLogout() {

        FacesContext context = FacesContext.getCurrentInstance();

        try {

            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();

            // pageMover.setValue01("page1.xhtml");
            //context.getExternalContext().getSessionMap().clear();
            NavigationHandler nav = context.getApplication().getNavigationHandler();

            nav.handleNavigation(context, null, "/index.xhtml?faces-redirect=true");
            //nav.handleNavigation(context, null, "/../../login.xhtml?faces-redirect=true");

            context.renderResponse();

            if (context.getExternalContext().getSessionMap().isEmpty()) {

                //System.out.println("Why:" + dto.getUsername());
                return true;

            } else {

                context.addMessage(null, new FacesMessage("App. cannot close at this time,try later."));
                //System.out.println("Why:" + dto.getUsername());
                return false;
            }

        } catch (Exception e) {

            context.addMessage(null, new FacesMessage("System Unavailable."));
            e.printStackTrace();
            return false;

        }

    }//end getLogout
    //check if verCodeExist

    public boolean checkIfVerExists() {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = dbConnections.mySqlDBconnection();
        try {
            String queryProfile = "select * from tbtempregistration "
                    + "where verificationcode=? and phonenumber=? and phoneverified=? order by createdon desc limit 1";
            pstmt = con.prepareStatement(queryProfile);
            pstmt.setString(1, getVerCode());
            pstmt.setString(2, getPnum());
            pstmt.setBoolean(3, false);
            rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean checkIfNumExists() {
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = dbConnections.mySqlDBconnection();
        try {
            String queryProfile = "select * from tbtempregistration "
                    + "where phonenumber=? and phoneverified=?";
            pstmt = con.prepareStatement(queryProfile);
            pstmt.setString(1, getPnum());
            pstmt.setBoolean(2, true);
            rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void register() {
        FacesContext context = FacesContext.getCurrentInstance();
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dbConnections.mySqlDBconnection();

            String pass = AESencrp.encrypt(getPwd());
            if (checkIfVerExists()) {
                String insert = "insert into tbregistration (firstname,lastname,fullname,phonenumber,password,datecreated,isdeleted)"
                        + "values(?,?,?,?,?,?,?)";
                pstmt = con.prepareStatement(insert);

                pstmt.setString(1, getFname());
                pstmt.setString(2, getLname());
                pstmt.setString(3, getLname() + " " + getFname());
                pstmt.setString(4, getPnum());
                pstmt.setString(5, pass);
                pstmt.setString(6, DateManipulation.dateAndTime());
                pstmt.setBoolean(7, false);
                pstmt.executeUpdate();

                String update = "update tbtempregistration set phoneverified=? where phonenumber=? and verificationcode=? order by createdon desc limit 1";
                pstmt = con.prepareStatement(update);

                pstmt.setBoolean(1, true);
                pstmt.setString(2, getPnum());
                pstmt.setString(3, getVerCode());
                pstmt.executeUpdate();

                NavigationHandler nav = context.getApplication().getNavigationHandler();

                String url_ = "/pages/success/index.xhtml?faces-redirect=true";
                nav.handleNavigation(context, null, url_);
                context.renderResponse();

            } else {
                context.addMessage(null, new FacesMessage("incorrect verification code!!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //this method generates validation code for the phonenumber
    public String generateRandom() {
        Random rnd = new Random();
        int a = 100000 + rnd.nextInt(900000);

        return String.valueOf(a);
    }

    //returns array string of word equivalent
    public static String[] values() {
        String values[] = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        return values;

    }

    // returns word equivalent of code generated
    public String wordEquivalent(String random) {
        String[] p = random.split("(?!^)");
        StringBuilder sdp = new StringBuilder();

        for (String n : p) {
            if (n.equalsIgnoreCase("0")) {
                sdp.append(values()[0]).append(" ");
            } else if (n.equalsIgnoreCase("1")) {
                sdp.append(values()[1]).append(" ");
            } else if (n.equalsIgnoreCase("2")) {
                sdp.append(values()[2]).append(" ");
            } else if (n.equalsIgnoreCase("3")) {
                sdp.append(values()[3]).append(" ");
            } else if (n.equalsIgnoreCase("4")) {
                sdp.append(values()[4]).append(" ");
            } else if (n.equalsIgnoreCase("5")) {
                sdp.append(values()[5]).append(" ");
            } else if (n.equalsIgnoreCase("6")) {
                sdp.append(values()[6]).append(" ");
            } else if (n.equalsIgnoreCase("7")) {
                sdp.append(values()[7]).append(" ");
            } else if (n.equalsIgnoreCase("8")) {
                sdp.append(values()[8]).append(" ");
            } else if (n.equalsIgnoreCase("9")) {
                sdp.append(values()[9]).append(" ");
            }

            System.out.println(n + " Hippee");
        }
        return sdp.toString();
    }

    public void nextPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        DbConnectionX dbConnections = new DbConnectionX();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        XMLCreator xmlcr = new XMLCreator();
        con = dbConnections.mySqlDBconnection();
        LoadPPTfile loadPPT = new LoadPPTfile();
        try {

            if (checkIfNumExists()) {
                context.addMessage(null, new FacesMessage("Phone number aready registered!!"));
            } else if (submit()) {
                System.out.println("Hello Gold");
                String lum = generateRandom();
                String num = lum + "voice.xml";
                String filename = loadPPT.xmlFolder() + num;
                String xmlPath = loadPPT.xmlPath() + num;
                String verificationMessage = " Your verification code is: " + wordEquivalent(lum) + ". Thank you.";
                System.out.println(loadPPT.xmlPath() + num + " done***");
                xmlcr.xmlCreate(verificationMessage, loadPPT.xmlPath() + num);
                String insertemail = "insert into tbtempregistration (phonenumber,verified,verificationcode,verificationxml,filename,phoneverified,createdon,xmlfilename)"
                        + "values(?,?,?,?,?,?,?,?)";
                pstmt = con.prepareStatement(insertemail);

                pstmt.setString(1, getPnum());
                pstmt.setBoolean(2, false);
                pstmt.setString(3, generateRandom());
                pstmt.setString(4, verificationMessage);
                pstmt.setString(5, filename);
                pstmt.setBoolean(6, false);
                pstmt.setString(7, DateManipulation.dateAndTime());
                pstmt.setString(8, xmlPath);
                pstmt.executeUpdate();

                setFirstPanel(false);
                setSecondPanel(true);
                System.out.println("This is it: " + generateRandom());
            } else {
                context.addMessage(null, new FacesMessage("Something went wrong!!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testResult() {
        Connection conn = null;

        String url = "jdbc:mysql://localhost:3306/bookings";
        //String dbName = "bookings";
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt;
        String driver = "com.mysql.jdbc.Driver";
        String databaseUserName = "root";
        String databasePassword = "Caroline93*";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, databaseUserName, "Caroline93*");
            System.out.println("this " + conn);

            String querySMSDetails = "select * from tbtempregistration "
                    + "where verified=?";
            //
            pstmt = conn.prepareStatement(querySMSDetails);
            pstmt.setBoolean(1, false);
            rs = pstmt.executeQuery();

            //
            String _val = null;

            while (rs.next()) {
                System.out.println(rs.getString("verificationcode") + "  ok");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isFirstPanel() {
        return firstPanel;
    }

    public void setFirstPanel(boolean firstPanel) {
        this.firstPanel = firstPanel;
    }

    public boolean isSecondPanel() {
        return secondPanel;
    }

    public void setSecondPanel(boolean secondPanel) {
        this.secondPanel = secondPanel;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isThirdPanel() {
        return thirdPanel;
    }

    public void setThirdPanel(boolean thirdPanel) {
        this.thirdPanel = thirdPanel;
    }

    public String getLogUsername() {
        return logUsername;
    }

    public void setLogUsername(String logUsername) {
        this.logUsername = logUsername;
    }

    public String getLogPassword() {
        return logPassword;
    }

    public void setLogPassword(String logPassword) {
        this.logPassword = logPassword;
    }

    public UserDetails getDto() {
        return dto;
    }

    public void setDto(UserDetails dto) {
        this.dto = dto;
    }

}
