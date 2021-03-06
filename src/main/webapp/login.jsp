<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
          <%@ taglib uri="WEB-INF/custom.tld" prefix="x"%>
    
<!DOCTYPE html>
<html style="height: 494px; ">
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<script src="js/jquery-3.3.1.slim-min.js" ></script>
<script src="js/popper.min.js" ></script>
<script src="bootstrap-4.2.1-dist/js/bootstrap.min.js"></script>
<script src="js/jquery.tablesort.min.js"></script>
<link rel = "stylesheet" href = "bootstrap-4.2.1-dist/css/bootstrap.min.css">

<link rel = "stylesheet" href= "css/style.css">
<script src="https://kit.fontawesome.com/5cfe696ca3.js"></script>
<link rel = "stylesheet" href= "css/login-style.css">

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

</head>
<body class = "bg-secondary text-white">
<x:AdminHead></x:AdminHead>
<div class="container">
    <div class="row justify-content-center">
      <div class="col-md-5">
        <div class="card-group mb-0">
          <div class="card p-4">
            <div class="card-body">
              <h1>Login</h1>
              <p class="text-muted">Login</p>
              <form action ="Login" method = "POST">
              <div class="input-group mb-3">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" class="form-control" name = "loginname" placeholder="Username">
              </div>
              <div class="input-group mb-4">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" name = "passwort" placeholder="Password">
              </div>
              <div class="row">
                <div class="col-4">
                  <button type="submit" class="btn btn-secondary px-4">Login</button>
                </div> 
                   <div>
                	<a href= "registrieren.jsp">Noch keinen Account?</a>
                
                </div>           
                             
              </div>
             </form>
            </div>            
          </div>
          
          
        </div>
      </div>
    </div>
  </div>

</body>
</html>