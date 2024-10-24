package com.example.sales_crud.configuration

import com.azure.storage.blob.BlobContainerClient
import com.azure.storage.blob.BlobServiceClient
import com.azure.storage.blob.BlobServiceClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AzureConfiguration(
    @Value("\${spring.cloud.azure.storage.blob.container-name}")
    private val containerName: String,
    @Value("\${spring.cloud.azure.storage.blob.connection-string}")
    private val connectionString: String
){
    @Bean
    fun blobServiceClient(): BlobServiceClient  {
       val serviceClient: BlobServiceClient  = BlobServiceClientBuilder()
            .connectionString(this.connectionString).buildClient();
        return serviceClient;
    }

    @Bean
    fun blobContainerClient(): BlobContainerClient {
        return this.blobServiceClient().getBlobContainerClient(this.containerName);
    }
}

