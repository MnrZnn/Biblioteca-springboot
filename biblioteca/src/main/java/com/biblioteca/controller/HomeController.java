package com.biblioteca.controller;

import com.biblioteca.service.EmprestimoService;
import com.biblioteca.service.LivroService;
import com.biblioteca.service.ReservaService;
import com.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private LivroService livroService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EmprestimoService emprestimoService;
    @Autowired
    private ReservaService reservaService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalLivros", livroService.listarTodos().size());
        model.addAttribute("totalUsuarios", usuarioService.listarTodos().size());
        model.addAttribute("totalEmprestimos", emprestimoService.listarAtivos().size());
        model.addAttribute("totalReservas", reservaService.listarAtivas().size());
        model.addAttribute("emprestimosRecentes", emprestimoService.listarAtivos());
        return "index";
    }
}
