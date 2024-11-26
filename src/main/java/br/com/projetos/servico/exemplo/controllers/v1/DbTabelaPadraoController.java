package br.com.projetos.servico.exemplo.controllers.v1;

import br.com.projetos.servico.exemplo.models.dtos.DbTabelaPadraoRequestDto;
import br.com.projetos.servico.exemplo.models.dtos.DbTabelaPadraoResponseDto;
import br.com.projetos.servico.exemplo.services.DbTabelaPadraoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/v1/tabela-padrao")
@RequiredArgsConstructor
public class DbTabelaPadraoController {

    private final DbTabelaPadraoService dbTabelaPadraoService;

    @GetMapping
    public ResponseEntity<Page<DbTabelaPadraoResponseDto>> listar(
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) String texto,
            @RequestParam(required = false) Integer numero,
            @RequestParam(required = false) LocalDate dt,
            @RequestParam(required = false) BigDecimal decimalGt,
            @RequestParam(required = false) BigDecimal decimalLt,
            Pageable pageable ){

        return ResponseEntity.ok(this.dbTabelaPadraoService.listar(status, texto, numero, dt, decimalGt, decimalLt, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DbTabelaPadraoResponseDto> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.dbTabelaPadraoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DbTabelaPadraoResponseDto> criar(@Valid @RequestBody DbTabelaPadraoRequestDto dbTabelaPadraoRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.dbTabelaPadraoService.criar(dbTabelaPadraoRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DbTabelaPadraoResponseDto> atualizar(@PathVariable("id") Long id, @Valid @RequestBody DbTabelaPadraoRequestDto dbTabelaPadraoRequestDto) {
        return ResponseEntity.ok(this.dbTabelaPadraoService.atualizar(id, dbTabelaPadraoRequestDto));
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable("id") Long id) {
        this.dbTabelaPadraoService.ativar(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable("id") Long id) {
        this.dbTabelaPadraoService.desativar(id);
        return ResponseEntity.ok().build();
    }
}
