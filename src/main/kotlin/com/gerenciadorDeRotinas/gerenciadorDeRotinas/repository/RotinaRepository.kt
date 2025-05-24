package com.gerenciadorDeRotinas.gerenciadorDeRotinas.repository

import com.gerenciadorDeRotinas.gerenciadorDeRotinas.model.Rotina
import org.springframework.stereotype.Repository
import com.google.firebase.cloud.FirestoreClient
import com.google.api.core.ApiFuture
import com.google.cloud.firestore.*

@Repository
class RotinaRepository {

    private val collectionName = "rotinas"

    fun salvar(rotina: Rotina): Rotina {
        val db = FirestoreClient.getFirestore()
        val docRef = if (rotina.codigo == null) {
            db.collection(collectionName).document()
        } else {
            db.collection(collectionName).document(rotina.codigo)
        }

        val rotinaComId = rotina.copy(codigo = docRef.id)
        val future: ApiFuture<WriteResult> = docRef.set(rotinaComId)
        future.get() // aguarda conclusão da escrita

        return rotinaComId
    }

    fun encontrarTodos(): List<Rotina> {
        val db = FirestoreClient.getFirestore()
        val future: ApiFuture<QuerySnapshot> = db.collection(collectionName).get()
        val documents = future.get().documents
        return documents.mapNotNull { it.toObject(Rotina::class.java) }
    }

    fun encontrarPorId(codigo: String): Rotina? {
        val db = FirestoreClient.getFirestore()
        val future: ApiFuture<DocumentSnapshot> = db.collection(collectionName).document(codigo).get()
        val document = future.get()
        return if (document.exists()) document.toObject(Rotina::class.java) else null
    }

    fun excluir(codigo: String): Boolean {
        val db = FirestoreClient.getFirestore()
        val future: ApiFuture<WriteResult> = db.collection(collectionName).document(codigo).delete()
        future.get()
        return true
    }
}
