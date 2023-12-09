<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Gerenciamento de Clientes</title>
<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
      integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
      crossorigin="anonymous">
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
	<br>
	<div class="container">
		<h3>Listagem de Clientes</h3>
		<hr>
		<div class="container text-right">
			<a href="<%=request.getContextPath()%>/novo"
               class="btn btn-success btn-sm">Adicionar</a>
		</div>
		<br>
		<table class="table table-hover table-sm">
			<thead>
				<tr>
					<th scope="col">Matricula</th>
					<th scope="col">Cliente</th>
					<th scope="col">Endereço</th>
					<th scope="col">Modalidade</th>
					<th scope="col">Ações</th>
				</tr>
			</thead>
			<tbody class="align-middle">
				<c:forEach var="cliente" items="${listarCliente}">
					<tr>
						<td><c:out value="${cliente.matricula}" /></td>
						<td><c:out value="${cliente.nome}" /></td>
						<td><c:out value="${cliente.endereco}" /></td>
						<td><c:out value="${cliente.modalidade}" /></td>
						<td>
							<a href="editar?matricula=<c:out value='${cliente.matricula}' />"
                                class="btn btn-primary btn-sm">Editar</a>
							<a href="deletar?matricula=<c:out value='${cliente.matricula}' />"
                                class="btn btn-danger btn-sm">Excluir</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>