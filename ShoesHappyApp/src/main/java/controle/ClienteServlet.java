package controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDao;
import modelo.Cliente;

/**
 * Este servlet atua como um controlador de página da aplicação, atendendo as
 * solicitações do usuário.
 *
 */

@WebServlet("/")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteDao clienteDao;

	public void init() {
		clienteDao = new ClienteDao();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getServletPath();

		try {
			switch (acao) {
			case "/novo":
				exibirFormularioInclusao(request, response);
				break;
			case "/inserir":
				inserirCliente(request, response);
				break;
			case "/deletar":
				deletarCliente(request, response);
				break;
			case "/editar":
				exibirFormularioEdicao(request, response);
				break;
			case "/atualizar":
				atualizarCliente(request, response);
				break;
			case "/listagem":
				listarCliente(request, response);
				break;
			default:
				menuInicial(request, response);
			}
		} catch (SQLException erro) {
			throw new ServletException(erro);
		}
	}
	
	private void menuInicial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher renderizar = request.getRequestDispatcher("main.jsp");
		renderizar.forward(request, response);
	}
	
	private void listarCliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List listarCliente = clienteDao.selecionarClientes();
		request.setAttribute("listarCliente", listarCliente);
		RequestDispatcher renderizar = request.getRequestDispatcher("listagem_cliente.jsp");
		renderizar.forward(request, response);
	}

	private void exibirFormularioInclusao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher renderizar = request.getRequestDispatcher("formulario_cliente.jsp");
		renderizar.forward(request, response);
	}

	private void exibirFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		Cliente selecaoCliente = clienteDao.selecionarCliente(matricula);
		RequestDispatcher renderizar = request.getRequestDispatcher("formulario_cliente.jsp");
		request.setAttribute("cliente", selecaoCliente);
		renderizar.forward(request, response);
	}

	private void inserirCliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String modalidade = request.getParameter("modalidade");
		Cliente adicaoCliente = new Cliente(nome, endereco, modalidade);
		clienteDao.inserirCliente(adicaoCliente);
		response.sendRedirect("listagem");
	}

	private void atualizarCliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String modalidade = request.getParameter("modalidade");
		Cliente atualizacaoCliente = new Cliente(matricula, nome, endereco, modalidade);
		clienteDao.atualizarCliente(atualizacaoCliente);
		response.sendRedirect("listagem");
	}

	private void deletarCliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		clienteDao.deletarCliente(matricula);
		response.sendRedirect("listagem");
	}

}
