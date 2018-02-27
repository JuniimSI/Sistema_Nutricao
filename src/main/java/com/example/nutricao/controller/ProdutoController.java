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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.nutricao.model.Produto;
import com.example.nutricao.service.declared.ProdutoService;
import com.example.nutricao.utils.UnidadeMedidas;

@Controller
@RequestMapping("produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping({"", "/", "/listar"})
	public String listarProduto(Model model, Authentication auth) {
		List<Produto> produtos = produtoService.getAll();
		System.out.println(produtos.size());
		model.addAttribute("unidadeMedidas", UnidadeMedidas.values());
		System.out.println("Test"+UnidadeMedidas.values());
		model.addAttribute("produtos", produtos);
		return "produto/listar_produtos";
	}
	
	@GetMapping("/cadastrar")
	public String cadastrarProdutoForm(Model model) {
		model.addAttribute("acao", "cadastrar");
		model.addAttribute("produto", new Produto());
		model.addAttribute("unidadeMedidas", UnidadeMedidas.values());
		return "produto/cadastrar_produto";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrarProduto(Produto produto, Authentication auth, Model model, RedirectAttributes redirect) {
		try {
			produtoService.cadastrar(produto);
			redirect.addFlashAttribute("info", "Cadastro realizado com sucesso!");
			return "redirect:/produto/listar";
		}catch (Exception e) {
			redirect.addFlashAttribute("erro", e.getMessage());
			return "redirect:/produto/listar";
		} 
	}
	
	@GetMapping("/editar/{id}")
	public String editarProdutoForm(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("acao", "editar");
		model.addAttribute("unidadeMedidas", UnidadeMedidas.values());
		model.addAttribute("produto", produtoService.getById(id));
		
		return "produto/cadastrar_produto";
	}
	
	@GetMapping("/deletar/{id}")
	public String deletarProdutoForm(@PathVariable("id") Integer id, Model model) {
		Produto produto = produtoService.getById(id);
			try {
				produtoService.excluir(produto);
				return "redirect:/produto/listar";
			} catch (Exception e) {
				List<String> s = new ArrayList<String>();
				s.add(e.getMessage());
				model.addAttribute("erro", s);
				System.out.println(e.getMessage());
			}
		
		return "redirect:/produto/listar";
	}
	
	
}
