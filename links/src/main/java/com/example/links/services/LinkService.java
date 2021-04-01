package com.example.links.services;

import com.example.links.dtos.LinkDTO;
import com.example.links.exceptions.LinkException;

public interface LinkService {
    LinkDTO buscarLinkPorID(int id) throws LinkException;
    void agregarLinkARepositorio(LinkDTO linkDTO) throws LinkException;
    void removerLinkEnRepositorio(int id) throws LinkException;
    LinkDTO redireccionarLink(int id, String pass) throws LinkException;
}
