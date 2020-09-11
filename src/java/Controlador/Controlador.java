/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import config.GenerarSerie;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ProDesk 2019
 */
public class Controlador extends HttpServlet {
    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    Cliente c = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    Producto p = new Producto();
    ProductoDAO pdao = new ProductoDAO();

    int ide;
    int idc;
    int idp;
    int idv;

    Venta v = new Venta();
    List<Venta> lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cantidad;
    double subtotal;
    double totalPagar;

    String numeroserie;
    VentaDAO vdao = new VentaDAO();

    int cantidad1;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("Empleado")) {
            switch (accion) {
                case "Listar":
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTelefono");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUsuario");
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTelefono");
                    String est1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUsuario");
                    em.setDni(dni1);
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);
                    edao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        if (menu.equals("Cliente")) {
            switch (accion) {
                case "Listar":
                    List lista = cdao.listar();
                    request.setAttribute("clientes", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String dir = request.getParameter("txtDireccion");
                    String est = request.getParameter("txtEstado");
                    c.setDni(dni);
                    c.setNom(nom);
                    c.setDir(dir);
                    c.setEstado(est);
                    cdao.agregar(c);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    idc = Integer.parseInt(request.getParameter("id"));
                    Cliente cl = cdao.listarId(idc);
                    request.setAttribute("cliente", cl);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String dir1 = request.getParameter("txtDireccion");
                    String est1 = request.getParameter("txtEstado");
                    c.setDni(dni1);
                    c.setNom(nom1);
                    c.setDir(dir1);
                    c.setEstado(est1);
                    c.setId(idc);
                    cdao.actualizar(c);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    idc = Integer.parseInt(request.getParameter("id"));
                    cdao.delete(idc);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List lista = pdao.listar();
                    request.setAttribute("productos", lista);
                    break;
                case "Agregar":
                    String nom = request.getParameter("txtNom");
                    String precio = request.getParameter("txtPrecio");
                    String stock = request.getParameter("txtStock");
                    String est = request.getParameter("txtEstado");
                    p.setNom(nom);
                    p.setPrecio(Double.parseDouble(precio));
                    p.setStock(Integer.parseInt(stock));
                    p.setEstado(est);
                    pdao.agregar(p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    Producto pr = pdao.listarId(idp);
                    request.setAttribute("producto", pr);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String nom1 = request.getParameter("txtNom");
                    String precio1 = request.getParameter("txtPrecio");
                    String stock1 = request.getParameter("txtStock");
                    String est1 = request.getParameter("txtEstado");
                    p.setNom(nom1);
                    p.setPrecio(Double.parseDouble(precio1));
                    p.setStock(Integer.parseInt(stock1));
                    p.setEstado(est1);
                    p.setId(idp);
                    pdao.actualizar(p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(idp);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
        if (menu.equals("Inicio")) {
            switch (accion) {
                case "a":

                    break;
                default:
                    request.getRequestDispatcher("Inicio.jsp").forward(request, response);
            }
        }
        if (menu.equals("NuevaVenta")) {

            switch (accion) {
                case "BuscarCliente":
                    String dni = request.getParameter("codigocliente");
                    c.setDni(dni);
                    c = cdao.buscar(dni);
                    if(request.getParameter("codigoproducto").isEmpty()){
                        request.setAttribute("c", c);
                        request.setAttribute("lista", lista);
                        request.setAttribute("nserie", numeroserie);
                    }else{
                        request.setAttribute("c", c);
                        request.setAttribute("producto", p);
                    request.setAttribute("lista", lista);     
                    request.setAttribute("nserie", numeroserie);
                    }
                    
                    
                    break;
                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    p = pdao.listarId(id);
                    if(lista.size()<=0){
                        request.setAttribute("c", c);
                    request.setAttribute("producto", p);
                    request.setAttribute("lista", lista);
                    request.setAttribute("nserie", numeroserie);
                    }else{
                        request.setAttribute("c", c);
                    request.setAttribute("producto", p);
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("nserie", numeroserie);
                    }
                    
                    break;
                case "Agregar":
                    request.setAttribute("c", c);
                    totalPagar = 0.0;
                    item = item + 1;
                    cod = Integer.parseInt(request.getParameter("codigoproducto"));
                    descripcion = request.getParameter("nomproducto");
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cantidad = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio * cantidad;
                    v = new Venta();
                    v.setItem(item);
                    v.setIdproducto(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cantidad);
                    v.setSubtotal(subtotal);
                    lista.add(v);
                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar = totalPagar + lista.get(i).getSubtotal();
                    }
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", lista);
                    request.setAttribute("nserie", numeroserie);
                    break;
                case "Eliminar":
                    idv=Integer.parseInt(request.getParameter("id"));
                    for (int i = 0; i < lista.size(); i++) {
                         if(lista.get(i).getItem()==idv){
                             lista.remove(i);
                         }
                    }
                    request.setAttribute("lista", lista);
                    request.setAttribute("c", c);
                    break;
                case "Editar":
                    idv=Integer.parseInt(request.getParameter("id"));
                    cantidad1=Integer.parseInt(request.getParameter("cantidad"));
                    for (int i = 0; i < lista.size(); i++) {
                        if(lista.get(i).getItem()==idv){
                            v=new Venta();
                            v.setItem(lista.get(i).getItem());
                            v.setIdproducto(lista.get(i).getIdproducto());
                            v.setDescripcionP(lista.get(i).getDescripcionP());
                            v.setPrecio(lista.get(i).getPrecio());
                            v.setCantidad(cantidad1);
                            subtotal = lista.get(i).getPrecio() * cantidad1;
                            v.setSubtotal(subtotal);
                            lista.set(i,v);
                            
                        }
                    }
                    totalPagar=0;
                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar = totalPagar + lista.get(i).getSubtotal();
                    }
                    request.setAttribute("lista", lista);
                    request.setAttribute("c", c);
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("nserie", numeroserie);
                    break;
                case "GenerarVenta":
                    for (int i = 0; i < lista.size(); i++) {
                        Producto pr = new Producto();
                        int cantidad = lista.get(i).getCantidad();
                        int idproducto = lista.get(i).getIdproducto();
                        ProductoDAO aO = new ProductoDAO();
                        pr = aO.buscar(idproducto);
                        int sac = pr.getStock() - cantidad;
                        aO.actualizarstock(idproducto, sac);
                    }
                    java.util.Date fecha = new java.util.Date();
                    String fec = "";
                    int mes = fecha.getMonth() + 1;
                    int año = fecha.getYear() + 2000 - 100;
                    fec = año + "-" + mes + "-" + fecha.getDate();
                    v.setIdcliente(c.getId());
                    v.setIdempleado(2);
                    v.setNumserie(numeroserie);
                    v.setFecha(fec);
                    v.setMonto(totalPagar);
                    v.setEstado("1");
                    vdao.guardarVenta(v);
                    int idv = Integer.parseInt(vdao.IdVentas());
                    for (int i = 0; i < lista.size(); i++) {
                        v = new Venta();
                        v.setId(idv);
                        v.setIdproducto(lista.get(i).getIdproducto());
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrecio(lista.get(i).getPrecio());
                        vdao.guardarDetalleventas(v);
                        request.setAttribute("nserie", numeroserie);
                    }
                    lista.clear();
                    break;
                case "Cancelar":
                    lista.clear();
                    request.setAttribute("lista", lista);
                    request.setAttribute("nserie", numeroserie);
                    break;
                default:
                    numeroserie = vdao.GenerarSerie();
                    if (numeroserie == null) {
                        numeroserie = "00000001";
                        request.setAttribute("nserie", numeroserie);
                    } else {
                        int incrementar = Integer.parseInt(numeroserie);
                        GenerarSerie gs = new GenerarSerie();
                        numeroserie = gs.NumeroSerie(incrementar);
                        request.setAttribute("nserie", numeroserie);
                    }
                    request.setAttribute("lista", lista);  
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
        /*switch(accion){
        case "Principal":
        request.getRequestDispatcher("Principal.jsp").forward(request, response);
        break;
        case "Producto":
        request.getRequestDispatcher("Producto.jsp").forward(request, response);
        break;
        case "Cliente":
        request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        break;
        case "Empleado":
        request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        break;
        case "NuevaVenta":
        request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        break;
        default:
        throw new AssertionError();
        }*/
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
