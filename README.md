# Assignment / Ödev

Production-style QA automation framework for **UI**, **API**, and **load** testing.  
Üretim ortamına yakın bir **UI**, **API** ve **yük** test otomasyon iskeleti.

| | |
|---|---|
| **Stack** | Java 21, Maven, Selenium 4, TestNG, RestAssured, WebDriverManager, AssertJ, Locust (optional) |
| **Main module** | [`insider-assignment/`](insider-assignment/) |

---

## English

### About the project

This repository contains an end-to-end QA automation assignment built around [Insider One](https://insiderone.com/) UI flows and public REST APIs (Petstore, JSONPlaceholder). The framework follows common industry patterns:

- **Page Object Model (POM)** for UI tests
- **Layered configuration** (`application.properties` + environment overlays)
- **Thread-safe WebDriver** management via `DriverManager`
- **RestAssured** client with reusable steps and DTOs for API tests
- **Locust** scaffolding for optional load scenarios

UI scenarios cover careers navigation, QA job filtering, and apply redirect flows. API tests include sample checks and full user CRUD against Petstore.

### Prerequisites

| Requirement | Version / notes |
|-------------|-----------------|
| JDK | 21 |
| Maven | 3.9+ |
| Browser | Chrome (default); Firefox and Edge supported via config |
| Python + Locust | Optional, for load tests only |

### Installation

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd Assignment
   ```

2. **Enter the Maven module**

   ```bash
   cd insider-assignment
   ```

3. **Install dependencies and compile** (Maven downloads artifacts on first run)

   ```bash
   mvn clean compile test-compile
   ```

4. **(Optional) Load-test tooling**

   ```bash
   python3 -m venv .venv-load
   source .venv-load/bin/activate   # Windows: .venv-load\Scripts\activate
   pip install locust
   ```

5. **Configuration** — edit or add property files under `src/test/resources/config/`:
   - `application.properties` — defaults
   - `application-local.properties` — local overrides
   - `application-ci.properties` — CI (headless, parallel)

   Active environment: `-Denv=local` or `TEST_ENV=ci` (default: `local`).

### Running tests

All commands below are run from `insider-assignment/`:

```bash
# Full suite (UI + API)
mvn clean test

# UI only
mvn clean test -Pui

# API only (no browser)
mvn clean test -Papi

# Force local property overlay
mvn clean test -Plocal

# CI profile: headless + parallel methods
mvn clean test -Pci
```

**Load tests** (optional):

```bash
locust -f src/test/java/load/locustfile.py
```

See [insider-assignment/src/test/java/load/scenarios.md](insider-assignment/src/test/java/load/scenarios.md) for scenario details.

**Reports:** Surefire HTML/XML reports are written to `insider-assignment/target/surefire-reports/` after each run.

More commands and property reference: [insider-assignment/README.md](insider-assignment/README.md).

### Project structure

```
Assignment/
├── insider-assignment/              # Maven module (all test code)
│   ├── pom.xml
│   ├── src/
│   │   ├── main/java/
│   │   │   └── com/insiderOne/
│   │   │       ├── config/          # Layered properties loader
│   │   │       ├── driver/          # WebDriver factory + ThreadLocal manager
│   │   │       └── utils/           # Shared waits and helpers
│   │   └── test/
│   │       ├── java/
│   │       │   ├── com/insiderOne/
│   │       │   │   ├── core/        # BaseTest, BaseUiTest
│   │       │   │   ├── listener/    # TestNG listener (screenshots, logging)
│   │       │   │   └── ui/
│   │       │   │       ├── pages/   # Page Object Model
│   │       │   │       └── tests/   # UI TestNG tests
│   │       │   ├── api/
│   │       │   │   ├── client/      # RestAssured specs and steps
│   │       │   │   ├── models/      # DTOs / fixtures
│   │       │   │   └── tests/       # API TestNG tests
│   │       │   └── load/
│   │       │       ├── locustfile.py
│   │       │       └── scenarios.md
│   │       └── resources/
│   │           ├── config/          # application*.properties
│   │           └── testng/          # suite XML (full, ui, api)
│   └── prompts/                     # AI collaboration log (per iteration)
├── AI_USAGE.md                      # Reflection on AI-assisted development
└── README.md                        # This file
```

### Key configuration properties

| Property | Description |
|----------|-------------|
| `browser` | `chrome`, `firefox`, or `edge` |
| `headless` | Run browser without UI |
| `base.url` | Application under test (UI) |
| `api.base.uri` | RestAssured base URI |
| `explicit.wait.seconds` | WebDriverWait timeout |

System properties and environment variables override file values.

### AI collaboration

- Iteration prompts: [`insider-assignment/prompts/`](insider-assignment/prompts/)
- Usage reflection: [`AI_USAGE.md`](AI_USAGE.md)

---

## Türkçe

### Proje hakkında

Bu depo, [Insider One](https://insiderone.com/) arayüz akışları ile herkese açık REST API’leri (Petstore, JSONPlaceholder) üzerine kurulu bir QA otomasyon ödevini içerir. Çerçeve yaygın endüstri kalıplarını kullanır:

- **Page Object Model (POM)** ile UI testleri
- **Katmanlı yapılandırma** (`application.properties` + ortam dosyaları)
- **Thread-safe WebDriver** yönetimi (`DriverManager`)
- **RestAssured** istemcisi, adımlar ve DTO’lar ile API testleri
- İsteğe bağlı yük testleri için **Locust** iskeleti

UI senaryoları kariyer sayfası gezintisi, QA iş ilanı filtreleme ve başvuru yönlendirme akışlarını kapsar. API tarafında örnek kontroller ve Petstore üzerinde tam kullanıcı CRUD testleri bulunur.

### Gereksinimler

| Gereksinim | Sürüm / not |
|------------|-------------|
| JDK | 21 |
| Maven | 3.9+ |
| Tarayıcı | Chrome (varsayılan); Firefox ve Edge yapılandırma ile desteklenir |
| Python + Locust | Yalnızca yük testleri için (isteğe bağlı) |

### Kurulum

1. **Depoyu klonlayın**

   ```bash
   git clone <repository-url>
   cd Assignment
   ```

2. **Maven modülüne girin**

   ```bash
   cd insider-assignment
   ```

3. **Bağımlılıkları indirin ve derleyin** (ilk çalıştırmada Maven bağımlılıkları indirir)

   ```bash
   mvn clean compile test-compile
   ```

4. **(İsteğe bağlı) Yük testi araçları**

   ```bash
   python3 -m venv .venv-load
   source .venv-load/bin/activate   # Windows: .venv-load\Scripts\activate
   pip install locust
   ```

5. **Yapılandırma** — `src/test/resources/config/` altındaki dosyaları düzenleyin:
   - `application.properties` — varsayılanlar
   - `application-local.properties` — yerel geçersiz kılmalar
   - `application-ci.properties` — CI (headless, paralel)

   Aktif ortam: `-Denv=local` veya `TEST_ENV=ci` (varsayılan: `local`).

### Testleri çalıştırma

Aşağıdaki komutların tamamı `insider-assignment/` dizininden çalıştırılır:

```bash
# Tüm suite (UI + API)
mvn clean test

# Yalnızca UI
mvn clean test -Pui

# Yalnızca API (tarayıcı açılmaz)
mvn clean test -Papi

# Yerel property dosyasını zorla
mvn clean test -Plocal

# CI profili: headless + paralel metotlar
mvn clean test -Pci
```

**Yük testleri** (isteğe bağlı):

```bash
locust -f src/test/java/load/locustfile.py
```

Senaryo ayrıntıları: [insider-assignment/src/test/java/load/scenarios.md](insider-assignment/src/test/java/load/scenarios.md).

**Raporlar:** Her çalıştırmadan sonra Surefire HTML/XML raporları `insider-assignment/target/surefire-reports/` altına yazılır.

Ek komutlar ve özellik tablosu: [insider-assignment/README.md](insider-assignment/README.md).

### Proje yapısı

```
Assignment/
├── insider-assignment/              # Maven modülü (tüm test kodu)
│   ├── pom.xml
│   ├── src/
│   │   ├── main/java/
│   │   │   └── com/insiderOne/
│   │   │       ├── config/          # Katmanlı properties yükleyici
│   │   │       ├── driver/          # WebDriver fabrikası + ThreadLocal
│   │   │       └── utils/           # Ortak bekleme ve yardımcılar
│   │   └── test/
│   │       ├── java/
│   │       │   ├── com/insiderOne/
│   │       │   │   ├── core/        # BaseTest, BaseUiTest
│   │       │   │   ├── listener/    # TestNG dinleyici (ekran görüntüsü, log)
│   │       │   │   └── ui/
│   │       │   │       ├── pages/   # Page Object Model
│   │       │   │       └── tests/   # UI TestNG testleri
│   │       │   ├── api/
│   │       │   │   ├── client/      # RestAssured spec ve adımlar
│   │       │   │   ├── models/      # DTO / fixture
│   │       │   │   └── tests/       # API TestNG testleri
│   │       │   └── load/
│   │       │       ├── locustfile.py
│   │       │       └── scenarios.md
│   │       └── resources/
│   │           ├── config/          # application*.properties
│   │           └── testng/          # suite XML (tam, ui, api)
│   └── prompts/                     # AI iş birliği günlüğü (iterasyon başına)
├── AI_USAGE.md                      # AI destekli geliştirme özeti
└── README.md                        # Bu dosya
```

### Önemli yapılandırma anahtarları

| Özellik | Açıklama |
|---------|----------|
| `browser` | `chrome`, `firefox` veya `edge` |
| `headless` | Tarayıcıyı arayüzsüz çalıştır |
| `base.url` | Test edilen uygulama (UI) |
| `api.base.uri` | RestAssured temel URI |
| `explicit.wait.seconds` | WebDriverWait süresi |

Sistem özellikleri ve ortam değişkenleri dosya değerlerinin üzerine yazar.

### AI iş birliği

- İterasyon promptları: [`insider-assignment/prompts/`](insider-assignment/prompts/)
- Kullanım özeti: [`AI_USAGE.md`](AI_USAGE.md)
