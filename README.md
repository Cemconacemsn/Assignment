# Assignment

QA automation framework (Java/Maven) with UI, API, and load-test scaffolding.

## Repository layout

```
Assignment/
├── insider-assignment/          # Maven project
│   └── src/
│       ├── main/java/           # Shared utilities only (config, driver)
│       └── test/java/
│           ├── ui/
│           │   ├── pages/
│           │   ├── tests/
│           │   └── utils/
│           ├── api/
│           │   ├── client/
│           │   ├── tests/
│           │   └── models/
│           └── load/
│               ├── locustfile.py
│               └── scenarios.md
├── prompts/                     # AI collaboration log (one file per iteration)
├── AI_USAGE.md                  # Reflection on AI usage
└── README.md                    # This file
```

## Quick start

See [insider-assignment/README.md](insider-assignment/README.md) for prerequisites, `mvn test` commands, and configuration.

Load tests: [insider-assignment/src/test/java/load/scenarios.md](insider-assignment/src/test/java/load/scenarios.md).

## AI collaboration

- Iteration log: [`prompts/`](prompts/)
- Reflection: [`AI_USAGE.md`](AI_USAGE.md)
