package com.AppRH.AppRH.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.models.Dependente;
import com.AppRH.AppRH.models.Empresa;
import com.AppRH.AppRH.models.Funcionario;
import com.AppRH.AppRH.models.Vaga;
import com.AppRH.AppRH.repository.EmpresaRepository;

@Controller
public class EmpresaController {

	@Autowired
	private EmpresaRepository er;
	
	@RequestMapping("/cadastrarEmpresa")
	public String form() {
		return "empresa/form-empresa";
	}
	
	@RequestMapping(value = "/cadastrarEmpresa", method = RequestMethod.POST)
	public String form(@Valid Empresa empresa, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarEmpresa";
		}

		er.save(empresa);
		attributes.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso!");
		System.out.println(empresa.getNome() + empresa.getCnpj() + empresa.getEmail() + empresa.getId());
		return "redirect:/cadastrarEmpresa";
	}
	
	// GET que lista empresas
	@RequestMapping("/empresas")
	public ModelAndView listaEmpresas() {
		ModelAndView mv = new ModelAndView("empresa/lista-empresa");
		Iterable<Empresa> empresas = er.findAll();
		mv.addObject("empresa", empresas);
		return mv;
	}
	
	@RequestMapping("/deletarEmpresa")
	public String deletarEmpresa(long id) {
		Empresa empresa = er.findById(id);
		er.delete(empresa);
		return "redirect:/empresa";
	}
		
	// Métodos que atualizam funcionário

	@RequestMapping("/editar-empresa")
	public ModelAndView editarEmpresa(long id) {
		Empresa empresa = er.findById(id);
		ModelAndView mv = new ModelAndView("empresa/update-empresa");
		mv.addObject("empresa", empresa);
		return mv;
	}
	
	
	
}
