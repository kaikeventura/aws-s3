# AWS S3 - Upload e Download de arquivos de imagem

* Subir AWS S3 [localstack](https://github.com/localstack/localstack)
```bash
make up
```

* Endpoint para fazer **upload** de uma imagem
```
POST localhost:8080/v1/s3/upload/{filename}
```

* Endpoint para fazer **dowload** de uma imagem
```
GET localhost:8080/v1/s3/dowload/{filename}
```
