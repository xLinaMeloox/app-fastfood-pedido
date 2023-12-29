package br.com.appfastfood.pedido.infraestrutura.entidades;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "custom_sequence")
public class CustomSequence {
    @Id
    private String id; // Use String para representar o nome da coleção
    private Long sequence;

    public CustomSequence() {
    }

    public CustomSequence(String id, Long sequence) {
        this.id = id;
        this.sequence = sequence;
    }

    public String getId() {
        return id;
    }

    public Long getSequence() {
        return sequence;
    }
}