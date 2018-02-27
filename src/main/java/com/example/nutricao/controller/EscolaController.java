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

import com.example.nutricao.model.Escola;
import com.example.nutricao.model.Programa;
import com.example.nutricao.service.declared.EscolaService;
import com.example.nutricao.service.declared.ProgramaService;
import com.example.nutricao.utils.UnidadeMedidas;

@Controller
@RequestMapping("escola")
public class EscolaController {
	@Autowired
	private EscolaService escolaService;

	@Autowired
	private ProgramaService programaService;

	@GetMapping({ "", "/", "/listar" })
	public String listarEscola(Model model, Authentication auth) {
		List<Escola> escolas = escolaService.getAll();
		model.addAttribute("escolas", escolas);
		return "escola/listar_escolas";
	}

	@GetMapping("/cadastrar")
	public String cadastrarEscolaForm(Model model) {
		model.addAttribute("acao", "cadastrar");
		model.addAttribute("escola", new Escola());
		model.addAttribute("unidadeMedidas", UnidadeMedidas.values());
		return "escola/cadastrar_escola";
	}

	@PostMapping("/cadastrar")
	public String cadastrarEscola(Escola escola, Authentication auth, Model model, RedirectAttributes redirect) {
		try {
			escolaService.cadastrar(escola);
			redirect.addFlashAttribute("info", "Cadastro realizado com sucesso!");
			return "redirect:/escola/listar";
		} catch (Exception e) {
			redirect.addFlashAttribute("erro", e.getMessage());
			return "redirect:/escola/listar";
		}
	}

	@GetMapping("/editar/{id}")
	public String editarEscolaForm(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("acao", "editar");
		model.addAttribute("escola", escolaService.getById(id));

		return "escola/cadastrar_escola";
	}

	@GetMapping("/deletar/{id}")
	public String deletarEscolaForm(@PathVariable("id") Integer id, Model model) {
		Escola escola = escolaService.getById(id);
		try {
			escolaService.excluir(escola);
			return "redirect:/escola/listar";
		} catch (Exception e) {
			List<String> s = new ArrayList<String>();
			s.add(e.getMessage());
			model.addAttribute("erro", s);
			System.out.println(e.getMessage());
		}

		return "redirect:/escola/listar";
	}

	@PostMapping("/programa/adicionar")
	public String adicionarProgramaEscola(@RequestParam Escola escola, @RequestParam Programa programa, Model model,
			Authentication auth, RedirectAttributes redirect) {

		if (escola != null && programa != null) {
			escolaService.adicionaProgramaEscola(escola, programa);
			return "redirect:/escola/detalhes/" + escola.getId();
		} else {
			return "error";
		}
	}

	@GetMapping("detalhes/{escola}")
	public String detalhes(@PathVariable Escola escola, Model model, Authentication auth, RedirectAttributes redirect) {

		if (escola == null) {
			redirect.addFlashAttribute("Erro", "escola inexistente");
			return "redirect:/escola/listar";
		}
		List<Programa> progs = new ArrayList<Programa>();
		progs = (escola.getProgramas());

		System.out.println("Numero de vezes "+progs.size());
		model.addAttribute("escola", escola).addAttribute("programasSelect", programaService.getAll())
				.addAttribute("programas", progs);
		return "escola/detalhes_escola";
	}

	@GetMapping(value = "/{escola}/programa/deletar/{programa}")
	public String deletarProgramaEscola(@PathVariable Escola escola, @PathVariable Programa programa) {
		try {
			escolaService.excluirProgramaEscola(escola, programa);
			return "redirect:/escola/detalhes/" + escola.getId();
		} catch (Exception e) {
			return "erro";
		}
	}

}
