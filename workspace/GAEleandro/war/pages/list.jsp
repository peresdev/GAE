<%@ page import="java.util.List" %>
<%@ page import="com.peres.model.Estoque" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<html>
<body>
	<h1>Leandro Peres - GAE COM JDO + SPRING MVC</h1>

	<a href="add">Adicionar Produto</a>
	<hr />

	<h2>Todos os produtos</h2>
	<table border="1">
		<thead>
			<tr>
				<td>Nome</td>
				<td>Descrição</td>
				<td>Preço</td>
			</tr>
		</thead>
		
		<%
			
			if(request.getAttribute("estoqueList")!=null){
				
				List<Estoque> estoques = (List<Estoque>)request.getAttribute("estoqueList");
				
				if(!estoques.isEmpty()){
					 for(Estoque c : estoques){
						 
		%>
					<tr>
					  <td><%=c.getNome() %></td>
					  <td><%=c.getDescricao() %></td>
					  <td><%=c.getPreco() %></td>
					  <td><a href="update/<%=c.getNome()%>">Atualizar</a> 
		                   | <a href="delete/<%=KeyFactory.keyToString(c.getKey()) %>">Deletar</a></td>
					</tr>
		<%	
			
					}
		    
				}
			
		   	}
		%>
         
        </tr>
     
	</table>

</body>
</html>