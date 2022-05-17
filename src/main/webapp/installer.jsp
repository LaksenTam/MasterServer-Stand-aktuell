<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Installation</title>
<script src="js/jquery-3.3.1.slim-min.js" ></script>
<script src="js/popper.min.js" ></script>
<script src="bootstrap-4.2.1-dist/js/bootstrap.min.js"></script>
<script src="js/jquery.tablesort.min.js"></script>
<link rel = "stylesheet" href = "bootstrap-4.2.1-dist/css/bootstrap.min.css">

<link rel = "stylesheet" href= "css/style.css">
<link rel = "stylesheet" href= "css/login-style.css">


</head>
<body class = "bg-secondary">
<div class = "container">
<div class="row justify-content-center">
      <div class="col-md-5">
        <div class="card-group mb-0">
          <div class="card p-4 mt-5">
            <div class="card-body">
            <h4 class = "text-center text-white card-title">Webanwendung installieren</h4>
           <p class = "text-white">	Bitte geben Sie die gewünschten Logindaten für den Adminaccount ein.</p>
            
<form action = "installServ" method = "POST">
	<div class="input-group mb-3">
		<span class="input-group-addon"></span>
		<input type="text" class="form-control" name = "name" placeholder="Username">
      </div>
      <div class="input-group mb-4">
                <span class="input-group-addon"></span>
                <input type="password" class="form-control" name = "password" placeholder="Password">
              </div>
              <div class="row">
                <div class="col-4">
                  <button type="submit" class="btn btn-secondary px-4">installieren</button>
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