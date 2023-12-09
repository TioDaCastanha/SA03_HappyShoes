<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Gerenciamento de Clientes</title>
<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
      integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
      crossorigin="anonymous">
<style>
    .logo-container {
        text-align: center;
        padding: 20px;
    }
    
    .logo {
        max-width: 100%;
        height: auto;
    }
</style>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark bg-dark">
			<div>
				<a href="" class="navbar-brand">Gerenciamento de Clientes</a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/main"
                       class="nav-link">Inicio</a></li>  
				<li><a href="<%=request.getContextPath()%>/listagem"
                       class="nav-link">Clientes</a></li>      
			</ul>
		</nav>
	</header>
	
	<div class="logo-container">
    	<img src="https://i.imgur.com/IhGd9qe.png" title="source: imgur.com" alt="logo" class="logo" />
    	<h1>Happy Shoes</h1>
	</div>
	
</body>
</html>