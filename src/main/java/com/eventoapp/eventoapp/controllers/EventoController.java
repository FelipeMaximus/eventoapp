package com.eventoapp.eventoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.eventoapp.models.Convidado;
import com.eventoapp.eventoapp.models.Evento;
import com.eventoapp.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.eventoapp.repository.EventoRepository;

import jakarta.validation.Valid;

@Controller
public class EventoController {
	
	//injecao de dependencia cria uma instancia automaticamente
	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
	
	//DEPOIS TIRE
	//@InitBinder
	//public void initBinder(WebDataBinder binder) {
	    //System.out.println("VALIDATOR: " + binder.getValidator());
	//}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	//salva os dados do evento no banco de dados
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(@Valid  @ModelAttribute("evento") Evento evento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			 //System.out.println("VALIDAÇÃO COM ERRO!"); // ALTERAÇÃO AQUI
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarEvento";
		}
		//persiste o evento no BD
		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
		return "redirect:/cadastrarEvento";
	}
	
	//metodo que mostra a lista de eventos
	@RequestMapping(value={"/", "/eventos"}, method=RequestMethod.GET)
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	// MOSTRA OS DETALHES DO EVENTO
	@RequestMapping(value="/eventos/{id}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("id") String id) {
	    Evento evento = er.findById(id).orElse(null);
	    ModelAndView mv = new ModelAndView("evento/detalhesEvento");
	    mv.addObject("evento", evento);
	    
	    //BUSCANDO A LISTA DE CONVIDADOS DO EVENTO
	    Iterable<Convidado> convidados = cr.findByEventoCodigo(id);
	    mv.addObject("convidados", convidados);
	    		
	    return mv;
	}
	
	//APAGAR DADOS DO BD
	@RequestMapping("/deletarEvento")
	public String deletarEvento(String id) {
		er.deleteById(id);
		return "redirect:/eventos";
	}

	
	//METODO PARA MOSTRAR CODIGO DO EVENTO NA URL
	@RequestMapping(value="/eventos/{id}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("id") String id, @Valid  @ModelAttribute("convidado") Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		//TESTE DE ERRO DE VALIDAÇÃO
		//System.out.println("QUANTIDADE DE ERROS: " + result.getErrorCount()); // ALTERAÇÃO AQUI
		//System.out.println("RG recebido: [" + convidado.getRg() + "]"); // ALTERAÇÃO AQUI
		//System.out.println("Nome recebido: [" + convidado.getNomeConvidado() + "]"); // ALTERAÇÃO AQUI
		if(result.hasErrors()) {
			 //System.out.println("VALIDAÇÃO COM ERRO!"); // ALTERAÇÃO AQUI
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/eventos/{id}";
		}
		//System.out.println("VALIDAÇÃO OK!"); // ALTERAÇÃO AQUI
		convidado.setEventoCodigo(id);
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com Sucesso!");
		return "redirect:/eventos/{id}";
	}
	
	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(String rg) {
		Convidado convidado = cr.findById(rg).orElse(null);

	    if (convidado != null) {
	        String eventoCodigo = convidado.getEventoCodigo();
	        cr.deleteById(rg);
	        return "redirect:/eventos/" + eventoCodigo;
	    }
	    return "redirect:/eventos";
	}
}
