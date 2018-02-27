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

import com.example.nutricao.model.Cardapio;
import com.example.nutricao.model.Custos;
import com.example.nutricao.model.Escola;
import com.example.nutricao.model.Programa;
import com.example.nutricao.model.Receita;
import com.example.nutricao.service.declared.CardapioService;
import com.example.nutricao.service.declared.ReceitaService;
import com.example.nutricao.utils.UnidadeMedidas;

@Controller
@RequestMapping("cardapio")
public class CardapioController {
	
	@Autowired
	private CardapioService cardapioService;
	
	@Autowired
	private ReceitaService receitaService;
	
	@GetMapping({"", "/", "/listar"})
	public String listarCardapio(Model model, Authentication auth) {
		List<Cardapio> cardapios = cardapioService.getAll();
		model.addAttribute("cardapios", cardapios);	
		return "cardapio/listar_cardapios";
	}

	@GetMapping("/cadastrar")
	public String cadastrarCardapioForm(Model model) {
		model.addAttribute("acao", "cadastrar");
		model.addAttribute("cardapio", new Cardapio());
		model.addAttribute("unidadeMedidas", UnidadeMedidas.values());
		return "cardapio/cadastrar_cardapio";
	}
	
	
	@GetMapping("escola/{escola}/programa/{programa}/cardapio/detalhes/{cardapio}")
	public String detalhes(@PathVariable Escola escola, @PathVariable Programa programa, @PathVariable Cardapio cardapio, Model model, Authentication auth, RedirectAttributes redirect) {
		
		if (cardapio == null) {
			redirect.addFlashAttribute("Erro", "cardapio inexistente");
			return "redirect:/cardapio/listar";
		}
		
		List<Receita> segunda = cardapio.getSegunda();
		List<Receita> terca = cardapio.getTerca();
		List<Receita> quarta = cardapio.getQuarta();
		List<Receita> quinta = cardapio.getQuinta();
		List<Receita> sexta = cardapio.getSexta();
		
		if(segunda != null) {
			model.addAttribute("segunda", segunda);
		} else {
			model.addAttribute("segunda", new Receita());
		}
		
		if(terca != null) {
			model.addAttribute("terca", terca);
		} else {
			model.addAttribute("terca", new Receita());
		}
		
		if(quarta != null) {
			model.addAttribute("quarta", quarta);
		} else {
			model.addAttribute("quarta", new Receita());
		}
		
		if(quinta != null) {
			model.addAttribute("quinta", quinta);
		} else {
			model.addAttribute("quinta", new Receita());
		}
		
		if(sexta != null) {
			model.addAttribute("sexta", sexta);
		} else {
			model.addAttribute("sexta", new Receita());
		}
		
		model.addAttribute("programa", programa).addAttribute("escola", escola).addAttribute("receitasSelect", receitaService.getAll());
		return "cardapio/detalhes_cardapio";
	}
	
	@GetMapping("/custos/{cardapio}")
	public String custosCardapio(@PathVariable Cardapio cardapio, Model model, Authentication auth, RedirectAttributes redirect) {
		List<Custos> custos = cardapio.getCustosOrganizados();
		System.out.println(custos.size()+ " " + custos.get(0).getQtdUtilizada()+ " " + custos.get(0).getPrecoProduto());
		model.addAttribute("custos_cardapio", custos);
		model.addAttribute("cardapio", cardapio);
		return "cardapio/custos_do_cardapio";
	}
	
	@GetMapping("/detalhes/{cardapio}")
	public String detalhesPrimario( @PathVariable Cardapio cardapio, Model model, Authentication auth, RedirectAttributes redirect) {
		if(cardapio == null) {
			redirect.addFlashAttribute("Erro", "cardapio inexistente");
			return "redirect:/cardapio/listar";
		}
		
		List<Receita> segunda = cardapio.getSegunda();
		List<Receita> terca = cardapio.getTerca();
		List<Receita> quarta = cardapio.getQuarta();
		List<Receita> quinta = cardapio.getQuinta();
		List<Receita> sexta = cardapio.getSexta();
		
		if(segunda != null) {
			model.addAttribute("segunda", segunda);
		} else {
			model.addAttribute("segunda", new Receita());
		}
		
		if(terca != null) {
			model.addAttribute("terca", terca);
		} else {
			model.addAttribute("terca", new Receita());
		}
		
		if(quarta != null) {
			model.addAttribute("quarta", quarta);
		} else {
			model.addAttribute("quarta", new Receita());
		}
		
		if(quinta != null) {
			model.addAttribute("quinta", quinta);
		} else {
			model.addAttribute("quinta", new Receita());
		}
		
		if(sexta != null) {
			model.addAttribute("sexta", sexta);
		} else {
			model.addAttribute("sexta", new Receita());
		}
		
		model.addAttribute("receitasSelect", receitaService.getAll());
		return "cardapio/detalhes_cardapio_simple";
	}
	
	
	@PostMapping("/cadastrar")
	public String cadastrarCardapio(Cardapio cardapio, Authentication auth, Model model, RedirectAttributes redirect) {
		try {
			cardapioService.cadastrar(cardapio);
			redirect.addFlashAttribute("info", "Cadastro realizado com sucesso!");
			return "redirect:/cardapio/listar";
		}catch (Exception e) {
			redirect.addFlashAttribute("erro", e.getMessage());
			return "redirect:/cardapio/listar";
		} 
	}
	
	@GetMapping("/editar/{id}")
	public String editarCardapioForm(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("acao", "editar");
		model.addAttribute("unidadeMedidas", UnidadeMedidas.values());
		model.addAttribute("cardapio", cardapioService.getById(id));

		return "cardapio/cadastrar_cardapio";
	}

	@GetMapping("/deletar/{id}")
	public String deletarCardapioForm(@PathVariable("id") Integer id, Model model) {
		Cardapio cardapio = cardapioService.getById(id);
		try {
			cardapioService.excluir(cardapio);
			return "redirect:/cardapio/listar";
		} catch (Exception e) {
			List<String> s = new ArrayList<String>();
			s.add(e.getMessage());
			model.addAttribute("erro", s);
			System.out.println(e.getMessage());
		}

		return "redirect:/cardapio/listar";
	}

	
	@PostMapping("{cardapio}/escola/{escola}/programa/{programa}/receita/segunda/adicionar")
	public String adicionarReceitaCardapioSegunda(@PathVariable Escola escola, @PathVariable Programa programa, @PathVariable Cardapio cardapio, @RequestParam Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && programa != null && receita != null && escola!=null) {
			cardapioService.adicionaReceitaCardapioSegunda( escola, programa, cardapio, receita);
			return "redirect:/cardapio/escola/"+escola.getId()+"/programa/"+programa.getId()+"/cardapio"+"/detalhes/" + cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@PostMapping("{cardapio}/receita/segunda/adicionar")
	public String adicionarReceitaCardapioSegundaSimple(@PathVariable Cardapio cardapio, @RequestParam Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && receita != null) {
			cardapioService.adicionaReceitaCardapioSegundaSimple( cardapio, receita);
			return "redirect:/cardapio/detalhes/"+cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@PostMapping("{cardapio}/escola/{escola}/programa/{programa}/receita/terca/adicionar")
	public String adicionarReceitaCardapioTerca(@PathVariable Escola escola, @PathVariable Programa programa, @PathVariable Cardapio cardapio, @RequestParam Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && programa != null && receita != null && escola!=null) {
			cardapioService.adicionaReceitaCardapioTerca( escola, programa, cardapio, receita);
			return "redirect:/cardapio/escola/"+escola.getId()+"/programa/"+programa.getId()+"/cardapio"+"/detalhes/" + cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@PostMapping("{cardapio}/receita/terca/adicionar")
	public String adicionarReceitaCardapioTercaSimple(@PathVariable Cardapio cardapio, @RequestParam Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && receita != null) {
			cardapioService.adicionaReceitaCardapioTercaSimple( cardapio, receita);
			return "redirect:/cardapio/detalhes/"+cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@PostMapping("{cardapio}/escola/{escola}/programa/{programa}/receita/quarta/adicionar")
	public String adicionarReceitaCardapioQuarta(@PathVariable Escola escola, @PathVariable Programa programa, @PathVariable Cardapio cardapio, @RequestParam Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && programa != null && receita != null && escola!=null) {
			cardapioService.adicionaReceitaCardapioQuarta( escola, programa, cardapio, receita);
			return "redirect:/cardapio/escola/"+escola.getId()+"/programa/"+programa.getId()+"/cardapio"+"/detalhes/" + cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@PostMapping("{cardapio}/receita/quarta/adicionar")
	public String adicionarReceitaCardapioQuartaSimple(@PathVariable Cardapio cardapio, @RequestParam Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		System.out.println("Algum null?");
		if (cardapio != null && receita != null) {
			System.out.println("Ta entrando no if");
			cardapioService.adicionaReceitaCardapioQuartaSimple( cardapio, receita);
			return "redirect:/cardapio/detalhes/"+cardapio.getId();
		} else {
			System.out.println("Ta entrando no else e error");
			return "error";
		}
	}
	
	@PostMapping("{cardapio}/escola/{escola}/programa/{programa}/receita/quinta/adicionar")
	public String adicionarReceitaCardapioQuinta(@PathVariable Escola escola, @PathVariable Programa programa, @PathVariable Cardapio cardapio, @RequestParam Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && programa != null && receita != null && escola!=null) {
			cardapioService.adicionaReceitaCardapioQuinta( escola, programa, cardapio, receita);
			return "redirect:/cardapio/escola/"+escola.getId()+"/programa/"+programa.getId()+"/cardapio"+"/detalhes/" + cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@PostMapping("{cardapio}/receita/quinta/adicionar")
	public String adicionarReceitaCardapioQuintaSimple(@PathVariable Cardapio cardapio, @RequestParam Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		System.out.println("Algum null?");
		if (cardapio != null && receita != null) {
			System.out.println("Ta entrando no if");
			cardapioService.adicionaReceitaCardapioQuintaSimple( cardapio, receita);
			return "redirect:/cardapio/detalhes/"+cardapio.getId();
		} else {
			System.out.println("Ta entrando no else e error");
			return "error";
		}
	}
	
	@PostMapping("{cardapio}/escola/{escola}/programa/{programa}/receita/sexta/adicionar")
	public String adicionarReceitaCardapioSexta(@PathVariable Escola escola, @PathVariable Programa programa, @PathVariable Cardapio cardapio, @RequestParam Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && programa != null && receita != null && escola!=null) {
			cardapioService.adicionaReceitaCardapioSexta( escola, programa, cardapio, receita);
			return "redirect:/cardapio/escola/"+escola.getId()+"/programa/"+programa.getId()+"/cardapio"+"/detalhes/" + cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@PostMapping("{cardapio}/receita/sexta/adicionar")
	public String adicionarReceitaCardapioSextaSimple(@PathVariable Cardapio cardapio, @RequestParam Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		System.out.println("Algum null?");
		if (cardapio != null && receita != null) {
			System.out.println("Ta entrando no if");
			cardapioService.adicionaReceitaCardapioSextaSimple( cardapio, receita);
			return "redirect:/cardapio/detalhes/"+cardapio.getId();
		} else {
			System.out.println("Ta entrando no else e error");
			return "error";
		}
	}
	
	@GetMapping("{cardapio}/escola/{escola}/programa/{programa}/receita/{receita}/segunda/excluir")
	public String excluirReceitaCardapioSegunda(@PathVariable Escola escola, @PathVariable Programa programa, @PathVariable Cardapio cardapio, @PathVariable Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && programa != null && receita != null && escola!=null) {
			cardapioService.excluiReceitaCardapioSegunda( escola, programa, cardapio, receita);
			return "redirect:/cardapio/escola/"+escola.getId()+"/programa/"+programa.getId()+"/cardapio"+"/detalhes/" + cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@GetMapping("{cardapio}/receita/{receita}/segunda/excluir")
	public String excluirReceitaCardapioSegunda(@PathVariable Cardapio cardapio, @PathVariable Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null  && receita != null ) {
			cardapioService.excluiReceitaCardapioSegundaSimple(  cardapio, receita);
			return "redirect:/cardapio/detalhes/"+cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@GetMapping("{cardapio}/escola/{escola}/programa/{programa}/receita/{receita}/terca/excluir")
	public String excluirReceitaCardapioTerca(@PathVariable Escola escola, @PathVariable Programa programa, @PathVariable Cardapio cardapio, @PathVariable Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && programa != null && receita != null && escola!=null) {
			cardapioService.excluiReceitaCardapioTerca( escola, programa, cardapio, receita);
			return "redirect:/cardapio/escola/"+escola.getId()+"/programa/"+programa.getId()+"/cardapio"+"/detalhes/" + cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@GetMapping("{cardapio}/receita/{receita}/terca/excluir")
	public String excluirReceitaCardapioTerca(@PathVariable Cardapio cardapio, @PathVariable Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null  && receita != null ) {
			cardapioService.excluiReceitaCardapioTercaSimple(  cardapio, receita);
			return "redirect:/cardapio/detalhes/"+cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@GetMapping("{cardapio}/escola/{escola}/programa/{programa}/receita/{receita}/quarta/excluir")
	public String excluirReceitaCardapioQuarta(@PathVariable Escola escola, @PathVariable Programa programa, @PathVariable Cardapio cardapio, @PathVariable Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && programa != null && receita != null && escola!=null) {
			cardapioService.excluiReceitaCardapioQuarta( escola, programa, cardapio, receita);
			return "redirect:/cardapio/escola/"+escola.getId()+"/programa/"+programa.getId()+"/cardapio"+"/detalhes/" + cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@GetMapping("{cardapio}/receita/{receita}/quarta/excluir")
	public String excluirReceitaCardapioQuarta(@PathVariable Cardapio cardapio, @PathVariable Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null  && receita != null ) {
			cardapioService.excluiReceitaCardapioQuartaSimple(  cardapio, receita);
			return "redirect:/cardapio/detalhes/"+cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@GetMapping("{cardapio}/escola/{escola}/programa/{programa}/receita/{receita}/quinta/excluir")
	public String excluirReceitaCardapioQuinta(@PathVariable Escola escola, @PathVariable Programa programa, @PathVariable Cardapio cardapio, @PathVariable Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && programa != null && receita != null && escola!=null) {
			cardapioService.excluiReceitaCardapioQuinta( escola, programa, cardapio, receita);
			return "redirect:/cardapio/escola/"+escola.getId()+"/programa/"+programa.getId()+"/cardapio"+"/detalhes/" + cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@GetMapping("{cardapio}/receita/{receita}/quinta/excluir")
	public String excluirReceitaCardapioQuinta(@PathVariable Cardapio cardapio, @PathVariable Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null  && receita != null ) {
			cardapioService.excluiReceitaCardapioQuintaSimple(  cardapio, receita);
			return "redirect:/cardapio/detalhes/"+cardapio.getId();
		} else {
			return "error";
		}
	}
	
	@GetMapping("{cardapio}/escola/{escola}/programa/{programa}/receita/{receita}/sexta/excluir")
	public String excluirReceitaCardapioSexta(@PathVariable Escola escola, @PathVariable Programa programa, @PathVariable Cardapio cardapio, @PathVariable Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && programa != null && receita != null && escola!=null) {
			cardapioService.excluiReceitaCardapioSexta( escola, programa, cardapio, receita);
			return "redirect:/cardapio/escola/"+escola.getId()+"/programa/"+programa.getId()+"/cardapio"+"/detalhes/" + cardapio.getId();
		} else {
			return "error";
		}
	}

	@GetMapping("{cardapio}/receita/{receita}/sexta/excluir")
	public String excluirReceitaCardapioSexta(@PathVariable Cardapio cardapio, @PathVariable Receita receita , Model model,
			Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null  && receita != null ) {
			cardapioService.excluiReceitaCardapioSextaSimple(  cardapio, receita);
			return "redirect:/cardapio/detalhes/"+cardapio.getId();
		} else {
			return "error";
		}
	}
}
