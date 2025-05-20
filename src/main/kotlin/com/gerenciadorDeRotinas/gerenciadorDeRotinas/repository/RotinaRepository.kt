package com.gerenciadorDeRotinas.gerenciadorDeRotinas.repository

import com.gerenciadorDeRotinas.gerenciadorDeRotinas.model.Rotina
import com.google.cloud.firestore.Firestore
import org.springframework.stereotype.Repository

@Repository
class RotinaRepository(//Essa val é a instância do banco
    private val firestore: Firestore //= FirestoreClient.getFirestore()//Buscar em um JSON
) {
    private val collectionName = "home" //O nome da coleção é o que esta no Firebase

    fun salvar(rotina: Rotina): Rotina {
        val documento =
            firestore.collection(collectionName).document(rotina.codigo!!)//Busca um documento pelo ID

        val rotinaSalva = rotina.copy(codigo = documento.id)//Copia do que foi resgatado do banco
        documento.set(rotinaSalva)//Edita ou cria os valores de cada CHAVE
        return rotinaSalva//retorna as alterações ou criações
    }

    fun buscarId(codigo: String): Rotina? {
        val documento = firestore.collection(collectionName)
            .document(codigo).get().get()//Retorna os dados da referência
        return if (documento.exists())
            documento.toObject(Rotina::class.java)
        else
            null
    }

    fun buscarTodos(): List<Rotina> {
        val query = firestore.collection(collectionName)
            .get().get()//Retorna todos os documentos da coleção
        //a query vem em vários JSON, basta converte para uma lista
        //um de cada vez
        return query.documents.mapNotNull { rotinas ->
            rotinas.toObject(Rotina::class.java)
        }
    }

    fun buscarPorTipos(tipo: String): List<Rotina> { //busca personalizada
        val query = firestore.collection(collectionName).whereEqualTo("tipo_1", tipo.uppercase())
            .get().get()//Retorna todos os documentos da coleção
        return query.documents.mapNotNull { rotinas ->
            rotinas.toObject(Rotina::class.java)
        }
    }

    fun excluirId(codigo: String): Boolean {
        //Busca um documento igual na buscaID, mas aqui deleta ele
        firestore.collection(collectionName).document(codigo).delete()
        return true
    }
}