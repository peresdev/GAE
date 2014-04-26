<%@ page import="com.peres.model.Estoque" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<html>
<body>
	<h1>Atualizar Produto</h1>
	
	<%
		Estoque estoque = new Estoque();
	
		if(request.getAttribute("estoque")!=null){
		
			estoque = (Estoque)request.getAttribute("estoque");
			
		}
		
	%>
	
	<form method="post" action="../update" >
		<input type="hidden" name="key" id="key" 
			value="<%=KeyFactory.keyToString(estoque.getKey()) %>" /> 
		
		<table>
			<tr>
				<td>
					Nome:
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="nome" id="nome" 
						value="<%=estoque.getNome() %>" />
				</td>
			</tr>
			<tr>
				<td>
					Descrição:
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="descricao" id="descricao" 
						value="<%=estoque.getDescricao() %>" />
				</td>
			</tr>
			<tr>
				<td>
					Preço:
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="preco" id="preco" 
						value="<%=estoque.getPreco() %>" />
				</td>
			</tr>
		</table>
		<input type="submit" class="update" title="Atualizar" value="Atualizar" />
	</form>
	
</body>
</html>