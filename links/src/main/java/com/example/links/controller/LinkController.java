package com.example.links.controller;

import com.example.links.dtos.LinkDTO;
import com.example.links.exceptions.LinkException;
import com.example.links.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;


@RestController
public class LinkController {
    @Autowired
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping(path = "/link/{linkId}")
    public ModelAndView redirByLinkId(@PathVariable("linkId") int linkId, @RequestParam(required = false) String password) throws LinkException
    {
        LinkDTO linkDTO = linkService.buscarLinkPorID(linkId);
        linkDTO.setContadorVisitas(linkDTO.getContadorVisitas()+1);
        linkDTO = linkService.redireccionarLink(linkId, password);
        return new ModelAndView("redirect:" + linkDTO.getLink());
    }

    @PostMapping("/generateLink")
    public ResponseEntity generateLink(@RequestBody LinkDTO link, @RequestParam(required = false) String password) throws LinkException
    {
        link.setContrasenia(password);
        linkService.agregarLinkARepositorio(link);
        return new ResponseEntity("Link agregado de manera exitosa", HttpStatus.OK);
    }

    @DeleteMapping("/invalidateLINK/{id}")
    public ResponseEntity invalidarLink(@PathVariable int id) throws LinkException
    {
        linkService.removerLinkEnRepositorio(id);
        return new ResponseEntity("Link removido de manera exitosa", HttpStatus.OK);
    }

    @GetMapping("/obtenerLink/{id}")
    public ResponseEntity obtenerLink(@PathVariable int id) throws LinkException
    {

        return new ResponseEntity(linkService.buscarLinkPorID(id), HttpStatus.OK);
    }

}
