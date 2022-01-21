<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="WEB-INF/custom.tld" prefix="x"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery-3.3.1.slim-min.js" ></script>
<script src="js/popper.min.js" ></script>
<link rel = "stylesheet" href = "bootstrap-4.2.1-dist/css/bootstrap.min.css">
<script src="bootstrap-4.2.1-dist/js/bootstrap.min.js"></script>
<link rel = "stylesheet" href= "css/login-style.css">

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

</head>
<body>

<x:AdminHead></x:AdminHead>
<div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card-group mb-0">
          <div class="card p-4">
            <div class="card-body">
              <h1>Sign-Up</h1>
              <p class="text-muted">Registrieren</p>
              <form action ="Registrierung" method = "POST">
              <div class="input-group mb-3">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" class="form-control" name = "loginname" placeholder="Username">
              </div>
              <div class="input-group mb-4">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" name = "passwort" placeholder="Passwort">
              </div>
              <div class="input-group mb-4">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" name = "pBestaetigen" placeholder="Passwort bestštigen">
              </div>
              <div class="row">
                <div class="col-6">
                  <button type="submit" class="btn btn-primary px-4">Registrieren</button>
                </div>               
                             
              </div>
             </form>
            </div>            
          </div>
          
          <div class="card text-white bg-primary py-5 d-md-down-none" style="width:44%">
            <div class="card-body text-center">
              <div>
                <h2>Login</h2>
                <p>Haben Sie bereits einen Account?</p>
                <p>Hier geht's zum Login</p>
                <a href="login.jsp"><button type="button" class="btn btn-primary active mt-3">Login!</button></a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>


</body>
</html>