# Load test scenarios

Locust scripts live in `src/test/java/load/`. See `locustfile.py` for the default user model.

## Prerequisites

```bash
pip install locust
```

## Run

From `insider-assignment/`:

```bash
locust -f src/test/java/load/locustfile.py
```

Open the Locust web UI at http://localhost:8089, set users/spawn rate, and start the run.

Headless example:

```bash
locust -f src/test/java/load/locustfile.py \
  --headless -u 10 -r 2 -t 1m \
  --host https://jsonplaceholder.typicode.com
```

## Scenarios

| ID | Name | Endpoint(s) | Weight | Success criteria |
|----|------|-------------|--------|------------------|
| L1 | List posts | `GET /posts` | 3 | HTTP 200, response time &lt; 2s p95 |
| L2 | Get post by id | `GET /posts/1` | 2 | HTTP 200, body contains `"id": 1` |
| L3 | List users | `GET /users` | 1 | HTTP 200 |

## Environment

| Variable | Default | Description |
|----------|---------|-------------|
| `LOCUST_HOST` | `https://jsonplaceholder.typicode.com` | API base URL |

Align `LOCUST_HOST` with `api.base.uri` in `src/test/resources/config/application.properties` when testing the same system under functional and load tests.
