package com.biblioteca;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Multa;
import com.biblioteca.model.Reserva;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BibliotecaApplicationTests {

    @Test
    void contextLoads() {
        // Verifica que o contexto Spring carrega corretamente
    }

    @Test
    void livroVerificaDisponibilidade() {
        Livro livro = new Livro("Test", "Autor", 2020, "Ficção", 2);
        assertTrue(livro.verificarDisponibilidade());

        livro.setQuantidadeDisponivel(0);
        assertFalse(livro.verificarDisponibilidade());
    }

    @Test
    void livroAtualizaQuantidade() {
        Livro livro = new Livro("Test", "Autor", 2020, "Ficção", 3);
        livro.diminuirQuantidade();
        assertEquals(2, livro.getQuantidadeDisponivel());
        livro.aumentarQuantidade();
        assertEquals(3, livro.getQuantidadeDisponivel());
    }

    @Test
    void emprestimoStatusInicialAtivo() {
        Emprestimo emp = new Emprestimo("uid1", "Ana", "lid1", "Livro X");
        assertEquals("ATIVO", emp.getStatus());
        assertEquals(LocalDate.now(), emp.getDataRetirada());
        assertEquals(LocalDate.now().plusDays(14), emp.getDataPrevista());
    }

    @Test
    void emprestimoFinalizacaoSemAtraso() {
        Emprestimo emp = new Emprestimo("uid1", "Ana", "lid1", "Livro X");
        emp.finalizarEmprestimo();
        assertEquals("FINALIZADO", emp.getStatus());
        assertNotNull(emp.getDataDevolucao());
    }

    @Test
    void multaCalculaValorCorretamente() {
        Multa multa = new Multa("emp1", "uid1", "Ana", 5);
        assertEquals(12.50, multa.calcularValor(), 0.001);
        assertEquals(12.50, multa.getValor(), 0.001);
    }

    @Test
    void reservaStatusInicialAtiva() {
        Reserva reserva = new Reserva("uid1", "Ana", "lid1", "Livro X");
        assertEquals("ATIVA", reserva.getStatus());
        assertEquals(LocalDate.now().plusDays(7), reserva.getDataExpiracao());
        assertTrue(reserva.validarReserva());
    }

    @Test
    void reservaCancelamento() {
        Reserva reserva = new Reserva("uid1", "Ana", "lid1", "Livro X");
        reserva.cancelar();
        assertEquals("CANCELADA", reserva.getStatus());
        assertFalse(reserva.validarReserva());
    }
}
