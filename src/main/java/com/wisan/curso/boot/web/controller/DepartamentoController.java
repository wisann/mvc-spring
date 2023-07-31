package com.wisan.curso.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wisan.curso.boot.domain.Departamento;
import com.wisan.curso.boot.services.DepartamentoService;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", service.findAll());
		return "/departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Departamento departamento, RedirectAttributes attr) {
		service.insert(departamento);
		attr.addFlashAttribute("success", "Departamento inserido com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable Long id, ModelMap model) {
	    Departamento departamento = service.findById(id);
	    model.addAttribute("departamento", departamento);
	    return "/departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@ModelAttribute Departamento departamento, RedirectAttributes attr) {
		service.update(departamento);
		attr.addFlashAttribute("success", "Departamento editado com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id, ModelMap model) {
		if (service.thereIsADepartment(id)) {
			model.addAttribute("fail", "Departamento nao removido, possui cargos vinculado(s)");
			
		} else {
			service.delete(id);
			model.addAttribute("sucess", "Departamento excluido com sucesso!");

		}
		return listar(model);
	}

}
