# Shareable Notes

Shareable Notes is a Spring Boot service for storing notes and sharing their
metadata. The planned storage model keeps note metadata in DynamoDB and note
content in S3-compatible object storage.

The project currently provides the application structure and local AWS service
configuration. The full create, upload, persistence, and listing workflow is
still being implemented.

## Technology

- Java 21
- Maven
- Spring Boot
- AWS SDK for Java v2
- DynamoDB Local for note metadata
- LocalStack for local S3-compatible storage

## Architecture

```text
Client
  |
  v
Spring Boot API
  |------------------> DynamoDB: note metadata
  |
  `------------------> S3 / LocalStack: note content
```

The project uses AWS SDK v2 clients. DynamoDB integration must use
`software.amazon.awssdk.services.dynamodb.DynamoDbClient`; it should not use
the older AWS SDK v1 `AmazonDynamoDB` client.

## Project Layout

```text
src/main/java/com/sharable
├── auth/model                 User model
├── enums                      Shared enums, including note types
└── notes
    ├── configuration           DynamoDB and S3 client configuration
    ├── controler               HTTP controller (package name kept as-is)
    ├── dto                     Request and response data types
    ├── model                   Note domain model
    ├── repository              Persistence boundary
    └── service                 Application logic
```

## Local Services

Start DynamoDB Local and LocalStack:

```bash
docker compose up
```

The local configuration in `application-local.yml` is:

| Service | Endpoint | Purpose |
| --- | --- | --- |
| DynamoDB Local | `http://localhost:8000` | Stores note metadata |
| LocalStack S3 | `http://localhost:4566` | Stores note content |
| AWS region | `eu-central-1` | Region used by the SDK clients |
| S3 bucket | `my-local-bucket` | Intended local content bucket |

Run the application with the `local` Spring profile enabled:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

To compile only:

```bash
mvn compile
```

## Current State

Implemented foundation:

- AWS SDK v2 dependencies are declared in Maven.
- `DynamoDbConfig` creates a `DynamoDbClient` using the configured region and
  local DynamoDB endpoint.
- Docker Compose starts DynamoDB Local and LocalStack.
- Domain types exist for notes, users, note types, and note creation requests.
- A REST controller and note service provide the starting API structure.

Still incomplete:

- `NoteService#createNote` builds a note but does not upload content or save
  metadata.
- `NoteRepository` has no DynamoDB implementation.
- The controller does not yet return a useful create response, and its list
  endpoint returns `null`.
- The referenced `StorageService` implementation is missing from the source
  tree.
- S3 configuration expects `aws.storage.bucket`, while the local configuration
  defines `aws.s3.bucket`.
- The S3 client does not yet apply the configured LocalStack endpoint.

## Next Implementation Steps

1. Align the S3 configuration property names and configure the LocalStack S3
   endpoint.
2. Add a `StorageService` that creates or checks the bucket and uploads note
   content to S3.
3. Implement a DynamoDB-backed `NoteRepository` using AWS SDK v2, preferably
   the DynamoDB Enhanced Client for mapping the `Note` model.
4. Update `NoteService#createNote` to validate content, upload it, then save
   metadata. Define cleanup behavior if DynamoDB saving fails after an upload.
5. Change the create endpoint to accept a realistic payload, such as
   `multipart/form-data` for uploaded files or JSON for text-only notes.
6. Implement listing and retrieval endpoints with meaningful response types.
7. Add integration tests against the local DynamoDB and LocalStack services.

## Configuration Note

Keep AWS SDK v2 throughout the project. The DynamoDB client import is:

```java
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
```

For local development, the client needs the DynamoDB Local endpoint override;
the existing `DynamoDbConfig` is the correct place for that setup.
