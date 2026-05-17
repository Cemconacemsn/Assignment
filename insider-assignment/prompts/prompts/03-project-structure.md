# Goal

Clean ve düzenli bir test automation project structure oluşturmak.

# Context provided

UI, API ve load testlerini aynı projede organize edeceğim. Selenium, Rest Assured ve Locust kullanıyorum.

# Prompt

Create the following project structure:

src/test/java/
ui/
pages/
tests/
utils/
api/
client/
tests/
models/
load/
locustfile.py
scenarios.md

src/main/java/
(only if needed for shared utilities)

prompts/
AI_USAGE.md
README.md

# Output evaluation

Structure genel olarak doğru ve kullanılabilir çıktı. UI, API ve load testlerini ayırması iyi.

# Iteration notes

İlk hali temel iskelet için kullandım, sonra framework büyüdükçe base class ve config yapısını ekleyerek daha düzenli hale getirdim.
