package org.example.Controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.Model.Enemigo;
import org.example.Repository.EnemigosRepo;

import java.util.List;

@Path("/enemigo")
@Produces(MediaType.APPLICATION_JSON)
public class EnemigosResource {

    // GET /api/enemigos - Obtener todos los enemigos
    @GET
    public List<Enemigo> obtenerTodos() {
        return EnemigosRepo.obtenerTodos();
    }
}
