package com.example.links.services;

import com.example.links.dtos.LinkDTO;
import com.example.links.exceptions.LinkException;
import com.example.links.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService{
    @Autowired
    private LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public LinkDTO buscarLinkPorID(int id) throws LinkException {
        LinkDTO linkDTO = linkRepository.buscarLinkPorID(id);
        return linkDTO;
    }

    @Override
    public void agregarLinkARepositorio(LinkDTO linkDTO) throws LinkException {
        this.linkRepository.agregarLinkARepositorio(linkDTO);
    }

    @Override
    public void removerLinkEnRepositorio(int id) throws LinkException {
        this.linkRepository.removerLinkEnRepositorio(id);
    }

    @Override
    public LinkDTO redireccionarLink(int id, String pass) throws LinkException {
        return this.linkRepository.redireccionarLink(id, pass);
    }


}
