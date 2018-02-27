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
import com.example.nutricao.model.Escola;
import com.example.nutricao.model.Programa;
import com.example.nutricao.service.declared.CardapioService;
import com.example.nutricao.service.declared.ItemCardapioProgramaEscolaService;
import com.example.nutricao.service.declared.ProgramaService;
import com.example.nutricao.utils.UnidadeMedidas;

@Controller
@RequestMapping("programa")
public class ProgramaController {

	@Autowired
	private ProgramaService programaService;

	@Autowired
	private CardapioService cardapioService;

	@Autowired
	private ItemCardapioProgramaEscolaService icpeService;

	@GetMapping({ "", "/", "/listar" })
	public String listarPrograma(Model model, Authentication auth) {
		List<Programa> programas = programaService.getAll();
		System.out.println(programas.size());
		model.addAttribute("programas", programas);
		return "programa/listar_programas";
	}

	@PostMapping("/escola/{escola}/cardapio/adicionar")
	public String adicionarCardapioPrograma(@PathVariable Escola escola, @RequestParam Programa programa,
			@RequestParam Cardapio cardapio, Model model, Authentication auth, RedirectAttributes redirect) {
		if (cardapio != null && programa != null && escola != null) {
			System.out.println(escola.getProgramas().get(0).getNome() + "      asdakskdas ");
			programaService.adicionaCardapioPrograma(escola, programa, cardapio);
			return "redirect:/programa/escola/" + escola.getId() + "/detalhes/" + programa.getId();
		} else {
			return "error";
		}
	}

	@GetMapping("escola/{escola}/detalhes/{programa}")
	public String detalhes(@PathVariable Escola escola, @PathVariable Programa programa, Model model,
			Authentication auth, RedirectAttributes redirect) {

		if (programa == null) {
			redirect.addFlashAttribute("Erro", "programa inexistente");
			return "redirect:/programa/listar";
		}
		
		Cardapio cardp = null;

		System.out.println("Escola.getId()" + escola.getId());
		System.out.println("Programa.getId()" + programa.getId());

		// cardp = escola.getProgramas().get(i).getCardapios().get(0);
		Integer icpe = icpeService.getCardapioId(escola.getId(), programa.getId());
		System.out.println("++++++++++++++++++++++++"+icpe);
		if(icpe!=null) {
			cardp = cardapioService.getById(icpe);	
		}
		
		if (cardp != null) {
			System.out.println("Entrou no primeiro" + cardp.getNome());
			model.addAttribute("cardapio", cardp);
		} else {
			System.out.println("Entrou no segundo" + escola.getProgramas().size());
			model.addAttribute("cardapio", new Cardapio());
		}

		model.addAttribute("programa", programa).addAttribute("escola", escola).addAttribute("cardapiosSelect",
				cardapioService.getAll());
		return "programa/detalhes_programa";
	}

	@GetMapping("/cadastrar")
	public String cadastrarProgramaForm(Model model) {
		model.addAttribute("acao", "cadastrar");
		model.addAttribute("programa", new Programa());
		return "programa/cadastrar_programa";
	}

	@PostMapping("/cadastrar")
	public String cadastrarPrograma(Programa programa, Authentication auth, Model model, RedirectAttributes redirect) {
		try {
			programaService.cadastrar(programa);
			redirect.addFlashAttribute("info", "Cadastro realizado com sucesso!");
			return "redirect:/programa/listar";
		} catch (Exception e) {
			redirect.addFlashAttribute("erro", e.getMessage());
			return "redirect:/programa/listar";
		}
	}

	@GetMapping("/editar/{id}")
	public String editarProgramaForm(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("acao", "editar");
		model.addAttribute("unidadeMedidas", UnidadeMedidas.values());
		model.addAttribute("programa", programaService.getById(id));

		return "programa/cadastrar_programa";
	}

	@GetMapping("/deletar/{id}")
	public String deletarProgramaForm(@PathVariable("id") Integer id, Model model) {
		Programa programa = programaService.getById(id);
		try {
			programaService.excluir(programa);
			return "redirect:/programa/listar";
		} catch (Exception e) {
			List<String> s = new ArrayList<String>();
			s.add(e.getMessage());
			model.addAttribute("erro", s);
			System.out.println(e.getMessage());
		}

		return "redirect:/programa/listar";
	}

	@GetMapping("/deletar/{programa}/escola/{escola}/cardapio/{cardapio}")
	public String deletarCardapioPrograma(@PathVariable Programa programa, @PathVariable Escola escola,
			@PathVariable Cardapio cardapio) {
		try {
			System.out.println(programa.getId() + " " + escola.getId() + " " + cardapio.getId());
			programaService.excluirCardapioPrograma(escola, programa, cardapio);
			return "redirect:/programa/escola/" + escola.getId() + "/detalhes/" + programa.getId();
		} catch (Exception e) {
System.out.println("CATCH");
			return "erro";
		}
	}
}
