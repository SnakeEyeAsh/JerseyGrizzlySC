package org.example.Repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.Model.Enemigo;

import java.util.ArrayList;
import java.util.List;

public class EnemigosRepo {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "mydb";
    private static final String COLLECTION_NAME = "enemigo";

    // Bloque de inicialización estática - se ejecuta al cargar la clase
    public static void start() {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            collection = database.getCollection(COLLECTION_NAME);
            System.out.println("Conexión a MongoDB establecida exitosamente");
        } catch (Exception e) {
            System.err.println("Error al conectar con MongoDB: " + e.getMessage());
            throw new RuntimeException("No se pudo conectar a MongoDB", e);
        }
    }

    // Constructor privado para evitar instanciación
    private EnemigosRepo() {
    }

    // Obtener todos los enemigos
    public static List<Enemigo> obtenerTodos() {
        List<Enemigo> enemigos = new ArrayList<>();
        collection.find().forEach(doc -> enemigos.add(documentToEnemigo(doc)));
        return enemigos;
    }

    // Convertir Document de MongoDB a objeto Enemigo
    private static Enemigo documentToEnemigo(Document doc) {
        Enemigo enemigo = new Enemigo();
        enemigo.setId(doc.getObjectId("_id"));
        enemigo.setPais(doc.getString("pais"));
        enemigo.setNombre(doc.getString("nombre"));
        enemigo.setAfiliacion(doc.getString("afiliacion"));
        return enemigo;
    }

    // Cerrar la conexión
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión a Mongo cerrada");
        }
    }
}
