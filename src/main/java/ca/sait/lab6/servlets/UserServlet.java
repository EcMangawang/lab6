package ca.sait.lab6.servlets;

import ca.sait.lab6.models.Role;
import ca.sait.lab6.models.User;
import ca.sait.lab6.services.RoleService;
import ca.sait.lab6.services.UserService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author emmari
 */
public class UserServlet extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
        UserService uservice = new UserService();
        List<User> users = uservice.getAll();
        request.setAttribute("users", users);
            
        RoleService rs = new RoleService();
        List<Role> roles = rs.getAll();
        request.setAttribute("roles", roles);
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        request.setAttribute("message", "");
       
                String email = request.getParameter("email");
                boolean active = request.getParameter("active") != null;
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                String roleName = request.getParameter("role");
                int roleId = 0;
                
         if (action != null && action.equals("add")) {
            
            try {
                RoleService roleService = new RoleService();
                List<Role> roleList;
                roleList = roleService.getAll();

                for (Role role : roleList) {
                    if (role.getName().equals(roleName)) {
                        roleId = role.getId();
                    }
                }

                if (roleId == 0) {
                    throw new Exception("Invalid role");
                }

                Role role = new Role(roleId, roleName);
                UserService userService = new UserService();
                
                userService.insert(email, active, firstName, lastName, password, role);
                request.setAttribute("message", "New user added");

            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "Failed to add new user, please ensure all fields are completed");
            }
        
        } else if (action != null && action.contains("edit?")) {
            try {
                email = action.split("\\?", 2)[1];
                UserService userService = new UserService();
                User user = userService.get(email);
                request.setAttribute("user", user);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }  
        } else if (action != null && action.equals("edit")) {
//Edit User
            try {
                RoleService roleService = new RoleService();
                List<Role> roleList;
                roleList = roleService.getAll();

                for (Role role : roleList) {
                    if (role.getName().equals(roleName)) {
                        roleId = role.getId();
                    }
                }

                if (roleId == 0) {
                    throw new Exception("Invalid role");
                }

                Role role = new Role(roleId, roleName);
                UserService userService = new UserService();
                userService.update(email, active, firstName, lastName, password, role);
                request.setAttribute("message", "User updated");

            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "Failed to update user, please ensure all fields are completed.");
            }
            
        } else if (action != null && action.contains("delete?")) {
//Delete User
            try {
                email = action.split("\\?", 2)[1];
                UserService userService = new UserService();
                userService.delete(email);
                request.setAttribute("message", "User deleted");
                
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        try {
            UserService userService = new UserService();
            List<User> users = userService.getAll();
            RoleService roleService = new RoleService();
            List<Role> roles = roleService.getAll();
            
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}
