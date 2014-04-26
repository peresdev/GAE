package com.peres.controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.peres.PMF;
import com.peres.model.Estoque;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddEstoquePage(ModelMap model) {

		return "add";

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request, ModelMap model) {

		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String preco = request.getParameter("preco");

		Estoque c = new Estoque();
		c.setNome(nome);
		c.setDescricao(descricao);
		c.setPreco(preco);

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(c);
		} finally {
			pm.close();
		}

		return new ModelAndView("redirect:list");

	}

	@RequestMapping(value = "/update/{nome}", method = RequestMethod.GET)
	public String getUpdateEstoquePage(@PathVariable String nome,
			HttpServletRequest request, ModelMap model) {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Estoque.class);
		q.setFilter("nome == nomeParameter");
		q.declareParameters("String nomeParameter");

		try {
			List<Estoque> results = (List<Estoque>) q.execute(nome);
			if (results.isEmpty()) {
				model.addAttribute("estoque", null);
			} else {
				model.addAttribute("estoque", results.get(0));
			}
		} finally {
			q.closeAll();
			pm.close();
		}

		return "update";

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, ModelMap model) {

		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String preco = request.getParameter("preco");
		String key = request.getParameter("key");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {

			Estoque c = pm.getObjectById(Estoque.class, key);

			c.setNome(nome);
			c.setDescricao(descricao);
			c.setPreco(preco);

		} finally {

			pm.close();
		}

		// return to list
		return new ModelAndView("redirect:list");

	}

	@RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String key,
			HttpServletRequest request, ModelMap model) {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {

			Estoque c = pm.getObjectById(Estoque.class, key);
			pm.deletePersistent(c);

		} finally {
			pm.close();
		}

		// return to list
		return new ModelAndView("redirect:../list");

	}

	// get all customers
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listEstoque(ModelMap model) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Estoque.class);

		List<Estoque> results = null;

		try {
			results = (List<Estoque>) q.execute();

			if (results.isEmpty()) {
				model.addAttribute("estoqueList", null);
			} else {
				model.addAttribute("estoqueList", results);
			}

		} finally {
			q.closeAll();
			pm.close();
		}

		return "list";

	}

}