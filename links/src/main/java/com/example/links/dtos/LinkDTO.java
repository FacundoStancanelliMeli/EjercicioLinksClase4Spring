package com.example.links.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO {
    private String link;
    private int contadorVisitas = 0;
    private String contrasenia;
    public LinkDTO(String link) {
        this.link = link;
    }

    public LinkDTO(String link, String contrasenia) {
        this.link = link;
        this.contrasenia = contrasenia;
    }

    public String getLink() {
        return link;
    }

    public int getContadorVisitas() {
        return contadorVisitas;
    }

    public void setContadorVisitas(int contadorVisitas) {
        this.contadorVisitas = contadorVisitas;
    }
}
