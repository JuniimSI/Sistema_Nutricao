package com.example.nutricao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nutricao.model.ItemReceita;
import com.example.nutricao.model.Produto;
import com.example.nutricao.model.Receita;
import com.example.nutricao.service.declared.ProdutoService;
import com.example.nutricao.service.declared.ReceitaService;
import com.example.nutricao.utils.UnidadeMedidas;

@Controller
@RequestMapping("receita")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private ProdutoService produtoService;

	@GetMapping({ "", "/", "/listar" })
	public String listarReceita(Model model, Authentication auth) {
		List<Receita> receitas = receitaService.getAll();
		model.addAttribute("unidadeMedidas", UnidadeMedidas.values());
		model.addAttribute("receitas", receitas);
		return "receita/listar_receita";
	}

	@GetMapping("detalhes/{receita}")
	public String detalhes(@PathVariable Receita receita, Model model, Authentication auth,
			RedirectAttributes redirect) {

		if (receita == null) {
			redirect.addFlashAttribute("Erro", "receita inexistente");
			return "redirect:/receita/listar";
		}
		List<ItemReceita> prods = new ArrayList<ItemReceita>();
		prods = (receita.getItemsReceita());

		model.addAttribute("receita", receita).addAttribute("produtosSelect", produtoService.getAll())
				.addAttribute("produtos", prods);
		return "receita/detalhes_receita";

	}

	@GetMapping("/cadastrar")
	public String cadastrarReceitaForm(Model model) {
		Receita k = new Receita();
		model.addAttribute("acao", "cadastrar");
		model.addAttribute("receita", k);
		return "receita/cadastrar_receita";
		
	}

	@PostMapping("/cadastrar")
	public String cadastrarReceita(Receita receita, Authentication auth, Model model, RedirectAttributes redirect) {
		try {
		
			System.out.println(receita.getColesterol()+" asdkaskdaksd");
			receitaService.cadastrar(receita);
			redirect.addFlashAttribute("info", "Cadastro realizado com sucesso!");
			return "redirect:/receita/listar";
		} catch (Exception e) {
			redirect.addFlashAttribute("erro", e.getMessage());
			return "redirect:/receita/listar";
		}
	}

	@PostMapping("/produto/adicionar")
	public String adicionarProdutoReceita(@RequestParam Receita receita, @RequestParam Produto produto,
			@RequestParam double valorParaConversao, Model model, Authentication auth, RedirectAttributes redirect) {

		if (receita != null && produto != null) {
			if (produto.verificaNulos()) {
				receitaService.adicionaProdutoReceita(receita, produto, valorParaConversao);
				return "redirect:/receita/detalhes/" + receita.getId();
			} else {
				return "error";
			}
		} else {
			return "error";
		}

	}

	@GetMapping("/editar/{id}")
	public String editarReceitaForm(Model model, @PathVariable("id") Integer id) {
		Receita r = receitaService.getById(id);
		System.out.println(r.getColesterol());
		model.addAttribute("acao", "editar");
		model.addAttribute("unidadeMedidas", UnidadeMedidas.values());
		model.addAttribute("receita", receitaService.getById(id));

		return "receita/cadastrar_receita";
	}

	@GetMapping("/deletar/{id}")
	public String deletarReceitaForm(@PathVariable("id") Integer id, Model model) {
		Receita receita = receitaService.getById(id);
		try {
			receitaService.excluir(receita);
			return "redirect:/receita/listar";
		} catch (Exception e) {
			List<String> s = new ArrayList<String>();
			s.add(e.getMessage());
			model.addAttribute("erro", s);
			System.out.println(e.getMessage());
		}

		return "redirect:/receita/listar";
	}

	@GetMapping(value = "/{receita}/produto/deletar/{produto}")
	public String deletarProdutoReceita(@PathVariable Receita receita, @PathVariable ItemReceita produto) {
		try {
			receitaService.excluirProdutoReceita(receita, produto, produto.getQtd_usada());
			return "redirect:/receita/detalhes/" + receita.getId();
		} catch (Exception e) {
			return "error";
		}
	}

}
