<%-- 
    Document   : Usuario
    Created on : 21/01/2020, 02:53:30 PM
    Author     : ProDesk 2019
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>VENTAS</title>
        <style>
            @media print{
                .parte01, .btn, .accion{
                    display:none;
                }
            }
        </style>
    </head>
    <body>
        <div class="d-flex">
            <div class="col-sm-5 parte01">
                <div class="card">
                    <form action="Controlador?menu=NuevaVenta" method="POST">
                        <div class="card-body">
                            <div class="form-group">
                                <label>Datos del Cliente</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="codigocliente" value="${c.getDni()}" class="form-control" placeholder="Codigo">
                                    <button type="submit" name="accion" value="BuscarCliente" class="btn btn-outline-info">Buscar</button>
                                </div>

                                <div class="col-sm-6">
                                    <input type="text" name="nombrescliente" value="${c.getNom()}" readonly placeholder="Datos Cliente" class="form-control">
                                </div>            
                            </div>
                            <div class="form-group">
                                <label>Datos Producto</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="codigoproducto" value="${producto.getId()}" class="form-control" placeholder="Codigo">
                                    <button type="submit" name="accion" value="BuscarProducto" class="btn btn-outline-info">Buscar</button>
                                </div>

                                <div class="col-sm-6">
                                    <input type="text" name="nomproducto" value="${producto.getNom()}" readonly class="form-control" placeholder="Datos Producto">
                                </div>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="precio" value="${producto.getPrecio()}" readonly class="form-control" placeholder="S/.0.00">                
                                </div>
                                <div class="col-sm-3">
                                    <input type="number" name="cant" class="form-control" value="1">                
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" name="stock" value="${producto.getStock()}" readonly class="form-control" placeholder="Stock">
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="accion" value="Agregar" class="btn btn-outline-info">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-sm-7">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex col-sm-6 ml-auto">
                            <label style="margin-top: 5px; font-weight: bolder; font-size: 20px;">Nro.Serie: </label>&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="text" name="NroSerie" readonly value="${nserie}" class="form-control">
                        </div>
                        <br>
                        <table class="table table-hover">
                            <thead>
                                <tr class="text-center">
                                    <th>Nro</th>
                                    <th>Codigo</th>
                                    <th>Descripcion</th>
                                    <th>Precio</th>
                                    <th>Cantidad</th>
                                    <th>SubTotal</th>
                                    <th class="accion">Acciones</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="list" items="${lista}">
                                <form action="Controlador?menu=NuevaVenta&accion=Editar&id=${list.getItem()}" method="POST">
                                    <tr class="text-center">
                                        <td>${list.getItem()}</td>
                                        <td>${list.getIdproducto()}</td>
                                        <td>${list.getDescripcionP()}</td>
                                        <td>${list.getPrecio()}</td>
                                        <td><input type="number" name="cantidad" value="${list.getCantidad()}"></td>
                                        <td>${list.getSubtotal()}</td>
                                        <td class="d-flex">
                                            <button type="submit" name="accion" value="Editar" class="btn btn-warning">Editar</button>

                                            <a href="Controlador?menu=NuevaVenta&accion=Eliminar&id=${list.getItem()}" class="btn btn-danger" style="margin-left: 10px">Eliminar</a>
                                        </td>
                                    </tr></form>
                                </c:forEach>
                            </tbody>
                        </table>

                    </div>
                    <div class="card-footer d-flex">
                        <div class="col-sm-6">
                            <a href="Controlador?menu=NuevaVenta&accion=GenerarVenta" onclick="print()" class="btn btn-success">Generar Venta</a>

                            <a href="Controlador?menu=NuevaVenta&accion=Cancelar" class="btn btn-danger">Cancelar</a>
                        </div>
                        <div class="col-sm-4 ml-auto">
                            <input type="text" name="txtTotal" value="S/. ${totalpagar}0" readonly class="form-control text-center font-weight-bold">
                        </div>
                    </div>

                </div>

            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
