package com.comunidade.renascer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.comunidade.renascer.model.Artigo;
import com.comunidade.renascer.repository.ArtigoRepository;
import com.comunidade.renascer.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/artigos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArtigoController {
    @Autowired
    private ArtigoRepository artigoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Artigo>> getAll() {
        return ResponseEntity.ok(artigoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artigo> getById(@PathVariable Long id) {
        return artigoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Artigo>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(artigoRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<Artigo> post(@Valid @RequestBody Artigo artigo) {

        if (categoriaRepository.existsById(artigo.getCategoria().getId())) {
            artigo.setId(null);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(artigoRepository.save(artigo));
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
    }

    @PutMapping
    public ResponseEntity<Artigo> put(@Valid @RequestBody Artigo Artigo) {
        if (artigoRepository.existsById(Artigo.getId())) {

            if (categoriaRepository.existsById(Artigo.getCategoria().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(artigoRepository.save(Artigo));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Artigo> Artigo = artigoRepository.findById(id);

        if (Artigo.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        artigoRepository.deleteById(id);
    }
}
