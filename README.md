# sherable-notes

> **STATUS: EARLY, UNFINISHED PROTOTYPE**
>
> The project does not currently compile or start as a Spring Boot application.
> Its final product behavior, API contract, DynamoDB schema, and storage flow have
> not been decided yet.

## What this repository contains

This repository is an early Java project exploring a notes service. The source
currently contains:

- models for a user and a note;
- note types for plain text and Markdown;
- unfinished create-note and list-notes controller methods;
- unfinished content validators;
- AWS SDK for Java v2 clients for DynamoDB and S3;
- local Docker services for DynamoDB Local and LocalStack S3.

The presence of both DynamoDB and S3 shows that both services were being
explored. It does **not** establish what each service must store or in which
order they must be called.

## Confirmed project decisions

- Java 21 and Maven are used.
- AWS integrations must stay on AWS SDK for Java v2.
- DynamoDB code must use
  `software.amazon.awssdk.services.dynamodb.DynamoDbClient`, not the AWS SDK v1
  `AmazonDynamoDB` client.
- Local development infrastructure includes DynamoDB Local on port `8000` and
  LocalStack S3 on port `4566`.

No create-note storage sequence has been agreed. In particular, neither
"S3 then DynamoDB" nor "DynamoDB then S3" is currently a project requirement.

## Current code state

The code is not a working application yet:

- `NoteService` builds a `Note` object and then ends with the incomplete
  statement `storageService.s`.
- `StorageService` is referenced but is not present in the repository.
- `NoteRepository` is empty, so no DynamoDB read or write operation exists.
- `Main` is the default IntelliJ sample program and is not a Spring Boot entry
  point.
- `NoteController` contains create and list methods, but the create method uses
  a hard-coded user and the list method returns `null`.
- `@RestController("/api/notes")` names the Spring bean; it does not map the
  controller to `/api/notes`.
- Markdown validation always returns `false`; plain-text validation always
  returns `true`.
- Response classes are placeholders without a completed public API contract.
- The S3 client reads the region but does not use the configured LocalStack
  endpoint.
- The Maven file declares the DynamoDB dependency twice and contains an unused
  `aws-version` property.

Other compile or runtime problems may become visible after the known blockers
are fixed.

## Local infrastructure

Start the configured containers with:

```bash
docker compose up
```

The checked-in local configuration is:

| Setting | Value | Used by current code |
| --- | --- | --- |
| AWS region | `eu-central-1` | DynamoDB and S3 clients |
| DynamoDB endpoint | `http://localhost:8000` | Yes |
| S3 endpoint | `http://localhost:4566` | No |
| S3 bucket | `my-local-bucket` | Read by `S3Config`, otherwise unused |

There is currently no valid command documented for starting the application,
because the repository has no Spring Boot application entry point and does not
compile.

## Decisions still required

Before continuing implementation, the expected application behavior needs to
be defined. The repository and prior discussion do not answer these questions:

1. What does a user send when creating a note: text, a file, or both?
2. What data, if any, belongs in S3 and what data belongs in DynamoDB?
3. Which operation makes a note visible, and how should partial failures be
   handled?
4. What endpoints and responses should the first version expose?
5. Is sharing part of the first version, and what does "sharing" mean?
6. Is real authentication required now, or is a mock user acceptable initially?

These are open product and architecture decisions, not implementation tasks
that have already been agreed.

## Conversation checkpoint

The work completed in the discussion was limited to identifying an AWS SDK
version mismatch and confirming that the project should keep AWS SDK v2. The
current `DynamoDbConfig` now constructs a v2 `DynamoDbClient` and applies the
configured endpoint override.

No end-to-end note flow was finalized. Any proposed ordering between S3 and
DynamoDB was an example for discussion, not a decision made for this project.
