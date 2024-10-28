package com.example.sales_crud.services

import com.azure.storage.blob.BlobContainerClient
import com.azure.storage.blob.BlobServiceClient
import com.example.sales_crud.error.exception.BadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream

@Service
class AzureServices(
    @Autowired
    private val blobServiceClient: BlobServiceClient
) {
    fun uploadFile(containerName: String, blobName:String, file:MultipartFile): Unit {
        try {
            val containerClient: BlobContainerClient = this.getBlobContainerClient(containerName);
            containerClient.getBlobClient(blobName).upload(ByteArrayInputStream(file.bytes));
        }catch (e:Exception){
            println(e)
            throw BadRequestException("El archivo no es valido");
        }
    }
    // Función para eliminar un archivo (blob)
    fun deleteFile(containerName: String, blobName: String): Unit {
        try {
            val containerClient: BlobContainerClient = this.getBlobContainerClient(containerName)
            val blobClient = containerClient.getBlobClient(blobName)
            if (blobClient.exists()) {
                blobClient.delete()
            } else {
                throw BadRequestException("El archivo no existe")
            }
        } catch (e: Exception) {
            println(e)
            throw BadRequestException("Error al eliminar el archivo")
        }
    }
    private fun getBlobContainerClient(containerName: String): BlobContainerClient {
        return blobServiceClient.getBlobContainerClient(containerName)
    }
}