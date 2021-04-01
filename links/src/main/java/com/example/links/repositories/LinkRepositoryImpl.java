package com.example.links.repositories;

import com.example.links.dtos.LinkDTO;
import com.example.links.exceptions.LinkException;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class LinkRepositoryImpl implements LinkRepository{
    private HashMap<Integer, LinkDTO> hashMap = new HashMap<>();

    @Override
    public LinkDTO buscarLinkPorID(int id) throws LinkException {
        LinkDTO linkDTO;
        if (validarIDExisteEnHash(id)){
            linkDTO = hashMap.get(id);
        }
        else{
            throw new LinkException(LinkException.LINK_NOT_FOUND, LinkException.LINK_NOT_FOUND_MSG);
        }
        return linkDTO;
    }

    @Override
    public void agregarLinkARepositorio(LinkDTO linkDTO) throws LinkException {
        if (validarContraseniaNoEsNull(linkDTO.getContrasenia())){
            if (validarURL(linkDTO.getLink())){
                this.hashMap.put(hashMap.size(), linkDTO);
            }
            else{
                throw new LinkException(LinkException.URL_NOT_VALID, LinkException.URL_NOT_VALID_MSG);
            }
        }
        else{
            throw new LinkException(LinkException.PASSWORD_NECESARIA, LinkException.PASSWORD_NECESARIA_MSG);
        }

    }

    @Override
    public void removerLinkEnRepositorio(int id) throws LinkException {
        if (validarIDExisteEnHash(id)){
            hashMap.remove(id);
        }
        else{
            throw new LinkException(LinkException.LINK_NOT_FOUND, LinkException.LINK_NOT_FOUND_MSG);
        }
    }

    @Override
    public LinkDTO redireccionarLink(int id, String pass) throws LinkException {
        LinkDTO linkDTO = this.buscarLinkPorID(id);

        if (validarContraseniaNoEsNull(linkDTO.getContrasenia())){
            if (!validarContrasenia(id, pass)){
                throw new LinkException(LinkException.PASSWORD_INVALID, LinkException.PASSWORD_INVALID_MSG);
            }
        }

        return linkDTO;
    }

    private boolean validarContraseniaNoEsNull(String contrasenia){
        return contrasenia != null;
    }

    private boolean validarContrasenia(int id, String contrasenia){
        return hashMap.get(id).getContrasenia().equals(contrasenia);
    }

    private boolean validarURL(String url){
        UrlValidator validar = new UrlValidator();
        return validar.isValid(url);
    }

    private boolean validarIDExisteEnHash(int id){
        boolean existe = true;
        if (hashMap.get(id) == null){
            existe = false;
        }

        return existe;
    }
}
