package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirConsumidor;
import entidades.exceptions.ConsumidorException;
import entidades.value_objects.ConsumidorVO;
@WebServlet("/ExcluirConsumidor")
public class ExcluirConsumidor extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		action(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException
	{
		action(req, resp);
	}
	
	private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (request.getParameter("idConsumidor") != null && !request.getParameter("idConsumidor").equals(""))
		{
			Long id = Long.parseLong(request.getParameter("idConsumidor"));
			ConsumidorVO consumidorVO = new ConsumidorVO();
			consumidorVO.setId(id);
			try
			{
				GerirConsumidor.getInstance().excluirConsumidor(consumidorVO);
			} catch (ConsumidorException e)
			{				
				e.printStackTrace();
			}
		}		
		
		request.getRequestDispatcher("WEB-INF/ListarConsumidor.jsp").forward(request,response);
		
	}
}
